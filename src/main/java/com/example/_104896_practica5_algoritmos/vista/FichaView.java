package com.example._104896_practica5_algoritmos.vista;

import com.example._104896_practica5_algoritmos.controlador.Controlador;
import com.example._104896_practica5_algoritmos.modelo.Carta;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FichaView extends HBox{
    public static final double ANCHO_FICHA=1300;
    public static final double ALTO_FICHA=400;
    private static final double ANCHO_IMAGEN=260;
    private static final double ANCHO_TEXTO=ANCHO_FICHA-ANCHO_IMAGEN-80;
    private static final String ESTILO_TEXTO="-fx-text-fill:white;-fx-font-size:18px;";
    private Controlador controlador;
    private Carta carta;

    public FichaView(Controlador controlador){
        this.controlador=controlador;
        setPrefSize(ANCHO_FICHA,ALTO_FICHA);
        setMinSize(ANCHO_FICHA,ALTO_FICHA);
        setMaxSize(ANCHO_FICHA,ALTO_FICHA);
        setSpacing(30);
        setPadding(new Insets(15));
        setStyle("-fx-background-color:rgba(0,0,0,0.6);-fx-border-color:#d4af37;-fx-border-width:3;");
        actualizar();
    }

    public void actualizar(){
        getChildren().clear();
        carta=controlador.getActual();
        if (carta==null){
            return;
        }
        getChildren().add(new CartaImage(carta,ANCHO_IMAGEN));
        crearVBox();
    }

    private void crearVBox(){
        VBox vBox=new VBox(15);
        vBox.setPrefWidth(ANCHO_TEXTO);

        Label lbNombre=new Label(carta.getNombre());
        lbNombre.setStyle("-fx-text-fill:#f5d97a;-fx-font-size:32px;-fx-font-weight:bold;");

        HBox hbAtributo=new HBox(8);
        hbAtributo.getChildren().add(new AtributoView(carta.getAtributo(),40));
        hbAtributo.getChildren().add(crearLabel(carta.getAtributo()));

        HBox hbDatos=new HBox(30);
        hbDatos.getChildren().add(hbAtributo);
        if (!carta.getNivel().isBlank()){
            hbDatos.getChildren().add(crearLabel(carta.getNivel()));
        }
        if (!carta.getTipo().isBlank()){
            hbDatos.getChildren().add(crearLabel("["+carta.getTipo()+"]"));
        }

        HBox hbValores=new HBox(30);
        hbValores.getChildren().add(crearLabel("ATK "+textoValor(carta.getAtk())));
        hbValores.getChildren().add(crearLabel("DEF "+textoValor(carta.getDef())));
        hbValores.getChildren().add(crearLabel("Rareza: "+carta.getRareza()));

        Label lbTexto=crearLabel(carta.getTexto());
        lbTexto.setWrapText(true);
        lbTexto.setMaxWidth(ANCHO_TEXTO);

        vBox.getChildren().add(lbNombre);
        vBox.getChildren().add(hbDatos);
        vBox.getChildren().add(hbValores);
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
}