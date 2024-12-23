package niemandsland;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameBoard extends JPanel {
    private final int rows = 8;
    private final int cols = 9;
    private Piece[][] boardGrid;
    private InventoryPanel inventoryPanel;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private JPanel gridPanel;
    private boolean isPlacementPhase; // Track if it's the placement phase
    private JButton selectedTile; // Track the currently selected tile for dragging
    private int startX, startY; // Track starting coordinates for moving pieces

    public GameBoard(InventoryPanel inventoryPanel) {
        this.inventoryPanel = inventoryPanel;
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        currentPlayer = player1;
        boardGrid = new Piece[rows][cols];
        isPlacementPhase = true; // Start with the placement phase
        setLayout(new BorderLayout());

        gridPanel = new JPanel(new GridLayout(rows, cols));
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JButton tile = new JButton();
                tile.setBackground(Color.LIGHT_GRAY);
                tile.setOpaque(true);
                tile.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                final int x = i;
                final int y = j;

                tile.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        handleMousePressed(x, y, tile);
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        handleMouseReleased(x, y, tile);
                    }
                });

                // Ensure clicking on the tile also works for placement
                tile.addActionListener(e -> handleTileClick(x, y));

                gridPanel.add(tile);
            }
        }

        add(gridPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton doneButton = new JButton("Done");
        doneButton.addActionListener(e -> handleDoneButtonClick());
        buttonPanel.add(doneButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void handleMousePressed(int x, int y, JButton tile) {
        if (!isPlacementPhase && boardGrid[x][y] != null) {
            if ((currentPlayer == player1 && boardGrid[x][y].getOwner() == player1) ||
                (currentPlayer == player2 && boardGrid[x][y].getOwner() == player2)) {
                selectedTile = tile;
                startX = x;
                startY = y;
                System.out.println("Selected piece at (" + startX + ", " + startY + ") by " + currentPlayer.getName());
            }
        }
    }

    private void handleMouseReleased(int x, int y, JButton tile) {
        if (selectedTile != null && (x != startX || y != startY)) {
            System.out.println("Trying to move piece to (" + x + ", " + y + ") by " + currentPlayer.getName());
            if (isValidMove(x, y)) {
                Piece piece = boardGrid[startX][startY];
                movePiece(piece, x, y, startX, startY);
                switchPlayer();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid move!");
            }
            selectedTile = null;
        }
    }

    private void handleTileClick(int x, int y) {
        if (isPlacementPhase) {
            handlePlacementPhase(x, y);
        }
    }

    private void handlePlacementPhase(int x, int y) {
        Piece selectedPiece = inventoryPanel.getSelectedPiece();

        if (selectedPiece != null && selectedPiece.getRemainingCount() > 0) {
            if (isValidPlacement(x, y) && placePiece(selectedPiece, x, y)) {
                selectedPiece.setOwner(currentPlayer); // Set the owner of the piece
                currentPlayer.updatePieceCount(selectedPiece.getName());
                inventoryPanel.updatePieceCount(selectedPiece);
                inventoryPanel.clearSelectedPiece();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid placement or tile occupied!");
            }
        } else {
            JOptionPane.showMessageDialog(this, currentPlayer.getName() + ": No piece selected or piece count is zero!");
        }
    }

    private void handleDoneButtonClick() {
        if (isPlacementPhase) {
            if (currentPlayer == player1) {
                if (player1.hasAllPiecesPlaced()) {
                    inventoryPanel.replenishInventory();
                    switchPlayer();
                } else {
                    JOptionPane.showMessageDialog(this, "Player 1 has not finished placing pieces!");
                }
            } else if (currentPlayer == player2) {
                if (player2.hasAllPiecesPlaced()) {
                    switchPlayer();
                    isPlacementPhase = false; // End placement phase
                    JOptionPane.showMessageDialog(this, "Placement phase completed. Player 1's turn to move.");
                } else {
                    JOptionPane.showMessageDialog(this, "Player 2 has not finished placing pieces!");
                }
            }
        } else {
            switchPlayer();
        }
    }

    private boolean isValidPlacement(int x, int y) {
        if (currentPlayer == player1) {
            return x >= 0 && x < 3 && y >= 0 && y < cols && boardGrid[x][y] == null;
        } else {
            return x >= 5 && x < rows && y >= 0 && y < cols && boardGrid[x][y] == null;
        }
    }

    private boolean isValidMove(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols && boardGrid[x][y] == null;
    }

    private void movePiece(Piece piece, int x, int y, int startX, int startY) {
        boardGrid[startX][startY] = null;
        boardGrid[x][y] = piece;
        updateTileDisplay(startX, startY, null);
        updateTileDisplay(x, y, piece);
    }

    private boolean placePiece(Piece piece, int x, int y) {
        if (isValidPlacement(x, y)) {
            boardGrid[x][y] = piece;
            updateTileDisplay(x, y, piece);
            return true;
        } else {
            return false;
        }
    }

    private void updateTileDisplay(int x, int y, Piece piece) {
        JButton tileButton = (JButton) gridPanel.getComponent(x * cols + y);
        if (piece != null) {
            tileButton.setText(piece.getName());
            tileButton.setForeground(Color.BLACK);
        } else {
            tileButton.setText("");
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;

        System.out.println("Switched to " + currentPlayer.getName());
        JOptionPane.showMessageDialog(this, currentPlayer.getName() + "'s turn!");

        inventoryPanel.updatePlayerLabel(currentPlayer.getName() + "'s Turn");
    }
}
