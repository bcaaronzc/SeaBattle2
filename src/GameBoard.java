import java.util.Scanner;

public class GameBoard {
	private static int BOARDROW = 7;
	private static int BOARDCOL = 7;
	private int shipNum = 4;
	int hitNum = 0;
	
	int numSink = 0;

	int[][] gameBoard;
	BattleShip[] battleShips;
	
	// 构造函数
	public GameBoard(){
		gameBoard = new int[BOARDROW][BOARDCOL];
		for (int row = 0; row < BOARDROW; row++){
			for (int col = 0; col < BOARDCOL; col++){
				gameBoard[row][col] = 0;
			}
		}
		battleShips = new BattleShip[shipNum];
		for (int i = 0; i < shipNum; i++){
			if (i < (shipNum / 2)){
				battleShips[i] = new BattleShip(2);
			}
			else{
				battleShips[i] = new BattleShip(3);
			}
		}
		arrangeShips();
	}
	
	// 第二个构造函数
	public GameBoard(int initShipNum){
		shipNum = initShipNum;
		battleShips = new BattleShip[shipNum];
		gameBoard = new int[BOARDROW][BOARDCOL];
		for (int row = 0; row < BOARDROW; row++){
			for (int col = 0; col < BOARDCOL; col++){
				gameBoard[row][col] = 0;
			}
		}
		for (int i = 0; i < shipNum; i++){
			if (i < (shipNum / 2)){
				battleShips[i] = new BattleShip(2);
			}
			else{
				battleShips[i] = new BattleShip(3);
			}
		}
	}

	// 添加单个船
	public void addOneShip(int shipNum, int[][] initLoc){
		battleShips[shipNum].setLoc(initLoc);
	}
	
	// 确定船的位置
	public void arrangeShips(){
		int counter = 0;	// 测试用
		boolean available = true;
		for (int i = 0; i < shipNum; i++){
			counter = 0;
			do{
				System.out.println("Ship " + (i + 1) + ": try " + ++counter);
				available = isLocAvailable(i);
			} while(!available);
		}
	}
	
	// 判断位置是否空余
	public boolean isLocAvailable(int shipNo){
		int tempLoc[][] = battleShips[shipNo].getRandomLoc(BOARDROW, BOARDCOL);
		if (battleShips[shipNo].getSize() == 2){
			if (gameBoard[tempLoc[0][0]][tempLoc[0][1]] == 0
					&& gameBoard[tempLoc[1][0]][tempLoc[1][1]] == 0){
				gameBoard[tempLoc[0][0]][tempLoc[0][1]] = shipNo + 1;
				gameBoard[tempLoc[1][0]][tempLoc[1][1]] = shipNo + 1;
				battleShips[shipNo].setLoc(tempLoc);
				return true;
			}
		}
		if (battleShips[shipNo].getSize() == 3){
			if (gameBoard[tempLoc[0][0]][tempLoc[0][1]] == 0
					&& gameBoard[tempLoc[1][0]][tempLoc[1][1]] == 0
					&& gameBoard[tempLoc[2][0]][tempLoc[2][1]] == 0){
				gameBoard[tempLoc[0][0]][tempLoc[0][1]] = shipNo + 1;
				gameBoard[tempLoc[1][0]][tempLoc[1][1]] = shipNo + 1;
				gameBoard[tempLoc[2][0]][tempLoc[2][1]] = shipNo + 1;
				battleShips[shipNo].setLoc(tempLoc);
				return true;
			}
		}
		return false;
	}

	// 发射炮弹
	public void fireCannon(int[] hitLoc){
		hitNum++;
		for (int i = 0; i < shipNum; i++){
			if (battleShips[i].isHit(hitLoc)){
				System.out.println("You hit battle ship " + i + "!");
				gameBoard[hitLoc[0]][hitLoc[1]] = -1;
				if (battleShips[i].isSink()){
					System.out.println("Battle ship " + (i + 1) + " is destroyed!");
					numSink++;
				}
			}
		}
	}

	// 判断是否获胜
	public boolean isWin(){
		if (numSink >= shipNum){
			return true;
		}
		return false;
	}
	
	// 获取打击次数
	public int getScore(){
		return (BOARDROW * BOARDCOL - hitNum);
	}
	
	// 获取游戏板宽度
	public int getRowNum(){
		return BOARDROW;
	}
	
	// 获取游戏板长度
	public int getColNum(){
		return BOARDCOL;
	}
	
	// 获取打击位置（测试用）
	public int[] getHitLoc(){
		int[] tempHit = new int[2];
		Scanner input = new Scanner(System.in);
		System.out.print("输入打击横坐标: ");
		tempHit[0] = input.nextInt();
		System.out.print("输入打击纵坐标: ");
		tempHit[1] = input.nextInt();
		return tempHit;
	}
	
	// 打印游戏板（测试用）
	public void printBoard(){
		System.out.println("-------The status of the board now:-------");
		for (int row = 0; row < BOARDROW; row++){
			System.out.print("             ");
			for (int col = 0; col < BOARDCOL; col++){
				System.out.print(gameBoard[row][col] + " ");
			}
			System.out.print("\n");
		}
		System.out.println("------------------------------------------");
	}
	
	public static void main(String[] args) {
		GameBoard gameBoard = new GameBoard();
		for (int i = 0; i < gameBoard.shipNum; i++){
			gameBoard.battleShips[i].printStatus();
		}
		gameBoard.printBoard();
		
		do{
			gameBoard.fireCannon(gameBoard.getHitLoc());
			gameBoard.printBoard();
		} while(!gameBoard.isWin());
		
		System.out.println("You Win!");
		System.out.println("You took " + gameBoard.getScore() + " steps.");
		
	}

}
