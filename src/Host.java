
public class Host {
	private String name;
	private String ip;
	private String port;
	public Host(){
		name = ip = port  =null;
	}
	public Host(String name,String ip,String port){
		this.name = name;
		this.ip = ip;
		this.port = port;
	}
	public void set_name(String name){
		this.name =name;
	}
	public void set_ip(String ip){
		this.ip = ip;
	}
	public void set_port(String port){
		this.port = port;
	}
	public void set_port(Integer port){
		this.port = String.valueOf(port);
	}
	public String get_name(){
		return name;
	}
	public String get_ip(){
		return ip;
	}
	public String get_port(){
		return port;
	}
	public void show(){
		System.out.println(name);
		System.out.println(ip);
		System.out.println(port);
		System.out.println();
	}
}