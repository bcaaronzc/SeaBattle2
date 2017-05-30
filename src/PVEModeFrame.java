import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PVEModeFrame extends JFrame implements ActionListener{
	GameBoard computerBoard, playerBoard;
	JButton[][] computerButtons, playerButtons;
	JTextArea instructionArea, stateArea;
	int shipCounter, shipLenCounter;
	ArrayList<int[]> tempLoc = new ArrayList<int[]>();
	
	// 构造函数
	public PVEModeFrame(){
		shipCounter = 0;
		shipLenCounter = 0;
		computerBoard = new GameBoard();
		playerBoard = new GameBoard(4);
		computerButtons = new JButton[computerBoard.getRowNum()][computerBoard.getColNum()];
		playerButtons = new JButton[playerBoard.getRowNum()][playerBoard.getColNum()];
		
		this.setTitle("SeaBattle2");
		this.setSize(240 + 2 * 80 * computerBoard.getRowNum(), 80 * computerBoard.getColNum());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		addButtons();
		addTextArea();
		
		for (int row = 0; row < computerBoard.getRowNum(); row++){
			for (int col = 0; col < computerBoard.getColNum(); col++){
				computerButtons[row][col].setEnabled(false);
			}
		}
		
		this.setVisible(true);
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
		stateArea.setText("Player choose the locations of the battle ships.");
		textAreaPanel.add(instructionArea);
		textAreaPanel.add(stateArea);
		this.add(textAreaPanel);
	}
	
	// constructor: 添加按钮
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

	// 开始游戏
	public void gameStart(){
		// TODO This function control all the process
	}
	
	// 玩家确定船的位置
	public void addShips(int row, int col){
		int[] tempInfo = {row, col};
		if (shipCounter == 0 || shipCounter == 1){
			if (shipLenCounter == 0){
				if (isLocOk(tempInfo)){
					shipLenCounter++;
					tempLoc.add(tempInfo);
					instructionArea.setText("Place battle ship No." + (shipCounter + 1) +"\nIt's size is 2");
				}
				else {
					instructionArea.setText("You should not place it here");
				}
			}
			if (shipLenCounter == 1){
				if (isLocOk(tempInfo)){
					shipLenCounter = 0;
					tempLoc.add(tempInfo);
					playerBoard.addOneShip(shipCounter, tempLoc);
					tempLoc.clear();
					shipCounter++;
					instructionArea.setText("Place battle ship No." + (shipCounter + 2) + "\nIt's size is " + (shipCounter + 2));
				}
				else {
					instructionArea.setText("You should not place it here");
				}
			}
		}
		if (shipCounter == 2){
			if (shipLenCounter == 0){
				if (isLocOk(tempInfo)){
					shipLenCounter++;
					tempLoc.add(tempInfo);
					instructionArea.setText("Place battle ship No." + (shipCounter + 1) +"\nIt's size is 3");
				}
				else {
					instructionArea.setText("You should not place it here");
				}
			}
			if (shipLenCounter == 1){
				if (isLocOk(tempInfo)){
					shipLenCounter++;
					tempLoc.add(tempInfo);
					instructionArea.setText("Place battle ship No." + (shipCounter + 1) +"\nIt's size is 3");
				}
				else {
					instructionArea.setText("You should not place it here");
				}
			}
			if (shipLenCounter == 2){
				if (isLocOk(tempInfo)){
					shipLenCounter = 0;
					tempLoc.add(tempInfo);
					playerBoard.addOneShip(shipCounter, tempLoc);
					tempLoc.clear();
					shipCounter++;
					instructionArea.setText("Place battle ship No." + (shipCounter + 1) +"\nIt's size is 3");
				}
				else {
					instructionArea.setText("You should not place it here");
				}
			}
		}
		if (shipCounter == 3){
			if (shipLenCounter == 0){
				if (isLocOk(tempInfo)){
					shipLenCounter++;
					tempLoc.add(tempInfo);
					instructionArea.setText("Place battle ship No." + (shipCounter + 1) +"\nIt's size is 3");
				}
				else {
					instructionArea.setText("You should not place it here");
				}
			}
			if (shipLenCounter == 1){
				if (isLocOk(tempInfo)){
					shipLenCounter++;
					tempLoc.add(tempInfo);
					instructionArea.setText("Place battle ship No." + (shipCounter + 1) +"\nIt's size is 3");
				}
				else {
					instructionArea.setText("You should not place it here");
				}
			}
			if (shipLenCounter == 2){
				if (isLocOk(tempInfo)){
					shipLenCounter = 0;
					tempLoc.add(tempInfo);
					playerBoard.addOneShip(shipCounter, tempLoc);
					tempLoc.clear();
					shipCounter = 0;
					instructionArea.setText("Game start");
					for (int boardRow = 0; boardRow < playerBoard.getRowNum(); boardRow++){
						for (int boardCol = 0; boardCol < playerBoard.getColNum(); boardCol++){
							playerButtons[boardRow][boardCol].setEnabled(false);
						}
					}
					for (int boardRow = 0; boardRow < computerBoard.getRowNum(); boardRow++){
						for (int boardCol = 0; boardCol < computerBoard.getColNum(); boardCol++){
							computerButtons[boardRow][boardCol].setEnabled(true);
						}
					}
				}
				else {
					instructionArea.setText("You should not place it here");
				}
			}
		}
	}
	
	// addShips: 确定选择的位置是否符合规则，会需要 shipLenCounter 的数值，所以需要放在改动之前
	public boolean isLocOk(int[] loc){
		// TODO 按照现在的逻辑，会出现在无法添加船的位置加了船之后无法继续的情况
		if (shipLenCounter == 0){
			if (playerBoard.gameBoard[loc[0]][loc[1]] != 0){
				return false;
			}
		}
		if (shipLenCounter == 1){
			// 计算两点间距离判断是否是周围八个格子中的一个
			int[] tempInfo = tempLoc.get(0);
			double dis = (tempInfo[0] - loc[0]) * (tempInfo[0] - loc[0])
					+ (tempInfo[1] - loc[1]) * (tempInfo[1] - loc[1]);
			if (playerBoard.gameBoard[loc[0]][loc[1]] != 0){
				return false;
			}
			if (dis >= 4){
				return false;
			}
		}
		if (shipLenCounter == 2){
			int[] tempInfo0 = tempLoc.get(0);
			int[] tempInfo1 = tempLoc.get(1);
			if (tempInfo0[0] == tempInfo1[0]){
				if (loc[0] != tempInfo0[0]){
					return false;
				}
				if (tempInfo0[1] > tempInfo1[1]){
					if ((loc[1] != tempInfo0[1] + 1) && (loc[1] != tempInfo1[1] - 1)){
						return false;
					}
				}
				else if (tempInfo0[1] < tempInfo1[1]){
					if ((loc[1] != tempInfo0[1] - 1) && (loc[1] != tempInfo1[1] + 1)){
						return false;
					}
				}
			}
			if (tempInfo0[1] == tempInfo1[1]){
				if (loc[1] != tempInfo0[1]){
					return false;
				}
				if (tempInfo0[0] > tempInfo1[0]){
					if ((loc[0] != tempInfo0[0] + 1) && (loc[0] != tempInfo1[0] - 1)){
						return false;
					}
				}
				else if (tempInfo0[0] < tempInfo1[0]){
					if ((loc[0] != tempInfo0[0] - 1) && (loc[0] != tempInfo1[0] + 1)){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	// 监听方法
	public void actionPerformed(ActionEvent e){
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PVEModeFrame pveMode = new PVEModeFrame();
	}

}
