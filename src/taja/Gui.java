package taja;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Gui extends JFrame implements ActionListener, KeyListener {
	private Dimension dim = new Dimension(400, 200);
	private GridLayout firstGrid = new GridLayout(3, 3); // 3x3 그리드레이아웃 정의
	private GridLayout secondGrid = new GridLayout(1, 2); // 1x2 그리드레이아웃 정의
	private JFrame frame = new JFrame("개발자타자연습"); // JFrame 정의
	public JLabel[] arrJ = new JLabel[9]; // JLabel 배열 생성, 일단 데이터 5개 입력하므로 5개.
	public JButton start; // JButton 정의
	public JTextField inputtext; // JTextField 정의
	public HashMap<Integer, String> wordData = new HashMap<Integer, String>(); // 해쉬맵
																				// 정의,
																				// key를
																				// 정수타입,
																				// 들어갈
																				// 내용을
																				// String타입으로
																				// 정의

	public List<Integer> arr = new ArrayList<>(); // 어레이리스트 arr 정의

	Record record1 = new Record(); // 스톱워치 클래스 생성

	public Gui() {
		wordData.put(1, "일가천"); // 해쉬맵 데이터 삽입
		wordData.put(2, "이가천"); // 해쉬맵 데이터 삽입
		wordData.put(3, "삼가천"); // 해쉬맵 데이터 삽입
		wordData.put(4, "사가천"); // 해쉬맵 데이터 삽입
		wordData.put(5, "오가천"); // 해쉬맵 데이터 삽입
		wordData.put(6, "육가천"); // 해쉬맵 데이터 삽입
		wordData.put(7, "칠가천"); // 해쉬맵 데이터 삽입
		wordData.put(8, "팔가천"); // 해쉬맵 데이터 삽입
		wordData.put(9, "아홉가천"); // 해쉬맵 데이터 삽입
		wordData.put(10, "십가천"); // 해쉬맵 데이터 삽입
		wordData.put(11, "십일가천"); // 해쉬맵 데이터 삽입
		wordData.put(12, "십이가천"); // 해쉬맵 데이터 삽입

		for (int i = 1; i < 13; i++) {
			arr.add(i);// 어레이리스트에 1부터~12까지 입력
		}

		Collections.shuffle(arr); // 어레이리스트 값들을 셔플함.

		JPanel panel1 = new JPanel();
		panel1.setLayout(firstGrid); // panel1에 정의된 레이블을 3x3그리드레이아웃으로 설정.
		for (int i = 0; i < arrJ.length; i++) {
			arrJ[i] = new JLabel(wordData.get(arr.get(i))); // JLabel을 차례대로 랜덥값이
															// 삽입된 어레이리스트 키값으로
															// 하는
															// 해쉬맵의 내용을 이름으로 정의
			panel1.add(arrJ[i]);
		}

		JPanel panel3 = new JPanel();
		start = new JButton("입력");
		inputtext = new JTextField(1);
		inputtext.addKeyListener(this); // 텍스트필드에 키 이벤트 추가(엔터)
		start.addActionListener(this);// 버튼에 이벤트 추가
		panel3.setLayout(secondGrid); // panel3에 1x2 그리드레이아웃 설정
		panel3.add(inputtext);
		panel3.add(start);

		JPanel panel4 = new JPanel();
		panel4.add(panel1);
		panel4.add(panel3);
		panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS)); // panel4에
																	// 패널1,3생성해서
																	// Y축으로
																	// 박스레이아웃

		frame.add(panel4);
		frame.setPreferredSize(dim); // 프레임을 dim크기로 정의
		frame.pack();
		frame.setVisible(true);
		record1.run(); // 스톱워치 시작

	}

	@Override
	public void actionPerformed(ActionEvent e) { // 버튼이벤트 정의
		if (e.getSource() == start) { // 버튼이 눌리면
			removeAnswer();
			endAnswer();

		}
	}

	@Override
	public void keyPressed(KeyEvent e) { // 키이벤트 정의
		// TODO Auto-generated method stub
		if (e.getKeyCode() == 10) // 엔터가 눌리면
			removeAnswer();
		endAnswer();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void removeAnswer() { // 정답처리 메소드
		for (int i = 0; i < 9; i++) {
			if (inputtext.getText().equals(wordData.get(arr.get(i)))) // 입력된 값이
																		// 해쉬맵의
																		// 어느 값과
																		// 일치하면
				arrJ[i].setVisible(false); // 그 JLabel을 안보이게 한다
		}
		inputtext.setText(""); // JLabel Visible False 후, TextLabel을 빈칸으로 두어서 바로
								// 입력할수 있게 한다.
	}

	public void endAnswer() { // 게임 끝 메소드
		if (arrJ[0].isVisible() == false && arrJ[1].isVisible() == false && arrJ[2].isVisible() == false
				&& arrJ[3].isVisible() == false && arrJ[4].isVisible() == false && arrJ[5].isVisible() == false
				&& arrJ[6].isVisible() == false && arrJ[7].isVisible() == false && arrJ[8].isVisible() == false) { // 모든
																													// JLabel이
																													// 보이지
																													// 않으면(즉,
																													// 완료되면)
			record1.exit(); // 스톱워치 종료
			JOptionPane.showMessageDialog(start, "기록  : " + record1.timerBuffer, "끝", JOptionPane.INFORMATION_MESSAGE);
			// 메세지로 끝을 알리고, 기록을 출력한다
			System.exit(0);// 프로그램종료

		}
	}

}
