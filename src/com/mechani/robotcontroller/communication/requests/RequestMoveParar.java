package com.mechani.robotcontroller.communication.requests;

import com.mechani.robotcontroller.Comunicacao;
import com.mechani.robotcontroller.communication.FastRequest;
import com.mechani.robotcontroller.communication.RHRequest;

@FastRequest(send="3")
public class RequestMoveParar extends RHRequest {

	@Override
	public void sendRequest(Comunicacao comunicacao) {
	}
	
}
