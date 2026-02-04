package com.mycompany.rafa_di_videojuegojavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.VBox;

public class SecondaryController {

    @FXML private VBox juego;

    @FXML private Button b00;
    @FXML private Button b01;
    @FXML private Button b02;
    @FXML private Button b10;
    @FXML private Button b11;
    @FXML private Button b12;
    @FXML private Button b20;
    @FXML private Button b21;
    @FXML private Button b22;

    @FXML private Label labelplayer;

    private boolean turnoX = true;
    private double zoom = 1.0;

    @FXML
    private void clicBoton(ActionEvent event) {
        Button boton = (Button) event.getSource();

        if (!boton.getText().isEmpty()) return;

        if (turnoX) {
            boton.setText("X");
            labelplayer.setText("Turno de jugador 2 (O)");
        } else {
            boton.setText("O");
            labelplayer.setText("Turno de jugador 1 (X)");
        }

        turnoX = !turnoX;

        verificarGanador();
    }


    @FXML
    private void reiniciarJuego() {
        b00.setText(""); b01.setText(""); b02.setText("");
        b10.setText(""); b11.setText(""); b12.setText("");
        b20.setText(""); b21.setText(""); b22.setText("");

        turnoX = true;
        labelplayer.setText("Turno de jugador 1 (X)");
    }


    @FXML
    private void hacerZoom(ScrollEvent event) {
        if (event.getDeltaY() > 0) {
            zoom += 0.1;
        } else {
            zoom -= 0.1;
        }

        if (zoom < 0.5) zoom = 0.5;
        if (zoom > 2.0) zoom = 2.0;

        juego.setScaleX(zoom);
        juego.setScaleY(zoom);
    }


    @FXML
    private void switchToPrimary() {
        try {
            App.setRoot("primary");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void verificarGanador() {
        String[][] tablero = new String[3][3];

        tablero[0][0] = b00.getText(); tablero[0][1] = b01.getText(); tablero[0][2] = b02.getText();
        tablero[1][0] = b10.getText(); tablero[1][1] = b11.getText(); tablero[1][2] = b12.getText();
        tablero[2][0] = b20.getText(); tablero[2][1] = b21.getText(); tablero[2][2] = b22.getText();

        for (int i = 0; i < 3; i++) {
            if (!tablero[i][0].isEmpty() &&
                tablero[i][0].equals(tablero[i][1]) &&
                tablero[i][0].equals(tablero[i][2])) {
                mostrarGanador(tablero[i][0]);
                return;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (!tablero[0][i].isEmpty() &&
                tablero[0][i].equals(tablero[1][i]) &&
                tablero[0][i].equals(tablero[2][i])) {
                mostrarGanador(tablero[0][i]);
                return;
            }
        }

        if (!tablero[0][0].isEmpty() &&
            tablero[0][0].equals(tablero[1][1]) &&
            tablero[0][0].equals(tablero[2][2])) {
            mostrarGanador(tablero[0][0]);
            return;
        }

        if (!tablero[0][2].isEmpty() &&
            tablero[0][2].equals(tablero[1][1]) &&
            tablero[0][2].equals(tablero[2][0])) {
            mostrarGanador(tablero[0][2]);
        }
    }

    private void mostrarGanador(String jugador) {
    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
    alerta.setTitle("PARTIDA FINALIZADA");
    alerta.setHeaderText(null);

    if (jugador.equals("X")) {
        alerta.setContentText("¡Jugador 1 (X) ha ganado!");
    } else {
        alerta.setContentText("¡Jugador 2 (O) ha ganado!");
    }

    alerta.showAndWait();
    reiniciarJuego();
}

}
