package com.mechani.robotcontroller.communication;

import com.mechani.robotcontroller.RHSpotter;
import com.mechani.robotcontroller.communication.exceptions.RobotError;

public interface RequestCallback {

	public void success(RHSpotter rhspotter);
	
	public void error(String message, RobotError error);
	
}
