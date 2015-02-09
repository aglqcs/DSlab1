import java.util.HashMap;
import java.util.List;


public class VectorClockService extends ClockService{
	public VectorTimeStamp clock;
	
	public VectorClockService() {
		super();
		create_clock();
	}

	public void create_clock() {
		clock = new VectorTimeStamp();
	}
	public TimeStamp getTimeStamp() {
		return clock;
	}
	/*	Note: when user wants to update system vector clock, they call this function
	 * 	there are 2 circumstances
	 * 	if sending a message 
	 * 		call UpdateTimeStamp(null)
	 * 		-just add 1 to own clock in vector
	 *	if receive a message
	 *		should call UpdateTimeStamp(TimeStamp t)
	 *		-modify the all clock items in vector to max(t,current+1) 
	 * */
	public void UpdateTimeStamp(TimeStamp t) {
		clock.set_localtime(t);
	}
	
}