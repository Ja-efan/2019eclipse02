package com.sample4;

public class CountNum {
	
	//private int num;
	String[] strarr;
	String[] splitarr;
	int[] intarr;
	int count1, count2, count3, count4, count5, count6, count7, count8, count9, count0 = 0;
	
	/*
	public void setIntarr() {
		
		for (int i = 1 ; i <= 1000 ; i++) {
			intarr[i-1] = i; // 1부터 1000까지 배열 intarr에 저장 
		}
	}
	*/
	
	public void setStrarr() {
		
		for (int i = 1; i <= 1000 ; i++) {
			strarr[i-1] = Integer.toString(i); // intarr[i]번째 원소를 String으로 형변환 후 배열 strarr에 저장
		}
	}
	
	public void countNum() {
		
		for(int i = 1; i <= 1000; i++) {
			for(int k = 0; k < strarr[i-1].length(); k++) {
				splitarr = strarr[i-1].split(""); // strarr의 배열저장값을 split해서 배열 splitarr에 저장 
				for(int j = 0; j < splitarr[j].length(); j++) {
					switch(Integer.parseInt(splitarr[j])) {
					case 0 : count0++;
					case 1 : count1++;
					case 2 : count2++;
					case 3 : count3++;
					case 4 : count4++;
					case 5 : count5++;
					case 6 : count6++;
					case 7 : count7++;
					case 8 : count8++;
					case 9 : count9++;
					}
					/*
					if(splitarr[j].equals("0")) {
						count0++;
					}else if(splitarr[j].equals("1")) {
						count1++;
					}else if(splitarr[j].equals("2")) {
						count2++;
					}else if(splitarr[j].equals("3")) {
						count3++;
					}else if(splitarr[j].equals("4")) {
						count4++;
					}else if(splitarr[j].equals("5")) {
						count5++;
					}else if(splitarr[j].equals("6")) {
						count6++;
					}else if(splitarr[j].equals("7")) {
						count7++;
					}else if()
					*/
				}
			}
		}
		
	}
	
	public void printCount() {
		System.out.println("0: "+count0);
		System.out.println("1: "+count1);
		System.out.println("2: "+count2);
		System.out.println("3: "+count3);
		System.out.println("4: "+count4);
		System.out.println("5: "+count5);
		System.out.println("6: "+count6);
		System.out.println("7: "+count7);
		System.out.println("8: "+count8);
		System.out.println("9: "+count9);
		
	}
	

}
