package com.mechani.robotcontroller.communication.requests;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import com.mechani.robotcontroller.Comunicacao;
import com.mechani.robotcontroller.RHSpotter;
import com.mechani.robotcontroller.communication.RHRequest;
import com.mechani.robotcontroller.communication.exceptions.RobotError;

public class RequestHandShake extends RHRequest {

	@Override
	public void sendRequest(Comunicacao c) {
		OutputStream output;
		InputStream input;
		try {
			output = c.getPorta().getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(output);
			osw.write("OK", 0, 2);
			osw.flush();
			
			input = c.getPorta().getInputStream();
			InputStreamReader inw = new InputStreamReader(input);
			
			long now = System.currentTimeMillis();
			while (true){
				if(inw.ready() && c.getPorta().bytesAvailable() > 0){
					char ok[] = new char[2];
					inw.read(ok);
					String dataString = new String(ok);
					boolean isOk = new String(ok).equalsIgnoreCase("OK");
					if(!isOk){
						this.getCallback().error("Não foi possivel enviar o HandShake: "+dataString, RobotError.ROBOT_CONNECTION);
						return;
					}
					break;
				}else{
					int passou = (int)(System.currentTimeMillis()-now);
					if(passou >= this.getTimeoutMillis()){
						this.getCallback().error("Não foi possivel enviar o HandShake. (TIMEOUT)", RobotError.ROBOT_CONNECTION);
						return;
					}
					Thread.sleep(20);
				}
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			this.getCallback().error("Não foi possivel enviar o HandShake", RobotError.ROBOT_CONNECTION);
			return;
		}
		
		RHSpotter robo = new RHSpotter(c);
		
		this.getCallback().error("Não foi possivel conectar a câmera", RobotError.CAMERA_CONNECTION);
		this.getCallback().success(robo);
		
	}
	
}
