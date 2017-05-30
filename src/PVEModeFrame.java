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
	
	// ���캯��
	public PVEModeFrame(){
		shipCounter = 0;
		shipLenCounter = 0;
		computerBoard = new GameBoard();
		playerBoard = new GameBoard(4);
		computerButtons = new JButton[computerBoard.getRowNum()][computerBoard.getColNum()];
		playerButtons = new JButton[playerBoard.getRowNum()][playerBoard.getColNum()];
		
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
	
	// constructor: ����ı���
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
	
	// constructor: ��Ӱ�ť
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
	
	// actionPerformed: ���ȷ������λ��
	public void addShips(int row, int col){
		int[] tempInfo = {row, col};
		// ��һ�Ҵ�
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
		// �ڶ��Ҵ�
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
		// �����Ҵ�
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
		// �����Ҵ�
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
	
	// addShips: ȷ��ѡ���λ���Ƿ���Ϲ��򣬻���Ҫ shipLenCounter ����ֵ��������Ҫ���ڸĶ�֮ǰ
	public boolean isLocOk(int[] loc){
		// TODO �������ڵ��߼�����������޷���Ӵ���λ�ü��˴�֮���޷����������
		if (shipLenCounter == 0){
			if (playerBoard.gameBoard[loc[0]][loc[1]] != 0){
				return false;
			}
		}
		if (shipLenCounter == 1){
			// �������������ж��Ƿ�����Χ�˸������е�һ��
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

	// actionPerformed: һ���غϣ������һ�������������ɲ�
	public void oneRound(int rowChoice, int colChoice){
		// TODO ģʽ�л������ѵĵ���
		ImageIcon hitIcon = new ImageIcon("src/Image/hit.jpg");
		int playerHitLoc[] = {rowChoice, colChoice};
		computerBoard.fireCannon(playerHitLoc);
		if (computerBoard.gameBoard[rowChoice][colChoice] == -1){
			computerButtons[rowChoice][colChoice].setIcon(hitIcon);
			if (computerBoard.isWin()){
				PlayerWinDialog playerWinDialog = new PlayerWinDialog();
				return;
			}
			return;
		}
		if (computerBoard.gameBoard[rowChoice][colChoice] == 0){
			computerButtons[rowChoice][colChoice].setBackground(new Color(162, 185, 255));
		}
		
		boolean repeat = false;
		do {
			int[] computerHitLoc = easyComputer();
			playerBoard.fireCannon(computerHitLoc);
			if (playerBoard.gameBoard[computerHitLoc[0]][computerHitLoc[1]] == -1){
				playerButtons[computerHitLoc[0]][computerHitLoc[1]].setIcon(hitIcon);
				if (playerBoard.isWin()){
					PlayerLoseDialog playerLoseDialog = new PlayerLoseDialog();
					repeat = false;
					break;
				}
				repeat = true;
			}
			if (playerBoard.gameBoard[computerHitLoc[0]][computerHitLoc[1]] == 0){
				playerButtons[computerHitLoc[0]][computerHitLoc[1]].setBackground(new Color(162, 185, 255));
				repeat = false;
			}
		} while (repeat);
	}
	
	// oneRound: �򵥵ĵ���
	public int[] easyComputer(){
		int maxRow = playerBoard.getRowNum();
		int maxCol = playerBoard.getColNum();
		int randomRow = (int)(Math.random() * maxRow);
		int randomCol = (int)(Math.random() * maxCol);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int[] randomLoc = {randomRow, randomCol};
		while (blocksTaken.contains(randomLoc)){
			randomRow = (int)(Math.random() * maxRow);
			randomCol = (int)(Math.random() * maxCol);
			randomLoc[0] = randomRow;
			randomLoc[1] = randomCol;
		}
		blocksTaken.add(randomLoc);
		return randomLoc;
	}
	
	// oneRound: ���ѵĵ���
	public int[] hardComputer(int maxRow, int maxCol){
		// TODO ���ѵĵ���
		int[] finalLoc = {maxRow, maxCol};
		return finalLoc;
	}
	
	// ��������
	public void actionPerformed(ActionEvent e){
		for (int row = 0; row < playerBoard.getRowNum(); row++){
			for (int col = 0; col < playerBoard.getColNum(); col++){
				if (e.getSource() == playerButtons[row][col]){
					addShips(row, col);
				}
			}
		}
		for (int row = 0; row < computerBoard.getRowNum(); row++){
			for (int col = 0; col < computerBoard.getColNum(); col++){
				if (e.getSource() == computerButtons[row][col]){
					oneRound(row, col);
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PVEModeFrame pveMode = new PVEModeFrame();
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
