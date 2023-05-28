package local.check.covidtextcheck;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CovidTextCheckApplication extends Application {
    private Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CovidTextCheckApplication.class.getResource("main-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 500);
        this.stage = stage;
        stage.setTitle("CovidTextCheck");
        stage.setScene(scene);
        stage.show();
        Checker.initWordArrays();
    }

    public static void main(String[] args) {
        launch();
    }
}