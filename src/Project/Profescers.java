/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author chain
 */
@Entity
@Table(name = "profescers", catalog = "dataproject", schema = "")
@NamedQueries({
    @NamedQuery(name = "Profescers.findAll", query = "SELECT p FROM Profescers p")
    , @NamedQuery(name = "Profescers.findById", query = "SELECT p FROM Profescers p WHERE p.id = :id")
    , @NamedQuery(name = "Profescers.findByProfescers", query = "SELECT p FROM Profescers p WHERE p.profescers = :profescers")})
public class Profescers implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "profescers")
    private String profescers;

    public Profescers() {
    }

    public Profescers(Integer id) {
        this.id = id;
    }

    public Profescers(Integer id, String profescers) {
        this.id = id;
        this.profescers = profescers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getProfescers() {
        return profescers;
    }

    public void setProfescers(String profescers) {
        String oldProfescers = this.profescers;
        this.profescers = profescers;
        changeSupport.firePropertyChange("profescers", oldProfescers, profescers);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profescers)) {
            return false;
        }
        Profescers other = (Profescers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Project.Profescers[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
