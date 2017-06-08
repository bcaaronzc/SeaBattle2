import java.util.Arrays;
import java.util.Scanner;

public class BattleShip {

	private int size;
	private int loc[][];
	private int hitNum;
	
	// ���캯��
	BattleShip(int initSize){
		size = initSize;
		hitNum = 0;
	}
	
	// �������λ��
	public int[][] getRandomLoc(int maxRow, int maxCol){
		int initRow, initCol;
		int randomLoc[][] = {};
		boolean isVertical = true;
		int TorF = (int)(Math.random() * 2);
		// ����жϴ�ֱ��ˮƽ
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
			initRow = (int)(Math.random() * (maxRow - size));
			initCol = (int)(Math.random() * (maxCol));
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
			initRow = (int)(Math.random() * (maxRow));
			initCol = (int)(Math.random() * (maxCol - size));
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
	
	// ȷ�����ص���ȷ��λ��
	public void setLoc(int finalLoc[][]){
		loc = finalLoc;
	}
	
	// �ж��Ƿ񱻻���
	public boolean isHit(int hitLoc[]){
		for (int i = 0; i < size; i++){
			if (loc[i][0] == hitLoc[0] && loc[i][1] == hitLoc[1]){
				hitNum++;
				return true;
			}
		}
		return false;
	}

	// �ж��Ƿ����
	public boolean isSink(){
		if (hitNum >= size){
			return true;
		}
		return false;
	}
	
	// ���ش�С
	public int getSize(){
		return size;
	}
	
	// ����λ��
	public int[][] getLoc(){
		return loc;
	}

	// ��ӡ״̬�������ã�
	public void printStatus(){
		System.out.println("Size = " + size);
		System.out.println("Loc = ");
		for (int i = 0; i < size; i++){
			System.out.println(Arrays.toString(loc[i]));
		}
		System.out.println("\n");
	}
	
	// ������
	public static void main(String args[]){
		BattleShip testShip = new BattleShip(3);
		int testPos[][] = testShip.getRandomLoc(7, 7);
		for (int i = 0; i < 3; i++){
			System.out.println(Arrays.toString(testPos[i]));
		}
		testShip.setLoc(testPos);
		
		Scanner input = new Scanner(System.in);
		
		int row, col;
		System.out.println("���������ĺ�����: ");
		row = input.nextInt();
		System.out.println("����������������: ");
		col = input.nextInt();
		int[] tempLoc = {row, col};
		
		System.out.println(testShip.isHit(tempLoc));
		
		input.close();
	}
}