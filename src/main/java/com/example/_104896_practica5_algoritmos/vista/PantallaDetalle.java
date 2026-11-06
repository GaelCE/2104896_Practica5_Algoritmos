package com.example._104896_practica5_algoritmos.vista;

import com.example._104896_practica5_algoritmos.controlador.Controlador;
import com.example._104896_practica5_algoritmos.controlador.Reproductor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

public class PantallaDetalle extends AnchorPane{
    private static final double RESX=1920.0;
    private static final double RESGAEL=1080.0;
    private Controlador controlador;
    private FichaView ficha;
    private CarruselView carrusel;
    private Label lbContador;
    private ImageButton btnVolver;
    private Reproductor reproductor;

    public PantallaDetalle(Controlador controlador, Reproductor reproductor){
        this.controlador=controlador;
        this.reproductor=reproductor;
        construirPantalla();
        actualizarPantalla();
    }

    private void construirPantalla(){
        getChildren().clear();

        ImageView fondo=new ImageView(new Image(getClass().getResourceAsStream("/recursos/yugiBackground.png")));
        fondo.setPreserveRatio(false);
        fondo.fitWidthProperty().bind(widthProperty());
        fondo.fitHeightProperty().bind(heightProperty());
        getChildren().add(fondo);

        PanelMusica musica=new PanelMusica(reproductor);
        getChildren().add(musica);
        posicionarEnPane(musica,1650/RESX,15/RESGAEL);

        btnVolver=new ImageButton("/recursos/btnRetroceder.png","/recursos/btnRetroceder.png",100,80);

        lbContador=new Label();
        lbContador.setStyle("-fx-text-fill:#f5d97a;-fx-font-size:28px;-fx-font-weight:bold;");

        ficha=new FichaView(controlador);

        carrusel=new CarruselView(controlador);
        carrusel.getBtnAnterior().setOnAction(e->{
            controlador.anterior();
            actualizarPantalla();
        });
        carrusel.getBtnSiguiente().setOnAction(e->{
            controlador.siguiente();
            actualizarPantalla();
        });

        getChildren().addAll(btnVolver,lbContador,ficha,carrusel);
        posicionarEnPane(btnVolver,20/RESX,20/RESGAEL);
        centrarEnPane(lbContador,25/RESGAEL);
        centrarEnPane(ficha,130/RESGAEL);
        centrarEnPane(carrusel,670/RESGAEL);
    }

    public void actualizarPantalla(){
        ficha.actualizar();
        carrusel.actualizar();
        lbContador.setText("("+controlador.getPosicion()+"/"+controlador.getTotal()+")");
    }

    private void posicionarEnPane(Node nodo,double porcentajeX,double porcentajeY){
        nodo.translateXProperty().unbind();
        nodo.translateYProperty().unbind();
        nodo.translateXProperty().bind(widthProperty().multiply(porcentajeX));
        nodo.translateYProperty().bind(heightProperty().multiply(porcentajeY));
    }

    private void centrarEnPane(Region nodo,double porcentajeY){
        nodo.translateXProperty().bind(widthProperty().subtract(nodo.widthProperty()).divide(2));
        nodo.translateYProperty().bind(heightProperty().multiply(porcentajeY));
    }

    public ImageButton getBotonVolver(){
        return btnVolver;
    }
}