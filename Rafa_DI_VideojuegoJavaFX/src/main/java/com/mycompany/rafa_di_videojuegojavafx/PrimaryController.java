package com.mycompany.rafa_di_videojuegojavafx;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void jugar() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void salir() {
        System.exit(0);
    }
}
