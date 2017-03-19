
public class Snake {
	private final int[] x = new int[Board.getTotDots()];
	private final int[] y = new int[Board.getTotDots()];
	
	private boolean moveLeft = false;
	private boolean moveRight = false;
	private boolean moveUp = false;
	private boolean moveDown = false;
	
	private int parts = 0;
	
	public int getXpos(int index){
		return x[index];
	}
	
	public int getYpos(int index){
		return y[index];
	}
	
	public void setXpos(int i){
		x[0] = i;
	}
	
	public void setYpos(int i){
		y[0] = i;
	}
	
	public boolean isMoveLeft() {
	    return moveLeft;
	}

	public void setMoveLeft(boolean moveLeft) {
	    this.moveLeft = moveLeft;
	}

	public boolean isMoveRight() {
	    return moveRight;
	}

	public void setMoveRight(boolean moveRight) {
	    this.moveRight = moveRight;
	}

	public boolean isMoveUp() {
	    return moveUp;
	}

	public void setMoveUp(boolean moveUp) {
	    this.moveUp = moveUp;
	}

	public boolean isMoveDown() {
	    return moveDown;
	}

	public void setMoveDown(boolean moveDown) {
	    this.moveDown = moveDown;
	}
	
	public int getParts(){
		return parts;
	}
	
	public void setParts(int p){
		parts = p;
	}
	
	public void move() {
		for (int i = parts; i > 0; i--) {
	    	x[i] = x[(i - 1)];
	    	y[i] = y[(i - 1)];
	    }
	    
	    if (moveLeft) {
	        x[0] -= Board.getDotSize();
	    }

	    if (moveRight) {
	        x[0] += Board.getDotSize();
	    }

	    if (moveDown) {
	        y[0] += Board.getDotSize();
	    }
	    
	    if (moveUp) {
	        y[0] -= Board.getDotSize();
	    }
	}
}
