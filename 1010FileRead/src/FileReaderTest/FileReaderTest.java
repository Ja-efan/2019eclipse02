package FileReaderTest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderTest {
	
	public static void main(String[] args) /*throws IOException*/ {
		try {
			FileReader fin = new FileReader("C:\\Users\\jaehwan\\test.txt"); // make a stream and connect to file
		
			int c;
			while((c=fin.read()) != -1) { // file�� �׻� �������� -1(EOF;end of file)�� ��ȯ�Ѵ�. & read()�� ���� �ϳ� �� ���Ͽ��� ����.
				System.out.print((char)c);
			
			}
			System.out.println();
			if ((c=fin.read()) == -1)
			{
				System.out.println(c);
			}
			fin.close(); // if stream is not need anymore, close stream. closed stream can't be readed.
		
		} catch (FileNotFoundException e) {
			System.out.println("������ ã�� �� �����ϴ�.");      
		} catch (IOException e) {
			System.out.println("����¿���");
		}
		
	}
}