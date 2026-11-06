package com.example._104896_practica5_algoritmos.modelo;
import java.util.ArrayList;
import java.util.Collections;

public class ListaDobleCircular <T extends Comparable<T>>{
    private NodoDoble<T>inicio;
    private NodoDoble<T> fin;

    public void insertaInicio(T dato){
        NodoDoble<T> n=new NodoDoble<>(dato);
        if (inicio==null){
            inicio=n;
            fin=inicio;
            n.setSig(n);
            n.setAnt(n);
        } else {
            n.setAnt(fin);
            n.setSig(inicio);
            inicio.setAnt(n);
            fin.setSig(n);
            inicio=n;
        }
    }

    public T eliminarInicio(){
        if (inicio==null){
            System.out.println("Lista vacia");
            return null;
        } else {
            T dato=inicio.getInfo();
            if (inicio==fin){
                inicio=null;
                fin=inicio;
            } else {
                inicio=inicio.getSig();
                inicio.setAnt(fin);
                fin.setSig(inicio);
            }
            return dato;
        }
    }

    public void insertaFinal(T dato){
        NodoDoble<T>n=new NodoDoble<>(dato);
        if (inicio==null){
            inicio=n;
            fin=inicio;
            n.setSig(n);
            n.setAnt(n);
        } else {
            n.setSig(inicio);
            n.setAnt(fin);
            fin.setSig(n);
            inicio.setAnt(n);
            fin=n;
        }
    }

    public T eliminarFinal(){
        if (inicio==null){
            System.out.println("lista vacia");
            return null;
        } else {
            T dato=fin.getInfo();
            if (inicio==fin){
                inicio=null;
                fin= inicio;
            } else {
                fin=fin.getAnt();
                fin.setSig(inicio);
                inicio.setAnt(fin);
            }
            return dato;
        }
    }

    public int getTamano(){
        if (inicio==null){
            return 0;
        }
        int contador=0;
        NodoDoble<T> n=inicio;
        do {
            contador++;
            n=n.getSig();
        }while (n!=inicio);
        return contador;
    }

    public T getElemento(int posicion){
        if (inicio==null||posicion<0){
            return null;
        }
        int tamano=getTamano();
        posicion=posicion%tamano;
        NodoDoble<T> n=inicio;
        if (posicion<=tamano/2){
            for (int i=0;i<posicion;i++){
                n=n.getSig();
            }
        } else {
            for (int i=0;i<tamano-posicion;i++){
                n=n.getAnt();
            }
        }
        return n.getInfo();
    }
}
