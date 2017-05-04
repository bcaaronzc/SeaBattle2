import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartFrame extends JFrame{
	JButton start, highScore, quit;
	
	public StartFrame(){
		this.setBounds(200, 200, 600, 400);
		this.setBackground(new Color(255, 255, 255));
		this.getContentPane().setBackground(Color.white);
		
		JPanel buttonPan = new JPanel();
		buttonPan.setLayout(new GridLayout(1, 3));
		
		start = new JButton("Start");
		highScore = new JButton("High Score");
		quit = new JButton("Quit");
		
		buttonPan.add(highScore);
		buttonPan.add(start);
		buttonPan.add(quit);
		
		this.add(buttonPan, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}

	public static void main(String[] args) {
		StartFrame startFrame = new StartFrame();
	}
	
}
