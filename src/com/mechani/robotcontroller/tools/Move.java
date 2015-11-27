package com.mechani.robotcontroller.tools;

import com.mechani.robotcontroller.Comunicacao;
import com.mechani.robotcontroller.RHSpotter;
import com.mechani.robotcontroller.communication.requests.RequestMoveFront;
import com.mechani.robotcontroller.communication.requests.RequestMoveLeft;
import com.mechani.robotcontroller.communication.requests.RequestMoveParar;
import com.mechani.robotcontroller.communication.requests.RequestMoveRetrograde;
import com.mechani.robotcontroller.communication.requests.RequestMoveRight;

public class Move {

	private Comunicacao comunicacao;
	private RHSpotter robo;
	private RequestMoveFront front;
	private RequestMoveRetrograde back;
	private RequestMoveRight right;
	private RequestMoveLeft left;
	private RequestMoveParar parar;
	
	public Move(RHSpotter robo){
		this.comunicacao = robo.getComunicacao();
		this.robo = robo;
		
		front = new RequestMoveFront();
		front.setRobo(robo);
		back = new RequestMoveRetrograde();
		back.setRobo(robo);
		right = new RequestMoveRight();
		right.setRobo(robo);
		left = new RequestMoveLeft();
		left.setRobo(robo);
		parar = new RequestMoveParar();
		parar.setRobo(robo);
	}
	
	
	public Comunicacao getComunicacao() {
		return comunicacao;
	}

	public RHSpotter getRobo() {
		return robo;
	}

	public void frente() {
		getComunicacao().makeRequest(front);
	}
	
	public void atras() {
		getComunicacao().makeRequest(back);
	}
	
	public void parar() {
		getComunicacao().makeRequest(parar);
	}
	
	public void girarEsquerda() {
		getComunicacao().makeRequest(left);
	}
	
	public void girarDireita() {
		getComunicacao().makeRequest(right);
	}
	
}
