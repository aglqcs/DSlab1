import java.io.Serializable;

public class Message implements Serializable{
	private static final long serialVersionUID = 1L;
	public static int seq_num = 1; // global seq number starting with 1
	private String src;
	private String dest;
	private String kind;
	private int seq;
	private Object data;
	private boolean duplicate = false;
	private boolean send_delay = false;
	
	public Message(String src,String dest, String kind, Object data){
		this.src = src;
		this.dest = dest;
		this.kind = kind;
		this.data = data;
		set_seqNum();
	}
	public Message(Message recv) {
		this.src = recv.get_src();
		this.dest = recv.get_dest();
		this.kind = recv.get_kind();
		this.seq = recv.get_int_seq();
		this.data = recv.get_data();
		this.duplicate = recv.get_duplicate();
		this.send_delay = recv.get_send_delay();
	}
	public void set_source(String source){
		this.src = source;
	}
	public void set_seqNum(){
		this.seq = seq_num;
		seq_num ++;
	}
	public void set_seqNum(int sequenceNumber){
		this.seq = sequenceNumber;
	}
	public void set_duplicate(boolean dup){
		this.duplicate = dup;
	}
	public void set_send_delay(boolean send_delay){
		this.send_delay = send_delay;
	}
	public String get_src(){return src;}
	public String get_dest(){return dest;}
	public String get_kind(){return kind;}
	public int get_int_seq(){return seq;}
	public String get_seq(){return String.valueOf(seq);}
	public boolean get_duplicate(){return duplicate;}
	public boolean get_send_delay(){return send_delay;}
	public Object get_data(){return data;}
	public void show(){
		System.out.println("-------------");
		System.out.println(src);
		System.out.println(dest);
		System.out.println(kind);
		System.out.println(this.seq);
		System.out.println(data);
		System.out.println("-------------");
	}
}
