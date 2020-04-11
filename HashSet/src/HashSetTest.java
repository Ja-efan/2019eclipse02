import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class HashSetTest {
	
	public static void main(String[] args) {
		
		Set hs = new HashSet(); // Set이라는 추상적인 개념아래 HashSet의 객체 hs생성
		hs.add("홍길동1");
		hs.add("홍길동2");
		hs.add("홍길동3");
		hs.add("홍길동4");
		hs.add("홍길동4"); // 중복데이터 허용 x
		
		
		Object[] arr = (Object[]) hs.toArray(); // HashSet 데이터를 배열로 변환하여 배열 arr에 저장
		for (int i =0; i<arr.length; i++) {
			System.out.println(arr[i]);
			/*
			 * 출력
			 * 홍길동4
			 * 홍길동2
			 * 홍길동3
			 * 홍길동1
			 * -> 순서의미x
			 */
			
		}
		
		System.out.println("==========================");
		
		Iterator it = hs.iterator(); // HashSet 데이터를 Iterator로 변환하여 it에 저장
		while(it.hasNext()) {
			String tmp = (String)it.next();
			System.out.println(tmp);
		}
		
	}

}
