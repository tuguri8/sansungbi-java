package taja;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Gui extends JPanel implements ActionListener, KeyListener {
	private Dimension dim = new Dimension(400, 400);
	public JLabel[] arrJlabel = new JLabel[9]; /*
												 * JLabel 배열 생성, 일단 데이터 5개 입력하므로
												 * 5개.
												 */
	public JButton inputButton; // JButton 정의
	public JButton startButton;
	public JTextField inputText; // JTextField 정의
	public HashMap<Integer, String> wordData = new HashMap<Integer, String>();
	// 해쉬맵 정의,key를 정수타입,들어갈 내용을 String타입으로 정의
	public List<Integer> arr = new ArrayList<>(); // 어레이리스트 arr 정의
	Random myRandom = new Random();
	totalPlayTime total_play_time = new totalPlayTime();
	public int tryCount = 0;
	public int correctCount = 0;
	
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
		//JPanel myPanel = new JPanel();
		setLayout(null); // myPanel에 정의된 레이블을 3x3그리드레이아웃으로 설정.
		
		for (int i = 0; i < arrJlabel.length; i++) {
			arrJlabel[i] = new JLabel(
					wordData.get(arr.get(i))); /*
												 * JLabel을 차례대로 랜덥값이 삽입된 어레이리스트
												 * 키값으로 하는 해쉬맵의 내용을 이름으로 정의
												 */
			arrJlabel[i].setBounds(myRandom.nextInt(350), myRandom.nextInt(150) + 50 , 60, 50);
			add(arrJlabel[i]);
		}
		
		startButton = new JButton("시작하기");
		startButton.addActionListener(this);
		startButton.setBounds(200, 200, 80, 80);
		add(startButton);
		total_play_time.playTime.setBounds(170, 0, 50, 20);
		add(total_play_time.playTime);
		inputButton = new JButton("입력");
		inputText = new JTextField(10);
		inputText.addKeyListener(this); // 텍스트필드에 키 이벤트 추가(엔터)
		inputButton.addActionListener(this);// 버튼에 이벤트 추가
		inputButton.setBounds(305, 290, 70, 49);
		inputText.setBounds(0, 290, 300, 50);
		add(inputButton);
		add(inputText);
		stop();

	}

	@Override
	public void actionPerformed(ActionEvent e) { // 버튼이벤트 정의
		if (e.getSource() == inputButton) { // 버튼이 눌리면

			removeAnswer();
			endAnswer();

		} else if (e.getSource() == startButton) {
			start();

		}
	}

	@Override
	public void keyPressed(KeyEvent e) { // 키이벤트 정의
		// TODO Auto-generated method stub
		if (e.getKeyCode() == 10) // 엔터가 눌리면
			tryCount++;
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
			if (inputText.getText().equals(
					wordData.get(arr.get(i)))) /* 입력된 값이 해쉬맵의 어느 값과 일치하면 */
				arrJlabel[i].setVisible(false); // 그 JLabel을 안보이게 한다
		}
		correctCount++;
		inputText.setText(""); /*
								 * JLabel Visible False 후, TextLabel을 빈칸으로 두어서 바로
								 * 입력할수 있게 한다.
								 */
		
	}

	public void endAnswer() { // 게임 끝 메소드
		if (arrJlabel[0].isVisible() == false && arrJlabel[1].isVisible() == false && arrJlabel[2].isVisible() == false
				&& arrJlabel[3].isVisible() == false && arrJlabel[4].isVisible() == false
				&& arrJlabel[5].isVisible() == false && arrJlabel[6].isVisible() == false
				&& arrJlabel[7].isVisible() == false && arrJlabel[8].isVisible() == false) { // 모든
			/* JLabel이 보이지 않으면(즉,완료되면) */
			total_play_time.stop();
			JOptionPane.showMessageDialog(inputButton, "기록  : " + total_play_time.gamePlayTime + "명중률 : " + (correctCount % tryCount), "끝",
					JOptionPane.INFORMATION_MESSAGE);
			// 메세지로 끝을 알리고, 기록을 출력한다
			// getContentPane().removeAll();
			System.exit(0);// 프로그램종료

		}
	}

	public void start() {
		total_play_time.playTime.setVisible(true);
		total_play_time.start();
		for (int i = 0; i < 9; i++) {
			arrJlabel[i].setVisible(true);
		}
		inputButton.setVisible(true);
		inputText.setVisible(true);
		startButton.setVisible(false);

	}

	public void stop() {
		for (int i = 0; i < 9; i++) {
			arrJlabel[i].setVisible(false);
		}
		inputButton.setVisible(false);
		inputText.setVisible(false);
		total_play_time.playTime.setVisible(false);
	}
}
