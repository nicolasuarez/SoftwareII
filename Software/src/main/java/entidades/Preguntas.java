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
@Table(name = "preguntas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Preguntas.findAll", query = "SELECT p FROM Preguntas p")
    , @NamedQuery(name = "Preguntas.findByIdPre", query = "SELECT p FROM Preguntas p WHERE p.idPre = :idPre")
    , @NamedQuery(name = "Preguntas.findByListaPre", query = "SELECT p FROM Preguntas p WHERE p.listaPre = :listaPre")})
public class Preguntas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IdPre")
    private Integer idPre;
    @Basic(optional = false)
    @Column(name = "ListaPre")
    private String listaPre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPre")
    private List<Encuestas> encuestasList;

    public Preguntas() {
    }

    public Preguntas(Integer idPre) {
        this.idPre = idPre;
    }

    public Preguntas(Integer idPre, String listaPre) {
        this.idPre = idPre;
        this.listaPre = listaPre;
    }

    public Integer getIdPre() {
        return idPre;
    }

    public void setIdPre(Integer idPre) {
        this.idPre = idPre;
    }

    public String getListaPre() {
        return listaPre;
    }

    public void setListaPre(String listaPre) {
        this.listaPre = listaPre;
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
        hash += (idPre != null ? idPre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Preguntas)) {
            return false;
        }
        Preguntas other = (Preguntas) object;
        if ((this.idPre == null && other.idPre != null) || (this.idPre != null && !this.idPre.equals(other.idPre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Preguntas[ idPre=" + idPre + " ]";
    }
    
}
