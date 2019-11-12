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
@Table(name = "respuestas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Respuestas.findAll", query = "SELECT r FROM Respuestas r")
    , @NamedQuery(name = "Respuestas.findByIdRes", query = "SELECT r FROM Respuestas r WHERE r.idRes = :idRes")
    , @NamedQuery(name = "Respuestas.findByListaRes", query = "SELECT r FROM Respuestas r WHERE r.listaRes = :listaRes")})
public class Respuestas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IdRes")
    private Integer idRes;
    @Column(name = "ListaRes")
    private String listaRes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRes")
    private List<Encuestas> encuestasList;

    public Respuestas() {
    }

    public Respuestas(Integer idRes) {
        this.idRes = idRes;
    }

    public Integer getIdRes() {
        return idRes;
    }

    public void setIdRes(Integer idRes) {
        this.idRes = idRes;
    }

    public String getListaRes() {
        return listaRes;
    }

    public void setListaRes(String listaRes) {
        this.listaRes = listaRes;
    }

    @XmlTransient
    public List<Encuestas> getEncuestasList() {
        return encuestasList;
    }

    public void setEncuestasList(List<Encuestas> encuestasList) {
        this.encuestasList = encuestasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRes != null ? idRes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Respuestas)) {
            return false;
        }
        Respuestas other = (Respuestas) object;
        if ((this.idRes == null && other.idRes != null) || (this.idRes != null && !this.idRes.equals(other.idRes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Respuestas[ idRes=" + idRes + " ]";
    }
    
}
