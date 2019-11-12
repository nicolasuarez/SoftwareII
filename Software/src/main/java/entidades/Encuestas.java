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
@Table(name = "encuestas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Encuestas.findAll", query = "SELECT e FROM Encuestas e")
    , @NamedQuery(name = "Encuestas.findByIdEnc", query = "SELECT e FROM Encuestas e WHERE e.idEnc = :idEnc")})
public class Encuestas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IdEnc")
    private Integer idEnc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEnc")
    private List<Usuencuesta> usuencuestaList;
    @JoinColumn(name = "IdPre", referencedColumnName = "IdPre")
    @ManyToOne(optional = false)
    private Preguntas idPre;
    @JoinColumn(name = "IdRes", referencedColumnName = "IdRes")
    @ManyToOne(optional = false)
    private Respuestas idRes;

    public Encuestas() {
    }

    public Encuestas(Integer idEnc) {
        this.idEnc = idEnc;
    }

    public Integer getIdEnc() {
        return idEnc;
    }

    public void setIdEnc(Integer idEnc) {
        this.idEnc = idEnc;
    }

    @XmlTransient
    public List<Usuencuesta> getUsuencuestaList() {
        return usuencuestaList;
    }

    public void setUsuencuestaList(List<Usuencuesta> usuencuestaList) {
        this.usuencuestaList = usuencuestaList;
    }

    public Preguntas getIdPre() {
        return idPre;
    }

    public void setIdPre(Preguntas idPre) {
        this.idPre = idPre;
    }

    public Respuestas getIdRes() {
        return idRes;
    }

    public void setIdRes(Respuestas idRes) {
        this.idRes = idRes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEnc != null ? idEnc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Encuestas)) {
            return false;
        }
        Encuestas other = (Encuestas) object;
        if ((this.idEnc == null && other.idEnc != null) || (this.idEnc != null && !this.idEnc.equals(other.idEnc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Encuestas[ idEnc=" + idEnc + " ]";
    }
    
}
