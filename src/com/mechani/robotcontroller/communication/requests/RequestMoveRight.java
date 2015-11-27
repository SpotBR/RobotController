package com.mechani.robotcontroller.communication.requests;

import com.mechani.robotcontroller.Comunicacao;
import com.mechani.robotcontroller.communication.FastRequest;
import com.mechani.robotcontroller.communication.RHRequest;

@FastRequest(send="4")
public class RequestMoveRight extends RHRequest {

	@Override
	public void sendRequest(Comunicacao c) {
		
	}
	
}
