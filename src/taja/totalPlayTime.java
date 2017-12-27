package taja;

import javax.swing.JLabel;

public class totalPlayTime extends Thread { // 게임시간을 측정하는 클래스, 쓰레드클래스를 상속받음
	public int gamePlayTime; // 정수형 게임시간 변수
	public JLabel playTime = new JLabel("0"); // 초기값은 0으로 한다

	public void run() {
		for (gamePlayTime = 0;; gamePlayTime++) {
			try {
				Thread.sleep(1000); // 1초에 한번씩 gamePlayTime 변수를 증가시킨다
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			playTime.setText(gamePlayTime + ""); // playTime레이블을 gamePlayTime변수로
													// 설정한다
		}
	}
}
