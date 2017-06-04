import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartFrame extends JFrame implements ActionListener{
	JButton start, highScore, quit;
	
	public StartFrame(){
		this.setBounds(200, 200, 860, 538);
		this.setTitle("SeaBattle2");
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
			/* ------------------------------- Changed in school ---------------------------------
			GameFrame gameFrame = new GameFrame();
			gameFrame.gameBoard.printBoard();
			-------------------------------------------------------------------------------------*/
			ModeChooseDialog modeChooseDialog = new ModeChooseDialog();
		}
		if (e.getActionCommand() == "High Score"){
			System.out.println("HighScore");
			/*----------------------------------------------- Changed for Java 1.6 -------------------------------------
			try {
				HighScoreDialog highScoreDialog = new HighScoreDialog();
			} catch (NumberFormatException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			*/
			try {
				HighScoreDialog highScoreDialog = new HighScoreDialog();
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			//----------------------------------------------------------------------------------------------------------
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

class HighScoreDialog extends JDialog implements ActionListener{
	JButton comfirmButton;
	
	public HighScoreDialog() throws NumberFormatException, IOException{
		this.setTitle("High Score");
		this.setBounds(200, 200, 600, 250);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		// 为最高分数组赋值
		int[] highScore = new int[10];
		File highScoreFile = new File("src/HighScore.txt");
		BufferedReader reader = new BufferedReader(new FileReader(highScoreFile));
		for (int n = 0; n < 10; n++){
			highScore[n] = Integer.parseInt(reader.readLine());
		}
		
		// 表格
		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new GridLayout(11, 2));
		JLabel rankLabel = new JLabel("Rank", JLabel.CENTER);
		JLabel scoreLabel = new JLabel("Score", JLabel.CENTER);
		tablePanel.add(rankLabel);
		tablePanel.add(scoreLabel);
		JLabel[] rankLabels = new JLabel[10];
		JLabel[] scoreLabels = new JLabel[10];
		for (int i = 0; i < 10; i++){
			rankLabels[i] = new JLabel("" + (i + 1), JLabel.CENTER);
			scoreLabels[i] = new JLabel("" + highScore[i], JLabel.CENTER);
			tablePanel.add(rankLabels[i]);
			tablePanel.add(scoreLabels[i]);
		}
		this.add(tablePanel, BorderLayout.CENTER);
		
		// 按钮
		JPanel buttonPanel = new JPanel();
		comfirmButton = new JButton("Ok");
		comfirmButton.addActionListener(this);
		buttonPanel.add(comfirmButton);
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getActionCommand() == "Ok"){
			this.dispose();
		}
	}
}

class ModeChooseDialog extends JDialog implements ActionListener{
	JButton originalModeButton, PVEModeButton, cancelButton;
	
	public ModeChooseDialog(){
		originalModeButton = new JButton("Original Mode");
		originalModeButton.addActionListener(this);
		PVEModeButton = new JButton("PVE Mode");
		PVEModeButton.addActionListener(this);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		
		this.setTitle("Choose Game Mode");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(530, 379, 200, 180);		
		JPanel modePanel = new JPanel();
		JPanel cancelPanel = new JPanel();
		modePanel.setBorder(BorderFactory.createTitledBorder("Choose game mode"));
		
		modePanel.add(originalModeButton);
		modePanel.add(PVEModeButton);
		cancelPanel.add(cancelButton);
		
		this.add(modePanel, BorderLayout.CENTER);
		this.add(cancelPanel, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getActionCommand() == "Cancel"){
			this.dispose();
		}
		if (e.getActionCommand() == "Original Mode"){
			GameFrame gameFrame = new GameFrame();
			gameFrame.gameBoard.printBoard();
			this.dispose();
		}
		if (e.getActionCommand() == "PVE Mode"){
			DifficultyChooseDialog difficultyChooseDialog = new DifficultyChooseDialog();
			this.dispose();
		}
	}
}

class DifficultyChooseDialog extends JDialog implements ActionListener{
	JButton easyButton, hardButton, cancelButton;
	
	public DifficultyChooseDialog(){
		easyButton = new JButton("Easy Mode");
		easyButton.addActionListener(this);
		hardButton = new JButton("Hard Mode");
		hardButton.addActionListener(this);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		
		this.setTitle("Choose Difficulty");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(530, 379, 200, 180);		
		JPanel difficultyPanel = new JPanel();
		JPanel cancelPanel = new JPanel();
		difficultyPanel.setBorder(BorderFactory.createTitledBorder("Choose difficulty"));
		
		difficultyPanel.add(easyButton);
		difficultyPanel.add(hardButton);
		cancelPanel.add(cancelButton);
		
		this.add(difficultyPanel, BorderLayout.CENTER);
		this.add(cancelPanel, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getActionCommand() == "Cancel"){
			this.dispose();
		}
		if (e.getActionCommand() == "Easy Mode"){
			PVEModeFrame PVEMode = new PVEModeFrame(false);
			this.dispose();
		}
		if (e.getActionCommand() == "Hard Mode"){
			PVEModeFrame PVEMode = new PVEModeFrame(true);
			this.dispose();
		}
	}
}