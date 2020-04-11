package ReadURL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ReadURL {
	
	public static void main(String[] args) {
		try {
			
			URL aURL = new URL("http://www.daum.com");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(aURL.openStream()));
			
			String inputLine;
			
			while((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
			}
			in.close();
		}catch(IOException e) {
			System.out.println("URL에서 데이터를 읽는중 오류 발생");
		}
	}
}
