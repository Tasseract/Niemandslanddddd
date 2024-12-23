package niemandsland;

import javax.swing.*;
import java.awt.*;

public class GameSetup extends JFrame {
    private GameBoard board;
    private InventoryPanel inventoryPanel;
    private PlacementController placementController;

    public GameSetup() {
        setTitle("Niemandsland - Setup Phase");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Components
        inventoryPanel = new InventoryPanel();
        board = new GameBoard(inventoryPanel); // Pass the inventoryPanel to GameBoard
        placementController = new PlacementController(board, inventoryPanel);

        add(board, BorderLayout.CENTER);
        add(inventoryPanel, BorderLayout.EAST);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new GameSetup();
    }
}
