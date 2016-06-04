package timer.controller;

import ch.qos.logback.classic.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;
import timer.Main;
import timer.model.IndexedLabel;
import timer.model.Session;
import timer.model.Timer;

import java.io.IOException;

/**
 * Created by Jakub Janusz on 2016-02-03.
 */
public class MainController {

    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass().toString());
    private Stage primaryStage;
    private Stage stage;
    private Session session;
    private Timer timer;
    private boolean timerStarted;

    public MainController() {
        this(null);
    }

    public MainController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.session = new Session(3);
        this.timer = new Timer();
        this.timerStarted = false;
    }

    @FXML
    private GridPane gridPane;
    @FXML
    private Label scrambleLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private ListView<IndexedLabel> labelListView;
    private ObservableList<IndexedLabel> labels = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        logger.debug("Initializing MainController");
        scrambleLabel.setText(session.getScramble());
    }

    public void show() {
        try {
            logger.debug("Loading Main.fxml file");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/Main.fxml"));
            BorderPane rootLayout = loader.load();
            loader.setController(this);

            stage = new Stage();
            stage.setOnCloseRequest(event -> logger.debug("Closing Application"));
            stage.setTitle("Timer - main window");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(primaryStage);
            stage.setMinWidth(800);
            stage.setMinHeight(600);

            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            logger.error("Error during loading Main.fxml: ", e);
        }
    }

    @FXML
    private void handleSpaceAction(KeyEvent event) {
        KeyCode code = event.getCode();
        if(code.equals(KeyCode.SPACE)) {
            logger.debug("SPACE pressed");
            if(timerStarted) {
                timer.stop();
                timerStarted = false;
                timeLabel.setText(timer.getTime());
            } else {
                timer.start();
                timerStarted = true;
            }
        }
    }

    @FXML
    private void handleTwoMenu(ActionEvent event) {
        this.session = new Session(2);
        scrambleLabel.setText(session.getScramble());
    }

    @FXML
    private void handleThreeMenu(ActionEvent event) {
        this.session = new Session(3);
        scrambleLabel.setText(session.getScramble());
    }

    @FXML
    private void handleFourMenu(ActionEvent event) {
        this.session = new Session(4);
        scrambleLabel.setText(session.getScramble());
    }

    @FXML
    private void handleFiveMenu(ActionEvent event) {
        this.session = new Session(5);
        scrambleLabel.setText(session.getScramble());
    }

    @FXML
    private void handleSixMenu(ActionEvent event) {
        this.session = new Session(6);
        scrambleLabel.setText(session.getScramble());
    }

    @FXML
    private void handleSevenMenu(ActionEvent event) {
        this.session = new Session(7);
        scrambleLabel.setText(session.getScramble());
    }

}
