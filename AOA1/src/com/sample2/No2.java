package com.sample2;

import java.util.Scanner;

public class No2 {
	
	private int a,b,c;
	private int max;
	private int min;
	
	public void setA() {
		
			Scanner sc =  new Scanner(System.in);
			System.out.print("���� 3�� �Է�: ");
		
			while(!sc.hasNextInt()) {
				sc.next();
				System.out.print("���ڸ� �Է� ���� \n���Է�: ");
			}
			a = sc.nextInt();
		}
	
	public void setB() {
		
		Scanner sc =  new Scanner(System.in);
		System.out.print("���� 3�� �Է�: ");
	
		while(!sc.hasNextInt()) {
			sc.next();
			System.out.print("���ڸ� �Է� ���� \n���Է�: ");
		}
		b = sc.nextInt();
	}
	
	public void setC() {
		
		Scanner sc =  new Scanner(System.in);
		System.out.print("���� 3�� �Է�: ");
	
		while(!sc.hasNextInt()) {
			sc.next();
			System.out.print("���ڸ� �Է� ���� \n���Է�: ");
		}
		c = sc.nextInt();
	}
	
	public void printABC() {
		System.out.println("�Է��� ����: "+ a + ", " + b + ", " + c);
	}
	
	public void setMax() {
		
		if(a>=b) {
			max = a;
			if(c>=max) {
				max = c;
			}else {}
			
		}else {
			max = b;
			if(c>=max) {
				max = c;
			}else {}
		}
	}
	
	public void setMin() {
		
		if(a>=b) {
			min = b;
			if(c<=min) {
				min = c;
			}else {}
			
		}else {
			min = a;
			if(c<=min) {
				min = c;
			}else {}
		}
	}
	
	public void printMax() {
		System.out.println("�ִ�: " + max);
	}
	
	public void printMin() {
		System.out.println("�ּڰ�: " + min);
	}
	
	
	
	

}
