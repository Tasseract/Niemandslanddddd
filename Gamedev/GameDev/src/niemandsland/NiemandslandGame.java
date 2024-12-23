package niemandsland;

import javax.swing.*;

public class NiemandslandGame {
    public static void main(String[] args) {
        JFrame gameBoardFrame = new JFrame("Niemandsland - Game Board");
        InventoryPanel inventoryPanel = new InventoryPanel();
        GameBoard gameBoard = new GameBoard(inventoryPanel);
        gameBoardFrame.add(gameBoard);
        gameBoardFrame.setSize(800, 800);
        gameBoardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameBoardFrame.setVisible(true);

        JFrame inventoryFrame = new JFrame("Niemandsland - Inventory");
        inventoryFrame.add(inventoryPanel);
        inventoryFrame.setSize(300, 400);
        inventoryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inventoryFrame.setVisible(true);
        inventoryFrame.setResizable(false);

        new PlacementController(gameBoard, inventoryPanel);
    }
}