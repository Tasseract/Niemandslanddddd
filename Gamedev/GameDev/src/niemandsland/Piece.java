package niemandsland;

public abstract class Piece {
    private String name;
    private int rank; // Rank used to determine defeat capability
    private int remainingCount; // Count of how many pieces are available
    private final int originalCount; // Track original count for replenishment
    private Player owner; // Track the owner of the piece

    public Piece(String name, int rank, int remainingCount) {
        this.name = name;
        this.rank = rank;
        this.remainingCount = remainingCount;
        this.originalCount = remainingCount; // Initialize original count
    }

    public String getName() {
        return name;
    }

    public int getRank() {
        return rank; // Added getter for rank
    }

    public int getRemainingCount() {
        return remainingCount; // Getter for remaining count
    }

    public void decrementCount() {
        if (remainingCount > 0) {
            remainingCount--; // Decrement count when a piece is placed
        }
    }

    public void resetCount() {
        this.remainingCount = originalCount; // Reset to original count
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public abstract boolean canMove(int startX, int startY, int endX, int endY);
    public abstract boolean canDefeat(Piece opponent);
}
