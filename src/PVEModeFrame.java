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

public class PVEModeFrame extends JFrame implements ActionListener{
	GameBoard computerBoard, playerBoard;
	JButton[][] computerButtons, playerButtons;
	JTextArea instructionArea, stateArea;
	int shipCounter = 0;
	int shipLenCounter = 0;
	ArrayList<int[]> tempLoc = new ArrayList<int[]>();
	ArrayList<int[]> blocksTaken = new ArrayList<int[]>();
	boolean isHard = false;
	
	// 构造函数
	public PVEModeFrame(boolean initIsHard){
		isHard = initIsHard;
		shipCounter = 0;
		shipLenCounter = 0;
		computerBoard = new GameBoard();
		playerBoard = new GameBoard(4);
		computerButtons = new JButton[computerBoard.getRowNum()][computerBoard.getColNum()];
		playerButtons = new JButton[playerBoard.getRowNum()][playerBoard.getColNum()];
		blocksTaken = new ArrayList<int[]>();
		
		this.setTitle("SeaBattle2");
		this.setSize(600 + 2 * 80 * computerBoard.getRowNum(), 80 * computerBoard.getColNum());
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
	
	// actionPerformed: 玩家确定船的位置
	public void addShips(int row, int col){
		int[] tempInfo = {row, col};
		// 第一艘船
		if (shipCounter == 0){
			if (shipLenCounter == 0){
				if (isLocOk(tempInfo)){
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
				if (isLocOk(tempInfo)){
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
				if (isLocOk(tempInfo)){
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
				if (isLocOk(tempInfo)){
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
				if (isLocOk(tempInfo)){
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
				if (isLocOk(tempInfo)){
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
				if (isLocOk(tempInfo)){
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
				if (isLocOk(tempInfo)){
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
				if (isLocOk(tempInfo)){
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
				if (isLocOk(tempInfo)){
					shipLenCounter = 0;
					tempLoc.add(tempInfo);
					playerBoard.addOneShip(shipCounter, tempLoc);
					tempLoc.clear();
					shipCounter = 0;
					instructionArea.setText("Choose a block to fire");
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
	public boolean playerMove(int rowChoice, int colChoice){
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

	// actionPerformed: 电脑回合
	public void computerMove(boolean isHardMode){
		ImageIcon hitIcon = new ImageIcon("src/Image/hit.jpg");
		int lastRow = 0;
		int lastCol = 0;
		boolean repeat = false;
		do {
			int[] computerHitLoc = {0, 0};
			if (repeat && isHardMode){
				computerHitLoc = hardComputer(lastRow, lastCol);
			}
			else {
				computerHitLoc = easyComputer();
			}
			System.out.println("Computer hit: " + computerHitLoc[0] + ", " + computerHitLoc[1]);
			playerBoard.fireCannon(computerHitLoc);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e2) {
				e2.printStackTrace();
			}
			if (playerBoard.gameBoard[computerHitLoc[0]][computerHitLoc[1]] == -1){
				lastRow = computerHitLoc[0];
				lastCol = computerHitLoc[1];
				playerButtons[computerHitLoc[0]][computerHitLoc[1]].setIcon(hitIcon);
				if (playerBoard.isWin()){
					PlayerLoseDialog playerLoseDialog = new PlayerLoseDialog();
					repeat = false;
					break;
				}
				else {
					repeat = true;
				}
			}
			else if (playerBoard.gameBoard[computerHitLoc[0]][computerHitLoc[1]] == 0){
				playerButtons[computerHitLoc[0]][computerHitLoc[1]].setBackground(new Color(162, 185, 255));
				repeat = false;
			}
			else {
				System.out.println("Something went wrong.");
			}
		} while (repeat);
	}
	
	// oneRound: 简单的电脑
	public int[] easyComputer(){
		int tryCounter = 0;
		int maxRow = playerBoard.getRowNum();
		int maxCol = playerBoard.getColNum();
		int randomRow = (int)(Math.random() * maxRow);
		int randomCol = (int)(Math.random() * maxCol);
		int[] randomLoc = {randomRow, randomCol};
		while (arrayListContains(blocksTaken, randomLoc)){
			tryCounter++;
			randomRow = (int)(Math.random() * maxRow);
			randomCol = (int)(Math.random() * maxCol);
			randomLoc[0] = randomRow;
			randomLoc[1] = randomCol;
		}
		System.out.println("Try: " + tryCounter);
		blocksTaken.add(randomLoc);
		return randomLoc;
	}
	
	// oneRound: 困难的电脑
	public int[] hardComputer(int hitRow, int hitCol){
		int[] finalLoc = {hitRow, hitCol};
		int[] tempLocUp = {hitRow - 1, hitCol};
		int[] tempLocDown = {hitRow + 1, hitCol};
		int[] tempLocLeft = {hitRow, hitCol - 1};
		int[] tempLocRight = {hitRow, hitCol + 1};
		if (hitRow - 1 >= 0 && !arrayListContains(blocksTaken, tempLocUp)){
			finalLoc = tempLocUp;
		}
		else if (hitRow + 1 <= playerBoard.getRowNum() && !arrayListContains(blocksTaken, tempLocDown)){
			finalLoc = tempLocDown;
		}
		else if (hitCol - 1 >= 0 && !arrayListContains(blocksTaken, tempLocLeft)){
			finalLoc = tempLocLeft;
		}
		else if (hitCol + 1 <= playerBoard.getColNum() && !arrayListContains(blocksTaken, tempLocRight)){
			finalLoc = tempLocRight;
		}
		else {
			finalLoc = easyComputer();
		}
		blocksTaken.add(finalLoc);
		showBlocksTaken();
		return finalLoc;
	}
	
	// ArrayList 查重, ArrayList 的 contains 方法用的是 equals 来判断……需要重写
	public boolean arrayListContains(ArrayList<int[]> arrayList, int[] item){
		for (int i = 0; i < arrayList.size(); i++){
			if (arrayList.get(i)[0] == item[0] && arrayList.get(i)[1] == item[1]){
				return true;
			}
		}
		return false;
	}
	
	// 监听方法
	public void actionPerformed(ActionEvent e){
		for (int row = 0; row < playerBoard.getRowNum(); row++){
			for (int col = 0; col < playerBoard.getColNum(); col++){
				if (e.getSource() == playerButtons[row][col]){
					addShips(row, col);
					playerButtons[row][col].setEnabled(false);
				}
			}
		}
		for (int row = 0; row < computerBoard.getRowNum(); row++){
			for (int col = 0; col < computerBoard.getColNum(); col++){
				if (e.getSource() == computerButtons[row][col]){
					System.out.println("Player Moved");
					if (!playerMove(row, col)){
						instructionArea.setText("Computer's turn");
						if (isHard){
							computerMove(true);
						}
						else {
							computerMove(false);
						}
						instructionArea.setText("Choose a block to fire");
						showBlocksTaken();
					}
					playerButtons[row][col].setEnabled(false);
				}
			}
		}
	}
	
	// 显示占用的格子
	public void showBlocksTaken(){
		System.out.println("Blocks taken: ");
		for (int i = 0; i < blocksTaken.size(); i++){
			System.out.println("(" + blocksTaken.get(i)[0] + ", " + blocksTaken.get(i)[1] + ") ");
		}
	}

	// 主函数
	public static void main(String[] args) {
		PVEModeFrame pveMode = new PVEModeFrame(true);
	}

}

class PlayerWinDialog extends JDialog implements ActionListener{
	JButton comfirmButton;
	JLabel winLabel;
	
	public PlayerWinDialog(){
		this.setTitle("Congratulations!");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(200, 200, 250, 140);
		
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
		if (e.getSource() == comfirmButton){
			this.dispose();
		}
	}
}

class PlayerLoseDialog extends JDialog implements ActionListener{
	JButton comfirmButton;
	JLabel loseLabel;
	
	public PlayerLoseDialog(){
		this.setTitle("Sorry!");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(200, 200, 250, 140);
		
		loseLabel = new JLabel("Sorry, you lose.");
		comfirmButton = new JButton("Ok");
		comfirmButton.addActionListener(this);
		
		JPanel labelPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		
		labelPanel.add(loseLabel);
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
