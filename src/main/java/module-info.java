module com.example._104896_practica5_algoritmos {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example._104896_practica5_algoritmos to javafx.fxml;
    exports com.example._104896_practica5_algoritmos;
    exports com.example._104896_practica5_algoritmos.vista;
    opens com.example._104896_practica5_algoritmos.vista to javafx.fxml;
}