import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class StudentMain {
	
	public static void main(String[] args) {
		
		ArrayList<Student> list = new ArrayList<Student>();
		
		while(true) {
			String menu = JOptionPane.showInputDialog("�޴��Է� \n 0:����, 1:�л���� , 2:�л�������, 3:���");
			
			if(menu.contentEquals("0")) {
				JOptionPane.showMessageDialog(null, "�����մϴ�.");
				break;
			}else if (menu.equals("1")) {
				Student st1 = new Student();
				String tmpName = JOptionPane.showInputDialog("�̸��Է�: ");
				st1.setName(tmpName);
				String tmpScore = JOptionPane.showInputDialog("�����Է�: ");
				st1.setScore(Integer.parseInt(tmpScore));
				list.add(st1);
				
			}else if (menu.contentEquals("2")) {
				Iterator it = list.iterator();
				while(it.hasNext()) {
					Student tmpstu = (Student)it.next();
					JOptionPane.showMessageDialog(null, "�̸�:"+tmpstu.getName() + "& ����:" + tmpstu.getScore());
				}
			}else if (menu.contentEquals("3")) {
				double total = 0;
				Iterator it = list.iterator();
				while(it.hasNext()) {
					Student tmpstu = (Student)it.next();
					total += tmpstu.getScore();
				}
				JOptionPane.showMessageDialog(null, "���: " + total/list.size());
			}else {
				JOptionPane.showMessageDialog(null, "��Ȯ�� �޴� �Է� \n 0:����, 1:�л����, 2:�л�������, 3:���");
			}
			
		}
	}

}
