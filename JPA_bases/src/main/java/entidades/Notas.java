/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pc
 */
@Entity
@Table(name = "notas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notas.findAll", query = "SELECT n FROM Notas n")
    , @NamedQuery(name = "Notas.findByIdNot", query = "SELECT n FROM Notas n WHERE n.idNot = :idNot")
    , @NamedQuery(name = "Notas.findByNotas", query = "SELECT n FROM Notas n WHERE n.notas = :notas")
    , @NamedQuery(name = "Notas.findByCorte", query = "SELECT n FROM Notas n WHERE n.corte = :corte")})
public class Notas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IdNot")
    private Integer idNot;
    @Basic(optional = false)
    @Column(name = "Notas")
    private double notas;
    @Basic(optional = false)
    @Column(name = "Corte")
    private int corte;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNot")
    private List<Materia> materiaList;

    public Notas() {
    }

    public Notas(Integer idNot) {
        this.idNot = idNot;
    }

    public Notas(Integer idNot, double notas, int corte) {
        this.idNot = idNot;
        this.notas = notas;
        this.corte = corte;
    }

    public Integer getIdNot() {
        return idNot;
    }

    public void setIdNot(Integer idNot) {
        this.idNot = idNot;
    }

    public double getNotas() {
        return notas;
    }

    public void setNotas(double notas) {
        this.notas = notas;
    }

    public int getCorte() {
        return corte;
    }

    public void setCorte(int corte) {
        this.corte = corte;
    }

    @XmlTransient
    public List<Materia> getMateriaList() {
        return materiaList;
    }

    public void setMateriaList(List<Materia> materiaList) {
        this.materiaList = materiaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNot != null ? idNot.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notas)) {
            return false;
        }
        Notas other = (Notas) object;
        if ((this.idNot == null && other.idNot != null) || (this.idNot != null && !this.idNot.equals(other.idNot))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Notas[ idNot=" + idNot + " ]";
    }
    
}
