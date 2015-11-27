package com.mechani.robotcontroller.communication;

import java.util.concurrent.ConcurrentLinkedQueue;

import com.mechani.robotcontroller.Comunicacao;

public class QueueRequestSync {

	protected static final int QUEUE_LOOP_DELAY = 100;
	
	private ConcurrentLinkedQueue<RHRequest> queue = new ConcurrentLinkedQueue<>();
	private Comunicacao comunicacao;
	
	public Thread workerThread;
	
	public QueueRequestSync(Comunicacao comunicacao){
		this.comunicacao = comunicacao;
		
		workerThread = new Thread(new RunnableQueue());
		workerThread.start();
	}
	
	public void call(RHRequest request){
		queue.add(request);
	}
	
	public void dispose(){
		workerThread.interrupt();
		this.queue.clear();
		this.queue = null;
		this.comunicacao = null;
	}
	
	public Thread getWorkerThread(){
		return workerThread;
	}

	public Comunicacao getComunicacao() {
		return comunicacao;
	}
	
	protected ConcurrentLinkedQueue<RHRequest> getQueue(){
		return queue;
	}
	
	private class RunnableQueue implements Runnable{

		@Override
		public void run() {
			
			while(true){
				
				RHRequest request = getQueue().poll();
				
				if(request != null){
					FastRequest fr = request.getClass().getAnnotation(FastRequest.class);
					if(fr == null){
						request.sendRequest(getComunicacao());
					}else{
						request.sendRequest(getComunicacao(), fr.send(), request.getRobo());
					}
				}else{
					try {Thread.sleep(QUEUE_LOOP_DELAY);} catch (InterruptedException e) {}
				}
			
			}
			
		}
		
	}
	
}
