package taja;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Main extends JFrame{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame myFrame = new JFrame();
		Dimension dim = new Dimension(400, 400);
		myFrame.add(new Gui());
		myFrame.setPreferredSize(dim); // 프레임을 dim크기로 정의
		myFrame.pack();
		myFrame.setVisible(true);
	}
	

}


