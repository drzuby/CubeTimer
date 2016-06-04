package timer;

import ch.qos.logback.classic.Logger;
import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;
import timer.controller.MainController;

/**
 * Created by Jakub Janusz on 2016-02-03.
 */
public class Main extends Application {

    private static Logger logger = (Logger) LoggerFactory.getLogger(Main.class.toString());

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Timer 1.0");

        logger.debug("Creating MainController");
        MainController mainController = new MainController(primaryStage);
        mainController.show();
    }

    public static void main(String[] args) {
        logger.debug("Launching Application");
        launch(args);
    }

}
