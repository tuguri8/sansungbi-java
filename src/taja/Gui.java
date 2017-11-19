package taja;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Gui extends JFrame{
	Dimension dim = new Dimension(1000,500);
	Dimension dim2 = new Dimension(200,20);
	public JFrame frame = new JFrame("개발자타자연습");
	public JLabel[] arrJ = new JLabel[5];
	public JButton start;
	public JTextField inputtext;
	public HashMap<Integer, String> newHash = new HashMap<Integer, String>();
	
	public int[] arr = new int[5];
	Random random = new Random();
	Record record1 = new Record();
	Gui(){
		newHash.put(1, "일가천");
		newHash.put(2, "이가천");
		newHash.put(3, "삼가천");
		newHash.put(4, "사가천");
		newHash.put(5, "오가천");
		for(int i = 0;i<arr.length;i++){
		arr[i] = random.nextInt(4)+1;
		}
		frame.setPreferredSize(dim);
		ButtonListener listner = new ButtonListener();
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1,BoxLayout.X_AXIS));
		for(int i = 0; i<arrJ.length; i++){
		arrJ[i] = new JLabel(newHash.get(arr[i]));
		panel1.add(arrJ[i]);
		}

		
		JPanel panel3 = new JPanel();
		start = new JButton("입력");
		inputtext = new JTextField(1);
		start.addActionListener(listner);
		panel3.setPreferredSize(dim2);
		panel3.setLayout(new BoxLayout(panel3,BoxLayout.X_AXIS));
		panel3.add(inputtext, BorderLayout.WEST);
		panel3.add(start, BorderLayout.EAST);
		
		JPanel panel4 = new JPanel();
		panel4.add(panel1);
		panel4.add(panel3);
		panel4.setLayout(new BoxLayout(panel4,BoxLayout.Y_AXIS));
		
		frame.add(panel4);
		frame.pack();
		frame.setVisible(true);
		record1.run();
	
		
		
	}
	
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == start){
				
				for(int i = 0; i < 5; i++){
				if(inputtext.getText().equals(newHash.get(arr[i])))
					arrJ[i].setVisible(false);
				}
				if(arrJ[0].isVisible() == false && arrJ[1].isVisible() == false
						&& arrJ[2].isVisible() == false && arrJ[3].isVisible() == false
						&& arrJ[4].isVisible() == false){
					record1.exit();
					JOptionPane.showMessageDialog(start, "끝"  + "기록은 " +record1.timerBuffer, "끝", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		}
	}
		
}
