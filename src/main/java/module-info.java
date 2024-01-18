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



    opens com.esprit.wesport to javafx.fxml;
    exports com.esprit.wesport;
    exports com.esprit.wesport.Entity;
    opens com.esprit.wesport.Entity to javafx.fxml;
    exports com.esprit.wesport.Controllers;
    opens com.esprit.wesport.Controllers to javafx.fxml;
    exports com.esprit.wesport.Utils;
    opens com.esprit.wesport.Utils to javafx.fxml;
}