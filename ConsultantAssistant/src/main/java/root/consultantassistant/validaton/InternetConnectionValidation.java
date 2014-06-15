package root.consultantassistant.validaton;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.scene.control.Button;

public class InternetConnectionValidation {

	
	public static boolean isConnected(String hostUrl, Button button){
		Socket socket = null;
		boolean reachable = false;
		try{
			socket = new Socket(hostUrl, 80);
			reachable = true;
		} catch (UnknownHostException e) {
			button.setId("disconnected");
			
		} catch (IOException e) {
			button.setId("disconnected");			
		}
		finally{
			if(socket != null)
				try{
					socket.close();
				}
			catch(IOException e){}
		}
		button.setId("connected");
		return reachable;
	}
}
