module com.example.we_sport {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;


    opens com.example.we_sport.controllers to javafx.fxml;

    exports com.example.we_sport;
    exports com.example.we_sport.Entity;

    opens com.example.we_sport to javafx.fxml;

}






