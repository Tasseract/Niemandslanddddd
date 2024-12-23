package niemandsland;

public class WallPiece extends Piece {
    public WallPiece() {
        super("Wall", 0, 2); // Rank 0, 2 pieces available
    }

    @Override
    public boolean canMove(int startX, int startY, int endX, int endY) {
        return false; // Wall cannot move
    }

    @Override
    public boolean canDefeat(Piece opponent) {
        return false; // Wall cannot defeat any piece
    }
}