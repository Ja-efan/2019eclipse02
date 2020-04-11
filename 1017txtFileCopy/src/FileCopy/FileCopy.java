package FileCopy;

import java.io.File;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileCopy {
	
	public static void main(String[] args) {
		
		File oriFile = new File("C:\\Users\\jaehwan\\test.txt");
		File destFile = new File("C:\\Users\\jaehwan\\test2.txt");
		int c;
		
		try {
			if(!destFile.exists()) {
				destFile.createNewFile();
			}
			FileReader fr = new FileReader(oriFile);
			FileWriter fw = new FileWriter(destFile);
			
			while((c=fr.read()) != -1) {
				fw.write((char)c);
			}
			fr.close();
			fw.close();
			System.out.println(oriFile.getPath()+"를 "+destFile.getPath()+"로 복사 하였습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
