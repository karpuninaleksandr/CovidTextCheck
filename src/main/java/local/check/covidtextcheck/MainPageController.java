package local.check.covidtextcheck;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MainPageController {
    @FXML
    VBox mainBox;

    @FXML
    Label file;

    @FXML
    Label amountOfWords;

    @FXML
    Label opinion;

    @FXML
    protected void onFindFileButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Проверка текста на отношение автора к вакцинации");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Текстовые файлы", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(mainBox.getScene().getWindow());
        if (selectedFile != null) {
            file.setText("Выбранный файл: " + selectedFile.getPath());
            Button checkTextButton = new Button("Анализировать текст");
            mainBox.getChildren().add(checkTextButton);
            checkTextButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    String outPut = "Отношение автора к вакцинации от Covid-19: скорее ";
                    if (checkAuthorOpinion(selectedFile.getPath())) opinion.setText(outPut + "положительное");
                    else opinion.setText(outPut + "негативное");
                }
            });
        }
    }

    private boolean checkAuthorOpinion(String path) {
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            StringBuilder fileInside = new StringBuilder();
            while (scanner.hasNextLine()) {
                try {
                    fileInside.append(scanner.next() + "\n");
                } catch (NoSuchElementException e) {
                    e.printStackTrace();
                }
            }
            List<Integer> resultOfCounting = Checker.getAmountOfWords(fileInside.toString());
            amountOfWords.setText("В тексте найдено " + resultOfCounting.get(0) + " лексем(ы), выражающих положительное\n" +
                    "отношение автора текста к вакцинации от Covid-19.\n" + "В тексте найдено " + resultOfCounting.get(1) +
                    " лексем(ы), выражающих негативное\nотношение автора к вакцинации от Covid-19 или призывающих\n" +
                    "читателей с осторожностью относиться к прививкам от Covid-19.");
            return (resultOfCounting.get(0) >= resultOfCounting.get(1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}