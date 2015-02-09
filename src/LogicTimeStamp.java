
public class LogicTimeStamp extends TimeStamp{
	private int local_time;
	
	public LogicTimeStamp() {
		super();
		this.local_time = 0;
	}
	public LogicTimeStamp(LogicTimeStamp t){
		super();
		this.local_time = t.get_localtime();
	}

	public void set_localtime(TimeStamp t) {		
		this.local_time ++;
		
		if(t == null) return;
		else{
			LogicTimeStamp temp = (LogicTimeStamp)t;
			int other_time = temp.get_localtime();
			local_time = (other_time + 1) > local_time ? (other_time + 1) : local_time;
			return;
		}
	}
	public void print_clock(){
		System.out.println("clock(logic) == "+local_time);
	}
	public int get_localtime() {
		return this.local_time;
	}

	public int compare(TimeStamp t) {
		LogicTimeStamp temp = (LogicTimeStamp)t;
		int other_time = temp.get_localtime();
		if(local_time < other_time) return -1;
		else if(local_time == other_time) return 0;
		else return 1;
	}

}
