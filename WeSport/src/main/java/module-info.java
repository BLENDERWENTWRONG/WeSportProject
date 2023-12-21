module com.example.cafe {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    requires java.sql;



    opens com.example.cafe to javafx.fxml;
    exports com.example.cafe;
    exports com.example.cafe.Entity;
    opens com.example.cafe.Entity to javafx.fxml;
    exports com.example.cafe.Controllers;
    opens com.example.cafe.Controllers to javafx.fxml;
    exports com.example.cafe.Utils;
    opens com.example.cafe.Utils to javafx.fxml;
}