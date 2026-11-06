package com.example._104896_practica5_algoritmos.modelo;

import java.util.ArrayList;
import java.util.Collections;

public class Catalogo{
    private ListaCircular<Carta> general;
    private ListaCircular<Carta> nivel;
    private ListaCircular<Carta> atributo;
    private ListaCircular<Carta> ataque;
    private ListaCircular<Carta> defensa;
    private ListaCircular<Carta> activa;
    private TipoLista tipoActivo;
    private int posicion;
    private int total;
    private ArrayList<String> niveles;
    private ArrayList<String> atributos;

    public Catalogo(String recurso){
        general=new ListaCircular<>();
        nivel=new ListaCircular<>();
        atributo=new ListaCircular<>();
        ataque=new ListaCircular<>();
        defensa=new ListaCircular<>();
        niveles=new ArrayList<>();
        atributos=new ArrayList<>();
        activa=general;
        tipoActivo=TipoLista.GENERAL;
        LectorCartas.cargar(recurso,general);
        obtenerEtiquetas();
        seleccionarGeneral();
    }

    public boolean seleccionarGeneral(){
        return activar(general,TipoLista.GENERAL);
    }

    public boolean seleccionarNivel(String etiqueta){
        ListaCircular<Carta> lista=new ListaCircular<>();
        int cantidad=general.getTamano();
        for (int i=0;i<cantidad;i++){
            Carta carta=general.getElemento(i);
            if (carta.getNivel().equals(etiqueta)){
                lista.insertaFinal(carta);
            }
        }
        nivel=lista;
        return activar(nivel,TipoLista.NIVEL);
    }

    public boolean seleccionarAtributo(String etiqueta){
        ListaCircular<Carta> lista=new ListaCircular<>();
        int cantidad=general.getTamano();
        for (int i=0;i<cantidad;i++){
            Carta carta=general.getElemento(i);
            if (carta.getAtributo().equals(etiqueta)){
                lista.insertaFinal(carta);
            }
        }
        atributo=lista;
        return activar(atributo,TipoLista.ATRIBUTO);
    }

    public boolean seleccionarAtaque(boolean ascendente){
        ataque=armarOrdenada(true,ascendente);
        return activar(ataque,TipoLista.ATAQUE);
    }

    public boolean seleccionarDefensa(boolean ascendente){
        defensa=armarOrdenada(false,ascendente);
        return activar(defensa,TipoLista.DEFENSA);
    }

    private static int prioridad(String etiqueta){
        if (etiqueta.startsWith("Nivel")){
            return 0;
        }
        if (etiqueta.startsWith("Rango")){
            return 1;
        }
        return 2;
    }

    private static int compararEtiquetas(String a,String b){
        int resultado=Integer.compare(prioridad(a),prioridad(b));
        if (resultado!=0){
            return resultado;
        }
        resultado=Integer.compare(Carta.extraerNumero(a),Carta.extraerNumero(b));
        if (resultado!=0){
            return resultado;
        }
        return a.compareTo(b);
    }

    public boolean mostrarPorNombre(String nombre){
        String buscado=nombre.trim();
        if (buscado.isEmpty()){
            return false;
        }
        int cantidad=general.getTamano();
        for (int i=0;i<cantidad;i++){
            Carta carta=general.getElemento(i);
            if (carta.getNombre().equalsIgnoreCase(buscado)){
                seleccionarGeneral();
                irA(i);
                return true;
            }
        }
        return false;
    }

    private void obtenerEtiquetas(){
        int cantidad=general.getTamano();
        for (int i=0;i<cantidad;i++){
            Carta carta=general.getElemento(i);
            if (!carta.getNivel().isBlank()&&!niveles.contains(carta.getNivel())){
                agregarNivel(carta.getNivel());
            }
            if (!carta.getAtributo().isBlank()&&!atributos.contains(carta.getAtributo())){
                atributos.add(carta.getAtributo());
            }
        }
        Collections.sort(atributos);
    }

    private void agregarNivel(String etiqueta){
        int i=0;
        while (i<niveles.size()&&compararEtiquetas(niveles.get(i),etiqueta)<0){
            i++;
        }
        niveles.add(i,etiqueta);
    }

    private boolean activar(ListaCircular<Carta> lista,TipoLista tipo){
        int cantidad=lista.getTamano();
        if (cantidad==0){
            return false;
        }
        activa=lista;
        tipoActivo=tipo;
        posicion=0;
        total=cantidad;
        return true;
    }

    private ListaCircular<Carta> armarOrdenada(boolean porAtaque,boolean ascendente){
        ListaCircular<Carta> lista=new ListaCircular<>();
        ComparadorCartas orden=new ComparadorCartas(porAtaque,ascendente);
        int cantidad=general.getTamano();
        for (int i=0;i<cantidad;i++){
            Carta carta=general.getElemento(i);
            int valor;
            if (porAtaque){
                valor=carta.getAtk();
            } else {
                valor=carta.getDef();
            }
            if (valor>=0){
                lista.insertaOrdenado(carta,orden);
            }
        }
        return lista;
    }



    public void siguiente(){
        if (total==0){
            return;
        }
        posicion++;
        if (posicion==total){
            posicion=0;
        }
    }

    public void anterior(){
        if (total==0){
            return;
        }
        posicion--;
        if (posicion<0){
            posicion=total-1;
        }
    }

    public void irA(int nuevaPosicion){
        if (nuevaPosicion>=0&&nuevaPosicion<total){
            posicion=nuevaPosicion;
        }
    }

    public Carta getActual(){
        return activa.getElemento(posicion);
    }

    public Carta getCartaRelativa(int desplazamiento){
        if (total==0){
            return null;
        }
        int indice=((posicion+desplazamiento)%total+total)%total;
        return activa.getElemento(indice);
    }

    public Carta getSiguiente(){
        return getCartaRelativa(1);
    }

    public Carta getAnterior(){
        return getCartaRelativa(-1);
    }

    public Carta getCartaEn(int indice){
        return activa.getElemento(indice);
    }

    public int getPosicion(){
        return posicion+1;
    }

    public int getTotal(){
        return total;
    }

    public TipoLista getTipoActivo(){
        return tipoActivo;
    }

    public ArrayList<String> getNiveles(){
        return new ArrayList<>(niveles);
    }

    public ArrayList<String> getAtributos(){
        return new ArrayList<>(atributos);
    }

    public String getDato(Carta carta){
        switch (tipoActivo){
            case NIVEL:
                return carta.getNivel();
            case ATRIBUTO:
                return carta.getAtributo();
            case ATAQUE:
                return "ATK "+carta.getAtk();
            case DEFENSA:
                return "DEF "+carta.getDef();
            default:
                return carta.getTipo();
        }
    }
}