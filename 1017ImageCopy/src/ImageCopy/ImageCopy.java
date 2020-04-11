package ImageCopy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ImageCopy {
	
	public static void main(String[] args) {
		
		File oriFile = new File("C:\\Users\\jaehwan\\Downloads\\Maison Margiela.png");
		File desFile = new File("C:\\Users\\jaehwan\\Downloads\\Maison Margiela2.png");
		
		try {
			FileInputStream fileIn =  new FileInputStream(oriFile);
			FileOutputStream fileOut = new FileOutputStream(desFile);	
			
			byte[] buffer =  new byte[1024];
			int length = 0;
			while((length = fileIn.read(buffer, 0, buffer.length)) != -1) {
				fileOut.write(buffer, 0, length);
				
			}
			fileIn.close();
			fileOut.close();
			System.out.println(oriFile.getPath() + "를 "+ desFile.getPath() + "로 복사하였습니다.");
		}
		catch (Exception e) {
			System.out.println("이미지 복사 오류: "+ e.toString());
		}
	}

}
