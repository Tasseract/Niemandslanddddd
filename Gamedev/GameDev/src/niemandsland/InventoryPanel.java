package niemandsland;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class InventoryPanel extends JPanel {
    private HashMap<String, Piece> piecesMap; // Track pieces
    private Piece selectedPiece; // Currently selected piece
    private JLabel playerTurnLabel; // Displays the current player's turn
    private JPanel piecesPanel; // Panel for piece buttons

    public InventoryPanel() {
        piecesMap = new HashMap<>();
        setLayout(new BorderLayout()); // Use BorderLayout
        setPreferredSize(new Dimension(300, 800)); // Panel size

        // Initialize and add the player turn label
        playerTurnLabel = new JLabel("Player 1's Turn", SwingConstants.CENTER);
        playerTurnLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(playerTurnLabel, BorderLayout.NORTH);

        // Initialize the pieces panel and add it
        piecesPanel = new JPanel();
        piecesPanel.setLayout(new GridLayout(0, 1)); // Vertical layout
        add(piecesPanel, BorderLayout.CENTER);

        initializePieces(); // Add default pieces
    }

    private void initializePieces() {
        addPieceToInventory(new LadyAssassin());
        addPieceToInventory(new General());
        addPieceToInventory(new Trooper());
        addPieceToInventory(new BombPiece());
        addPieceToInventory(new WallPiece());
        addPieceToInventory(new Private());
        addPieceToInventory(new Sergeant());
        addPieceToInventory(new Corporal());
        addPieceToInventory(new Brigadier());
        addPieceToInventory(new Captain());
        addPieceToInventory(new Major());
        addPieceToInventory(new Lieutenant());
        addPieceToInventory(new Colonel());
    }

    private void addPieceToInventory(Piece piece) {
        piecesMap.put(piece.getName(), piece);

        JButton pieceButton = new JButton(piece.getName() + " (x" + piece.getRemainingCount() + ")"); // Displaying count
        pieceButton.addActionListener(e -> {
            selectedPiece = piece; // Set the selected piece
            JOptionPane.showMessageDialog(this, "Selected: " + piece.getName());
        });

        piecesPanel.add(pieceButton);
    }

    public Piece getSelectedPiece() {
        return selectedPiece;
    }

    public void clearSelectedPiece() {
        selectedPiece = null; // Clear the currently selected piece
    }

    public void updatePieceCount(Piece piece) {
        piece.decrementCount(); // Decrement the count
        updateButtonLabels(); // Update button labels after decrement
    }

    public void replenishInventory() {
        for (Piece piece : piecesMap.values()) {
            piece.resetCount(); // Reset piece counts as needed
        }
        updateButtonLabels(); // Refresh button labels
    }

    public void updatePlayerLabel(String text) {
        playerTurnLabel.setText(text);
    }

    public void updateButtonLabels() {
        for (Component comp : piecesPanel.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                String pieceName = button.getText().split(" \\(")[0]; // Extract piece name
                Piece piece = piecesMap.get(pieceName);
                if (piece != null) {
                    button.setText(piece.getName() + " (x" + piece.getRemainingCount() + ")"); // Update button label
                    button.setEnabled(piece.getRemainingCount() > 0); // Enable or disable button
                    button.setForeground(piece.getRemainingCount() > 0 ? Color.BLACK : Color.GRAY); // Grey out if no pieces left
                }
            }
        }
    }
}
