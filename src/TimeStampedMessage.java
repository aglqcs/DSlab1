import java.util.Comparator;


public class TimeStampedMessage extends Message implements Comparator{
	public TimeStamp time;
	public TimeStampedMessage(TimeStampedMessage recv){
		super(recv);
		time = recv.get_timestamp();
	}
	public TimeStampedMessage(Message recv,TimeStamp current) {
		super(recv);
		time = current;
	}
	public void set_timestamp(TimeStamp current){
		this.time = current;
	}
	public TimeStamp get_timestamp(){
		return time;
	}

	public int compare(Object o1, Object o2) {
		TimeStamp t1,t2;
		t1 = ((TimeStampedMessage)o1).get_timestamp();
		t2 = ((TimeStampedMessage)o2).get_timestamp();
		return t1.compare(t2);
	}
}
