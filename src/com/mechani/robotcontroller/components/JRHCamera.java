package com.mechani.robotcontroller.components;

import javafx.scene.layout.Pane;

import com.mechani.robotcontroller.RHSpotter;

public class JRHCamera extends Pane {

	private RHSpotter rhspotter;

	public RHSpotter getRHSpotter() {
		return rhspotter;
	}

	public void setRHSpotter(RHSpotter rhspotter) {
		this.rhspotter = rhspotter;
	}
	
	
	
}
