package BufferedReaderTest;

import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BufferedReaderTest {
	
	public static void main(String[] args) {
		
		String fName = JOptionPane.showInputDialog("파일경로 입력");
		
		BufferedReader reader;
		
		try {
			
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(fName),"UTF8"));
			int c;
			while((c=reader.read()) != -1) {
				System.out.print((char)c);
			}
			System.out.println();
			System.out.println(c);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
