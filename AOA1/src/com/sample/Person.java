package com.sample;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Person {
	
	private int no;
	private String name;
	private int bday;
	
	public void setNo() {
		
		Scanner sc =  new Scanner(System.in);
		System.out.print("ÇĞ¹ø: ");
		while(!sc.hasNextInt()) {
			sc.next();
			System.out.print("¼ıÀÚ¸¸ ÀÔ·Â °¡´É \nÀçÀÔ·Â: ");
		}
		// String ÇüÀ¸·Î ¹Ş¾Æ¼­ tmpNo¿¡ ÀÓ½ÃÀúÀå
		// integerÇüÀÎÁö È®ÀÎÇÏ´Â ±¸¹®//
		no = sc.nextInt();
		System.out.println("ÀÔ·ÂÇÑ ÇĞ¹ø: " + no);
	}
	
	public void setName() {
		
		//String pattern1 = "(^[a-zA-Z|°¡-ÆR]*$)"; // Á¤±ÔÇ¥Çö½ÄÀÌ¿ë ¿µ¾î orÇÑ±Û ÆĞÅÏ Á¤¸®
		//String pattern2 = "^[°¡-ÆR]*$";
		
		String pattern1 = "^[¤¡-¤¾°¡-ÆRa-zA-Z]*$";
		
		Scanner sc =  new Scanner(System.in);
		System.out.print("ÀÌ¸§: ");
		
		String tmpsc = sc.nextLine();
		
		if(Pattern.matches(pattern1, tmpsc)) {
			name = tmpsc;
			System.out.println("ÀÔ·ÂÇÑ ÀÌ¸§: " + name);
		}else {
			System.out.println("ÇÑ±Û/¿µ¾î¸¸ ÀÔ·Â°¡´É");
			setName();
		}
		
		
		
		/*
		if(Pattern.matches(pattern1,name) == false) {
			if(Pattern.matches(pattern2,name) == false ) {
				System.out.print("ÇÑ±Û ¶Ç´Â ¿µ¹®¸¸ ÀÔ·Â °¡´É \nÀçÀÔ·Â: ");
				sc.nextLine();
			}else {
				name = sc.nextLine();
			}
		}else {
			name= sc.nextLine();
			System.out.println("ÀÔ·ÂÇÑ ÀÌ¸§: " + name);
		}
		*/
		
		
	}
	
	public void setBday() {
		
		Scanner sc =  new Scanner(System.in);
		System.out.print("»ı³â¿ùÀÏ: ");
		while(!sc.hasNextInt()) {
			sc.next();
			System.out.print("¼ıÀÚ¸¸ ÀÔ·Â °¡´É \nÀçÀÔ·Â: ");
		}
		bday = sc.nextInt();
		System.out.println("ÀÔ·ÂÇÑ »ı³â¿ùÀÏ : " + bday);
		
		
	}
	
	public void setPerson() {
		System.out.print("ÇĞ¹ø: " + no + "\nÀÌ¸§: " + name + "\n»ı³â¿ùÀÏ: " + bday  );
	}

}
