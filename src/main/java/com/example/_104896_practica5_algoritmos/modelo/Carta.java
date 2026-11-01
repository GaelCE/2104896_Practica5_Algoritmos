package com.example._104896_practica5_algoritmos.modelo;

public class Carta{
    private String nombre;
    private String atributo;
    private String nivel;
    private int valorNivel;
    private String tipo;
    private int atk;
    private int def;
    private String texto;
    private String rareza;
    private String archivoImagen;

    public Carta(String nombre,String atributo,String nivel,String tipo,int atk,int def,String texto,String rareza,String archivoImagen){
        this.nombre=nombre;
        this.atributo=atributo;
        this.nivel=nivel;
        this.valorNivel=extraerNumero(nivel);
        this.tipo=tipo;
        this.atk=atk;
        this.def=def;
        this.texto=texto;
        this.rareza=rareza;
        this.archivoImagen=archivoImagen;
    }

    private static int extraerNumero(String texto){
        String digitos=texto.replaceAll("[^0-9]","");
        if(digitos.isEmpty()){
            return -1;
        }
        return Integer.parseInt(digitos);
    }

    public String getNombre(){
        return nombre;
    }

    public String getAtributo(){
        return atributo;
    }

    public String getNivel(){
        return nivel;
    }

    public int getValorNivel(){
        return valorNivel;
    }

    public String getTipo(){
        return tipo;
    }

    public int getAtk(){
        return atk;
    }

    public int getDef(){
        return def;
    }

    public String getTexto(){
        return texto;
    }

    public String getRareza(){
        return rareza;
    }

    public String getArchivoImagen(){
        return archivoImagen;
    }
}