package com.mechani.robotcontroller.communication.requests;

import com.mechani.robotcontroller.Comunicacao;
import com.mechani.robotcontroller.communication.FastRequest;
import com.mechani.robotcontroller.communication.RHRequest;

@FastRequest(send="1")
public class RequestMoveFront extends RHRequest {

	@Override
	public void sendRequest(Comunicacao comunicacao) {
	}
	
	
	
}
