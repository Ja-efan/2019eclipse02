package com.sample;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Person {
	
	private int no;
	private String name;
	private int bday;
	
	public void setNo() {
		
		Scanner sc =  new Scanner(System.in);
		System.out.print("�й�: ");
		while(!sc.hasNextInt()) {
			sc.next();
			System.out.print("���ڸ� �Է� ���� \n���Է�: ");
		}
		// String ������ �޾Ƽ� tmpNo�� �ӽ�����
		// integer������ Ȯ���ϴ� ����//
		no = sc.nextInt();
		System.out.println("�Է��� �й�: " + no);
	}
	
	public void setName() {
		
		//String pattern1 = "(^[a-zA-Z|��-�R]*$)"; // ����ǥ�����̿� ���� or�ѱ� ���� ����
		//String pattern2 = "^[��-�R]*$";
		
		String pattern1 = "^[��-����-�Ra-zA-Z]*$";
		
		Scanner sc =  new Scanner(System.in);
		System.out.print("�̸�: ");
		
		String tmpsc = sc.nextLine();
		
		if(Pattern.matches(pattern1, tmpsc)) {
			name = tmpsc;
			System.out.println("�Է��� �̸�: " + name);
		}else {
			System.out.println("�ѱ�/��� �Է°���");
			setName();
		}
		
		
		
		/*
		if(Pattern.matches(pattern1,name) == false) {
			if(Pattern.matches(pattern2,name) == false ) {
				System.out.print("�ѱ� �Ǵ� ������ �Է� ���� \n���Է�: ");
				sc.nextLine();
			}else {
				name = sc.nextLine();
			}
		}else {
			name= sc.nextLine();
			System.out.println("�Է��� �̸�: " + name);
		}
		*/
		
		
	}
	
	public void setBday() {
		
		Scanner sc =  new Scanner(System.in);
		System.out.print("�������: ");
		while(!sc.hasNextInt()) {
			sc.next();
			System.out.print("���ڸ� �Է� ���� \n���Է�: ");
		}
		bday = sc.nextInt();
		System.out.println("�Է��� ������� : " + bday);
		
		
	}
	
	public void setPerson() {
		System.out.print("�й�: " + no + "\n�̸�: " + name + "\n�������: " + bday  );
	}

}
