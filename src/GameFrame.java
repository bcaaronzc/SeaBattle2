import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		buttonPanel.setLayout(new GridLayout(gameBoard.getRowNum(), gameBoard.getColNum()));
		for (int row = 0; row < gameBoard.getRowNum(); row++){
			for (int col = 0; col < gameBoard.getColNum(); col++){
				buttons[row][col] = new JButton("");
				buttons[row][col].addActionListener(this);
				buttonPanel.add(buttons[row][col]);
			}
		}
		this.add(buttonPanel, BorderLayout.CENTER);
	}
	
	// 实现监听方法
	public void actionPerformed(ActionEvent e){
		for (int row = 0; row < gameBoard.getRowNum(); row++){
			for (int col = 0; col < gameBoard.getColNum(); col++){
				if (e.getSource() == buttons[row][col]){
					int hitLoc[] = {row, col};
					gameBoard.fireCannon(hitLoc);
					buttons[row][col].setText("" + gameBoard.gameBoard[row][col]);
					if (gameBoard.isWin()){
						WinDialog winDialog = new WinDialog();
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
	JLabel winLabel;
	
	public WinDialog(){
		this.setTitle("Congratulations!");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(200, 200, 300, 115);
		
		winLabel = new JLabel("Congratulations! You win!");
		comfirmButton = new JButton("Ok");
		comfirmButton.addActionListener(this);
		
		JPanel labelPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		
		labelPanel.add(winLabel);
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
