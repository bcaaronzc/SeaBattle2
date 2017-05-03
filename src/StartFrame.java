import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartFrame extends JFrame{
	JButton start, highScore, quit;
	JLabel background;
	
	public StartFrame(){
		this.setBounds(200, 200, 600, 400);
		this.setBackground(new Color(255, 255, 255));
		this.getContentPane().setBackground(Color.white);
		
		JPanel imagePanel = new JPanel();
		ImageIcon backgroundIcon = new ImageIcon("src/Image/StartFrameBoat.png");
		Image backgroundImage = backgroundIcon.getImage().getScaledInstance(imagePanel.getWidth(),imagePanel.getHeight(),backgroundIcon.getImage().SCALE_DEFAULT);  
		ImageIcon finalIcon = new ImageIcon(backgroundImage);
		background = new JLabel();
		background.setIcon(finalIcon);
		this.add(background, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 3));
		Font buttonFont = new Font("Arial", Font.BOLD, 24);
		
		start = new JButton();
		start.setIcon(new ImageIcon("src/Image/StartFrameBoat.png"));
		start.setBackground(Color.WHITE);
		highScore = new JButton("High Score");
		quit = new JButton("Quit");
		
		start.setFont(buttonFont);
		highScore.setFont(buttonFont);
		quit.setFont(buttonFont);
		
		buttonPanel.add(highScore);
		buttonPanel.add(start);
		buttonPanel.add(quit);
		
		start.setForeground(Color.BLUE);
		highScore.setForeground(Color.BLUE);
		quit.setForeground(Color.BLUE);
		
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}

	public static void main(String[] args) {
		StartFrame startFrame = new StartFrame();
	}
	
}
