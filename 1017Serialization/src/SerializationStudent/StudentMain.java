package SerializationStudent;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;




public class StudentMain {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		Student member = new Student("홍길동", 89);
		
		byte[] serializedMember; // 직렬화된 객체를 저장할 byte형의 배열 선언
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(member);
		// serializedMember -> 직렬화된 member 객체
		serializedMember = baos.toByteArray();
		// 바이트 배열로 생성된 직렬화 데이터를 base64로 변환
		// base64 로 encoding을 할 경우 파일이 전송되다 깨질일이 없다.
		String serializedStr = Base64.getEncoder().encodeToString(serializedMember);
		System.out.println("직렬화된 문자 = " + serializedStr);
		oos.close();
		baos.close();
		
		
		/////////역직렬화///////////////
		
		
		String base64Member = serializedStr;
		byte[] deSerializedMember = Base64.getDecoder().decode(base64Member);
		ByteArrayInputStream bais = new ByteArrayInputStream(deSerializedMember);
		ObjectInputStream ois = new ObjectInputStream(bais);
		//역직렬화된 Member객체를 읽어온다
		Object objectMember = ois.readObject();
		Student deMember = (Student)objectMember;
		System.out.println("역직렬화 객체 = " + deMember.toString());
		ois.close();
		bais.close();
		
	}

}
