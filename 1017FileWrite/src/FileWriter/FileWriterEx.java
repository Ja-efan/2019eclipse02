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
			while((c=in.read()) != -1) { // 종료를 위해 사용자는 키입력 후 라인 첫위치에 ctrl+z(eof)를 입력해야함.
				fout.write(c);
			}
			in.close();
			fout.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	

}