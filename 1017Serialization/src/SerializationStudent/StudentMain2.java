package SerializationStudent;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StudentMain2 {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		Student member = new Student("ȫ�浿",99);
		
		FileOutputStream fileStream = new FileOutputStream("c:\\Temp\\ȫ�浿.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fileStream);
		oos.writeObject(member);
		
		oos.close();
		System.out.println("��ü ���� ��� �Ϸ�. ");
		
		/////////������ȭ/////////
		FileInputStream fis = new FileInputStream("c:\\\\Temp\\\\ȫ�浿.ser");
		

		ObjectInputStream ois = new ObjectInputStream(fis);
		Object object = ois.readObject();
		ois.close();
		
		System.out.println("�о�� ��ü�� ������ -> " + object.toString());
	
	}

}
