package com.example._104896_practica5_algoritmos.vista;

import com.example._104896_practica5_algoritmos.controlador.Controlador;
import com.example._104896_practica5_algoritmos.modelo.Carta;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class CarruselView extends HBox {
    private static final double ANCHO_CENTRO=200;
    private static final double ANCHO_CERCANA=140;
    private static final double ANCHO_LEJANA=100;
    private static final String ESTILO_FLECHA="-fx-background-color:transparent;-fx-text-fill:#d4af37;-fx-font-size:40px;-fx-font-weight:bold;";
    private Controlador controlador;
    private Button btnAnterior;
    private Button btnSiguiente;

    public CarruselView(Controlador controlador){
        this.controlador=controlador;
        setAlignment(Pos.CENTER);
        setSpacing(20);
        btnAnterior=crearFlecha("◀");
        btnSiguiente=crearFlecha("▶");
        actualizar();
    }

    private Button crearFlecha(String texto){
        Button boton=new Button(texto);
        boton.setStyle(ESTILO_FLECHA);
        boton.setCursor(Cursor.HAND);
        return boton;
    }

    public void actualizar(){
        getChildren().clear();
        getChildren().add(btnAnterior);
        int total=controlador.getTotal();
        for (int d=-2;d<=2;d++){
            if (hayCarta(d,total)){
                Carta carta=controlador.getCartaRelativa(d);
                CartaImage imagen=new CartaImage(carta,obtenerAncho(d));
                if (d==0){
                    imagen.setEffect(new DropShadow(25,Color.GOLD));
                }
                getChildren().add(imagen);
            }
        }
        getChildren().add(btnSiguiente);
        btnAnterior.setDisable(total<=1);
        btnSiguiente.setDisable(total<=1);
    }

    private boolean hayCarta(int d,int total){
        if (total==0){
            return false;
        }
        if (d<0){
            return -d<=(total-1)/2;
        }
        return d<=total/2;
    }

    private double obtenerAncho(int d){
        if (d==0){
            return ANCHO_CENTRO;
        }
        if (d==-1||d==1){
            return ANCHO_CERCANA;
        }
        return ANCHO_LEJANA;
    }

    public Button getBtnAnterior(){
        return btnAnterior;
    }

    public Button getBtnSiguiente(){
        return btnSiguiente;
    }
}