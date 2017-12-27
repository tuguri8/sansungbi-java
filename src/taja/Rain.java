package taja;

import javax.swing.JOptionPane;

public class Rain extends Thread { // 산성비 메소드 쓰레드를 상속받는다.

	public int life = 3; // 초기 라이프 값 = 3

	public void run() {
		for (int i = 0; i < 100; i++) {
			try {
				for (int j = 0; j < Gui.arrJlabel.length; j++) {
					int x = Gui.arrJlabel[j].getX(); // Gui의 arrJLabel의 x좌표값을
														// x변수에 저장한다
					int y = Gui.arrJlabel[j].getY();// Gui의 arrJLabel의 y좌표값을
													// y변수에 저장한다
					y += 10; // 좌표를 10씩 증가시킨다
					Gui.arrJlabel[j].setLocation(x, y); // arrJLabel의 위치를 다시
														// 지정한다
					if (Gui.arrJlabel[j].isVisible() && Gui.arrJlabel[j].getY() > 400) {
						Gui.arrJlabel[j].setVisible(false); // 만약 arrJlabe의 Y좌표가
															// 400이상이 되면(바다에
															// 진입하면)
						life -= 1; // 라이프를 1감소시킨다
						switch (life) { // 라이프의 switch case문
						case 2: // life가 2일 때
							Gui.lifeMark[2].setVisible(false); // lifeMark 레이블
																// 하나를 안보이게 한다
							break;
						case 1:
							Gui.lifeMark[1].setVisible(false); // lifeMark 레이블
																// 하나를 안보이게 한다
							break;
						case 0:
							Gui.lifeMark[0].setVisible(false); // lifeMark 레이블
																// 하나를 안보이게 한다
							JOptionPane.showMessageDialog(Gui.lifeMark[0], "Game Over", "Game Over",
									JOptionPane.INFORMATION_MESSAGE); /*
																		 * 라이프가
																		 * 0이
																		 * 되었으므로
																		 * Game
																		 * Over라는
																		 * 메세지를
																		 * 출력한다
																		 */
							System.exit(0); // 프로그램을 종료시킨다
							break;

						}
					}

				}
				Thread.sleep(Gui.speed); // Gui의 speed변수만큼 떨어지는 속도를 지정한다
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
