/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liga;

/**
 *
 * @author User
 */
public class Equipo {

    private String nombre;
    private int juegosJugados;
    private int juegosGanados;
    private int juegosEmpatados;
    private int juegosPerdidos;
    private int golesFavor;
    private int golesEnContra;

    private final jugador[] Jugadores = new jugador[22];

    public Equipo() {
    }

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.juegosJugados = 0;
        this.juegosGanados = 0;
        this.juegosEmpatados = 0;
        this.juegosPerdidos = 0;
        this.golesFavor = 0;
        this.golesEnContra = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getJuegosJugados() {
        return juegosJugados;
    }

    public void setJuegosJugados(int juegosJugados) {
        this.juegosJugados = juegosJugados;
    }

    public int getJuegosGanados() {
        return juegosGanados;
    }

    public void setJuegosGanados(int juegosGanados) {
        this.juegosGanados = juegosGanados;
    }

    public int getJuegosEmpatados() {
        return juegosEmpatados;
    }

    public void setJuegosEmpatados(int juegosEmpatados) {
        this.juegosEmpatados = juegosEmpatados;
    }

    public int getJuegosPerdidos() {
        return juegosPerdidos;
    }

    public void setJuegosPerdidos(int juegosPerdidos) {
        this.juegosPerdidos = juegosPerdidos;
    }

    public int getGolesFavor() {
        return golesFavor;
    }

    public void setGolesFavor(int golesFavor) {
        this.golesFavor = golesFavor;
    }

    public int getGolesEnContra() {
        return golesEnContra;
    }

    public void setGolesEnContra(int golesEnContra) {
        this.golesEnContra = golesEnContra;
    }

    public int DiferenciaGol() {
        int a = golesFavor - golesEnContra;
        
      
        return a;
    }

    public void addJugador(jugador j) {
        for (int i = 0; i < Jugadores.length; i++) {
            if (Jugadores[i] == null) {
                Jugadores[i] = j;
            }

        }

    }

    public int pts() {
        int pts = (juegosGanados * 3) + juegosEmpatados;
        return pts;
    }
}
