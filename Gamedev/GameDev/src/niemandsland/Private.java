package niemandsland;

public class Private extends Piece {
    public Private() {
        super("Private", 2, 6); // Rank 2, 8 pieces available
    }

    @Override
    public boolean canMove(int startX, int startY, int endX, int endY) {
        return Math.abs(startX - endX) == 1 && Math.abs(startY - endY) == 1; // 1-tile move
    }

    @Override
    public boolean canDefeat(Piece opponent) {
        return this.getRank() > opponent.getRank(); // Defeats opponents with lower rank
    }
}