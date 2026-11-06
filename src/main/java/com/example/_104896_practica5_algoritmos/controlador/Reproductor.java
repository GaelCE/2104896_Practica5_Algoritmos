package com.example._104896_practica5_algoritmos.controlador;

import com.example._104896_practica5_algoritmos.modelo.ListaDobleCircular;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Reproductor{
    private ListaDobleCircular<String> canciones;
    private int posicion;
    private int total;
    private MediaPlayer reproductor;
    private BooleanProperty silenciado;

    public Reproductor(String[] rutas){
        canciones=new ListaDobleCircular<>();
        for (int i=0;i<rutas.length;i++){
            canciones.insertaFinal(rutas[i]);
        }
        total=canciones.getTamano();
        posicion=0;
        silenciado=new SimpleBooleanProperty(false);
        cargarCancion();
    }

    private void cargarCancion(){
        if (reproductor!=null){
            reproductor.stop();
            reproductor.dispose();
            reproductor=null;
        }
        String ruta=canciones.getElemento(posicion);
        if (ruta==null||getClass().getResource(ruta)==null){
            System.out.println("No se encontró la canción "+ruta);
            return;
        }
        Media media=new Media(getClass().getResource(ruta).toExternalForm());
        reproductor=new MediaPlayer(media);
        reproductor.setVolume(0.5);
        reproductor.setOnEndOfMedia(()->siguienteCancion());
        if (!silenciado.get()){
            reproductor.play();
        }
    }

    public void siguienteCancion(){
        if (total==0){
            return;
        }
        posicion++;
        if (posicion==total){
            posicion=0;
        }
        silenciado.set(false);
        cargarCancion();
    }

    public void anteriorCancion(){
        if (total==0){
            return;
        }
        posicion--;
        if (posicion<0){
            posicion=total-1;
        }
        silenciado.set(false);
        cargarCancion();
    }

    public void alternarSilencio(){
        if (reproductor==null){
            return;
        }
        if (silenciado.get()){
            silenciado.set(false);
            reproductor.play();
        } else {
            silenciado.set(true);
            reproductor.pause();
        }
    }

    public boolean estaSilenciado(){
        return silenciado.get();
    }

    public BooleanProperty silenciadoProperty(){
        return silenciado;
    }
}