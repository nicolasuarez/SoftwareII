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
@Table(name = "usuencuesta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuencuesta.findAll", query = "SELECT u FROM Usuencuesta u")
    , @NamedQuery(name = "Usuencuesta.findByIdUsuEnc", query = "SELECT u FROM Usuencuesta u WHERE u.idUsuEnc = :idUsuEnc")})
public class Usuencuesta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IdUsuEnc")
    private Integer idUsuEnc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuEnc")
    private List<Historia> historiaList;
    @JoinColumn(name = "IdUsu", referencedColumnName = "IdUsu")
    @ManyToOne(optional = false)
    private Usuarios idUsu;
    @JoinColumn(name = "IdEnc", referencedColumnName = "IdEnc")
    @ManyToOne(optional = false)
    private Encuestas idEnc;

    public Usuencuesta() {
    }

    public Usuencuesta(Integer idUsuEnc) {
        this.idUsuEnc = idUsuEnc;
    }

    public Integer getIdUsuEnc() {
        return idUsuEnc;
    }

    public void setIdUsuEnc(Integer idUsuEnc) {
        this.idUsuEnc = idUsuEnc;
    }

    @XmlTransient
    public List<Historia> getHistoriaList() {
        return historiaList;
    }

    public void setHistoriaList(List<Historia> historiaList) {
        this.historiaList = historiaList;
    }

    public Usuarios getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(Usuarios idUsu) {
        this.idUsu = idUsu;
    }

    public Encuestas getIdEnc() {
        return idEnc;
    }

    public void setIdEnc(Encuestas idEnc) {
        this.idEnc = idEnc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuEnc != null ? idUsuEnc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuencuesta)) {
            return false;
        }
        Usuencuesta other = (Usuencuesta) object;
        if ((this.idUsuEnc == null && other.idUsuEnc != null) || (this.idUsuEnc != null && !this.idUsuEnc.equals(other.idUsuEnc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Usuencuesta[ idUsuEnc=" + idUsuEnc + " ]";
    }
    
}
