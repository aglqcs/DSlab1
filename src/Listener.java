import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;


public class Listener implements Runnable{
	private static Queue<Message> recv_queue = new LinkedList<Message>();
	private static Queue<TimeStampedMessage> delay_queue = new LinkedList<TimeStampedMessage>();
	private Socket socket;
	
	public Listener(){
	}
	public Listener(Socket conn){
		this.socket = conn;
	}
	public Queue<Message> get_recv_queue(){
		return recv_queue;
	}
	public static void show_queue_length(){
		System.out.println("recv : " + recv_queue.size());
		System.out.println("delay: " + delay_queue.size());
	}
	synchronized private void insert_recv_queue(TimeStampedMessage recv){
		recv_queue.add(recv);
	}
	synchronized private void insert_delay_queue(TimeStampedMessage recv){
		delay_queue.add(recv);
	}
	synchronized public static void clear_delay_queue(){
		while( !delay_queue.isEmpty() ){
			recv_queue.add(delay_queue.poll());
		}
	}
	public void run() {
		try {	
			while(true){
				ObjectInputStream readin = new ObjectInputStream(socket.getInputStream()); //fixed but note: if put this outside the while loop it will just receive only 1 message why?
				
				TimeStampedMessage recv = (TimeStampedMessage)readin.readObject();
				/*		check if recv follows recv rule, and insert to recv queue
				 * 			The insert process need to be synchronized!
				 * */
				int result = MessagePasser.recv_check(recv);
				if(result == 0){
					//recv the message
					insert_recv_queue(recv);
				}
				else if(result == 1){
					//drop message
					System.out.println("[recv rule]: dropped message");
				}
				else if(result == 2){
					//delay message
					System.out.println("[recv rule]: delay message");
					insert_delay_queue(recv);
				}
				else if(result == 3){
					System.out.println("[recv rule]: duplicate message");
					//duplicate message
					TimeStampedMessage dup = new TimeStampedMessage(recv);
					insert_recv_queue(recv);
					insert_recv_queue(dup);
				}
				else{
					System.out.println("error recieve type!");
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			//e.printStackTrace();
			/*note: here I delete this function because there is a EOFexception, even the program is working good, So I just do not let it print to create noise*/
		}
	}
	
}