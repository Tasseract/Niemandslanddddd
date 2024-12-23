package niemandsland;

import java.util.HashMap;

public class Player {
    private String name;
    private HashMap<String, Piece> piecesMap; // Track pieces for this player

    public Player(String name) {
        this.name = name;
        piecesMap = new HashMap<>();
        initializePieces(); // Initialize player pieces
    }

    private void initializePieces() {
        // Example pieces with their respective counts
        piecesMap.put("Lady Assassin", new LadyAssassin());
        piecesMap.put("General", new General());
        piecesMap.put("Trooper", new Trooper());
        piecesMap.put("BombPiece", new BombPiece());
        piecesMap.put("Wall", new WallPiece());
        piecesMap.put("Private", new Private());
        piecesMap.put("Sergeant", new Sergeant());
        piecesMap.put("Corporal", new Corporal());
        piecesMap.put("Brigadier", new Brigadier());
        piecesMap.put("Captain", new Captain());
        piecesMap.put("Major", new Major());
        piecesMap.put("Lieutenant", new Lieutenant());
        piecesMap.put("Colonel", new Colonel());
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Piece> getPiecesMap() {
        return piecesMap;
    }

    public Piece getPiece(String pieceName) {
        return piecesMap.get(pieceName);
    }

    public void updatePieceCount(String pieceName) {
        Piece piece = piecesMap.get(pieceName);
        if (piece != null) {
            piece.decrementCount(); // Decrement count when a piece is placed
            System.out.println(pieceName + " count after decrement: " + piece.getRemainingCount()); // Debug statement
        }
    }

    public boolean hasPiecesLeft() {
        for (Piece piece : piecesMap.values()) {
            if (piece.getRemainingCount() > 0) {
                return true; // Player has pieces remaining
            }
        }
        return false; // No pieces left
    }

    public boolean hasAllPiecesPlaced() {
        for (Piece piece : piecesMap.values()) {
            if (piece.getRemainingCount() > 0) {
                System.out.println(piece.getName() + " still has pieces left: " + piece.getRemainingCount()); // Debug statement
                return false; // Player has not placed all pieces
            }
        }
        System.out.println("All pieces placed."); // Debug statement
        return true; // All pieces are placed
    }

    public void replenishInventory() {
        for (Piece piece : piecesMap.values()) {
            piece.resetCount(); // Reset piece counts to their original values
        }
    }
}
