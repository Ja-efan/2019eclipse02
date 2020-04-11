package com.sample3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Split {
	
	private static final String String = null;
	private String name = "������,���翵,����ǥ,���翵,�ڹ�ȣ,������,���翵,������,�ֽ���,�̼���,�ڿ���,�ڹ�ȣ,������,����ȯ,���缺,������,������";
	private String[] nameArray = name.split(",");
	
	public void printName() {
		System.out.println(name);
	}
	
	public void KimLee() {
		
		int numKim = 0;
		int numLee = 0;
		
		for (int i=0; i<nameArray.length; i++) {
			if (nameArray[i].startsWith("��")) {
				numKim += 1;
			}else if(nameArray[i].startsWith("��")) {
				numLee += 1;
			}
		}
		
		System.out.println("�达: " + numKim +"��\n�̾�: " + numLee +"��" );
	}
	
	public void Leejaeyoung() {
		
		int num = 0;
		
		for (int i = 0; i<nameArray.length; i++) {
			if(nameArray[i].equals("���翵")) {
				num +=1 ;
			}
		}
		
		System.out.println("���翵�� �� "+ num + "�� �Դϴ�.");
	}
	
	public void rmRedun() {
		
		ArrayList<String> arr = new ArrayList<String>();
		
		// ArrayList�� �迭 ���� ����
		for(int i = 0; i<nameArray.length; i++) {
			arr.add(nameArray[i]);
		}
		
		// �ߺ��� ������ �ʴ� HashSet�� arr�� ���� -> �ߺ�����
		HashSet<String> arr2 = new HashSet<String>(arr); 
		System.out.println(arr2);
		
		
		
		/*
		ArrayList<String> arr3 = new ArrayList<String>(arr2);
		System.out.println(arr3);
		*/
		
	}
	
	public void sortName() {
		
		ArrayList<String> arr = new ArrayList<String>();
		
		// ArrayList�� �迭 ���� ����
		for(int i = 0; i<nameArray.length; i++) {
			arr.add(nameArray[i]);
		}
		
		// �ߺ��� ������ �ʴ� HashSet�� arr�� ���� -> �ߺ�����
		HashSet<String> arr2 = new HashSet<String>(arr); 
		
		//ArrayList<String> arr3 = new ArrayList<String>(arr2);
		
		List nameList = new ArrayList(arr2);
		
		Collections.sort(nameList);
		
		for (Object s : nameList) {
			System.out.print(s+".");
		
			
		}
	}
}
