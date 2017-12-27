package taja;

import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JLabel;

abstract public class Word { // 문제애 관한 추상 클래스
	public ArrayList<String> arr = new ArrayList<>(); // 어레이리스트 arr 정의

	abstract void create(); // create 추상 메소드

	abstract void shuffle();

}

class WordData extends Word { // 문제정보에 관한 클래스, 추상크래스 Word를 상속받는다

	@Override
	public void create() { // 문제를 생성하는 create메소드 추상메소드 create를 오버라이딩한다
		// TODO Auto-generated method stub
		try {
			Scanner inputStream = new Scanner(
					new File("word.txt")); /*
											 * inputStream 객체 생성, 파일이름은
											 * word.txt이다 , word.txt파일을 읽음
											 */
			while (inputStream.hasNextLine()) // word.txt에 더이상 단어가 없을 때 까지 읽음
				this.arr.add(inputStream.nextLine()); // 어레이리스트 arr에 각 단어들을 추가함
		} catch (FileNotFoundException e) { // 파일을 찾을 수 없을 때
			System.out.println("File Not Found"); // File Not Found를 출력
		}

	}

	@Override
	void shuffle() {
		// TODO Auto-generated method stub
		Collections.shuffle(
				this.arr); /*
							 * 어레이리스트 arr의 값들을 무작위로 섞는다(출제 문제를 매번 다르게 해야하기 때문)
							 */
	}
}
