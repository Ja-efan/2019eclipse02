import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class HashSetTest {
	
	public static void main(String[] args) {
		
		Set hs = new HashSet(); // Set�̶�� �߻����� ����Ʒ� HashSet�� ��ü hs����
		hs.add("ȫ�浿1");
		hs.add("ȫ�浿2");
		hs.add("ȫ�浿3");
		hs.add("ȫ�浿4");
		hs.add("ȫ�浿4"); // �ߺ������� ��� x
		
		
		Object[] arr = (Object[]) hs.toArray(); // HashSet �����͸� �迭�� ��ȯ�Ͽ� �迭 arr�� ����
		for (int i =0; i<arr.length; i++) {
			System.out.println(arr[i]);
			/*
			 * ���
			 * ȫ�浿4
			 * ȫ�浿2
			 * ȫ�浿3
			 * ȫ�浿1
			 * -> �����ǹ�x
			 */
			
		}
		
		System.out.println("==========================");
		
		Iterator it = hs.iterator(); // HashSet �����͸� Iterator�� ��ȯ�Ͽ� it�� ����
		while(it.hasNext()) {
			String tmp = (String)it.next();
			System.out.println(tmp);
		}
		
	}

}
