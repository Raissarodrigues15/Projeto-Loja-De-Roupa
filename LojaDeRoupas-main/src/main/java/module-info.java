module com.example.projetoloja {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.projetoloja to javafx.fxml;
    exports com.example.projetoloja;
    exports com.example.projetoloja.Exception;
    opens com.example.projetoloja.Exception to javafx.fxml;
    exports com.example.projetoloja.menu;
    opens com.example.projetoloja.menu to javafx.fxml;
}