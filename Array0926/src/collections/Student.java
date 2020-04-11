package collections;

public class Student {
	
	private String no;
	private String name;
	
	public void setName(String name) {
		this.name = name;
	}
	
	/*
	public String getName() {
		return name;
	}
	*/
	
	public void setNo(String no) {
		this.no = no;
	}
	
	/*
	public String getNO() {
		return no;
	}
	*/
	
	public void printName() {
		System.out.println(name);
	}
	
	public void pritnNo() {
		System.out.println(no);
	}
}
