package SerializationStudent;

import java.io.Serializable;

public class Student implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int score;
	
	public Student(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	public String toString() {
		return String.format("Member{name='%s',score='%s'}",name,score);
	}
	
	

}
