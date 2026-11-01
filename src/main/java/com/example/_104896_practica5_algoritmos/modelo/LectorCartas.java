package com.example._104896_practica5_algoritmos.modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class LectorCartas{
    private static final int NUM_CAMPOS=9;

    public static Carta parsearLinea(String linea){
        String[] campos=linea.split("\\|",-1);
        if(campos.length!=NUM_CAMPOS){
            throw new IllegalArgumentException("Se esperaban "+NUM_CAMPOS+" campos y se encontraron "+campos.length);
        }
        return new Carta(campos[0],campos[1],campos[2],campos[3],aEntero(campos[4]),aEntero(campos[5]),campos[6],campos[7],campos[8]);
    }

    private static int aEntero(String texto){
        try{
            return Integer.parseInt(texto.trim());
        }catch(NumberFormatException e){
            return -1;
        }
    }

    public static void cargar(String recurso,ListaCircular<Carta> lista){
        InputStream flujo=LectorCartas.class.getResourceAsStream(recurso);
        if(flujo==null){
            System.out.println("No se encontró el archivo "+recurso);
            return;
        }
        try(BufferedReader lector=new BufferedReader(new InputStreamReader(flujo,StandardCharsets.UTF_8))){
            lector.readLine();
            String linea;
            int numero=1;
            while((linea=lector.readLine())!=null){
                numero++;
                if(linea.isBlank()){
                    continue;
                }
                try{
                    lista.insertaFinal(parsearLinea(linea));
                }catch(IllegalArgumentException e){
                    System.out.println("Línea "+numero+": "+e.getMessage());
                }
            }
        }catch(IOException e){
            System.out.println("Error al leer el archivo: "+e.getMessage());
        }
    }
}