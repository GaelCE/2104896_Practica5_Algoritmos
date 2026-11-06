package com.example._104896_practica5_algoritmos.vista;

import com.example._104896_practica5_algoritmos.controlador.Reproductor;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class PanelMusica extends HBox{
    private static final String ESTILO_FLECHA="-fx-background-color:transparent;-fx-text-fill:#d4af37;-fx-font-size:30px;-fx-font-weight:bold;";
    private Reproductor reproductor;
    private ImageButton btnBocina;
    private Button btnAnterior;
    private Button btnSiguiente;

    public PanelMusica(Reproductor reproductor){
        this.reproductor=reproductor;
        setSpacing(5);
        setAlignment(Pos.CENTER);
        construirPanel();
        actualizarIcono();
        reproductor.silenciadoProperty().addListener((o,viejo,nuevo)->actualizarIcono());
    }

    private void construirPanel(){
        btnAnterior=new Button("◀");
        btnAnterior.setStyle(ESTILO_FLECHA);
        btnAnterior.setCursor(Cursor.HAND);
        btnAnterior.setOnAction(e->reproductor.anteriorCancion());

        btnBocina=new ImageButton("/recursos/btnAudio.png","/recursos/btnAudio.png",80,60);
        btnBocina.setOnAction(e->reproductor.alternarSilencio());

        btnSiguiente=new Button("▶");
        btnSiguiente.setStyle(ESTILO_FLECHA);
        btnSiguiente.setCursor(Cursor.HAND);
        btnSiguiente.setOnAction(e->reproductor.siguienteCancion());

        getChildren().addAll(btnAnterior,btnBocina,btnSiguiente);
    }

    private void actualizarIcono(){
        if (reproductor.estaSilenciado()){
            btnBocina.setImagen("/recursos/btnAudioMute.png");
        } else {
            btnBocina.setImagen("/recursos/btnAudio.png");
        }
    }
}