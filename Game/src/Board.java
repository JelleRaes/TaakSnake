import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener {
	
	private final static int width = 700;
	private final static int heigth = 700;
	
	private final static int size = 25;
	
	private final static int totDots = (width * heigth) / (size * size);
	
	private boolean alive = true;
	
	private Timer timer;
	
	private static int speed = 90;
	
	private Snake snake = new Snake();
	private Food food = new Food();
	
	public Board() {
		
		addKeyListener(new Keys());
		setBackground(Color.BLACK);
		setFocusable(true);
		
		setPreferredSize(new Dimension(width, heigth));
		
		startGame();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	void draw(Graphics g) {
		if (alive == true) {
			g.setColor(Color.green);
			g.fillRect(food.getFoodX(), food.getFoodY(), size, size); // food
			
			for (int i = 0; i < snake.getParts(); i++) {
				if (i == 0) {
					g.setColor(Color.RED);
					g.fillRect(snake.getXpos(i), snake.getYpos(i),size, size);
				} else {
					g.fillRect(snake.getXpos(i), snake.getYpos(i),
							size, size);
				}
			}
			
			Toolkit.getDefaultToolkit().sync();
		} else {
			endGame(g);
		}
	}
	
	void startGame() {
		snake.setParts(1);
		
		for (int i = 0; i < snake.getParts(); i++) {
			snake.setXpos(width / 2);
			snake.setYpos(heigth / 2);
		}
		snake.setMoveRight(true);
		
		food.createFood();
		
		timer = new Timer(speed, this);
		timer.start();
	}
	
	void checkAteFood() {
		
		if ((distance(snake.getXpos(0), food.getFoodX(), 20))
				&& (distance(snake.getYpos(0), food.getFoodY(), 20))) {
			System.out.println("intersection");
			snake.setParts(snake.getParts() + 1);
			food.createFood();
		}
	}
	
	void checkHit() {
		
		for (int i = snake.getParts(); i > 0; i--) {
			if ((i > 5)
					&& (snake.getXpos(0) == snake.getXpos(i) && (snake
							.getYpos(0) == snake.getYpos(i)))) {
				alive = false;
			}
		}
		
		if (snake.getYpos(0) >= heigth) {
			alive = false;
		}
		
		if (snake.getYpos(0) < 0) {
			alive = false;
		}
		
		if (snake.getXpos(0) >= width) {
			alive = false;
		}
		
		if (snake.getXpos(0) < 0) {
			alive = false;
		}
		if (!alive) {
			timer.stop();
		}
	}
	
	void endGame(Graphics g) {
		String message = "Game over  score was:"+snake.getParts();
		g.setColor(Color.red);
		g.drawString(message, (width- message.length()) / 2,heigth / 2);
		System.out.println("Game Ended");
	}
	
	public void actionPerformed(ActionEvent e) {
		if (alive == true) {
			checkAteFood();
			checkHit();
			snake.move();
			System.out.println(snake.getXpos(0) + " " + snake.getYpos(0)
			+ " " + food.getFoodX() + ", " + food.getFoodY());
		}
		repaint();
	}
	
	private class Keys extends KeyAdapter {
		
		
		public void keyPressed(KeyEvent e) {
			
			int key = e.getKeyCode();
			
			if ((key == KeyEvent.VK_LEFT) && (!snake.isMoveRight())) {
				snake.setMoveLeft(true);
				snake.setMoveUp(false);
				snake.setMoveDown(false);
			}
			
			if ((key == KeyEvent.VK_RIGHT) && (!snake.isMoveLeft())) {
				snake.setMoveRight(true);
				snake.setMoveUp(false);
				snake.setMoveDown(false);
			}
			
			if ((key == KeyEvent.VK_UP) && (!snake.isMoveDown())) {
				snake.setMoveUp(true);
				snake.setMoveRight(false);
				snake.setMoveLeft(false);
			}
			
			if ((key == KeyEvent.VK_DOWN) && (!snake.isMoveUp())) {
				snake.setMoveDown(true);
				snake.setMoveRight(false);
				snake.setMoveLeft(false);
			}
			
			if ((key == KeyEvent.VK_ENTER) && (alive == false)) {
				
				alive = true;
				snake.setMoveDown(false);
				snake.setMoveRight(false);
					snake.setMoveLeft(false);
					snake.setMoveUp(false);
            
					startGame();
			}
		}
	}
	
	private boolean distance(int a, int b, int c) {
		return Math.abs((long) a - b) <= c;
	}
	
	public static int getTotDots() {
		return totDots;
	}
	
	public static int getDotSize() {
		return size;
	}
}