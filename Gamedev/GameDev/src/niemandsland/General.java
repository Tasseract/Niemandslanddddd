package niemandsland;

public class General extends Piece {
    public General() {
        super("General", 12, 1); // Rank 12
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