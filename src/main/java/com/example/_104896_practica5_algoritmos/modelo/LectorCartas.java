package com.example._104896_practica5_algoritmos.modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class LectorCartas{
    private static final int NUM_CAMPOS=8;
    private static final String CARPETA_IMAGENES="/MP23_es/";

    public static Carta parsearLinea(String linea){
        String[] campos=linea.split("\\|",-1);
        if (campos.length!=NUM_CAMPOS){
            throw new IllegalArgumentException("Se esperaban "+NUM_CAMPOS+" campos y se encontraron "+campos.length);
        }
        for (int i=0;i<campos.length;i++){
            campos[i]=campos[i].trim();
        }
        int atk=aEntero(campos[4]);
        int def=aEntero(campos[5]);
        return new Carta(campos[0],campos[1],campos[2],campos[3],atk,def,campos[6],campos[7]);
    }

    private static int aEntero(String texto){
        try{
            return Integer.parseInt(texto);
        } catch (NumberFormatException e){
            return -1;
        }
    }

    public static void cargar(String recurso,ListaCircular<Carta> lista){
        if (LectorCartas.class.getResource(recurso)==null){
            System.out.println("No se encontró el archivo "+recurso);
            return;
        }
        try{
            BufferedReader lector=new BufferedReader(new InputStreamReader(LectorCartas.class.getResourceAsStream(recurso),StandardCharsets.UTF_8));
            lector.readLine();
            String linea=lector.readLine();
            int numero=2;
            while (linea!=null){
                if (!linea.isBlank()){
                    agregarCarta(linea,numero,lista);
                }
                linea=lector.readLine();
                numero++;
            }
            lector.close();
        } catch (IOException e){
            System.out.println("Error al leer el archivo: "+e.getMessage());
        }
    }

    private static void agregarCarta(String linea,int numero,ListaCircular<Carta> lista){
        try{
            Carta carta=parsearLinea(linea);
            lista.insertaFinal(carta);
            if (LectorCartas.class.getResource(CARPETA_IMAGENES+carta.getArchivoImagen())==null){
                System.out.println("Imagen no encontrada: "+carta.getArchivoImagen());
            }
        } catch (IllegalArgumentException e){
            System.out.println("Línea "+numero+": "+e.getMessage());
        }
    }
}