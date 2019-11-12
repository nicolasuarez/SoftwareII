/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import controladores.exceptions.PreexistingEntityException;
import entidades.Historia;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Usuencuesta;
import entidades.Horario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author pc
 */
public class HistoriaJpaController implements Serializable {

    public HistoriaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_JPA_bases_jar_1.0-SNAPSHOTPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Historia historia) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuencuesta idUsuEnc = historia.getIdUsuEnc();
            if (idUsuEnc != null) {
                idUsuEnc = em.getReference(idUsuEnc.getClass(), idUsuEnc.getIdUsuEnc());
                historia.setIdUsuEnc(idUsuEnc);
            }
            Horario idHor = historia.getIdHor();
            if (idHor != null) {
                idHor = em.getReference(idHor.getClass(), idHor.getIdHor());
                historia.setIdHor(idHor);
            }
            em.persist(historia);
            if (idUsuEnc != null) {
                idUsuEnc.getHistoriaList().add(historia);
                idUsuEnc = em.merge(idUsuEnc);
            }
            if (idHor != null) {
                idHor.getHistoriaList().add(historia);
                idHor = em.merge(idHor);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHistoria(historia.getIdHistoria()) != null) {
                throw new PreexistingEntityException("Historia " + historia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Historia historia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Historia persistentHistoria = em.find(Historia.class, historia.getIdHistoria());
            Usuencuesta idUsuEncOld = persistentHistoria.getIdUsuEnc();
            Usuencuesta idUsuEncNew = historia.getIdUsuEnc();
            Horario idHorOld = persistentHistoria.getIdHor();
            Horario idHorNew = historia.getIdHor();
            if (idUsuEncNew != null) {
                idUsuEncNew = em.getReference(idUsuEncNew.getClass(), idUsuEncNew.getIdUsuEnc());
                historia.setIdUsuEnc(idUsuEncNew);
            }
            if (idHorNew != null) {
                idHorNew = em.getReference(idHorNew.getClass(), idHorNew.getIdHor());
                historia.setIdHor(idHorNew);
            }
            historia = em.merge(historia);
            if (idUsuEncOld != null && !idUsuEncOld.equals(idUsuEncNew)) {
                idUsuEncOld.getHistoriaList().remove(historia);
                idUsuEncOld = em.merge(idUsuEncOld);
            }
            if (idUsuEncNew != null && !idUsuEncNew.equals(idUsuEncOld)) {
                idUsuEncNew.getHistoriaList().add(historia);
                idUsuEncNew = em.merge(idUsuEncNew);
            }
            if (idHorOld != null && !idHorOld.equals(idHorNew)) {
                idHorOld.getHistoriaList().remove(historia);
                idHorOld = em.merge(idHorOld);
            }
            if (idHorNew != null && !idHorNew.equals(idHorOld)) {
                idHorNew.getHistoriaList().add(historia);
                idHorNew = em.merge(idHorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = historia.getIdHistoria();
                if (findHistoria(id) == null) {
                    throw new NonexistentEntityException("The historia with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Historia historia;
            try {
                historia = em.getReference(Historia.class, id);
                historia.getIdHistoria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historia with id " + id + " no longer exists.", enfe);
            }
            Usuencuesta idUsuEnc = historia.getIdUsuEnc();
            if (idUsuEnc != null) {
                idUsuEnc.getHistoriaList().remove(historia);
                idUsuEnc = em.merge(idUsuEnc);
            }
            Horario idHor = historia.getIdHor();
            if (idHor != null) {
                idHor.getHistoriaList().remove(historia);
                idHor = em.merge(idHor);
            }
            em.remove(historia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Historia> findHistoriaEntities() {
        return findHistoriaEntities(true, -1, -1);
    }

    public List<Historia> findHistoriaEntities(int maxResults, int firstResult) {
        return findHistoriaEntities(false, maxResults, firstResult);
    }

    private List<Historia> findHistoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Historia.class));
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

    public Historia findHistoria(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Historia.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistoriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Historia> rt = cq.from(Historia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
