package com.example._104896_practica5_algoritmos.vista;

import com.example._104896_practica5_algoritmos.modelo.Carta;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;

public class CartaImage extends Label{
    public static final double PROPORCION=1.46;
    private static final HashMap<String,Image> IMAGENES=new HashMap<>();
    private Carta carta;

    public CartaImage(Carta carta,double ancho){
        this.carta=carta;
        double alto=ancho*PROPORCION;
        setPrefSize(ancho,alto);
        Image imagen=obtenerImagen(carta,ancho);
        ImageView view=new ImageView(imagen);
        view.setFitWidth(ancho);
        view.setFitHeight(alto);
        view.setPreserveRatio(false);
        setGraphic(view);
    }

    private static Image obtenerImagen(Carta carta,double ancho){
        String clave=carta.getArchivoImagen()+"@"+(int)ancho;
        if (!IMAGENES.containsKey(clave)){
            String ruta="/renombradas/"+carta.getArchivoImagen();
            Image imagen=null;
            if (CartaImage.class.getResource(ruta)!=null){
                imagen=new Image(CartaImage.class.getResourceAsStream(ruta),ancho,0,true,true);
            }
            IMAGENES.put(clave,imagen);
        }
        return IMAGENES.get(clave);
    }

    public Carta getCarta(){
        return carta;
    }
}