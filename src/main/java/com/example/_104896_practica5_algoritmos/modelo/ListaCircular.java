package com.example._104896_practica5_algoritmos.modelo;

import java.util.Comparator;

public class ListaCircular <T>{
    private Nodo<T> inicio;
    private Nodo<T> fin;

    public void insertaInicio(T dato){
        Nodo<T>n=new Nodo<T>(dato);
        if (inicio==null){
            inicio=n;
            fin=inicio;
            inicio.setSig(fin);
        } else {
            n.setSig(inicio);
            fin.setSig(n);
            inicio=n;
        }
    }

    public T eliminarInicio(){
        if (inicio==null){
            System.out.println("lista vacia");
            return null;
        } else {
            T dato=inicio.getInfo();
            if (inicio==fin){
                inicio=null;
                fin=inicio;
            } else {
                fin.setSig(inicio.getSig());
                inicio=inicio.getSig();
            }
            return dato;
        }
    }

    public void insertaFinal(T dato){
        Nodo<T> n=new Nodo<>(dato);
        if (inicio==null){
            inicio=n;
            fin=inicio;
            n.setSig(inicio);
        } else {
            n.setSig(inicio);
            fin.setSig(n);
            fin=n;
        }
    }

    public T eliminarFinal(){
        if (inicio==null){
            System.out.println("Lista vacia");
            return null;
        } else {
            T dato=fin.getInfo();
            if (inicio==fin){
                inicio=null;
                fin=inicio;
            } else {
                Nodo<T>r=inicio;
                while (r.getSig()!=fin){
                    r=r.getSig();
                }
                r.setSig(inicio);
                fin=r;
            }
            return dato;
        }
    }

    public int buscar(T objeto){
        if (inicio==null){
            return -1;
        }
        int posicion=0;
        Nodo<T> n=inicio;
        do {
            if (n.getInfo().equals(objeto)){
                return posicion;
            }
            posicion++;
            n=n.getSig();
        }while (n!=inicio);
        return -1;
    }

    public void insertaOrdenado(T dato, Comparator<? super T> comparador){
        if (inicio==null||comparador.compare(dato,inicio.getInfo())<0){
            insertaInicio(dato);
            return;
        }
        Nodo<T> anterior=inicio;
        while (anterior!=fin&&comparador.compare(dato,anterior.getSig().getInfo())>=0){
            anterior=anterior.getSig();
        }
        if (anterior==fin){
            insertaFinal(dato);
        } else {
            Nodo<T> nuevo=new Nodo<>(dato);
            nuevo.setSig(anterior.getSig());
            anterior.setSig(nuevo);
        }
    }

    public int getTamano(){
        if (inicio==null){
            return 0;
        }
        int contador=0;
        Nodo<T> n=inicio;
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
        Nodo<T> n=inicio;
        for (int i=0;i<posicion;i++){
            n=n.getSig();
        }
        return n.getInfo();
    }

}
