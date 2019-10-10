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
public class Liga {

    /**
     * @param args the command line arguments
     */
    private Equipo[] equipos = new Equipo[10];
    private String nombre;

    public Liga(String nombre) {
        this.nombre = nombre;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Liga a = new Liga("a");
        Equipo A = new Equipo("a");
        Equipo B = new Equipo("b");
        Equipo C = new Equipo("c");
        Equipo D = new Equipo("d");

        a.addEquipo(A);
        a.addEquipo(B);
        a.addEquipo(D);
        a.addEquipo(C);

        a.Calendario();
        a.registrarParido(1, 2, 2, 1);
        a.mostrarT();

    }

    public Equipo[] getEquipos() {
        return equipos;
    }

    public void setEquipos(Equipo[] equipos) {
        this.equipos = equipos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addEquipo(Equipo e) {
        for (int i = 0; i < equipos.length; i++) {

            if (equipos[i] == null) {

                equipos[i] = e;
                i = equipos.length;
            }
        }
    }

    public void mostrarT() {
        System.out.println("Equipo  PJ PG PE PP GF GC DG PTS");
        System.out.println("------  -- -- -- -- -- -- -- ---");
        for (int i = 0; i < equipos.length; i++) {
            Equipo e = equipos[i];
            if (e != null) {

                System.out.println("  " + e.getNombre() + "     " + e.getJuegosJugados() + "  " + e.getJuegosGanados() + "  " + e.getJuegosEmpatados()
                        + "  " + e.getJuegosPerdidos() + "  " + e.getGolesFavor() + "  " + e.getGolesEnContra() + "  " + e.DiferenciaGol() + "  " + e.pts());
            }

        }
    }

    public void registrarParido(int a, int b, int ngol1, int ngol2) {
        Equipo e1 = new Equipo();
        Equipo e2 = new Equipo();
        for (int i = 0; i < a; i++) {
            e1 = equipos[i];
        }
        for (int i = 0; i < b; i++) {
            e2 = equipos[i];
        }
        e1.setJuegosJugados(e1.getJuegosJugados() + 1);
        e2.setJuegosJugados(e2.getJuegosJugados() + 1);

        e1.setGolesEnContra(ngol2);
        e2.setGolesEnContra(ngol1);

        e1.setGolesFavor(ngol1);
        e2.setGolesFavor(ngol2);
        System.out.println(ngol1);
        System.out.println(e1.getGolesFavor());
        if (ngol1 > ngol2) {
            e1.setJuegosGanados(e1.getJuegosGanados() + 1);
            e2.setJuegosPerdidos(e2.getJuegosPerdidos() + 1);
        } else if (ngol1 < ngol2) {
            e1.setJuegosPerdidos(e1.getJuegosPerdidos() + 1);
            e2.setJuegosGanados(e2.getJuegosGanados() + 1);

        } else {
            e1.setJuegosEmpatados(1 + e1.getJuegosEmpatados());
            e2.setJuegosEmpatados(1 + e2.getJuegosEmpatados());
        }
    }

    private String[][] matriz1, matriz2, jornadas;

    public void Calendario() {
        int N = 0;
        for (int i = 0; i < equipos.length; i++) {
            if (equipos[i] != null) {
                N ++;
            }
        }

        matriz1 = new String[N - 1][N / 2];
        matriz2 = new String[N - 1][N / 2];
        jornadas = new String[N - 1][N / 2]; //primera vuelta
     

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N / 2; j++) {
                //matriz1
                matriz1[i][j] = equipos[i].getNombre();
               

                //matriz2
                if (j == 0) {
                    matriz2[i][j] = equipos[N-1].getNombre();
                } else {
                    matriz2[i][j] = equipos[i].getNombre();
                    
                }

                //Elaboro la matriz final de enfrentamientos por jornada (primera vuelta)
                if (j == 0) {
                    if (i % 2 == 0) {
                        jornadas[i][j] = matriz2[i][j] + "-" + matriz1[i][j] + " ";
                    } else {
                        jornadas[i][j] = matriz1[i][j] + "-" + matriz2[i][j] + " ";
                    }
                } else {
                    jornadas[i][j] = matriz1[i][j] + "-" + matriz2[i][j] + " ";
                }

            }
        }

        //Solo para mostrarlo por consola
        int jorn = 1;
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N / 2; j++) {
                System.out.print("J" + jorn + " " + jornadas[i][j]);
                if (j == (N / 2) - 1) {
                    System.out.println();
                }
            }
            jorn++;
        }

        System.out.println();

    }
}
