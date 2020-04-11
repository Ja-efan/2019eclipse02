package com.sample3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Split {
	
	private static final String String = null;
	private String name = "이유덕,이재영,권종표,이재영,박민호,강상희,이재영,김지완,최승혁,이성연,박영서,박민호,전경헌,송정환,김재성,이유덕,전경헌";
	private String[] nameArray = name.split(",");
	
	public void printName() {
		System.out.println(name);
	}
	
	public void KimLee() {
		
		int numKim = 0;
		int numLee = 0;
		
		for (int i=0; i<nameArray.length; i++) {
			if (nameArray[i].startsWith("김")) {
				numKim += 1;
			}else if(nameArray[i].startsWith("이")) {
				numLee += 1;
			}
		}
		
		System.out.println("김씨: " + numKim +"명\n이씨: " + numLee +"명" );
	}
	
	public void Leejaeyoung() {
		
		int num = 0;
		
		for (int i = 0; i<nameArray.length; i++) {
			if(nameArray[i].equals("이재영")) {
				num +=1 ;
			}
		}
		
		System.out.println("이재영은 총 "+ num + "명 입니다.");
	}
	
	public void rmRedun() {
		
		ArrayList<String> arr = new ArrayList<String>();
		
		// ArrayList에 배열 원소 삽입
		for(int i = 0; i<nameArray.length; i++) {
			arr.add(nameArray[i]);
		}
		
		// 중복이 허용되지 않는 HashSet에 arr을 삽입 -> 중복제거
		HashSet<String> arr2 = new HashSet<String>(arr); 
		System.out.println(arr2);
		
		
		
		/*
		ArrayList<String> arr3 = new ArrayList<String>(arr2);
		System.out.println(arr3);
		*/
		
	}
	
	public void sortName() {
		
		ArrayList<String> arr = new ArrayList<String>();
		
		// ArrayList에 배열 원소 삽입
		for(int i = 0; i<nameArray.length; i++) {
			arr.add(nameArray[i]);
		}
		
		// 중복이 허용되지 않는 HashSet에 arr을 삽입 -> 중복제거
		HashSet<String> arr2 = new HashSet<String>(arr); 
		
		//ArrayList<String> arr3 = new ArrayList<String>(arr2);
		
		List nameList = new ArrayList(arr2);
		
		Collections.sort(nameList);
		
		for (Object s : nameList) {
			System.out.print(s+".");
		
			
		}
	}
}
