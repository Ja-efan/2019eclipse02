package com.sample;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Person {
	
	private int no;
	private String name;
	private int bday;
	
	public void setNo() {
		
		Scanner sc =  new Scanner(System.in);
		System.out.print("학번: ");
		while(!sc.hasNextInt()) {
			sc.next();
			System.out.print("숫자만 입력 가능 \n재입력: ");
		}
		// String 형으로 받아서 tmpNo에 임시저장
		// integer형인지 확인하는 구문//
		no = sc.nextInt();
		System.out.println("입력한 학번: " + no);
	}
	
	public void setName() {
		
		//String pattern1 = "(^[a-zA-Z|가-힣]*$)"; // 정규표현식이용 영어 or한글 패턴 정리
		//String pattern2 = "^[가-힣]*$";
		
		String pattern1 = "^[ㄱ-ㅎ가-힣a-zA-Z]*$";
		
		Scanner sc =  new Scanner(System.in);
		System.out.print("이름: ");
		
		String tmpsc = sc.nextLine();
		
		if(Pattern.matches(pattern1, tmpsc)) {
			name = tmpsc;
			System.out.println("입력한 이름: " + name);
		}else {
			System.out.println("한글/영어만 입력가능");
			setName();
		}
		
		
		
		/*
		if(Pattern.matches(pattern1,name) == false) {
			if(Pattern.matches(pattern2,name) == false ) {
				System.out.print("한글 또는 영문만 입력 가능 \n재입력: ");
				sc.nextLine();
			}else {
				name = sc.nextLine();
			}
		}else {
			name= sc.nextLine();
			System.out.println("입력한 이름: " + name);
		}
		*/
		
		
	}
	
	public void setBday() {
		
		Scanner sc =  new Scanner(System.in);
		System.out.print("생년월일: ");
		while(!sc.hasNextInt()) {
			sc.next();
			System.out.print("숫자만 입력 가능 \n재입력: ");
		}
		bday = sc.nextInt();
		System.out.println("입력한 생년월일 : " + bday);
		
		
	}
	
	public void setPerson() {
		System.out.print("학번: " + no + "\n이름: " + name + "\n생년월일: " + bday  );
	}

}
