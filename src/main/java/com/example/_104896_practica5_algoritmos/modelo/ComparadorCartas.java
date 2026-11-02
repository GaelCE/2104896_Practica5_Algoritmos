package com.example._104896_practica5_algoritmos.modelo;

import java.util.Comparator;

public class ComparadorCartas implements Comparator<Carta>{
    private boolean porAtaque;
    private boolean ascendente;

    public ComparadorCartas(boolean porAtaque,boolean ascendente){
        this.porAtaque=porAtaque;
        this.ascendente=ascendente;
    }

    @Override
    public int compare(Carta a,Carta b){
        int resultado;
        if (porAtaque){
            resultado=Integer.compare(a.getAtk(),b.getAtk());
        } else {
            resultado=Integer.compare(a.getDef(),b.getDef());
        }
        if (!ascendente){
            resultado=-resultado;
        }
        if (resultado==0){
            resultado=a.getNombre().compareTo(b.getNombre());
        }
        return resultado;
    }
}