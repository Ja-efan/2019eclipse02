package collections;

public class StuArrMain {
	
	public static void main(String[] args) {
		
		Student st1 = new Student();
		st1.setName("홍길동");
		st1.setNo("1");
		
		Student st2 = new Student();
		st2.setName("이순신");
		st2.setNo("2");
		
		Student stuArr[] = new Student[2];
		stuArr[0] = st1;
		stuArr[1] = st2;
		
		for(int i =0; i<stuArr.length;i++) {
			stuArr[i].printName();
			stuArr[i].pritnNo();
		}
	}

}
