/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author User
 */
import java.io.BufferedReader;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.GsonBuilder;

public class utils {

    public static String readParams(HttpServletRequest request) {
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            request.setCharacterEncoding("UTF-8");
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception e) {
            /* report an error */ }

        line = jb.toString();
        return line;
    }

    /**
     * FUNCIONES BASICAS *
     */
    private static Random r = new Random();

    public static int aleatorioEntreDosNumeros(int min, int max) {
        return (r.nextInt((max - min) + 1) + min);
    }

    public static GsonBuilder builder = null;

    /**
     * Metodo encargado de recibir un objeto java y convertirlo en estructura
     * JSON.
     *
     * @param obj
     * @return String JSON
     */
    public static String toJson(Object obj) {
        if (builder == null) {
            builder = new GsonBuilder();
            builder.setExclusionStrategies(new TestExclStrat());
        }
        return builder.create().toJson(obj);
    }

    public static String JSONBase() {
        if (builder == null) {
            builder = new GsonBuilder();
            builder.setExclusionStrategies(new TestExclStrat());
        }
        return builder.create().toJson("");
    }

    /**
     * Esta Clase esta encargada de recibir un JSON y convertirlo en Objeto
     *
     * @param <T> clase a la cual quiere convertir
     * @param json JSON con la estructura y datos
     * @return Object objeto resultante del JSON
     */
    public static <T> Object fromJson(String json, Class<T> obj) {
        if (builder == null) {
            builder = new GsonBuilder();
            //builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            //builder.setDateFormat(DateFormat.FULL, DateFormat.FULL);
            //builder.setExclusionStrategies(new TestExclStrat());
        }
        return builder.create().fromJson(json, obj);
    }

    public static class TestExclStrat implements ExclusionStrategy {

        public boolean shouldSkipClass(Class<?> arg0) {
            return false;
        }

        public boolean shouldSkipField(FieldAttributes f) {
            return false;
        }
    }

    /*
     Metodo que recorre un vector de string comprobando que no hayan espacios vacios.
     */
    public static boolean espaciosVacios(String[] vector) {
        boolean nulls = false;
        for (String vector1 : vector) {
            if (vector1 == null || "".equals(vector1)) {
                nulls = true;
                break;
            }
        }
        return nulls;
    }

}