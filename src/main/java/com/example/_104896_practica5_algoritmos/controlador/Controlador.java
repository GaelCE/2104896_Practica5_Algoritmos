package com.example._104896_practica5_algoritmos.controlador;

import com.example._104896_practica5_algoritmos.modelo.Carta;
import com.example._104896_practica5_algoritmos.modelo.Catalogo;
import com.example._104896_practica5_algoritmos.modelo.TipoLista;

import java.util.ArrayList;

public class Controlador{
    private Catalogo catalogo;

    public Controlador(String recurso){
        catalogo=new Catalogo(recurso);
    }

    public boolean verTodas(){
        return catalogo.seleccionarGeneral();
    }

    public boolean verPorNivel(String nivel){
        return catalogo.seleccionarNivel(nivel);
    }

    public boolean verPorAtributo(String atributo){
        return catalogo.seleccionarAtributo(atributo);
    }

    public boolean verPorAtaque(boolean ascendente){
        return catalogo.seleccionarAtaque(ascendente);
    }

    public boolean verPorDefensa(boolean ascendente){
        return catalogo.seleccionarDefensa(ascendente);
    }

    public void siguiente(){
        catalogo.siguiente();
    }

    public void anterior(){
        catalogo.anterior();
    }

    public void irA(int posicion){
        catalogo.irA(posicion);
    }

    public boolean buscarCarta(String nombre){
        return catalogo.mostrarPorNombre(nombre);
    }

    public boolean hayCartas(){
        return catalogo.getTotal()>0;
    }

    public Carta getActual(){
        return catalogo.getActual();
    }

    public Carta getSiguiente(){
        return catalogo.getSiguiente();
    }

    public Carta getAnterior(){
        return catalogo.getAnterior();
    }

    public Carta getCartaEn(int indice){
        return catalogo.getCartaEn(indice);
    }

    public String getDato(Carta carta){
        return catalogo.getDato(carta);
    }

    public int getPosicion(){
        return catalogo.getPosicion();
    }

    public int getTotal(){
        return catalogo.getTotal();
    }

    public TipoLista getTipoActivo(){
        return catalogo.getTipoActivo();
    }

    public ArrayList<String> getNiveles(){
        return catalogo.getNiveles();
    }

    public ArrayList<String> getAtributos(){
        return catalogo.getAtributos();
    }

    public Carta getCartaRelativa(int desplazamiento){
        return catalogo.getCartaRelativa(desplazamiento);
    }
}