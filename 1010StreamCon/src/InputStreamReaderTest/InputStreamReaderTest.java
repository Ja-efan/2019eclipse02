package InputStreamReaderTest;

import java.io.IOException;
import java.io.InputStreamReader;

public class InputStreamReaderTest {
	
	
	public static void main(String[] args) throws IOException {
		
		InputStreamReader rd = new InputStreamReader(System.in);
		while(true) { //���ѷ���-> �������� ctrl+z
			int c;
			//System.out.println("Ű�����Է�: ");
			try {
				c= rd.read();
				if(c==-1) {
					System.out.println("���α׷�����");
					break;
				}else {
					System.out.println("�Է�Ű: " + c);
				}
			}catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		rd.close();
		
		
	}
}
