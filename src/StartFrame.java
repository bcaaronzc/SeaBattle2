import javax.swing.JButton;
import javax.swing.JFrame;

public class StartFrame extends JFrame{
	JButton start, highScore, quit;
	
	public StartFrame(){
		this.setBounds(200, 200, 600, 400);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		StartFrame startFrame = new StartFrame();
	}
	
}
