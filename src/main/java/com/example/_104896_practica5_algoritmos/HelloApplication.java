package com.example._104896_practica5_algoritmos;

import com.example._104896_practica5_algoritmos.controlador.Controlador;
import com.example._104896_practica5_algoritmos.controlador.Reproductor;
import com.example._104896_practica5_algoritmos.vista.PantallaDetalle;
import com.example._104896_practica5_algoritmos.vista.PantallaGaleria;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application{
    private Controlador controlador;
    private PantallaGaleria galeria;
    private PantallaDetalle detalle;

    @Override
    public void start(Stage stage){
        controlador=new Controlador("/cartas.csv");
        Reproductor reproductor=new Reproductor(new String[]{"/Musica/CiudadFayenza.mp3","/Musica/CastleFunk.mp3","/Musica/CelesteChill.mp3","/Musica/Welcome to the Green Room.mp3","/Musica/Yu-Gi-Oh! - Seto Kaiba (SPOTISAVER).mp3"});
        detalle=new PantallaDetalle(controlador,reproductor);
        galeria=new PantallaGaleria(controlador,detalle,reproductor);

        detalle.getBotonVolver().setOnAction(e->stage.getScene().setRoot(galeria));

        stage.setScene(new Scene(galeria,1500,843));
        stage.show();
    }
}
