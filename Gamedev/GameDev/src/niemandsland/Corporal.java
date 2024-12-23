package niemandsland;

public class Corporal extends Piece {
    public Corporal() {
        super("Corporal", 6, 1); // Rank 6
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