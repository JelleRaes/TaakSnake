import java.awt.EventQueue;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Game extends JFrame {

public Game() {
	add(new Board());
	setResizable(false);
	pack();
	setTitle("Snake");
	setLocationRelativeTo(null);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			JFrame frame = new Game();
			frame.setVisible(true);
		}
	});
}
}