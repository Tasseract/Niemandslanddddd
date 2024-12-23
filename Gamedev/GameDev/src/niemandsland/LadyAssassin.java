package niemandsland;

public class LadyAssassin extends Piece {
    public LadyAssassin() {
        super("Lady Assassin", 1, 1); // Rank 1
    }

    @Override
    public boolean canMove(int startX, int startY, int endX, int endY) {
        return Math.abs(startX - endX) <= 1 && Math.abs(startY - endY) <= 1; // 1-tile move
    }

    @Override
    public boolean canDefeat(Piece opponent) {
        return true; // Can defeat any piece
    }
}