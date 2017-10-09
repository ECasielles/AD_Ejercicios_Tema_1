package com.example.usuario.ad_ejercicios_tema_1;

//Autor: Enrique Casielles

/**
 * FUENTE PARA LISTADO DE UTF-8:
 * http://www.fileformat.info/info/charset/UTF-8/list.htm
 * <p>
 * Valor mínimo soportado para un carácter es U+0001
 * Valor máximo soportado para un carácter es U+10FFFF
 * http://www.fileformat.info/info/charset/UTF-8/list.htm?start=100352
 * <p>
 * Fuente con tutorial de buenas prácticas de formateo de texto:
 * http://www.joelonsoftware.com/articles/Unicode.html
 * <p>
 * Valores con los intervalos de los caracteres codificables a UTF-8:
 * http://www.fileformat.info/info/unicode/utf8.htm
 * <p>
 * En hexadecimal:
 * -Primer byte: 00 -> 7F
 * -Segundo byte: C2 80 -> DF BF
 * -Tercer byte: E0 A0 80 -> EF BF BF
 * -Cuarto byte: F0 90 80 80 -> F4 8F BF BF
 * <p>
 * Aunque Java no trabaja con UTF-8, Android sí lo hace.
 */

import android.app.Activity;
import android.widget.Toast;

public class Cesar {

    private int valorByte0, valorByte1, valorByte2, valorByte3, valorByte4,
            valorByte5,
            intervalo0, intervalo1, intervalo2, intervalo3, intervalo4,
            valorSumaMap0, valorSumaMap1, valorSumaMap2;

    private long valorByte6, valorByte7, intervalo5, intervalo6,
            valorSumaMap3, totalElementos;

    private Activity miActividad;

    public Cesar(Ejercicio4Activity miActividad) {
        this.miActividad = miActividad;
    }

    // Si falla devuelve -1
    public String cifrar(String fraseEntrada, long clave) {

        String salida = "?";

        try {
            // Inicializa los valores de las constantes de UTF-8 y de mapeo necesarias
            inicializaUTF8();

            // Limito el valor de la clave dentro del rango de los elementos deseados
            if (clave != 0)
                clave %= totalElementos;

            // Claves negativas permiten acceder a los elementos del final del conjunto
            if (clave < 0)
                clave += totalElementos;

            salida = new String();

            for (int i = 0; i < fraseEntrada.length(); i++){
                salida += (char)(((int)fraseEntrada.charAt(i) + clave) % totalElementos);
            }

        } catch (Exception e) {
            Toast.makeText(miActividad.getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return salida;
    }

    // Como la lista UTF-8 contiene intervalos vacíos de números, hay que hacer una transformación
    // a lista contínua a la que llamaremos mapeo
    private long mapeaCaracter(long entrada) {

        // Inicializamos salida en caso de que el carácter pasado no sea válido
        long salida = (int)'?';

        if (entrada >= valorByte0 && entrada <= valorByte1) {
            salida = entrada - valorSumaMap0;

        } else if (entrada >= valorByte3 && entrada <= valorByte2) {
            salida = entrada - valorSumaMap1;

        } else if (entrada >= valorByte5 && entrada <= valorByte4) {
            salida = entrada - valorSumaMap2;

        } else if (entrada >= valorByte7 && entrada <= valorByte4) {
            salida = entrada - valorSumaMap3;
        }

        return salida;
    }

    // Devuelve equivalente UTF-8 respecto al correspondiente a nuestro mapa
    private long desmapeaCaracter(long entrada) {

        long salida, demap;

        demap = entrada + valorSumaMap0;

        if (demap >= valorByte0 && demap <= valorByte1) {
            salida = demap;

        } else {
            demap = entrada + valorSumaMap1;
            if (demap >= valorByte2 && demap <= valorByte3) {
                salida = demap;

            } else {
                demap = entrada + valorSumaMap2;
                if (demap >= valorByte4 && demap <= valorByte5) {
                    salida = demap;

                } else {
                    demap = entrada + valorSumaMap3;
                    if (demap >= valorByte6 && demap <= valorByte7) {
                        salida = demap;

                    } else {
                        salida = (int)'?';
                    }
                }
            }
        }

        return salida;
    }

    // Inicializa todas las variables de programa según los valores de la documentación
    private void inicializaUTF8() {

        // Valores de cada intervalo según la documentación UTF-8
        valorByte0 = Integer.decode("#00");
        valorByte1 = Integer.decode("#7f");
        valorByte2 = Integer.decode("#c280");
        valorByte3 = Integer.decode("#dfbf");
        valorByte4 = Integer.decode("#e0a080");
        valorByte5 = Integer.decode("#efbfbf");
        valorByte6 = Long.decode("#f0908080");
        valorByte7 = Long.decode("#f48fbfbf");

        intervalo0 = valorByte1 - valorByte0;
        intervalo1 = valorByte2 - valorByte1;
        intervalo2 = valorByte3 - valorByte2;
        intervalo3 = valorByte4 - valorByte3;
        intervalo4 = valorByte5 - valorByte4;
        intervalo5 = valorByte6 - valorByte5;
        intervalo6 = valorByte7 - valorByte6;

        /**
         La operación de mapeo funciona con la siguiente fórmula
         valorSumaMap0 = Dentro1
         valorSumaMap1 = Dentro2 - Fuera1 + valorSumaMap0 - 1
         valorSumaMap2 = Dentro3 - Fuera2 + valorSumaMap1 - 1
         valorSumaMap3 = Dentro4 - Fuera3 + valorSumaMap2 - 1

         Así obtenemos los valores de transformación
         */

        // Valores de transformación para mapear
        valorSumaMap0 = valorByte0;
        valorSumaMap1 = intervalo1 + valorSumaMap0 - 1;
        valorSumaMap2 = intervalo3 + valorSumaMap1 - 1;
        valorSumaMap3 = intervalo5 + valorSumaMap2 - 1;

        // Número total de elementos omitiendo intervalos vacíos. También es el máximo de la clave.
        totalElementos = intervalo0 + intervalo2 + intervalo4 + intervalo6;
    }

}
