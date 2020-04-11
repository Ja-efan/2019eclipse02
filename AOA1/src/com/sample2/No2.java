package com.sample2;

import java.util.Scanner;

public class No2 {
	
	private int a,b,c;
	private int max;
	private int min;
	
	public void setA() {
		
			Scanner sc =  new Scanner(System.in);
			System.out.print("숫자 3개 입력: ");
		
			while(!sc.hasNextInt()) {
				sc.next();
				System.out.print("숫자만 입력 가능 \n재입력: ");
			}
			a = sc.nextInt();
		}
	
	public void setB() {
		
		Scanner sc =  new Scanner(System.in);
		System.out.print("숫자 3개 입력: ");
	
		while(!sc.hasNextInt()) {
			sc.next();
			System.out.print("숫자만 입력 가능 \n재입력: ");
		}
		b = sc.nextInt();
	}
	
	public void setC() {
		
		Scanner sc =  new Scanner(System.in);
		System.out.print("숫자 3개 입력: ");
	
		while(!sc.hasNextInt()) {
			sc.next();
			System.out.print("숫자만 입력 가능 \n재입력: ");
		}
		c = sc.nextInt();
	}
	
	public void printABC() {
		System.out.println("입력한 숫자: "+ a + ", " + b + ", " + c);
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
		System.out.println("최댓값: " + max);
	}
	
	public void printMin() {
		System.out.println("최솟값: " + min);
	}
	
	
	
	

}
