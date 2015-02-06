
public class LogicClockService extends ClockService{
	public LogicTimeStamp clock;
	public LogicClockService() {
		super();
		create_clock();
	}

	public void create_clock() {
		clock = new LogicTimeStamp();
	}
	public TimeStamp getTimeStamp() {
		return clock;
	}
	public void UpdateTimeStamp(TimeStamp t) {
		clock.set_localtime(t);
	}
	
}
