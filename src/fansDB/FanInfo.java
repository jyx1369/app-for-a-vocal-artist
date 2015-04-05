package fansDB;

public class FanInfo {
	private int id;
	private String name;
	private String emailAD;
	
	public FanInfo(){}
	public FanInfo(String name,String emailAD){
		super();
		this.name=name;
		this.emailAD=emailAD;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailAD() {
		return emailAD;
	}
	public void setEmailAD(String emailAD) {
		this.emailAD = emailAD;
	}
	public String toString(){
		return "FanInfo ["+"id="+id+", name="+name+", emailAD="+emailAD+"]";
	}	
}
