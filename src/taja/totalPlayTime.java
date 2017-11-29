package taja;

import javax.swing.JLabel;

public class totalPlayTime extends Thread {
	
	public int gamePlayTime;
	JLabel playTime = new JLabel("0");
	
	public void run() {
		for (gamePlayTime = 0;; gamePlayTime++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			playTime.setText(gamePlayTime + "");
		}
	}
}