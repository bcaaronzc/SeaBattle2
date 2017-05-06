import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
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
				}
			}
		}
	}
	
	public static void main(String args[]){
		GameFrame gameFrame = new GameFrame();
	}
}

class WinDialog extends JDialog{
	
	public WinDialog(){
		
	}
}
