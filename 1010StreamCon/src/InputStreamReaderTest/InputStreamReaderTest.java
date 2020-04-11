package InputStreamReaderTest;

import java.io.IOException;
import java.io.InputStreamReader;

public class InputStreamReaderTest {
	
	
	public static void main(String[] args) throws IOException {
		
		InputStreamReader rd = new InputStreamReader(System.in);
		while(true) { //무한루프-> 강제종료 ctrl+z
			int c;
			//System.out.println("키보드입력: ");
			try {
				c= rd.read();
				if(c==-1) {
					System.out.println("프로그램종료");
					break;
				}else {
					System.out.println("입력키: " + c);
				}
			}catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		rd.close();
		
		
	}
}
