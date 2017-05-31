import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PVPModeFrame extends JFrame implements ActionListener{
	GameBoard playerOneBoard, playerTwoBoard;
	JButton[][] playerOneButtons, playerTwoButtons;
	JTextArea instructionArea, stateArea;
	
	// 构造函数
	public PVPModeFrame(){
		playerOneBoard = new GameBoard(4);
		playerTwoBoard = new GameBoard(4);
		playerOneButtons = new JButton[playerOneBoard.getRowNum()][playerOneBoard.getColNum()];
		playerTwoButtons = new JButton[playerTwoBoard.getRowNum()][playerTwoBoard.getColNum()];
		
		this.setTitle("SeaBattle2");
		this.setSize(600 + 2 * 80 * playerOneBoard.getRowNum(), 80 * playerOneBoard.getColNum());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		addButtons();
		addTextArea();
		
		this.setVisible(true);
		
		for (int row = 0; row < playerTwoBoard.getRowNum(); row++){
			for (int col = 0; col < playerTwoBoard.getColNum(); col++){
				playerTwoButtons[row][col].setEnabled(false);
			}
		}
	}
	
	// constructor: 添加文本区
	public void addTextArea(){
		JPanel textAreaPanel = new JPanel();
		textAreaPanel.setLayout(new GridLayout(2, 1));
		instructionArea = new JTextArea("");
		instructionArea.setEditable(false);
		instructionArea.setBorder(BorderFactory.createLineBorder(Color.black,1));
		instructionArea.setFont(new Font("Arial", Font.BOLD, 24));
		instructionArea.setLineWrap(true);
		instructionArea.setText("Place battle ship No.1\nIt's size is 2");
		stateArea = new JTextArea("");
		stateArea.setEditable(false);
		stateArea.setBorder(BorderFactory.createLineBorder(Color.black,1));
		stateArea.setFont(new Font("Arial", Font.BOLD, 24));
		stateArea.setLineWrap(true);
		stateArea.setText("Player1 choose the locations of the battle ships.");
		textAreaPanel.add(instructionArea);
		textAreaPanel.add(stateArea);
		this.add(textAreaPanel);
	}
	
	// constructor: 添加按钮
	public void addButtons(){
		JPanel playerOneButtonPanel = new JPanel();
		JPanel playerTwoButtonPanel = new JPanel();
		playerOneButtonPanel.setLayout(new GridLayout(playerOneBoard.getRowNum(), playerOneBoard.getColNum()));
		playerTwoButtonPanel.setLayout(new GridLayout(playerTwoBoard.getRowNum(), playerTwoBoard.getColNum()));
		for (int row = 0; row < playerOneBoard.getRowNum(); row++){
			for (int col = 0; col < playerOneBoard.getColNum(); col++){
				playerOneButtons[row][col] = new JButton("");
				playerOneButtons[row][col].setBackground(new Color(162, 230, 255));
				playerOneButtons[row][col].addActionListener(this);
				playerOneButtons[row][col].setPreferredSize(new Dimension(80, 80));
				playerOneButtonPanel.add(playerOneButtons[row][col]);
			}
		}
		for (int row = 0; row < playerOneBoard.getRowNum(); row++){
			for (int col = 0; col < playerOneBoard.getColNum(); col++){
				playerTwoButtons[row][col] = new JButton("");
				playerTwoButtons[row][col].setBackground(new Color(162, 230, 255));
				playerTwoButtons[row][col].addActionListener(this);
				playerTwoButtons[row][col].setPreferredSize(new Dimension(80, 80));
				playerTwoButtonPanel.add(playerTwoButtons[row][col]);
			}
		}
		this.add(playerOneButtonPanel, BorderLayout.WEST);
		this.add(playerTwoButtonPanel, BorderLayout.WEST);
	}
	
	// 监听方法
	public void actionPerformed(ActionEvent e){
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
