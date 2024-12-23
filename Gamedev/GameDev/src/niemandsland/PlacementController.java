package niemandsland;

public class PlacementController {
    private GameBoard gameBoard;
    private InventoryPanel inventoryPanel;

    public PlacementController(GameBoard gameBoard, InventoryPanel inventoryPanel) {
        this.gameBoard = gameBoard;
        this.inventoryPanel = inventoryPanel;

        // Setup communication between inventory and board
        setupPlacementLogic();
    }

    private void setupPlacementLogic() {
        // This logic is now handled in the GameBoard and InventoryPanel
    }
}