/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pc
 */
@Entity
@Table(name = "materia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Materia.findAll", query = "SELECT m FROM Materia m")
    , @NamedQuery(name = "Materia.findByIdMat", query = "SELECT m FROM Materia m WHERE m.idMat = :idMat")
    , @NamedQuery(name = "Materia.findByNombre", query = "SELECT m FROM Materia m WHERE m.nombre = :nombre")
    , @NamedQuery(name = "Materia.findByHora", query = "SELECT m FROM Materia m WHERE m.hora = :hora")})
public class Materia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IdMat")
    private Integer idMat;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "Hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMat")
    private List<Horario> horarioList;
    @JoinColumn(name = "IdNot", referencedColumnName = "IdNot")
    @ManyToOne(optional = false)
    private Notas idNot;

    public Materia() {
    }

    public Materia(Integer idMat) {
        this.idMat = idMat;
    }

    public Materia(Integer idMat, String nombre, Date hora) {
        this.idMat = idMat;
        this.nombre = nombre;
        this.hora = hora;
    }

    public Integer getIdMat() {
        return idMat;
    }

    public void setIdMat(Integer idMat) {
        this.idMat = idMat;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    @XmlTransient
    public List<Horario> getHorarioList() {
        return horarioList;
    }

    public void setHorarioList(List<Horario> horarioList) {
        this.horarioList = horarioList;
    }

    public Notas getIdNot() {
        return idNot;
    }

    public void setIdNot(Notas idNot) {
        this.idNot = idNot;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMat != null ? idMat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materia)) {
            return false;
        }
        Materia other = (Materia) object;
        if ((this.idMat == null && other.idMat != null) || (this.idMat != null && !this.idMat.equals(other.idMat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Materia[ idMat=" + idMat + " ]";
    }
    
}
