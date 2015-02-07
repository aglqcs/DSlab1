import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;


public class logger {
	public static MessagePasser mp;
	public static String localname = "logger";
	public static LinkedList<TimeStampedMessage> logs = new LinkedList<TimeStampedMessage>();
	
	public static void main(String[] args) throws IOException{
		String configuration_addr;
		Scanner scanner = new Scanner(System.in);
		System.out.println("input config file addr");
		configuration_addr = scanner.nextLine();

		mp = new MessagePasser(configuration_addr,localname);
		Userapplication listener = new Userapplication();
		new Thread(listener).start();
		System.out.println("logger start listen");
		while(true){
			String t = scanner.nextLine();
			if(t.compareToIgnoreCase("show") == 0){
				printlogs();
			}
		}
	}

	public static void printlogs(){
		System.out.println("current logs:");
		while( !logs.isEmpty()){
			TimeStampedMessage t = logs.poll();
			System.out.println(t.get_src() + "->" + t.get_dest() + " ");
		}
	}
	public void run() {
		while(true){
			if( mp != null){
				Message recv = mp.receive();
				logs.add((TimeStampedMessage)recv);
			}
		}
	}
}
