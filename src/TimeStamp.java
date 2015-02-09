import java.io.Serializable;


public abstract class TimeStamp implements Serializable{
	
	/*	set_localtime method:
	 *		for logic clock
	 *			if there is an user event, 
	 *				should call set_localtime(null), just add 1 to local time
	 *			if receive a message
	 *				should call set_localtime(timestamp), set the max value between local time and timestamp
	 *		
	 *		for vector clock
	 *			if there is a user event,
	 *				call set_localtime(null), add 1 to local time within vector
	 *			if message received,
	 *				call set_localtime(timestamp), increment own local time, compare all items in local vector to items in other vector and set max value for all 
	 *			TODO
	 */
	public abstract void set_localtime(TimeStamp t);
		
	/* compare to method:
			if this <= t return 0
			if this > t return 1
	*/
	public abstract int compare(TimeStamp t);
	
	public abstract void print_clock();
	
}
