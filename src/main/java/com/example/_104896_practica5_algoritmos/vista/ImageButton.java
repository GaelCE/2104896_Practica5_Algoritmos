package com.example._104896_practica5_algoritmos.vista;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageButton extends Button {
    private ImageView normal;
    private ImageView resaltado;
    private double ancho;
    private double alto;

    public ImageButton(String rutaNormal, String rutaResaltado, double weigth, double heigth){
        ancho=weigth;
        alto=heigth;
        Image imagenNormal=new Image(getClass().getResourceAsStream(rutaNormal));
        normal=new ImageView(imagenNormal);
        normal.setFitWidth(ancho);
        normal.setFitHeight(alto);
        normal.setPreserveRatio(false);

        Image imagenResaltada=new Image(getClass().getResourceAsStream(rutaResaltado));
        resaltado=new ImageView(imagenResaltada);
        resaltado.setFitWidth(ancho);
        resaltado.setFitHeight(alto);
        resaltado.setPreserveRatio(false);

        setGraphic(normal);
        setStyle("-fx-background-color:transparent;-fx-padding:0;");
        setCursor(Cursor.HAND);
        setOnMouseEntered(e->setGraphic(resaltado));
        setOnMouseExited(e->setGraphic(normal));
    }

    public void setImagen(String ruta){
        Image nuevaImagen=new Image(getClass().getResourceAsStream(ruta));
        normal=new ImageView(nuevaImagen);
        normal.setFitWidth(ancho);
        normal.setFitHeight(alto);
        normal.setPreserveRatio(false);
        setGraphic(normal);
        setStyle("-fx-background-color:transparent;-fx-padding:0;");
        setCursor(Cursor.HAND);
    }

    public void setImagenHover(String ruta){
        Image nuevaImagen=new Image(getClass().getResourceAsStream(ruta));
        resaltado=new ImageView(nuevaImagen);
        resaltado.setFitWidth(ancho);
        resaltado.setFitHeight(alto);
        resaltado.setPreserveRatio(false);
    }
}