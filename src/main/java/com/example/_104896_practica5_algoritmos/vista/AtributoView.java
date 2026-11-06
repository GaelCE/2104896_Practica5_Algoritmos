package com.example._104896_practica5_algoritmos.vista;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;

public class AtributoView extends Label{
    private static final HashMap<String,Image> IMAGENES=new HashMap<>();

    public AtributoView(String atributo,double ancho){
        setPrefSize(ancho,ancho);
        Image imagen=obtenerImagen(atributo,ancho);
        if (imagen!=null){
            ImageView view=new ImageView(imagen);
            view.setFitWidth(ancho);
            view.setFitHeight(ancho);
            view.setPreserveRatio(false);
            setGraphic(view);
        } else {
            setText(atributo);
            setStyle("-fx-text-fill:white;-fx-font-size:12px;");
        }
    }

    private static Image obtenerImagen(String atributo,double ancho){
        String clave=atributo+"@"+(int)ancho;
        if (!IMAGENES.containsKey(clave)){
            Image imagen=null;
            String ruta="/recursos/atributo"+atributo+".png";
            if (AtributoView.class.getResource(ruta)!=null){
                imagen=new Image(AtributoView.class.getResourceAsStream(ruta),ancho,0,true,true);
            }
            IMAGENES.put(clave,imagen);
        }
        return IMAGENES.get(clave);
    }
}