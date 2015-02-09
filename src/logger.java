import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class logger implements Runnable{
	public static MessagePasser mp;
	public static String localname = "logger";
	public static ArrayList<TimeStampedMessage> logs = new ArrayList<TimeStampedMessage>();
	public static String type = new String();
	public logger(){
		
	}
	public static void main(String[] args) throws IOException{
		String configuration_addr;
		Scanner scanner = new Scanner(System.in);
		System.out.println("input clock type");
	//	configuration_addr = scanner.nextLine();
		type = scanner.nextLine();
		mp = new MessagePasser(null,localname);
		mp.set_clockType(type);
		
	//	mp = new MessagePasser(configuration_addr,localname);
		logger listener = new logger();
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
			TimeStampedMessage t = logs.remove(0);
			System.out.println(t.get_src() + "->" + t.get_dest() + " "+t.get_data());
			//t.get_timestamp().print_clock();
		}
	}
	public void run() {
		while(true){
			if( mp != null){
				Message recv = mp.receive();
				if( recv != null){
					System.out.println("[RECV]	"+recv.get_src() +":"+ recv.get_data().toString());
					System.out.println(recv.get_log());
					if(recv.get_log())
					insert((TimeStampedMessage)recv);
				}
			}
		}
	}
	public static void check_concurrent(TimeStampedMessage recv){
		TimeStamp r = recv.get_timestamp();
		for(int i = 0; i < logs.size();i ++){
			TimeStamp p = logs.get(i).get_timestamp();
			if(p.compare(r) == 0 && 0 == type.compareToIgnoreCase("vector")){
				System.out.println("--------------------");
				System.out.println("concurrent message:");
				p.print_clock();
				r.print_clock();
				System.out.println("--------------------");
			}
		}
	}
	public static void insert( TimeStampedMessage recv){
		int index;
		if(type.compareToIgnoreCase("vector") == 0)
			check_concurrent(recv);
		TimeStamp r = recv.get_timestamp();
		for(int i = 0; i < logs.size();i ++){
			TimeStamp p = logs.get(i).get_timestamp();
			if(p.compare(r) >= 0){
				logs.add(i, recv);	
				return;
			}
		}
		logs.add(recv);
		return;
	}
}
