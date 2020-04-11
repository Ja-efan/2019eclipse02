import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class StudentMain {
	
	public static void main(String[] args) {
		
		ArrayList<Student> list = new ArrayList<Student>();
		
		while(true) {
			String menu = JOptionPane.showInputDialog("메뉴입력 \n 0:종료, 1:학생등록 , 2:학생목록출력, 3:평균");
			
			if(menu.contentEquals("0")) {
				JOptionPane.showMessageDialog(null, "종료합니다.");
				break;
			}else if (menu.equals("1")) {
				Student st1 = new Student();
				String tmpName = JOptionPane.showInputDialog("이름입력: ");
				st1.setName(tmpName);
				String tmpScore = JOptionPane.showInputDialog("점수입력: ");
				st1.setScore(Integer.parseInt(tmpScore));
				list.add(st1);
				
			}else if (menu.contentEquals("2")) {
				Iterator it = list.iterator();
				while(it.hasNext()) {
					Student tmpstu = (Student)it.next();
					JOptionPane.showMessageDialog(null, "이름:"+tmpstu.getName() + "& 점수:" + tmpstu.getScore());
				}
			}else if (menu.contentEquals("3")) {
				double total = 0;
				Iterator it = list.iterator();
				while(it.hasNext()) {
					Student tmpstu = (Student)it.next();
					total += tmpstu.getScore();
				}
				JOptionPane.showMessageDialog(null, "평균: " + total/list.size());
			}else {
				JOptionPane.showMessageDialog(null, "정확한 메뉴 입력 \n 0:종료, 1:학생등록, 2:학생목록출력, 3:평균");
			}
			
		}
	}

}
