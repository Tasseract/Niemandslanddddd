package niemandsland;

public class Colonel extends Piece {
    public Colonel() {
        super("Colonel", 11, 1); // Rank 11
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