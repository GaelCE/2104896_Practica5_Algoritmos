package com.example._104896_practica5_algoritmos.vista;

import com.example._104896_practica5_algoritmos.modelo.Carta;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TarjetaView extends HBox{
    public static final double ANCHO_TARJETA=1100;
    public static final double ALTO_TARJETA=190;
    private static final double ANCHO_IMAGEN=100;
    private static final String ESTILO_TEXTO="-fx-text-fill:white;-fx-font-size:14px;";
    private Carta carta;
    private int indice;

    public TarjetaView(Carta carta,int indice){
        this.carta=carta;
        this.indice=indice;
        setPrefSize(ANCHO_TARJETA,ALTO_TARJETA);
        setMinSize(ANCHO_TARJETA,ALTO_TARJETA);
        setMaxSize(ANCHO_TARJETA,ALTO_TARJETA);
        setSpacing(15);
        setPadding(new Insets(10));
        setStyle("-fx-background-color:rgba(0,0,0,0.6);-fx-border-color:#d4af37;-fx-border-width:2;");
        getChildren().add(new CartaImage(carta,ANCHO_IMAGEN));
        crearVBox();
    }

    private void crearVBox(){
        VBox vBox=new VBox(8);
        vBox.setPrefWidth(ANCHO_TARJETA-ANCHO_IMAGEN-60);

        Label lbNombre=new Label(carta.getNombre());
        lbNombre.setStyle("-fx-text-fill:#f5d97a;-fx-font-size:20px;-fx-font-weight:bold;");

        HBox hbDatos=new HBox(20);
        HBox hbAtributo=new HBox(5);
        hbAtributo.getChildren().add(new AtributoView(carta.getAtributo(),30));
        hbAtributo.getChildren().add(crearLabel(carta.getAtributo()));
        hbDatos.getChildren().add(hbAtributo);
        if (!carta.getNivel().isBlank()){
            hbDatos.getChildren().add(crearLabel(carta.getNivel()));
        }
        if (!carta.getTipo().isBlank()){
            hbDatos.getChildren().add(crearLabel("["+carta.getTipo()+"]"));
        }
        hbDatos.getChildren().add(crearLabel("ATK "+textoValor(carta.getAtk())));
        hbDatos.getChildren().add(crearLabel("DEF "+textoValor(carta.getDef())));

        Label lbTexto=crearLabel(carta.getTexto());
        lbTexto.setWrapText(true);
        lbTexto.setMaxWidth(ANCHO_TARJETA-ANCHO_IMAGEN-60);

        vBox.getChildren().add(lbNombre);
        vBox.getChildren().add(hbDatos);
        vBox.getChildren().add(lbTexto);
        getChildren().add(vBox);
    }

    private Label crearLabel(String texto){
        Label label=new Label(texto);
        label.setStyle(ESTILO_TEXTO);
        return label;
    }

    private String textoValor(int valor){
        if (valor<0){
            return "—";
        }
        return ""+valor;
    }

    public Carta getCarta(){
        return carta;
    }

    public int getIndice(){
        return indice;
    }
}