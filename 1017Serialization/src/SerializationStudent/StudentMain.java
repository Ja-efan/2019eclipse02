package SerializationStudent;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;




public class StudentMain {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		Student member = new Student("ȫ�浿", 89);
		
		byte[] serializedMember; // ����ȭ�� ��ü�� ������ byte���� �迭 ����
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(member);
		// serializedMember -> ����ȭ�� member ��ü
		serializedMember = baos.toByteArray();
		// ����Ʈ �迭�� ������ ����ȭ �����͸� base64�� ��ȯ
		// base64 �� encoding�� �� ��� ������ ���۵Ǵ� �������� ����.
		String serializedStr = Base64.getEncoder().encodeToString(serializedMember);
		System.out.println("����ȭ�� ���� = " + serializedStr);
		oos.close();
		baos.close();
		
		
		/////////������ȭ///////////////
		
		
		String base64Member = serializedStr;
		byte[] deSerializedMember = Base64.getDecoder().decode(base64Member);
		ByteArrayInputStream bais = new ByteArrayInputStream(deSerializedMember);
		ObjectInputStream ois = new ObjectInputStream(bais);
		//������ȭ�� Member��ü�� �о�´�
		Object objectMember = ois.readObject();
		Student deMember = (Student)objectMember;
		System.out.println("������ȭ ��ü = " + deMember.toString());
		ois.close();
		bais.close();
		
	}

}
