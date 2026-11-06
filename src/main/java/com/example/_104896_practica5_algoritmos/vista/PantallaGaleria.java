package com.example._104896_practica5_algoritmos.vista;

import com.example._104896_practica5_algoritmos.controlador.Controlador;
import com.example._104896_practica5_algoritmos.controlador.Reproductor;
import com.example._104896_practica5_algoritmos.modelo.Carta;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class PantallaGaleria extends AnchorPane{
    private static final double RESX=1920.0;
    private static final double RESGAEL=1080.0;
    private Controlador controlador;
    private PantallaDetalle detalle;
    private Label lbContador;
    private Label lbError;
    private TextField txtBuscar;
    private ImageButton btnLupa;
    private ImageButton botonAbrirCerrar;
    private boolean barraAbierta;
    private PanelAvanzado panel;
    private VBox lista;
    private Reproductor reproductor;
    private PanelMusica musica;
    private ScrollPane scroll;

    public PantallaGaleria(Controlador controlador, PantallaDetalle detalle, Reproductor reproductor){
        this.controlador=controlador;
        this.reproductor=reproductor;
        this.detalle=detalle;
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

        musica=new PanelMusica(reproductor);
        getChildren().add(musica);
        posicionarEnPane(musica,0/RESX,80/RESGAEL);

        lbContador=new Label();
        lbContador.setStyle("-fx-text-fill:#f5d97a;-fx-font-size:24px;-fx-font-weight:bold;");

        txtBuscar=new TextField();
        txtBuscar.setPromptText("Nombre exacto de la carta");
        txtBuscar.setPrefWidth(400);
        txtBuscar.setStyle("-fx-background-color:#2b3a4a;-fx-border-color:#d4af37;-fx-font-size:16px;-fx-text-fill:white;");
        txtBuscar.setOnAction(e->buscarPorNombre());

        btnLupa=new ImageButton("/recursos/btnBuscar.png","/recursos/btnBuscar.png",60,60);
        btnLupa.setOnAction(e->buscarPorNombre());

        lbError=new Label();
        lbError.setStyle("-fx-text-fill:#ff6666;-fx-font-size:15px;");
        lbError.setVisible(false);

        lista=new VBox(10);
        lista.setAlignment(Pos.TOP_CENTER);
        lista.setPadding(new Insets(20));
        lista.setStyle("-fx-background-color:transparent;");

        scroll=new ScrollPane(lista);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background:transparent;-fx-background-color:transparent;");
        scroll.prefWidthProperty().bind(widthProperty());
        scroll.prefHeightProperty().bind(heightProperty().multiply(0.72));

        panel=new PanelAvanzado(controlador,this);
        panel.prefWidthProperty().bind(widthProperty());
        panel.prefHeightProperty().bind(heightProperty().multiply(0.2));

        botonAbrirCerrar=new ImageButton("/recursos/btnMostrar.png","/recursos/btnMostrar.png",300,120);
        botonAbrirCerrar.setOnAction(e->alternarBarra());

        getChildren().addAll(lbContador,txtBuscar,btnLupa,lbError,scroll,panel,botonAbrirCerrar);
        posicionarEnPane(lbContador,40/RESX,30/RESGAEL);
        posicionarEnPane(txtBuscar,1350/RESX,30/RESGAEL);
        posicionarEnPane(btnLupa,1780/RESX,19/RESGAEL);
        posicionarEnPane(lbError,1100/RESX,40/RESGAEL);
        posicionarEnPane(scroll,0,295/RESGAEL);
        posicionarEnPane(panel,0,-215/RESGAEL);
        posicionarEnPane(botonAbrirCerrar,800/RESX,-50/RESGAEL);
    }

    public void actualizarPantalla(){
        lista.getChildren().clear();
        int total=controlador.getTotal();
        for (int i=0;i<total;i++){
            Carta carta=controlador.getCartaEn(i);
            TarjetaView tarjeta=new TarjetaView(carta,i);
            int indiceFinal=i;
            tarjeta.setOnMouseClicked(e->{
                controlador.irA(indiceFinal);
                detalle.actualizarPantalla();
                getScene().setRoot(detalle);
            });
            lista.getChildren().add(tarjeta);
        }
        lbContador.setText("Total de "+total+" cartas");
        scroll.setVvalue(0);
    }

    private void buscarPorNombre(){
        if (controlador.buscarCarta(txtBuscar.getText())){
            lbError.setVisible(false);
            panel.marcarTodas();
            actualizarPantalla();
            detalle.actualizarPantalla();
            getScene().setRoot(detalle);
        } else {
            lbError.setText("No se encontró esa carta. Escribe el nombre completo");
            lbError.setVisible(true);
        }
    }

    private void alternarBarra() {
        double distancia = panel.getHeight();
        System.out.println("" + distancia);
        double direccion;
        if (barraAbierta) {
            direccion = -distancia;
        } else {
            direccion = distancia;
        }

        panel.translateYProperty().unbind();
        botonAbrirCerrar.translateYProperty().unbind();
        lbContador.translateYProperty().unbind();
        txtBuscar.translateYProperty().unbind();
        musica.translateYProperty().unbind();

        TranslateTransition transBarra = new TranslateTransition(Duration.millis(300), panel);
        transBarra.setByY(direccion);

        TranslateTransition transBoton = new TranslateTransition(Duration.millis(300), botonAbrirCerrar);
        transBoton.setByY(direccion);

        TranslateTransition transContador = new TranslateTransition(Duration.millis(300), lbContador);
        transContador.setByY(direccion);

        TranslateTransition transBuscar = new TranslateTransition(Duration.millis(300), txtBuscar);
        transBuscar.setByY(direccion);

        TranslateTransition transMusica = new TranslateTransition(Duration.millis(300), musica);
        transMusica.setByY(direccion);

        transBarra.play();
        transBoton.play();
        transBuscar.play();
        transContador.play();
        transMusica.play();

        if (barraAbierta) {
            botonAbrirCerrar.setImagen("/recursos/btnMostrar.png");
            botonAbrirCerrar.setImagenHover("/recursos/btnMostrar.png");
        } else {
            botonAbrirCerrar.setImagen("/recursos/btnOcultar.png");
            botonAbrirCerrar.setImagenHover("/recursos/btnOcultar.png");
        }
        barraAbierta = !barraAbierta;
    }

    private void posicionarEnPane(Node nodo,double porcentajeX,double porcentajeY){
        nodo.translateXProperty().unbind();
        nodo.translateYProperty().unbind();
        nodo.translateXProperty().bind(widthProperty().multiply(porcentajeX));
        nodo.translateYProperty().bind(heightProperty().multiply(porcentajeY));
    }
}