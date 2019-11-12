/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pc
 */
@Entity
@Table(name = "historia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historia.findAll", query = "SELECT h FROM Historia h")
    , @NamedQuery(name = "Historia.findByIdHistoria", query = "SELECT h FROM Historia h WHERE h.idHistoria = :idHistoria")})
public class Historia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IdHistoria")
    private Integer idHistoria;
    @JoinColumn(name = "IdUsuEnc", referencedColumnName = "IdUsuEnc")
    @ManyToOne(optional = false)
    private Usuencuesta idUsuEnc;
    @JoinColumn(name = "IdHor", referencedColumnName = "IdHor")
    @ManyToOne(optional = false)
    private Horario idHor;

    public Historia() {
    }

    public Historia(Integer idHistoria) {
        this.idHistoria = idHistoria;
    }

    public Integer getIdHistoria() {
        return idHistoria;
    }

    public void setIdHistoria(Integer idHistoria) {
        this.idHistoria = idHistoria;
    }

    public Usuencuesta getIdUsuEnc() {
        return idUsuEnc;
    }

    public void setIdUsuEnc(Usuencuesta idUsuEnc) {
        this.idUsuEnc = idUsuEnc;
    }

    public Horario getIdHor() {
        return idHor;
    }

    public void setIdHor(Horario idHor) {
        this.idHor = idHor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistoria != null ? idHistoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historia)) {
            return false;
        }
        Historia other = (Historia) object;
        if ((this.idHistoria == null && other.idHistoria != null) || (this.idHistoria != null && !this.idHistoria.equals(other.idHistoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Historia[ idHistoria=" + idHistoria + " ]";
    }
    
}
