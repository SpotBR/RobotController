package com.mechani.robotcontroller;

import com.mechani.robotcontroller.communication.RHRequest;
import com.mechani.robotcontroller.communication.requests.RequestLightsOff;
import com.mechani.robotcontroller.communication.requests.RequestLightsOn;
import com.mechani.robotcontroller.tools.Camera;
import com.mechani.robotcontroller.tools.Move;

public class RHSpotter {

	private Move move;
	private boolean lanterna;
	private Camera camera;
	private Comunicacao comunicacao;
	
	public RHSpotter(Comunicacao comunicacao){
		this.comunicacao = comunicacao;
		this.move = new Move(this);
		this.camera = new Camera();
	}

	public Comunicacao getComunicacao() {
		return comunicacao;
	}

	public Move getMove() {
		return move;
	}
	
	public boolean isLanterna() {
		return lanterna;
	}
	
	public void setLanterna(boolean lanterna) {
		RHRequest request = lanterna ? new RequestLightsOn() : new RequestLightsOff();
		request.setRobo(this);
		getComunicacao().makeRequest(request);
		
		this.lanterna = lanterna;
	}
	
	public Camera getCamera() {
		return camera;
	}
	
	
	
}
