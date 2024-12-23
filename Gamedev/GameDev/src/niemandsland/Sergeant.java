package niemandsland;

public class Sergeant extends Piece {
    public Sergeant() {
        super("Sergeant", 4, 2); // Rank 4, 2 pieces available
    }

    @Override
    public boolean canMove(int startX, int startY, int endX, int endY) {
        return Math.abs(startX - endX) == 1 && startY == endY; // Move vertically
    }

    @Override
    public boolean canDefeat(Piece opponent) {
        return this.getRank() > opponent.getRank(); // Defeats opponents with lower rank
    }
}