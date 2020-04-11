package FileWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileWriterEx {
	
	public static void main(String[] args) {
		
		InputStreamReader in = new InputStreamReader(System.in);
		FileWriter fout = null;
		
		int c;
		
		try {
			fout =  new FileWriter("C:\\Users\\jaehwan\\test.txt");
			while((c=in.read()) != -1) { // ���Ḧ ���� ����ڴ� Ű�Է� �� ���� ù��ġ�� ctrl+z(eof)�� �Է��ؾ���.
				fout.write(c);
			}
			in.close();
			fout.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	

}