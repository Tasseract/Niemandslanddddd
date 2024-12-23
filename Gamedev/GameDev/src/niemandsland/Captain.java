package niemandsland;

public class Captain extends Piece {
    public Captain() {
        super("Captain", 8, 1); // Rank 8
    }

    @Override
    public boolean canMove(int startX, int startY, int endX, int endY) {
        return Math.abs(startX - endX) <= 1 && Math.abs(startY - endY) <= 1; // 1-tile move
    }

    @Override
    public boolean canDefeat(Piece opponent) {
        return this.getRank() > opponent.getRank(); // Defeats opponents with lower rank
    }
}