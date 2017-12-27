package taja;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Gui extends JPanel implements ActionListener, KeyListener {
	public static JLabel[] arrJlabel = new JLabel[9]; /*
														 * JLabel 배열 생성, 9개의 문제가
														 * 나오므로 9개를 생성한다.
														 * Rain클래서에서 사용해야 하므로
														 * 정적(static) 필드
														 */
	public static JLabel[] lifeMark = new JLabel[3]; /*
														 * 또다른 JLabel 배열, life가
														 * 3개이므로 3개 생성
														 */
	public static int speed = 1000; // 글자가 내려오는 속도를 정적으로 정수형 변수로 선언한다. 초기값은 1000
	private JLabel numLabel, nameLabel, resultName, resultNumber, resultAc, resultTime;
	private JButton startButton, lowButton, highButton, midButton, quitButton; // JButton
																				// 정의
	private JTextField inputText, inputName, inputNum; // JTextField 정의
	private Random myRandom = new Random(); // 랜덤함수 정의
	totalPlayTime total_play_time = new totalPlayTime(); // 시간 타이머 쓰레드 클래스 생성
	Rain data_rain = new Rain(); // 글자가 떨어지게하는 쓰레드 클래스 생성
	WordData word_create = new WordData(); // 문제를 생성하는 클래스 생성
	private float tryCount = 0; // 시도 횟수 변수
	private float correctCount = 0; // 맞은 횟수 정수
	private int correctPercent; // 명중률 변수
	public static ImageIcon icon, buttonIcon, buttonOnclick, lifeIcon, lowIcon, lowIconClick, midIcon, midIconClick,
			highIcon, highIconClick; // ImageIcon 정의
	private String studentName, studentNumber; // 학번과 , 학생이름 변수 String으로 정의

	public Gui() {
		setSize(800, 596); // 패널 사이즈를 800x600으로 지정한다
		setLayout(null); // 위치를 절대값 위치로 지정하기 때문에, 레이아웃을 null로 지정한다

		icon = new ImageIcon("img/background.jpg");
		buttonIcon = new ImageIcon("img/button.png");
		buttonOnclick = new ImageIcon("img/buttonclick.png");
		lifeIcon = new ImageIcon("img/life3.png");
		lowIcon = new ImageIcon("img/low.png");
		lowIconClick = new ImageIcon("img/lowclick.png");
		midIcon = new ImageIcon("img/mid.png");
		midIconClick = new ImageIcon("img/midclick.png");
		highIcon = new ImageIcon("img/high.png");
		highIconClick = new ImageIcon("img/highclick.png");

		startButton = new JButton(buttonIcon);
		startButton.setRolloverIcon(buttonOnclick); // startButton에 마우스를
													// 가져다놓았을때의 아이콘
		startButton.setOpaque(false); // startButton을 보이게 한다
		startButton.setBounds(560, 360, 100, 43); // strartButton의 좌표와,범위 지정
		add(startButton); // Gui JPanel에 startButton을 추가한다.
		startButton.addActionListener(this); // startButton에 ActionListener 추가
		startButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		startButton.setBorderPainted(false);
		startButton.setFocusPainted(false);
		startButton.setContentAreaFilled(false); // startButton을 투명으로 만든다.

		lowButton = new JButton(lowIcon);
		lowButton.setOpaque(false);
		lowButton.setBounds(555, 310, 40, 43);
		add(lowButton);
		lowButton.addActionListener(this);
		lowButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		lowButton.setBorderPainted(false);
		lowButton.setFocusPainted(false);
		lowButton.setContentAreaFilled(false); // 난이도 하 버튼을 추가하고 투명으로 만든다

		midButton = new JButton(midIcon);
		midButton.setOpaque(false);
		midButton.setBounds(590, 310, 40, 43);
		add(midButton);
		midButton.addActionListener(this);
		midButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		midButton.setBorderPainted(false);
		midButton.setFocusPainted(false);
		midButton.setContentAreaFilled(false); // 난이도 중 버튼을 추가하고 투명으로 만든다

		highButton = new JButton(highIcon);
		highButton.setOpaque(false);
		highButton.setBounds(625, 310, 40, 43);
		add(highButton);
		highButton.addActionListener(this);
		highButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		highButton.setBorderPainted(false);
		highButton.setFocusPainted(false);
		highButton.setContentAreaFilled(false); // 난이도 상 버튼을 추가하고 투명으로 만든다

		inputNum = new JTextField(10);
		inputNum.setOpaque(false);
		inputNum.setBounds(360, 295, 180, 35);
		add(inputNum);
		inputNum.setBorder(javax.swing.BorderFactory
				.createEmptyBorder());/* 학번 입력 텍스트필드 추가하고 투명으로 만든다 */

		inputName = new JTextField(10);
		inputName.setOpaque(false);
		inputName.setBounds(360, 370, 180, 35);
		add(inputName);
		inputName.setBorder(javax.swing.BorderFactory
				.createEmptyBorder()); /* 이름 입력 텍스트필드 추가하고 투명으로 만든다 */
		total_play_time.playTime.setBounds(390, 0, 200, 50);
		total_play_time.playTime.setFont(new Font("Dialog", Font.BOLD, 30));
		add(total_play_time.playTime);
		total_play_time.playTime.setVisible(false); // 타이머추가하고, 안보이게 한다
		word_create.create();// word_create클래스에서 단어를 생성하는 create메소드를 실행한다

	}

	@Override
	public void actionPerformed(ActionEvent e) { // 버튼이벤트 정의
		if (e.getSource() == startButton) { // startButton을 클릭하게 되면
			studentName = inputName.getText(); // studentName은 inputName텍스트필드에
												// 입력한 값이 된다.
			studentNumber = inputNum.getText(); // studentNumber은 inputNum텍스트필드에
												// 입력한 값이 된다.
			nameLabel = new JLabel(studentName);
			numLabel = new JLabel(studentNumber);
			firstStart(); // firstStart 메소드 실행

		}

		else if (e.getSource() == lowButton) { // lowButton 버튼을 클릭하게 되면
			lowButton.setIcon(lowIconClick);
			midButton.setIcon(midIcon);
			highButton.setIcon(highIcon);
			speed = 1200; // lowButton아이콘을 lowIconClick아이콘으로 바꾸고, speed를 1200으로
							// 지정한다.
		}

		else if (e.getSource() == midButton) {// midButton 버튼을 클릭하게 되면
			midButton.setIcon(midIconClick);
			lowButton.setIcon(lowIcon);
			highButton.setIcon(highIcon);
			speed = 700; // lowButton으로 바꾸고, speed를 700으로 지정한다.

		}

		else if (e.getSource() == highButton) {// highButton 버튼을 클릭하게 되면
			highButton.setIcon(highIconClick);
			lowButton.setIcon(lowIcon);
			midButton.setIcon(midIcon);
			speed = 200;// highButton으로 바꾸고, speed를 200으로 지정한다.

		} else if (e.getSource() == quitButton) { // quit버튼을 누르면
			System.exit(0); // 프로그램을 종료한다

		}
	}

	@Override
	public void keyPressed(KeyEvent e) { // 키이벤트 정의
		// TODO Auto-generated method stub
		if (e.getKeyCode() == 10) { // 엔터가 눌리면
			tryCount++; // 시도 회수 1 증가
			removeAnswer(); // 정답처리 메소드 실행
			endAnswer(); // 모두 정답후 종료 메소드 실행
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	private void removeAnswer() { // 정답처리 메소드
		for (int i = 0; i < 9; i++) {
			if (inputText.getText().equals(
					word_create.arr.get(i))) { /* 입력된 값이 어레이리스트의 어느 값과 일치하면 */
				arrJlabel[i].setVisible(false);
				correctCount++;
			} // 그 JLabel을 안보이게 하고, correctCount(맞은 수)를 1 증가시킨다
		}
		inputText.setText("");
		/*
		 * JLabel Visible False 후, TextLabel을 빈칸으로 두어서 바로 입력할수 있게 한다.
		 */

	}

	private void endAnswer() { // 게임이 끝났을 때의 메소드(정답을 모두 맞췄을 때)
		if (arrJlabel[0].isVisible() == false && arrJlabel[1].isVisible() == false && arrJlabel[2].isVisible() == false
				&& arrJlabel[3].isVisible() == false && arrJlabel[4].isVisible() == false
				&& arrJlabel[5].isVisible() == false && arrJlabel[6].isVisible() == false
				&& arrJlabel[7].isVisible() == false && arrJlabel[8].isVisible() == false) { // 모든
			/* JLabel이 보이지 않으면(즉,완료되면) */

			for (int i = 0; i < arrJlabel.length; i++) {
				arrJlabel[i].setVisible(true);
				arrJlabel[i].setFont(new Font("굴림", Font.BOLD, 12));
				arrJlabel[i].setForeground(Color.BLUE);
				;
				arrJlabel[i].setLocation(i * 90, 0);
			} // 문제로 나왔던 단어들을 게임상단에 표시해 준다

			data_rain.stop(); // 산성비 쓰레드 멈춤
			total_play_time.stop(); // 시간 타이머 쓰레드 멈춤.
			correctPercent = Math.round((correctCount / tryCount)
					* 100); /*
							 * 명중률을 계산함. 맞은회수/총횟수 * 100해서 소수점 버림
							 */
			icon = new ImageIcon("img/background3.jpg"); // 배경을 background3으로
															// 바꾼다

			resultNumber = new JLabel(studentNumber);
			resultNumber.setBounds(360, 156, 200, 100);
			resultNumber.setFont(new Font("굴림", Font.BOLD, 22));
			resultNumber.setForeground(Color.WHITE);
			add(resultNumber); // 결과창에 학번레이블을 생성해서 추가한다

			resultName = new JLabel(studentName);
			resultName.setBounds(360, 206, 200, 100);
			resultName.setFont(new Font("굴림", Font.BOLD, 22));
			resultName.setForeground(Color.WHITE);
			add(resultName);// 결과창에 이름레이블을 생성해서 추가한다

			resultTime = new JLabel((Integer.toString(total_play_time.gamePlayTime) + "초"));
			/*
			 * 게임시간을 String으로 바꾸어서 JLabel을 만든다
			 */
			resultTime.setBounds(360, 256, 200, 100);
			resultTime.setFont(new Font("굴림", Font.BOLD, 22));
			resultTime.setForeground(Color.WHITE);
			add(resultTime); // 결과창에 시간 레이블을 생성해서 추가한다

			resultAc = new JLabel(Integer.toString(correctPercent) + "%");
			/*
			 * 명중률을 String으로 바꾸어서 JLabel을 만든다.
			 */
			resultAc.setBounds(360, 306, 200, 100);
			resultAc.setFont(new Font("굴림", Font.BOLD, 22));
			resultAc.setForeground(Color.WHITE);
			add(resultAc); // 결과창에 명중률 레이블을 생성해서 추가한다

			quitButton = new JButton();
			quitButton.setOpaque(false);
			quitButton.setBounds(360, 400, 80, 30);
			add(quitButton);
			quitButton.addActionListener(this);
			quitButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			quitButton.setBorderPainted(false);
			quitButton.setFocusPainted(false);
			quitButton.setContentAreaFilled(false); // 종료하기 버튼을 만들어서 투명으로 설정한후
													// ActionListener 추가한다
			for (int j = 0; j < lifeMark.length; j++) {
				lifeMark[j].setVisible(false);
			} // 생명을 보이지 않게 한다
		}
	}

	private void firstStart() { // 시작하기 버튼을 눌렀을 때의 메소드(게임시작)
		inputText = new JTextField(2);
		inputText.addKeyListener(this); // 텍스트필드에 키 이벤트 추가(엔터)
		inputText.setBounds(360, 493, 150, 58);
		inputText.setOpaque(false);
		inputText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		add(inputText); // 답을 입력하는 텍스트필드를 투명으로 만들어서 추가한다

		for (int i = 0; i < lifeMark.length; i++) {
			lifeMark[i] = new JLabel(lifeIcon);
			lifeMark[i].setOpaque(false);
			lifeMark[i].setBounds(0 + (i * 80), 460, 80, 80);
			add(lifeMark[i]);
			lifeMark[i].setBorder(javax.swing.BorderFactory.createEmptyBorder());
			lifeMark[i].setVisible(true); // 생명을 보여주는 JLabel을 만들어서 추가한다
		}

		add(numLabel); // 학번 JLabel을 추가한다
		add(nameLabel); // 학생이름 JLabel을 추가한다
		numLabel.setBounds(105, 4, 100, 60);
		nameLabel.setBounds(110, 34, 100, 60);

		startButton.setVisible(false); // 시작 버튼 안보이게
		inputName.setVisible(false);
		inputNum.setVisible(false);
		inputText.setVisible(true); // 답 입력창 보이게 함
		lowButton.setVisible(false);
		midButton.setVisible(false);
		highButton.setVisible(false);
		icon = new ImageIcon("img/background2.jpg"); // 배경 이미지를 두번째 이미지로 바꿈
		
		word_create.shuffle();// word_create클래스에서 단어순서를 섞는 shuffle메소드를 실행한다.
		for (int i = 0; i < arrJlabel.length; i++) {
			arrJlabel[i] = new JLabel(
					word_create.arr.get(i)); /*
												 * JLabel을 차례대로 랜덥값이 삽입된 어레이리스트
												 * 키값으로 하는 해쉬맵의 내용을 이름으로 정의
												 */

			arrJlabel[i].setBounds(0, 0, 150, 20); // arrJLabel의 범위 지정
			arrJlabel[i].setFont(new Font("굴림", Font.BOLD, 15)); // arrJLabel의
																	// 폰트 지정
			arrJlabel[i].setLocation(i * 85, myRandom.nextInt(250) + 10); // arrJLabel의
																			// 위치를
																			// 지정한다.
			add(arrJlabel[i]); // arrJLabel을 패널에 추가한다

		}

		for (int j = 0; j < lifeMark.length; j++) {
			lifeMark[j].setVisible(true); // 생명현황을 보여준다
		}
		total_play_time.playTime.setVisible(true); // 시간타이머를 보이게 함
		total_play_time.start(); // 시간 타이머 쓰레드 시작
		data_rain.start(); // 산성비 쓰레드 시작

	}

	public void paintComponent(Graphics g) {
		g.drawImage(icon.getImage(), 0, 0, null); // 0,0좌표부터 이미지를 뿌림
		setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
		super.paintComponent(g);
	}

}
