import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class IncomeHandler implements Runnable {
	private int server_port;
	public IncomeHandler(int port){
		server_port = port;
	}
	public void run() {
		ServerSocket socket = null;
		try {
			socket = new ServerSocket(server_port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(true){
			Socket connectionSocket = null;
			try {
				connectionSocket = socket.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
        	Listener listen = new Listener(connectionSocket);
        	new Thread(listen).start();
		}
	}
}
