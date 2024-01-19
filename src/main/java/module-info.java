module com.example.we_sport {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;

    opens com.example.we_sport to javafx.fxml;
    exports com.example.we_sport;
    exports com.example.we_sport.Controllers;
    opens com.example.we_sport.Controllers to javafx.fxml;


}