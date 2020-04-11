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
			String menu = JOptionPane.showInputDialog("상점관리 프로그램\n메뉴를 입력하세요\n"
					+ "0:종료, 1:상점등록, 2:상점삭제, 3:상점정보출력, 4:전체상점매출출력");
			
			int menui = Integer.parseInt(menu);
			
			if(menui == 0) { // 종료
				
				JOptionPane.showMessageDialog(null, "프로그램을 종료합니다.");
				break;
				
			}else if(menui == 1) { // 상점등록
				
				Market mk = new Market(); // 마켓 객체 생성
				
				// 키 입력
				String tmpKey = JOptionPane.showInputDialog("등록할 상점의 키입력: ");
				
				// 키값이 중복되지 않을때까지 재입력
				while(MarketMap.containsKey(tmpKey)) { 
					
					tmpKey = JOptionPane.showInputDialog("이미 있는 상점 키 입니다.\n등록할 상점의 키 재입력: ");
					
				}
				mk.setKey(tmpKey);
				
				
				// 이름입력
				String tmpName = JOptionPane.showInputDialog("등록할 상점의 이름입력: ");
				mk.setName(tmpName);
				
				// 주소입력
				String tmpAddress = JOptionPane.showInputDialog("등록할 상점의 주소 입력: ");
				mk.setAddress(tmpAddress);
				
				// 매출입력
				int tmpSales = Integer.parseInt(JOptionPane.showInputDialog("상점 매출입력(정수만입력,단위:만원): "));
				mk.setSales(tmpSales);
				
				
				MarketMap.put(tmpKey,mk); //key값과 상점정보를 hashmap에 저장
				
				// 입력한정보 출력
				JOptionPane.showMessageDialog(null, 
						"입력한 정보\n이름: " + mk.getName() + " 키: " + mk.getKey() + " 주소: "+ mk.getAddress() + " 매출: "+mk.getSales());
			
				
			}else if(menui == 2) { // 상점삭제
				
				String delKey =  JOptionPane.showInputDialog("삭제할 상점의 키 입력: ");
				if(MarketMap.containsKey(delKey)) {
					JOptionPane.showMessageDialog(null, "삭제할 상점 키값: "+ delKey);
					MarketMap.remove(delKey);
				}else {
					JOptionPane.showMessageDialog(null, "존재하지 않는 키값입니다.");
				}
				
				
			}else if(menui == 3) { // 특정상점의 정보출력
				String tmpKey = JOptionPane.showInputDialog("정보를 출력할 상점의 키값: ");
				Market tmpmk = MarketMap.get(tmpKey);
				if(MarketMap.containsKey(tmpKey)) {
					JOptionPane.showMessageDialog(null, "입력한 상점의 정보\n이름: "+tmpmk.getName()+" 키: "+tmpmk.getKey()+
							" 주소: "+tmpmk.getAddress() +"매출: "+tmpmk.getSales());
				}else {
					JOptionPane.showMessageDialog(null, "존재하지 않는 키값입니다.");
				}
				
				
			}else if(menui == 4) {// 전체상점의 매출출력
				
				double total = 0;
				
				Set<String> Keyset = MarketMap.keySet();
				Iterator<String> it = Keyset.iterator();
				
				while(it.hasNext()) {
					String key = (String) it.next();
					Market tmpmk = MarketMap.get(key);
					total += tmpmk.getSales();
				}
				JOptionPane.showMessageDialog(null, "전체상점의 매출액: " + total+"만원");
				
			}else {
				JOptionPane.showMessageDialog(null, "정확한 메뉴 입력 \n 0:종료, 1:상점등록, 2:상점삭제, 3:상점정보출력, 4:전체상점 매출출력");
			}
		}
		
		
	}
}