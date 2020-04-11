package FileClass;

import java.io.File;

public class FileClassEx {
	
	public static void listDirectory(File dir) {
		
		System.out.println("-----" + dir.getPath() + "�� SUB LIST------");
		File[] subFiles = dir.listFiles();
		
		for (int i=0; i<subFiles.length; i++) {
			File f = subFiles[i];
			long t = f.lastModified();
			System.out.print(f.getName());
			System.out.print("\t����ũ��: " + f.length());
			System.out.printf("\t�����ѽð�: %tb %td %ta %tT\n", t,t,t,t);
		}
		
	}
	
	public static void main(String[]args) {
		
		File f1 = new File("c:\\Temp\\Temp2");
		System.out.println(f1.getPath() + ", " + f1.getParent() + ", " + f1.getName());
		String res = "";
		if(f1.isFile()) res = "����";
		else if(f1.isDirectory()) res = "���丮";
		System.out.println(f1.getPath() + "�� " + res + "�Դϴ�.");
		
		File f2 = new File("c:\\Temp\\Temp2");
		if(!f2.exists()) {
			f2.mkdir();
		}
		listDirectory(new File("c:\\Temp"));
		f2.renameTo(new File("c:\\Temp\\Temp100"));
		listDirectory(new File("c:\\Temp"));
	}

}
