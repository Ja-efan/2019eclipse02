package SerializationStudent;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StudentMain2 {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		Student member = new Student("홍길동",99);
		
		FileOutputStream fileStream = new FileOutputStream("c:\\Temp\\홍길동.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fileStream);
		oos.writeObject(member);
		
		oos.close();
		System.out.println("객체 파일 출력 완료. ");
		
		/////////역직렬화/////////
		FileInputStream fis = new FileInputStream("c:\\\\Temp\\\\홍길동.ser");
		

		ObjectInputStream ois = new ObjectInputStream(fis);
		Object object = ois.readObject();
		ois.close();
		
		System.out.println("읽어온 객체의 데이터 -> " + object.toString());
	
	}

}
