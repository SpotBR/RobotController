package com.mechani.robotcontroller.communication.requests;

import com.mechani.robotcontroller.Comunicacao;
import com.mechani.robotcontroller.communication.FastRequest;
import com.mechani.robotcontroller.communication.RHRequest;

@FastRequest(send="2")
public class RequestMoveRetrograde extends RHRequest {

	@Override
	public void sendRequest(Comunicacao c) {
		
	}
	
}
