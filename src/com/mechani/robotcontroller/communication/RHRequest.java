package com.mechani.robotcontroller.communication;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import com.mechani.robotcontroller.Comunicacao;
import com.mechani.robotcontroller.RHSpotter;
import com.mechani.robotcontroller.communication.exceptions.RobotError;

public abstract class RHRequest {

	private static final int DEFAULT_REQUEST_TIMEOUT = 5000;
	private RequestCallback callback;
	private RHSpotter robo = null;
	
	public abstract void sendRequest(Comunicacao comunicacao);
	
	public void sendRequest(Comunicacao c, String data, RHSpotter robo) {
		OutputStream output;
		try {
			output = c.getPorta().getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(output);
			osw.write(data, 0, data.length());
			osw.flush();
		} catch (IOException e) {
			e.printStackTrace();
			this.getCallback().error("Não foi possivel realizar o request", RobotError.ROBOT_CONNECTION);
			return;
		}
		
		this.getCallback().success(robo);
	}
	
	public RHSpotter getRobo() {
		return robo;
	}

	public void setRobo(RHSpotter robo) {
		this.robo = robo;
	}

	public RequestCallback getCallback() {
		return callback;
	}

	public void setCallback(RequestCallback callback) {
		this.callback = callback;
	}

	public int getTimeoutMillis(){
		return DEFAULT_REQUEST_TIMEOUT;
	}
	
}
