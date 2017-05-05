import java.util.Arrays;
import java.util.Scanner;

public class BattleShip {

	private int size;
	private int loc[][];
	private int hitNum;
	
	// 构造函数
	BattleShip(int initSize){
		size = initSize;
		hitNum = 0;
	}
	
	// 生成随机位置
	public int[][] getRandomLoc(int maxRow, int maxCol){
		int initRow, initCol;
		int randomLoc[][] = {};
		boolean isVertical = true;
		int TorF = (int)(Math.random() * 2);
		// 随机判断垂直或水平
		if (TorF == 0){
			isVertical = true;
		}
		else if (TorF == 1){
			isVertical = false;
		}
		else{
			System.out.println("Something is wrong with the random direction.");
		}
		
		if (isVertical){
			//System.out.println("isVertical");
			initRow = (int)(Math.random() * (maxRow - size + 1));
			initCol = (int)(Math.random() * (maxCol + 1));
			if (size == 2){
				int[][] tempLoc = {{initRow, initCol}, {initRow + 1, initCol}};
				randomLoc = tempLoc;
			}
			if (size == 3){
				int[][] tempLoc = {{initRow, initCol}, {initRow + 1, initCol}, {initRow + 2, initCol}};
				randomLoc = tempLoc;
			}
		}
		else{
			//System.out.println("!isVertical");
			initRow = (int)(Math.random() * (maxRow + 1));
			initCol = (int)(Math.random() * (maxCol - size + 1));
			if (size == 2){
				int[][] tempLoc = {{initRow, initCol}, {initRow, initCol + 1}};
				randomLoc = tempLoc;
			}
			if (size == 3){
				int[][] tempLoc = {{initRow, initCol}, {initRow, initCol + 1}, {initRow, initCol + 2}};
				randomLoc = tempLoc;
			}
		}
		
		return randomLoc;
	}
	
	// 确定无重叠后确定位置
	public void setLoc(int finalLoc[][]){
		loc = finalLoc;
	}
	
	// 判断是否被击中
	public boolean isHit(int hitLoc[]){
		for (int i = 0; i < size; i++){
			if (loc[i][0] == hitLoc[0] && loc[i][1] == hitLoc[1]){
				hitNum++;
				return true;
			}
		}
		return false;
	}

	// 判断是否击沉
	public boolean isSink(){
		if (hitNum >= size){
			return true;
		}
		return false;
	}
	
	// 返回大小
	public int getSize(){
		return size;
	}
	
	// 返回位置
	public int[][] getLoc(){
		return loc;
	}
	
	// 打印信息（测试用）
	public void printStatus(){
		System.out.println("Size = " + size);
		System.out.println("Loc = ");
		for (int i = 0; i < size; i++){
			System.out.println(Arrays.toString(loc[i]));
		}
		System.out.print("\n");
	}
	
	public static void main(String[] args) {
		BattleShip testShip = new BattleShip(3);
		int testPos[][] = testShip.getRandomLoc(7, 7);
		for (int i = 0; i < 3; i++){
			System.out.println(Arrays.toString(testPos[i]));
		}
		testShip.setLoc(testPos);
		
		Scanner input = new Scanner(System.in);
		
		int row, col;
		System.out.print("请输入打击横坐标：");
		row = input.nextInt();
		System.out.print("请输入打击纵坐标：");
		col = input.nextInt();
		int[] tempLoc = {row, col};
		
		System.out.println(testShip.isHit(tempLoc));
		
		input.close();
	}
}
