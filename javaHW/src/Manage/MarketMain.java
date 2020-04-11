package Manage;

import java.util.HashMap;
import java.util.*; // java.util.Iterator

import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

public class MarketMain {
	
	public static void main(String[] args) {
		
		Map<String, Market> MarketMap = new HashMap<String, Market>();
		
		while(true) {
			String menu = JOptionPane.showInputDialog("�������� ���α׷�\n�޴��� �Է��ϼ���\n"
					+ "0:����, 1:�������, 2:��������, 3:�����������, 4:��ü�����������");
			
			int menui = Integer.parseInt(menu);
			
			if(menui == 0) { // ����
				
				JOptionPane.showMessageDialog(null, "���α׷��� �����մϴ�.");
				break;
				
			}else if(menui == 1) { // �������
				
				Market mk = new Market(); // ���� ��ü ����
				
				// Ű �Է�
				String tmpKey = JOptionPane.showInputDialog("����� ������ Ű�Է�: ");
				
				// Ű���� �ߺ����� ���������� ���Է�
				while(MarketMap.containsKey(tmpKey)) { 
					
					tmpKey = JOptionPane.showInputDialog("�̹� �ִ� ���� Ű �Դϴ�.\n����� ������ Ű ���Է�: ");
					
				}
				mk.setKey(tmpKey);
				
				
				// �̸��Է�
				String tmpName = JOptionPane.showInputDialog("����� ������ �̸��Է�: ");
				mk.setName(tmpName);
				
				// �ּ��Է�
				String tmpAddress = JOptionPane.showInputDialog("����� ������ �ּ� �Է�: ");
				mk.setAddress(tmpAddress);
				
				// �����Է�
				int tmpSales = Integer.parseInt(JOptionPane.showInputDialog("���� �����Է�(�������Է�,����:����): "));
				mk.setSales(tmpSales);
				
				
				MarketMap.put(tmpKey,mk); //key���� ���������� hashmap�� ����
				
				// �Է������� ���
				JOptionPane.showMessageDialog(null, 
						"�Է��� ����\n�̸�: " + mk.getName() + " Ű: " + mk.getKey() + " �ּ�: "+ mk.getAddress() + " ����: "+mk.getSales());
			
				
			}else if(menui == 2) { // ��������
				
				String delKey =  JOptionPane.showInputDialog("������ ������ Ű �Է�: ");
				if(MarketMap.containsKey(delKey)) {
					JOptionPane.showMessageDialog(null, "������ ���� Ű��: "+ delKey);
					MarketMap.remove(delKey);
				}else {
					JOptionPane.showMessageDialog(null, "�������� �ʴ� Ű���Դϴ�.");
				}
				
				
			}else if(menui == 3) { // Ư�������� �������
				String tmpKey = JOptionPane.showInputDialog("������ ����� ������ Ű��: ");
				Market tmpmk = MarketMap.get(tmpKey);
				if(MarketMap.containsKey(tmpKey)) {
					JOptionPane.showMessageDialog(null, "�Է��� ������ ����\n�̸�: "+tmpmk.getName()+" Ű: "+tmpmk.getKey()+
							" �ּ�: "+tmpmk.getAddress() +"����: "+tmpmk.getSales());
				}else {
					JOptionPane.showMessageDialog(null, "�������� �ʴ� Ű���Դϴ�.");
				}
				
				
			}else if(menui == 4) {// ��ü������ �������
				
				double total = 0;
				
				Set<String> Keyset = MarketMap.keySet();
				Iterator<String> it = Keyset.iterator();
				
				while(it.hasNext()) {
					String key = (String) it.next();
					Market tmpmk = MarketMap.get(key);
					total += tmpmk.getSales();
				}
				JOptionPane.showMessageDialog(null, "��ü������ �����: " + total+"����");
				
			}else {
				JOptionPane.showMessageDialog(null, "��Ȯ�� �޴� �Է� \n 0:����, 1:�������, 2:��������, 3:�����������, 4:��ü���� �������");
			}
		}
		
		
	}
}