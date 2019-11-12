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
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u")
    , @NamedQuery(name = "Usuarios.findByIdUsu", query = "SELECT u FROM Usuarios u WHERE u.idUsu = :idUsu")
    , @NamedQuery(name = "Usuarios.findByUsu", query = "SELECT u FROM Usuarios u WHERE u.usu = :usu")
    , @NamedQuery(name = "Usuarios.findByContrese\u00f1a", query = "SELECT u FROM Usuarios u WHERE u.contrese\u00f1a = :contrese\u00f1a")
    , @NamedQuery(name = "Usuarios.findByNombre", query = "SELECT u FROM Usuarios u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Usuarios.findByApellido", query = "SELECT u FROM Usuarios u WHERE u.apellido = :apellido")
    , @NamedQuery(name = "Usuarios.findByTipo", query = "SELECT u FROM Usuarios u WHERE u.tipo = :tipo")
    , @NamedQuery(name = "Usuarios.findBySexo", query = "SELECT u FROM Usuarios u WHERE u.sexo = :sexo")
    , @NamedQuery(name = "Usuarios.findByCorreo", query = "SELECT u FROM Usuarios u WHERE u.correo = :correo")})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IdUsu")
    private Integer idUsu;
    @Basic(optional = false)
    @Column(name = "Usu")
    private String usu;
    @Basic(optional = false)
    @Column(name = "Contrese\u00f1a")
    private String contreseña;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "Apellido")
    private String apellido;
    @Basic(optional = false)
    @Column(name = "Tipo")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "Sexo")
    private String sexo;
    @Basic(optional = false)
    @Column(name = "Correo")
    private String correo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsu")
    private List<Usuencuesta> usuencuestaList;

    public Usuarios() {
    }

    public Usuarios(Integer idUsu) {
        this.idUsu = idUsu;
    }

    public Usuarios(Integer idUsu, String usu, String contreseña, String nombre, String apellido, String tipo, String sexo, String correo) {
        this.idUsu = idUsu;
        this.usu = usu;
        this.contreseña = contreseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipo = tipo;
        this.sexo = sexo;
        this.correo = correo;
    }

    public Integer getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(Integer idUsu) {
        this.idUsu = idUsu;
    }

    public String getUsu() {
        return usu;
    }

    public void setUsu(String usu) {
        this.usu = usu;
    }

    public String getContreseña() {
        return contreseña;
    }

    public void setContreseña(String contreseña) {
        this.contreseña = contreseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @XmlTransient
    public List<Usuencuesta> getUsuencuestaList() {
        return usuencuestaList;
    }

    public void setUsuencuestaList(List<Usuencuesta> usuencuestaList) {
        this.usuencuestaList = usuencuestaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsu != null ? idUsu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.idUsu == null && other.idUsu != null) || (this.idUsu != null && !this.idUsu.equals(other.idUsu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Usuarios[ idUsu=" + idUsu + " ]";
    }
    
}
