module local.check.covidtextcheck {
    requires javafx.controls;
    requires javafx.fxml;


    opens local.check.covidtextcheck to javafx.fxml;
    exports local.check.covidtextcheck;
}