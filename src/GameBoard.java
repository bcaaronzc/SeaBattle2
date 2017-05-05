
public class GameBoard {
	static int BOARDROW = 7;
	static int BOARDCOL = 7;
	static int shipNum = 3;

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
	}

}
