
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
	/*	note: when user want to update system logic clock call this function
	 * 	there are 2 circumstances
	 * 	if send a message 
	 * 		should call UpdateTimeStamp(null)
	 * 		just add 1 to logic clock
	 *	if receive a message
	 *		should call UpdateTimeStamp(TimeStamp t)
	 *		modify the logic clock to max(t,current+1) 
	 * */
	public void UpdateTimeStamp(TimeStamp t) {
		clock.set_localtime(t);
	}
	
}
