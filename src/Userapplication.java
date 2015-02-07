import java.io.IOException;
import java.util.Scanner;


public class Userapplication implements Runnable{
	// When user create message: should call set_seqnumber, set_dest,set_kind
	// server port should choose to what?
	public static MessagePasser mp;
	private static LogicClockService logic_clock;
	private static int clock_type;
	
	public Userapplication(){
	}
	public static void main(String[] args) throws IOException{
		//String configuration_addr = "/home/chenshuo/18842/lab0/configuration";
		String configuration_addr;
		Scanner scanner = new Scanner(System.in);
		System.out.println("input config file addr");
		configuration_addr = scanner.nextLine();
		System.out.println("input local name:");
		String localname = scanner.nextLine();
		mp = new MessagePasser(configuration_addr,localname);
		Userapplication listener = new Userapplication();
		new Thread(listener).start();
		System.out.println("start send (input format: dest \\n kind \\n data \\n )");
		while(true){
			String dest = scanner.nextLine();
			String kind = scanner.nextLine();
			String data = scanner.nextLine();
			Message to_send = new Message(localname,dest,kind,data);
			mp.send(to_send);
			System.out.println("seq = "+to_send.get_seq());
		}
	}

	public void run() {
		while(true){
			if( mp != null){
				Message recv = mp.receive();
				if( recv != null){
					System.out.println("[RECV]	"+recv.get_src() +":"+ recv.get_data().toString());
				}
			}
		}
	}
}
