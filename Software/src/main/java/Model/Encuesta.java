package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MACC2
 */
public class Encuesta {
    
    private int id;
    private String genero;
    private int Semestre;
    private String habitos_De_Estudio;
    private String Edad;
    private boolean trabaja;
    private String hobbies;
    private boolean statusCarrera;
    private String nucleoFamiliar;
    private String lugarDeOrigen;
    private String Enfermedad;
    private String gusto;
    private String Carrera;

    public Encuesta() {
    }

    public Encuesta(int id, String genero, int Semestre, String habitos_De_Estudio, String Edad, boolean trabaja, String hobbies, boolean statusCarrera, String nucleoFamiliar, String lugarDeOrigen, String Enfermedad, String gusto, String Carrera) {
        this.id = id;
        this.genero = genero;
        this.Semestre = Semestre;
        this.habitos_De_Estudio = habitos_De_Estudio;
        this.Edad = Edad;
        this.trabaja = trabaja;
        this.hobbies = hobbies;
        this.statusCarrera = statusCarrera;
        this.nucleoFamiliar = nucleoFamiliar;
        this.lugarDeOrigen = lugarDeOrigen;
        this.Enfermedad = Enfermedad;
        this.gusto = gusto;
        this.Carrera = Carrera;
    }    

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getSemestre() {
        return Semestre;
    }

    public void setSemestre(int Eemestre) {
        this.Semestre = Eemestre;
    }

    public String getHabitos_De_Estudio() {
        return habitos_De_Estudio;
    }

    public void setHabitos_De_Estudio(String habitos_De_Estudio) {
        this.habitos_De_Estudio = habitos_De_Estudio;
    }

    public String getEdad() {
        return Edad;
    }

    public void setEdad(String Edad) {
        this.Edad = Edad;
    }

    public boolean isTrabaja() {
        return trabaja;
    }

    public void setTrabaja(boolean trabaja) {
        this.trabaja = trabaja;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public boolean isStatusCarrera() {
        return statusCarrera;
    }

    public void setStatusCarrera(boolean statusCarrera) {
        this.statusCarrera = statusCarrera;
    }

    public String getNucleoFamiliar() {
        return nucleoFamiliar;
    }

    public void setNucleoFamiliar(String nucleoFamiliar) {
        this.nucleoFamiliar = nucleoFamiliar;
    }

    public String getLugarDeOrigen() {
        return lugarDeOrigen;
    }

    public void setLugarDeOrigen(String lugarDeOrigen) {
        this.lugarDeOrigen = lugarDeOrigen;
    }

    public String getEnfermedad() {
        return Enfermedad;
    }

    public void setEnfermedad(String Enfermedad) {
        this.Enfermedad = Enfermedad;
    }

    public String getGusto() {
        return gusto;
    }

    public void setGusto(String gusto) {
        this.gusto = gusto;
    }

    public String getCarrera() {
        return Carrera;
    }

    public void setCarrera(String Carrera) {
        this.Carrera = Carrera;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
