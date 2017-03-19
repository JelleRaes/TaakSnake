
public class Food {
	private int foodX;
	private int foodY;

	private final int RANDOMPOSITION = 27;

	public void createFood() {
	    int location = (int) (Math.random() * RANDOMPOSITION);
	    foodX = ((location * Board.getDotSize()));

	    location = (int) (Math.random() * RANDOMPOSITION);
	    foodY = ((location * Board.getDotSize()));
	}

	public int getFoodX() {
	    return foodX;
	}

	public int getFoodY() {
	    return foodY;
	}
	
}
