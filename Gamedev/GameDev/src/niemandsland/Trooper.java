package niemandsland;

public class Trooper extends Piece {
    public Trooper() {
        super("Trooper", 3, 4); // Rank 3, 4 pieces available
    }

    @Override
    public boolean canMove(int startX, int startY, int endX, int endY) {
        return (Math.abs(startX - endX) == 1 && startY == endY) || 
               (Math.abs(startY - endY) == 1 && startX == endX); // Move vertically or horizontally by one tile
    }

    @Override
    public boolean canDefeat(Piece opponent) {
        return this.getRank() > opponent.getRank(); // Defeats opponents with lower rank
    }
}