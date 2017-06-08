import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameFrame extends JFrame implements ActionListener{
	GameBoard gameBoard = new GameBoard();
	JButton[][] buttons = new JButton[gameBoard.getRowNum()][gameBoard.getColNum()];
	
	// 构造函数
	public GameFrame(){
		this.setTitle("SeaBattle2");
		this.setSize(80 * gameBoard.getRowNum(), 80 * gameBoard.getColNum());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		addButtons();
		
		this.setVisible(true);
	}
	
	// 添加按钮
	public void addButtons(){
		JPanel buttonPanel = new JPanel();
		//ImageIcon water = new ImageIcon("src/Image/Water.png");
		buttonPanel.setLayout(new GridLayout(gameBoard.getRowNum(), gameBoard.getColNum()));
		for (int row = 0; row < gameBoard.getRowNum(); row++){
			for (int col = 0; col < gameBoard.getColNum(); col++){
				buttons[row][col] = new JButton("");
				buttons[row][col].setBackground(new Color(162, 230, 255));
				buttons[row][col].addActionListener(this);
				buttonPanel.add(buttons[row][col]);
			}
		}
		this.add(buttonPanel, BorderLayout.CENTER);
	}
	
	// 保存最高分
	public void saveHighScore() throws NumberFormatException, IOException{
		int currentScore = gameBoard.getScore();
		File highScoreFile = new File("src/HighScore.txt");
		int[] scoreList = new int[10];
		int[] originalList = new int[10];
		if (highScoreFile.exists()){
			BufferedReader reader = new BufferedReader(new FileReader(highScoreFile));
			for (int n = 0; n < 10; n++){
				originalList[n] = Integer.parseInt(reader.readLine());
			}
			int i;
			for (i = 0; i < 10; i++){
				if (currentScore <= originalList[i]){
					scoreList[i] = originalList[i];
				}
				else if (currentScore > originalList[i]){
					scoreList[i] = currentScore;
					System.out.println("Write " + scoreList[i]);
					break;
				}
			}
			for (int j = i + 1; j < 10; j++){
				scoreList[j] = originalList[j - 1];
			}
			FileWriter clearer = new FileWriter("src/HighScore.txt");
			clearer.write("");
			clearer.close();
			FileWriter writer = new FileWriter("src/HighScore.txt", true);
			for (int m = 0; m < 10; m++){
				System.out.println("Write " + scoreList[m]);
				writer.write("" + scoreList[m]);
				writer.write("\r\n");
			}
			writer.close();
		}
		else {
			System.out.println("Something is wrong, high score file not found.");
		}
	}
	
	// 实现监听方法
	public void actionPerformed(ActionEvent e){
		ImageIcon hitIcon = new ImageIcon("src/Image/hit.jpg");
		for (int row = 0; row < gameBoard.getRowNum(); row++){
			for (int col = 0; col < gameBoard.getColNum(); col++){
				if (e.getSource() == buttons[row][col]){
					int hitLoc[] = {row, col};
					gameBoard.fireCannon(hitLoc);
					if (gameBoard.gameBoard[row][col] == -1){
						buttons[row][col].setIcon(hitIcon);
						buttons[row][col].setEnabled(false);
					}
					if (gameBoard.gameBoard[row][col] == 0){
						buttons[row][col].setBackground(new Color(162, 185, 255));
						buttons[row][col].setEnabled(false);
					}
					if (gameBoard.isWin()){
						WinDialog winDialog = new WinDialog(gameBoard.getScore());
						try {
							saveHighScore();
						} catch (NumberFormatException | IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		}
	}

	// 主函数
	public static void main(String args[]){
		GameFrame gameFrame = new GameFrame();
		gameFrame.gameBoard.printBoard();
	}

}

class WinDialog extends JDialog implements ActionListener{
	JButton comfirmButton;
	JLabel winLabel, scoreLabel;
	
	public WinDialog(int score){
		this.setTitle("Congratulations!");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(200, 200, 250, 140);
		
		winLabel = new JLabel("Congratulations! You win!");
		scoreLabel = new JLabel("Your score is " + score);
		comfirmButton = new JButton("Ok");
		comfirmButton.addActionListener(this);
		
		JPanel labelPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		
		labelPanel.add(winLabel);
		labelPanel.add(scoreLabel);
		buttonPanel.add(comfirmButton);
		
		this.add(labelPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getActionCommand() == "Ok"){
			this.dispose();
		}
	}
}
