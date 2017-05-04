import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartFrame extends JFrame implements ActionListener{
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
		
		start.addActionListener(this);
		highScore.addActionListener(this);
		quit.addActionListener(this);
		
		buttonPan.add(highScore);
		buttonPan.add(start);
		buttonPan.add(quit);
		
		this.add(buttonPan, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getActionCommand() == "Start"){
			System.out.println("Start");
		}
		if (e.getActionCommand() == "High Score"){
			System.out.println("HighScore");
		}
		if (e.getActionCommand() == "Quit"){
			System.out.println("Quit");
		}
	}

	public static void main(String[] args) {
		StartFrame startFrame = new StartFrame();
	}
	
}
