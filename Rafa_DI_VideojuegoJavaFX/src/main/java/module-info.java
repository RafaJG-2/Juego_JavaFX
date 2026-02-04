module com.mycompany.rafa_di_videojuegojavafx {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.rafa_di_videojuegojavafx to javafx.fxml;
    exports com.mycompany.rafa_di_videojuegojavafx;
}
