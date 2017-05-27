import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PVEModeFrame extends JFrame implements ActionListener{
	GameBoard computerBoard, playerBoard;
	JButton[][] computerButtons, playerButtons;
	JTextArea instructionArea, stateArea;
	
	// 构造函数
	public PVEModeFrame(){
		computerBoard = new GameBoard();
		playerBoard = new GameBoard();
		computerButtons = new JButton[computerBoard.getRowNum()][computerBoard.getColNum()];
		playerButtons = new JButton[playerBoard.getRowNum()][playerBoard.getColNum()];
		
		this.setTitle("SeaBattle2");
		this.setSize(240 + 2 * 80 * computerBoard.getRowNum(), 80 * computerBoard.getColNum());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		addButtons();
		addTextArea();
		
		this.setVisible(true);
	}
	
	// 开始游戏
	public void gameStart(){
		// TODO This function control all the process
	}
	
	// 添加文本区(for the constructor)
	public void addTextArea(){
		JPanel textAreaPanel = new JPanel();
		textAreaPanel.setLayout(new GridLayout(2, 1));
		instructionArea = new JTextArea("");
		instructionArea.setEditable(false);
		instructionArea.setBorder(BorderFactory.createLineBorder(Color.black,1));
		stateArea = new JTextArea("");
		stateArea.setEditable(false);
		stateArea.setBorder(BorderFactory.createLineBorder(Color.black,1));
		textAreaPanel.add(instructionArea);
		textAreaPanel.add(stateArea);
		this.add(textAreaPanel);
	}
	
	// 添加按钮(for the constructor)
	public void addButtons(){
		JPanel computerButtonPanel = new JPanel();
		JPanel playerButtonPanel = new JPanel();
		computerButtonPanel.setLayout(new GridLayout(computerBoard.getRowNum(), computerBoard.getColNum()));
		playerButtonPanel.setLayout(new GridLayout(playerBoard.getRowNum(), playerBoard.getColNum()));
		for (int row = 0; row < computerBoard.getRowNum(); row++){
			for (int col = 0; col < computerBoard.getColNum(); col++){
				computerButtons[row][col] = new JButton("");
				computerButtons[row][col].setBackground(new Color(162, 230, 255));
				computerButtons[row][col].addActionListener(this);
				computerButtons[row][col].setPreferredSize(new Dimension(80, 80));
				computerButtonPanel.add(computerButtons[row][col]);
			}
		}
		for (int row = 0; row < playerBoard.getRowNum(); row++){
			for (int col = 0; col < playerBoard.getColNum(); col++){
				playerButtons[row][col] = new JButton("");
				playerButtons[row][col].setBackground(new Color(162, 230, 255));;
				playerButtons[row][col].addActionListener(this);
				playerButtons[row][col].setPreferredSize(new Dimension(80, 80));
				playerButtonPanel.add(playerButtons[row][col]);
			}
		}
		this.add(computerButtonPanel, BorderLayout.WEST);
		this.add(playerButtonPanel, BorderLayout.EAST);
	}

	// 监听方法
	public void actionPerformed(ActionEvent e){
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PVEModeFrame pveMode = new PVEModeFrame();
	}

}
