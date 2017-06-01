import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PVPModeFrame extends JFrame implements ActionListener{
	GameBoard playerOneBoard, playerTwoBoard;
	JButton[][] playerOneButtons, playerTwoButtons;
	JTextArea instructionArea, stateArea;
	int shipCounter = 0;
	int shipLenCounter = 0;
	ArrayList<int[]> tempLoc = new ArrayList<int[]>();
	
	// 构造函数
	public PVPModeFrame(){
		shipCounter = 0;
		shipLenCounter = 0;
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
	
	// actionPerformed: 玩家确定船的位置
	public void addShips(int row, int col, JButton[][] playerButtons, JButton[][] computerButtons, GameBoard playerBoard, GameBoard computerBoard){
		int[] tempInfo = {row, col};
		// 第一艘船
		if (shipCounter == 0){
			if (shipLenCounter == 0){
				if (isLocOk(tempInfo, playerBoard)){
					shipLenCounter++;
					tempLoc.add(tempInfo);
					instructionArea.setText("Place battle ship No.1" +"\nIt's size is 2");
					playerButtons[row][col].setBackground(new Color(162, 185, 255));
					return;
				}
				else {
					instructionArea.setText("You should not place it here");
					return;
				}
			}
			if (shipLenCounter == 1){
				if (isLocOk(tempInfo, playerBoard)){
					shipLenCounter = 0;
					tempLoc.add(tempInfo);
					playerBoard.addOneShip(shipCounter, tempLoc);
					tempLoc.clear();
					shipCounter++;
					instructionArea.setText("Place battle ship No.2" + "\nIt's size is 2");
					playerButtons[row][col].setBackground(new Color(162, 185, 255));
					return;
				}
				else {
					instructionArea.setText("You should not place it here");
					return;
				}
			}
		}
		// 第二艘船
		if (shipCounter == 1){
			if (shipLenCounter == 0){
				if (isLocOk(tempInfo, playerBoard)){
					shipLenCounter++;
					tempLoc.add(tempInfo);
					instructionArea.setText("Place battle ship No.2" + "\nIt's size is 2");
					playerButtons[row][col].setBackground(new Color(162, 185, 255));
					return;
				}
				else {
					instructionArea.setText("You should not place it here");
					return;
				}
			}
			if (shipLenCounter == 1){
				if (isLocOk(tempInfo, playerBoard)){
					shipLenCounter = 0;
					tempLoc.add(tempInfo);
					playerBoard.addOneShip(shipCounter, tempLoc);
					tempLoc.clear();
					shipCounter++;
					instructionArea.setText("Place battle ship No.3" + "\nIt's size is 3");
					playerButtons[row][col].setBackground(new Color(162, 185, 255));
					return;
				}
				else {
					instructionArea.setText("You should not place it here");
					return;
				}
			}
		}
		// 第三艘船
		if (shipCounter == 2){
			if (shipLenCounter == 0){
				if (isLocOk(tempInfo, playerBoard)){
					shipLenCounter++;
					tempLoc.add(tempInfo);
					instructionArea.setText("Place battle ship No.3" + "\nIt's size is 3");
					playerButtons[row][col].setBackground(new Color(162, 185, 255));
					return;
				}
				else {
					instructionArea.setText("You should not place it here");
					return;
				}
			}
			if (shipLenCounter == 1){
				if (isLocOk(tempInfo, playerBoard)){
					shipLenCounter++;
					tempLoc.add(tempInfo);
					instructionArea.setText("Place battle ship No.3" + "\nIt's size is 3");
					playerButtons[row][col].setBackground(new Color(162, 185, 255));
					return;
				}
				else {
					instructionArea.setText("You should not place it here");
					return;
				}
			}
			if (shipLenCounter == 2){
				if (isLocOk(tempInfo, playerBoard)){
					shipLenCounter = 0;
					tempLoc.add(tempInfo);
					playerBoard.addOneShip(shipCounter, tempLoc);
					tempLoc.clear();
					shipCounter++;
					instructionArea.setText("Place battle ship No.4" + "\nIt's size is 3");
					playerButtons[row][col].setBackground(new Color(162, 185, 255));
					return;
				}
				else {
					instructionArea.setText("You should not place it here");
					return;
				}
			}
		}
		// 第四艘船
		if (shipCounter == 3){
			if (shipLenCounter == 0){
				if (isLocOk(tempInfo, playerBoard)){
					shipLenCounter++;
					tempLoc.add(tempInfo);
					instructionArea.setText("Place battle ship No.4" + "\nIt's size is 3");
					playerButtons[row][col].setBackground(new Color(162, 185, 255));
					return;
				}
				else {
					instructionArea.setText("You should not place it here");
					return;
				}
			}
			if (shipLenCounter == 1){
				if (isLocOk(tempInfo, playerBoard)){
					shipLenCounter++;
					tempLoc.add(tempInfo);
					instructionArea.setText("Place battle ship No.4" + "\nIt's size is 3");
					playerButtons[row][col].setBackground(new Color(162, 185, 255));
					return;
				}
				else {
					instructionArea.setText("You should not place it here");
					return;
				}
			}
			if (shipLenCounter == 2){
				if (isLocOk(tempInfo, playerBoard)){
					shipLenCounter = 0;
					tempLoc.add(tempInfo);
					playerBoard.addOneShip(shipCounter, tempLoc);
					tempLoc.clear();
					shipCounter = 0;
					instructionArea.setText("Your turn");
					stateArea.setText("Game start");
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
					playerButtons[row][col].setBackground(new Color(162, 185, 255));
					return;
				}
				else {
					instructionArea.setText("You should not place it here");
					return;
				}
			}
		}
	}
	
	// addShips: 确定选择的位置是否符合规则，会需要 shipLenCounter 的数值，所以需要放在改动之前
	public boolean isLocOk(int[] loc, GameBoard playerBoard){
		// TODO 按照现在的逻辑，会出现在无法添加船的位置加了船之后无法继续的情况
		if (shipLenCounter == 0){
			if (playerBoard.gameBoard[loc[0]][loc[1]] != 0){
				return false;
			}
		}
		if (shipLenCounter == 1){
			// 计算两点间距离判断是否是周围八个格子中的一个
			int[] tempInfo = tempLoc.get(0);
			double dis = (tempInfo[0] - loc[0]) * (tempInfo[0] - loc[0]) + (tempInfo[1] - loc[1]) * (tempInfo[1] - loc[1]);
			if (playerBoard.gameBoard[loc[0]][loc[1]] != 0){
				return false;
			}
			if (dis >= 2){
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
	
	// actionPerformed: 玩家回合，返回值为是否命中
		public boolean playerMove(int rowChoice, int colChoice, JButton[][] computerButtons, GameBoard computerBoard){
			ImageIcon hitIcon = new ImageIcon("src/Image/hit.jpg");
			int playerHitLoc[] = {rowChoice, colChoice};
			computerBoard.fireCannon(playerHitLoc);
			if (computerBoard.gameBoard[rowChoice][colChoice] == -1){
				computerButtons[rowChoice][colChoice].setIcon(hitIcon);
				if (computerBoard.isWin()){
					PlayerWinDialog playerWinDialog = new PlayerWinDialog();
					return true;
				}
				return true;
			}
			if (computerBoard.gameBoard[rowChoice][colChoice] == 0){
				computerButtons[rowChoice][colChoice].setBackground(new Color(162, 185, 255));
				return false;
			}
			return false;
		}
	
	// 监听方法
	public void actionPerformed(ActionEvent e){
		for (int row = 0; row < playerOneBoard.getRowNum(); row++){
			for (int col = 0; col < playerOneBoard.getColNum(); col++){
				
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class SomeoneWinDialog extends JDialog implements ActionListener{
	JButton comfirmButton;
	JLabel winLabel;
	
	public SomeoneWinDialog(int winPerson){
		this.setTitle("Congratulations!");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(200, 200, 250, 140);
		
		winLabel = new JLabel("Player" + winPerson + "win!");
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
		if (e.getSource() == comfirmButton){
			this.dispose();
		}
	}
}
