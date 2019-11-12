/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.IllegalOrphanException;
import controladores.exceptions.NonexistentEntityException;
import controladores.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Encuestas;
import entidades.Respuestas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author pc
 */
public class RespuestasJpaController implements Serializable {

    public RespuestasJpaController() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_JPA_bases_jar_1.0-SNAPSHOTPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Respuestas respuestas) throws PreexistingEntityException, Exception {
        if (respuestas.getEncuestasList() == null) {
            respuestas.setEncuestasList(new ArrayList<Encuestas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Encuestas> attachedEncuestasList = new ArrayList<Encuestas>();
            for (Encuestas encuestasListEncuestasToAttach : respuestas.getEncuestasList()) {
                encuestasListEncuestasToAttach = em.getReference(encuestasListEncuestasToAttach.getClass(), encuestasListEncuestasToAttach.getIdEnc());
                attachedEncuestasList.add(encuestasListEncuestasToAttach);
            }
            respuestas.setEncuestasList(attachedEncuestasList);
            em.persist(respuestas);
            for (Encuestas encuestasListEncuestas : respuestas.getEncuestasList()) {
                Respuestas oldIdResOfEncuestasListEncuestas = encuestasListEncuestas.getIdRes();
                encuestasListEncuestas.setIdRes(respuestas);
                encuestasListEncuestas = em.merge(encuestasListEncuestas);
                if (oldIdResOfEncuestasListEncuestas != null) {
                    oldIdResOfEncuestasListEncuestas.getEncuestasList().remove(encuestasListEncuestas);
                    oldIdResOfEncuestasListEncuestas = em.merge(oldIdResOfEncuestasListEncuestas);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRespuestas(respuestas.getIdRes()) != null) {
                throw new PreexistingEntityException("Respuestas " + respuestas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Respuestas respuestas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Respuestas persistentRespuestas = em.find(Respuestas.class, respuestas.getIdRes());
            List<Encuestas> encuestasListOld = persistentRespuestas.getEncuestasList();
            List<Encuestas> encuestasListNew = respuestas.getEncuestasList();
            List<String> illegalOrphanMessages = null;
            for (Encuestas encuestasListOldEncuestas : encuestasListOld) {
                if (!encuestasListNew.contains(encuestasListOldEncuestas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Encuestas " + encuestasListOldEncuestas + " since its idRes field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Encuestas> attachedEncuestasListNew = new ArrayList<Encuestas>();
            for (Encuestas encuestasListNewEncuestasToAttach : encuestasListNew) {
                encuestasListNewEncuestasToAttach = em.getReference(encuestasListNewEncuestasToAttach.getClass(), encuestasListNewEncuestasToAttach.getIdEnc());
                attachedEncuestasListNew.add(encuestasListNewEncuestasToAttach);
            }
            encuestasListNew = attachedEncuestasListNew;
            respuestas.setEncuestasList(encuestasListNew);
            respuestas = em.merge(respuestas);
            for (Encuestas encuestasListNewEncuestas : encuestasListNew) {
                if (!encuestasListOld.contains(encuestasListNewEncuestas)) {
                    Respuestas oldIdResOfEncuestasListNewEncuestas = encuestasListNewEncuestas.getIdRes();
                    encuestasListNewEncuestas.setIdRes(respuestas);
                    encuestasListNewEncuestas = em.merge(encuestasListNewEncuestas);
                    if (oldIdResOfEncuestasListNewEncuestas != null && !oldIdResOfEncuestasListNewEncuestas.equals(respuestas)) {
                        oldIdResOfEncuestasListNewEncuestas.getEncuestasList().remove(encuestasListNewEncuestas);
                        oldIdResOfEncuestasListNewEncuestas = em.merge(oldIdResOfEncuestasListNewEncuestas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = respuestas.getIdRes();
                if (findRespuestas(id) == null) {
                    throw new NonexistentEntityException("The respuestas with id " + id + " no longer exists.");
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
            Respuestas respuestas;
            try {
                respuestas = em.getReference(Respuestas.class, id);
                respuestas.getIdRes();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The respuestas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Encuestas> encuestasListOrphanCheck = respuestas.getEncuestasList();
            for (Encuestas encuestasListOrphanCheckEncuestas : encuestasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Respuestas (" + respuestas + ") cannot be destroyed since the Encuestas " + encuestasListOrphanCheckEncuestas + " in its encuestasList field has a non-nullable idRes field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(respuestas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Respuestas> findRespuestasEntities() {
        return findRespuestasEntities(true, -1, -1);
    }

    public List<Respuestas> findRespuestasEntities(int maxResults, int firstResult) {
        return findRespuestasEntities(false, maxResults, firstResult);
    }

    private List<Respuestas> findRespuestasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Respuestas.class));
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

    public Respuestas findRespuestas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Respuestas.class, id);
        } finally {
            em.close();
        }
    }

    public int getRespuestasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Respuestas> rt = cq.from(Respuestas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
