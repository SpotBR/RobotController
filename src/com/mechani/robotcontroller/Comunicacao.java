package com.mechani.robotcontroller;

import com.fazecast.jSerialComm.SerialPort;
import com.mechani.robotcontroller.communication.QueueRequestSync;
import com.mechani.robotcontroller.communication.RHRequest;
import com.mechani.robotcontroller.communication.RequestCallback;
import com.mechani.robotcontroller.communication.exceptions.ConnectionException;
import com.mechani.robotcontroller.communication.requests.RequestHandShake;

public class Comunicacao {
		
	private String comPort;
	private SerialPort porta;
	private QueueRequestSync queue;
	
	public Comunicacao(String comPort){
		this.comPort = comPort;
	}
	
	public Comunicacao(){
		this.comPort = null;
	}

	public void iniciar(RequestCallback request) throws ConnectionException{
		
		SerialPort[] ports = SerialPort.getCommPorts();
		
		if(comPort == null){
			if(ports.length == 0)
				throw new ConnectionException("Não existe nenhuma porta COM liberada");
			
			String stringArray[] = new String[ports.length];
			for(int i = 0; i < ports.length; i++)
				stringArray[i] = ports[i].getSystemPortName();
			
//		    comPort = (String)JOptionPane.showInputDialog(null, "Seleciona a porta COM que deseja utilizar para a comunicação", "Selecione uma opção.",
//		        JOptionPane.QUESTION_MESSAGE, null, stringArray,
//		        stringArray[0]);
			comPort = "COM8";

		    if(comPort == null)
		    	throw new ConnectionException("É necessário escolher uma porta COM!");
		}
		
		porta = SerialPort.getCommPort(comPort);
		boolean ok = porta.openPort();
		if(!ok){
			throw new ConnectionException("Não foi possível se conectar com a porta "+porta.getSystemPortName());
		}
		
		porta.setBaudRate(9600);
		queue = new QueueRequestSync(this);
		
		RequestHandShake handShake = new RequestHandShake();
		handShake.setCallback((RequestCallback)request);
		makeRequest(handShake);
	}
	
	public void makeRequest(RHRequest request){
		getQueue().call(request);
	}
	
	public void fechar(){
		porta.closePort();
		porta = null;
		queue = null;
		
	}

	public SerialPort getPorta() {
		return porta;
	}

	public String getComPort() {
		return comPort;
	}

	public QueueRequestSync getQueue() {
		return queue;
	}
	
}
