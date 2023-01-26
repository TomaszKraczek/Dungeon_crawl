package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.buttons.ExportBtn;
import com.codecool.dungeoncrawl.buttons.ImportBtn;
import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Monster;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.items.potion.Potion;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Optional;
import java.sql.SQLException;


public class Main extends Application {
    String filename = levelmaps.get(1);
    InputStream is;
    GameMap map;
    Canvas canvas;
    GraphicsContext context;
    Label healthLabel = new Label();
    Label armorLabel = new Label();
    Label attackPowerLabel = new Label();
    Label expLabel = new Label();
    Label playerLvlLabel = new Label();
    Button pickUpButton = new Button("Pick up");
    ExportBtn exportBtn = new ExportBtn();
    ImportBtn importBtn = new ImportBtn();
    ObservableList<String> itemList;
    ListView<String> listView = new ListView<>();
    private static HashMap<Integer, String> levelmaps = new HashMap<>();
/* blok static wykona się przy inicjalizacji KLASY! zawsze, dając pewność,
że w momencie gdy potrzebujemy levelmaps jest już gotowe  */
    static {
        levelmaps.put(1, "map.txt");
        levelmaps.put(2, "map3.txt");
    }

    private static Stage stage;
    GameDatabaseManager dbManager = new GameDatabaseManager();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        showMenu(primaryStage);
    }

    private void showMenu(Stage menuStage) {
        Menu m = new Menu("Menu");

        MenuItem m1 = new MenuItem("Play new game");
        MenuItem m2 = new MenuItem("Play saved game");
        MenuItem m3 = new MenuItem("Exit");

        m.getItems().add(m1);
        m.getItems().add(m2);
        m.getItems().add(m3);

        MenuBar mb = new MenuBar();
        mb.getMenus().add(m);

        VBox vb = new VBox(mb);
        Scene sc = new Scene(vb, 300, 300);
        menuStage.setScene(sc);
        menuStage.show();

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                    startGame(menuStage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        EventHandler<ActionEvent> event3 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                    exit();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };

        m1.setOnAction(event);
        m2.setOnAction(event);
        m3.setOnAction(event3);


    }

    //    @Override
    public void startGame(Stage primaryStage) throws Exception {
        dbManager.setup();
        setupMapTxt("map.txt");
        canvasSetup();
        createPlayer();
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        addLabels(ui);

        addPickupButton(ui);
        exportBtn.addExportButton(ui, map.getPlayer());
        importBtn.addImportButton(ui);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        stage = primaryStage;
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
        scene.setOnKeyReleased(this::onKeyReleased);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void setupMapTxt(String filename) {
        is = MapLoader.class.getResourceAsStream("/" + filename);
        map = new MapLoader().loadMap(is);
    }

    private void createPlayer() {
        Cell playerCell = new Cell(map, 18,18,CellType.FLOOR);
        map.setPlayer(new Player(1,playerCell, 50, "Tomasz"));
        refresh();
    }

    private void canvasSetup() {
        canvas = new Canvas(
                map.getWidth() * Tiles.TILE_WIDTH,
                map.getHeight() * Tiles.TILE_WIDTH);
        context = canvas.getGraphicsContext2D();
    }

    private void refresh() {
        int playerXOffset = map.getWidth() / 2;
        int playerYOffset = map.getHeight() / 2;

        drawMap(playerXOffset, playerYOffset);

        map.getPlayer().updatePlayerStats();

        updateLabels();

        ChangeMapIfDoorOpened();

        if (map.getPlayer().isPlayerKilled()) {
            gameOver();
        }
        if (map.getPlayer().hasCrown()) {
            playerWins();
        }
    }

    private void updateLabels() {
        healthLabel.setText("" + map.getPlayer().getHealth());
        armorLabel.setText("" + map.getPlayer().getArmorPoints());
        attackPowerLabel.setText("" + map.getPlayer().getAttackStrength());
        playerLvlLabel.setText("" + map.getPlayer().getPlayerLvl());
        expLabel.setText("" + map.getPlayer().getPlayerExp());
        listView.setItems(FXCollections.observableArrayList(map.getPlayer().getItemsNames()));
    }

    private void drawMap(int playerXOffset, int playerYOffset) {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                Tiles.drawTile(context, cell, x - map.getPlayer().getX() + playerXOffset, y - map.getPlayer().getY() + playerYOffset);
            }
        }
    }

    private void ChangeMapIfDoorOpened() {
        if (map.getPlayer().getCell().getType() == CellType.OPENED_EXIT) {
            MapLoader mapLoader = new MapLoader();
            filename = levelmaps.get(map.getPlayer().incrementPlayerLevel());
            is = MapLoader.class.getResourceAsStream("/" + filename);
            map = mapLoader.loadMap(is);

        }
    }

    private void addLabels(GridPane ui) {
        addStatsLabels(ui);
        addItemList(ui);
    }

    private void addItemList(GridPane ui) {
        ui.add(new Label("Items: "), 0, 7);
        ui.add(listView, 0, 8);
        listView.setPrefSize(150, 400);
        listView.setItems(itemList);
        listView.setFocusTraversable(false);
    }

    private void addStatsLabels(GridPane ui) {
        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);
        ui.add(new Label("Attack power: "), 0, 1);
        ui.add(attackPowerLabel, 1, 1);
        ui.add(new Label("Armor :"), 0, 2);
        ui.add(armorLabel, 1, 2);
        ui.add(new Label("-----------"), 0, 3);
        ui.add(new Label("Player level: "), 0, 4);
        ui.add(playerLvlLabel, 1, 4);
        ui.add(new Label("Player exp: "), 0, 5);
        ui.add(expLabel, 1, 5);
        ui.add(new Label("-----------"), 0, 6);
    }

    private void addPickupButton(GridPane ui) {
        ui.add(pickUpButton, 0, 400);
        pickUpButton.setFocusTraversable(false);
        pickUpButton.setOnAction(event -> {
            pickUpItem();
        });
    }


    private void pickUpItem() {
        if (map.getPlayer().getCell().getItem() != null) {
            if (map.getPlayer().getCell().getItem().getName() == "potion") {
                map.getPlayer().drinkPotion((Potion) map.getPlayer().getCell().getItem());
            } else {
                map.getPlayer().addItemToEq(map.getPlayer().getCell().getItem());
            }
            map.getPlayer().getCell().setItem(null);
        } else {
            showNoItemMessage("", "There is no item.");
        }
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP -> map.getPlayer().move(0, -1);
            case DOWN -> map.getPlayer().move(0, 1);
            case LEFT -> map.getPlayer().move(-1, 0);
            case RIGHT -> map.getPlayer().move(1, 0);
            case SPACE -> pickUpItem();
        }
        for (Monster monster : map.getMonsters()) {
            monster.move();
        }
        map.getPlayer().checkCellForCrown();
        refresh();

    }

    private void gameOver() {
        getAlertWindow("Game over", "You were killed by a monster!");
    }

    private void playerWins() {
        getAlertWindow("Congratulations", "You won!");
    }

    private void getAlertWindow(String title, String header) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText("Do you want to try again?");
        Optional<ButtonType> result = alert.showAndWait();
        ButtonType button = result.orElse(ButtonType.OK);
        if (button == ButtonType.OK) {
            stage.close();
            Platform.runLater(() -> {
                try {
                    new Main().start(new Stage());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        } else {
            System.exit(0);
        }
    }



    private void showNoItemMessage(String title, String header) {
        Alert alert = new Alert((Alert.AlertType.INFORMATION));
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.showAndWait();
    }

    private void exit() {
        try {
            stop();
        } catch (Exception e) {
            System.exit(1);
        }
        System.exit(0);
    }

    private void setupDbManager() {
        GameDatabaseManager dbManager = new GameDatabaseManager();
        try {
            dbManager.setup();
        } catch (SQLException ex) {
            System.out.println("Cannot connect to database.");
        }
    }

    private void onKeyReleased(KeyEvent keyEvent) {
        KeyCombination exitCombinationMac = new KeyCodeCombination(KeyCode.W, KeyCombination.SHORTCUT_DOWN);
        KeyCombination exitCombinationWin = new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN);
        if (exitCombinationMac.match(keyEvent)
                || exitCombinationWin.match(keyEvent)
                || keyEvent.getCode() == KeyCode.ESCAPE) {
            exit();
        }
        KeyCombination saveGame = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_ANY);

        if (saveGame.match(keyEvent)) {
            String saveName = getSaveNameFromWindow();
            dbManager.saveGame(saveName, map);
        }
    }

    private String getSaveNameFromWindow() {
        TextInputDialog saveWindow = new TextInputDialog();
        saveWindow.setTitle("Save game");
        saveWindow.setHeaderText("");
        Button okButton = (Button) saveWindow.getDialogPane().lookupButton(ButtonType.OK);
        okButton.setText("Save");
        saveWindow.getDialogPane().setContentText("Save name");
        Optional<String> result = saveWindow.showAndWait();
        if(result.isPresent()){
            return result.get();
        }
        return null;
    }
}
