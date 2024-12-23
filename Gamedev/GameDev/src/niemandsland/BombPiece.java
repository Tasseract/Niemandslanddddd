package niemandsland;

public class BombPiece extends Piece {
    public BombPiece() {
        super("BombPiece", 0, 2); // Rank 0, 2 pieces available
    }

    @Override
    public boolean canMove(int startX, int startY, int endX, int endY) {
        return false; // Bomb cannot move
    }

    @Override
    public boolean canDefeat(Piece opponent) {
        return true; // Bomb can defeat any piece
    }
}