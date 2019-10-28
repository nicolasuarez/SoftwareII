/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.IllegalOrphanException;
import controladores.exceptions.NonexistentEntityException;
import controladores.exceptions.PreexistingEntityException;
import entidades.Usuarios;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Usuencuesta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author pc
 */
public class UsuariosJpaController implements Serializable {

    public UsuariosJpaController() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_JPA_bases_jar_1.0-SNAPSHOTPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuarios usuarios) throws PreexistingEntityException, Exception {
        if (usuarios.getUsuencuestaList() == null) {
            usuarios.setUsuencuestaList(new ArrayList<Usuencuesta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Usuencuesta> attachedUsuencuestaList = new ArrayList<Usuencuesta>();
            for (Usuencuesta usuencuestaListUsuencuestaToAttach : usuarios.getUsuencuestaList()) {
                usuencuestaListUsuencuestaToAttach = em.getReference(usuencuestaListUsuencuestaToAttach.getClass(), usuencuestaListUsuencuestaToAttach.getIdUsuEnc());
                attachedUsuencuestaList.add(usuencuestaListUsuencuestaToAttach);
            }
            usuarios.setUsuencuestaList(attachedUsuencuestaList);
            em.persist(usuarios);
            for (Usuencuesta usuencuestaListUsuencuesta : usuarios.getUsuencuestaList()) {
                Usuarios oldIdUsuOfUsuencuestaListUsuencuesta = usuencuestaListUsuencuesta.getIdUsu();
                usuencuestaListUsuencuesta.setIdUsu(usuarios);
                usuencuestaListUsuencuesta = em.merge(usuencuestaListUsuencuesta);
                if (oldIdUsuOfUsuencuestaListUsuencuesta != null) {
                    oldIdUsuOfUsuencuestaListUsuencuesta.getUsuencuestaList().remove(usuencuestaListUsuencuesta);
                    oldIdUsuOfUsuencuestaListUsuencuesta = em.merge(oldIdUsuOfUsuencuestaListUsuencuesta);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuarios(usuarios.getIdUsu()) != null) {
                throw new PreexistingEntityException("Usuarios " + usuarios + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuarios usuarios) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios persistentUsuarios = em.find(Usuarios.class, usuarios.getIdUsu());
            List<Usuencuesta> usuencuestaListOld = persistentUsuarios.getUsuencuestaList();
            List<Usuencuesta> usuencuestaListNew = usuarios.getUsuencuestaList();
            List<String> illegalOrphanMessages = null;
            for (Usuencuesta usuencuestaListOldUsuencuesta : usuencuestaListOld) {
                if (!usuencuestaListNew.contains(usuencuestaListOldUsuencuesta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usuencuesta " + usuencuestaListOldUsuencuesta + " since its idUsu field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Usuencuesta> attachedUsuencuestaListNew = new ArrayList<Usuencuesta>();
            for (Usuencuesta usuencuestaListNewUsuencuestaToAttach : usuencuestaListNew) {
                usuencuestaListNewUsuencuestaToAttach = em.getReference(usuencuestaListNewUsuencuestaToAttach.getClass(), usuencuestaListNewUsuencuestaToAttach.getIdUsuEnc());
                attachedUsuencuestaListNew.add(usuencuestaListNewUsuencuestaToAttach);
            }
            usuencuestaListNew = attachedUsuencuestaListNew;
            usuarios.setUsuencuestaList(usuencuestaListNew);
            usuarios = em.merge(usuarios);
            for (Usuencuesta usuencuestaListNewUsuencuesta : usuencuestaListNew) {
                if (!usuencuestaListOld.contains(usuencuestaListNewUsuencuesta)) {
                    Usuarios oldIdUsuOfUsuencuestaListNewUsuencuesta = usuencuestaListNewUsuencuesta.getIdUsu();
                    usuencuestaListNewUsuencuesta.setIdUsu(usuarios);
                    usuencuestaListNewUsuencuesta = em.merge(usuencuestaListNewUsuencuesta);
                    if (oldIdUsuOfUsuencuestaListNewUsuencuesta != null && !oldIdUsuOfUsuencuestaListNewUsuencuesta.equals(usuarios)) {
                        oldIdUsuOfUsuencuestaListNewUsuencuesta.getUsuencuestaList().remove(usuencuestaListNewUsuencuesta);
                        oldIdUsuOfUsuencuestaListNewUsuencuesta = em.merge(oldIdUsuOfUsuencuestaListNewUsuencuesta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuarios.getIdUsu();
                if (findUsuarios(id) == null) {
                    throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios usuarios;
            try {
                usuarios = em.getReference(Usuarios.class, id);
                usuarios.getIdUsu();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Usuencuesta> usuencuestaListOrphanCheck = usuarios.getUsuencuestaList();
            for (Usuencuesta usuencuestaListOrphanCheckUsuencuesta : usuencuestaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuarios (" + usuarios + ") cannot be destroyed since the Usuencuesta " + usuencuestaListOrphanCheckUsuencuesta + " in its usuencuestaList field has a non-nullable idUsu field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(usuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuarios> findUsuariosEntities() {
        return findUsuariosEntities(true, -1, -1);
    }

    public List<Usuarios> findUsuariosEntities(int maxResults, int firstResult) {
        return findUsuariosEntities(false, maxResults, firstResult);
    }

    private List<Usuarios> findUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuarios.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Usuarios findUsuarios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuarios> rt = cq.from(Usuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
