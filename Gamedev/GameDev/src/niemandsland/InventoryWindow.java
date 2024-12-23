package niemandsland;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class InventoryWindow extends JFrame {
    private JLabel playerTurnLabel; // Displays the current player's turn
    private HashMap<String, Piece> piecesMap; // Maps button names to Piece objects
    private Piece selectedPiece; // Currently selected piece
    private JPanel piecesPanel; // Panel for piece buttons

    public InventoryWindow() {
        setTitle("Niemandsland - Inventory");
        setSize(300, 400);
        setLayout(new BorderLayout());

        piecesMap = new HashMap<>();
        selectedPiece = null;

        // Top Panel: Player's Turn
        playerTurnLabel = new JLabel("Player 1's Turn", SwingConstants.CENTER);
        playerTurnLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(playerTurnLabel, BorderLayout.NORTH);

        // Center Panel: Piece Buttons
        piecesPanel = new JPanel();
        piecesPanel.setLayout(new GridLayout(0, 1));
        add(piecesPanel, BorderLayout.CENTER);

        // Add pieces to inventory
        initializePieces();

        // Bottom Panel: Clear Selection Button
        JButton clearButton = new JButton("Clear Selection");
        clearButton.addActionListener(e -> clearSelectedPiece());
        add(clearButton, BorderLayout.SOUTH);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Initialize pieces and add them to the UI
    private void initializePieces() {
        addPieceToInventory("Lady Assassin", 1);
        addPieceToInventory("General", 1);
        addPieceToInventory("Trooper", 2);
        // Add more pieces here as needed...
    }

    // Add a piece to the inventory based on the piece name
    private void addPieceToInventory(String name, int count) {
        Piece piece;

        // Instantiate the appropriate subclass based on the piece name
        switch (name) {
            case "Lady Assassin":
                piece = new LadyAssassin();
                break;
            case "General":
                piece = new General();
                break;
            case "Trooper":
                piece = new Trooper();
                break;
            default:
                throw new IllegalArgumentException("Unknown piece type: " + name);
        }

        // Reset the count for this piece
       // piece.resetCount(count);

        // Add the piece to the inventory map
        piecesMap.put(name, piece);

        // Create a button for the piece
        JButton pieceButton = new JButton(name + " (x" + count + ")");
        pieceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectPiece(name, pieceButton);
            }
        });

        piecesPanel.add(pieceButton);
    }

    // Update button label to reflect remaining count
    public void updatePieceButton(Piece piece) {
        for (Component comp : piecesPanel.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                if (button.getText().startsWith(piece.getName())) {
                    button.setText(piece.getName() + " (x" + piece.getRemainingCount() + ")");
                }
            }
        }
    }

    // Select a piece and highlight the button
    private void selectPiece(String pieceName, JButton button) {
        selectedPiece = piecesMap.get(pieceName);
        JOptionPane.showMessageDialog(this, "Selected: " + pieceName);
    }

    // Clear the currently selected piece
    private void clearSelectedPiece() {
        selectedPiece = null;
        JOptionPane.showMessageDialog(this, "Selection Cleared!");
    }

    // Return the selected piece
    public Piece getSelectedPiece() {
        return selectedPiece;
    }

    // Update the player turn label
    public void updatePlayerLabel(String text) {
        playerTurnLabel.setText(text);
    }
}