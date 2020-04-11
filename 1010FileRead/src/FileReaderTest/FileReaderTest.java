package FileReaderTest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderTest {
	
	public static void main(String[] args) /*throws IOException*/ {
		try {
			FileReader fin = new FileReader("C:\\Users\\jaehwan\\test.txt"); // make a stream and connect to file
		
			int c;
			while((c=fin.read()) != -1) { // file은 항상 마지막에 -1(EOF;end of file)을 반환한다. & read()로 문자 하나 씩 파일에서 읽음.
				System.out.print((char)c);
			
			}
			System.out.println();
			if ((c=fin.read()) == -1)
			{
				System.out.println(c);
			}
			fin.close(); // if stream is not need anymore, close stream. closed stream can't be readed.
		
		} catch (FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다.");      
		} catch (IOException e) {
			System.out.println("입출력오류");
		}
		
	}
}