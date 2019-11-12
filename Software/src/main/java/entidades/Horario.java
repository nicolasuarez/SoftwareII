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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "horario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Horario.findAll", query = "SELECT h FROM Horario h")
    , @NamedQuery(name = "Horario.findByIdHor", query = "SELECT h FROM Horario h WHERE h.idHor = :idHor")})
public class Horario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IdHor")
    private Integer idHor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idHor")
    private List<Historia> historiaList;
    @JoinColumn(name = "IdMat", referencedColumnName = "IdMat")
    @ManyToOne(optional = false)
    private Materia idMat;

    public Horario() {
    }

    public Horario(Integer idHor) {
        this.idHor = idHor;
    }

    public Integer getIdHor() {
        return idHor;
    }

    public void setIdHor(Integer idHor) {
        this.idHor = idHor;
    }

    @XmlTransient
    public List<Historia> getHistoriaList() {
        return historiaList;
    }

    public void setHistoriaList(List<Historia> historiaList) {
        this.historiaList = historiaList;
    }

    public Materia getIdMat() {
        return idMat;
    }

    public void setIdMat(Materia idMat) {
        this.idMat = idMat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHor != null ? idHor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horario)) {
            return false;
        }
        Horario other = (Horario) object;
        if ((this.idHor == null && other.idHor != null) || (this.idHor != null && !this.idHor.equals(other.idHor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Horario[ idHor=" + idHor + " ]";
    }
    
}
