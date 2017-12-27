package taja;

import javax.swing.JFrame;

public class Main extends JFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame myFrame = new JFrame("Java Typing Practice"); // JFrame 객체를 생성한다
		myFrame.add(new Gui()); // JFrame에 Gui JPanel을 추가한다
		myFrame.setSize(800, 596); // JFrame 사이즈 정의
		myFrame.setLocationRelativeTo(null); // JFrame을 모니터의 중앙에 띄우게 한다
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setVisible(true);
	}

}
