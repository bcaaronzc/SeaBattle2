import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartFrame extends JFrame implements ActionListener{
	JButton start, highScore, quit;
	
	public StartFrame(){
		this.setBounds(200, 200, 860, 538);
		this.setBackground(new Color(255, 255, 255));
		this.getContentPane().setBackground(Color.white);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		ImageIcon background = new ImageIcon("src/Image/background.jpg");
		JLabel backgroundLabel = new JLabel();
		backgroundLabel.setIcon(background);
		this.add(backgroundLabel, BorderLayout.CENTER);
		
		JPanel buttonPan = new JPanel();
		buttonPan.setLayout(new GridLayout(1, 3));
		
		start = new JButton("Start");
		highScore = new JButton("High Score");
		quit = new JButton("Quit");
		
		start.addActionListener(this);
		highScore.addActionListener(this);
		quit.addActionListener(this);
		
		start.setBackground(new Color(0,0,255));  
		start.setOpaque(false);
		highScore.setBackground(new Color(0,0,255));  
		highScore.setOpaque(false);
		quit.setBackground(new Color(0,0,255));  
		quit.setOpaque(false);
		
		buttonPan.add(highScore);
		buttonPan.add(start);
		buttonPan.add(quit);
		
		this.add(buttonPan, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getActionCommand() == "Start"){
			System.out.println("Start");
			GameFrame gameFrame = new GameFrame();
			gameFrame.gameBoard.printBoard();
		}
		if (e.getActionCommand() == "High Score"){
			System.out.println("HighScore");
		}
		if (e.getActionCommand() == "Quit"){
			System.out.println("Quit");
			this.dispose();
		}
	}

	public static void main(String[] args) {
		StartFrame startFrame = new StartFrame();
	}	
}