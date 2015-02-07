
public class TimeStampedMessage extends Message{
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
}
