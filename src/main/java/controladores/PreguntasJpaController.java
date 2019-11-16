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
import entidades.Preguntas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author pc
 */
public class PreguntasJpaController implements Serializable {

    public PreguntasJpaController() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_JPA_bases_jar_1.0-SNAPSHOTPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Preguntas preguntas) throws PreexistingEntityException, Exception {
        if (preguntas.getEncuestasList() == null) {
            preguntas.setEncuestasList(new ArrayList<Encuestas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Encuestas> attachedEncuestasList = new ArrayList<Encuestas>();
            for (Encuestas encuestasListEncuestasToAttach : preguntas.getEncuestasList()) {
                encuestasListEncuestasToAttach = em.getReference(encuestasListEncuestasToAttach.getClass(), encuestasListEncuestasToAttach.getIdEnc());
                attachedEncuestasList.add(encuestasListEncuestasToAttach);
            }
            preguntas.setEncuestasList(attachedEncuestasList);
            em.persist(preguntas);
            for (Encuestas encuestasListEncuestas : preguntas.getEncuestasList()) {
                Preguntas oldIdPreOfEncuestasListEncuestas = encuestasListEncuestas.getIdPre();
                encuestasListEncuestas.setIdPre(preguntas);
                encuestasListEncuestas = em.merge(encuestasListEncuestas);
                if (oldIdPreOfEncuestasListEncuestas != null) {
                    oldIdPreOfEncuestasListEncuestas.getEncuestasList().remove(encuestasListEncuestas);
                    oldIdPreOfEncuestasListEncuestas = em.merge(oldIdPreOfEncuestasListEncuestas);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPreguntas(preguntas.getIdPre()) != null) {
                throw new PreexistingEntityException("Preguntas " + preguntas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Preguntas preguntas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Preguntas persistentPreguntas = em.find(Preguntas.class, preguntas.getIdPre());
            List<Encuestas> encuestasListOld = persistentPreguntas.getEncuestasList();
            List<Encuestas> encuestasListNew = preguntas.getEncuestasList();
            List<String> illegalOrphanMessages = null;
            for (Encuestas encuestasListOldEncuestas : encuestasListOld) {
                if (!encuestasListNew.contains(encuestasListOldEncuestas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Encuestas " + encuestasListOldEncuestas + " since its idPre field is not nullable.");
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
            preguntas.setEncuestasList(encuestasListNew);
            preguntas = em.merge(preguntas);
            for (Encuestas encuestasListNewEncuestas : encuestasListNew) {
                if (!encuestasListOld.contains(encuestasListNewEncuestas)) {
                    Preguntas oldIdPreOfEncuestasListNewEncuestas = encuestasListNewEncuestas.getIdPre();
                    encuestasListNewEncuestas.setIdPre(preguntas);
                    encuestasListNewEncuestas = em.merge(encuestasListNewEncuestas);
                    if (oldIdPreOfEncuestasListNewEncuestas != null && !oldIdPreOfEncuestasListNewEncuestas.equals(preguntas)) {
                        oldIdPreOfEncuestasListNewEncuestas.getEncuestasList().remove(encuestasListNewEncuestas);
                        oldIdPreOfEncuestasListNewEncuestas = em.merge(oldIdPreOfEncuestasListNewEncuestas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = preguntas.getIdPre();
                if (findPreguntas(id) == null) {
                    throw new NonexistentEntityException("The preguntas with id " + id + " no longer exists.");
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
            Preguntas preguntas;
            try {
                preguntas = em.getReference(Preguntas.class, id);
                preguntas.getIdPre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The preguntas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Encuestas> encuestasListOrphanCheck = preguntas.getEncuestasList();
            for (Encuestas encuestasListOrphanCheckEncuestas : encuestasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Preguntas (" + preguntas + ") cannot be destroyed since the Encuestas " + encuestasListOrphanCheckEncuestas + " in its encuestasList field has a non-nullable idPre field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(preguntas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Preguntas> findPreguntasEntities() {
        return findPreguntasEntities(true, -1, -1);
    }

    public List<Preguntas> findPreguntasEntities(int maxResults, int firstResult) {
        return findPreguntasEntities(false, maxResults, firstResult);
    }

    private List<Preguntas> findPreguntasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Preguntas.class));
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

    public Preguntas findPreguntas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Preguntas.class, id);
        } finally {
            em.close();
        }
    }

    public int getPreguntasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Preguntas> rt = cq.from(Preguntas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
