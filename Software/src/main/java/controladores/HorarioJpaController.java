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
import entidades.Materia;
import entidades.Historia;
import entidades.Horario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author pc
 */
public class HorarioJpaController implements Serializable {

    public HorarioJpaController() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_JPA_bases_jar_1.0-SNAPSHOTPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Horario horario) throws PreexistingEntityException, Exception {
        if (horario.getHistoriaList() == null) {
            horario.setHistoriaList(new ArrayList<Historia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materia idMat = horario.getIdMat();
            if (idMat != null) {
                idMat = em.getReference(idMat.getClass(), idMat.getIdMat());
                horario.setIdMat(idMat);
            }
            List<Historia> attachedHistoriaList = new ArrayList<Historia>();
            for (Historia historiaListHistoriaToAttach : horario.getHistoriaList()) {
                historiaListHistoriaToAttach = em.getReference(historiaListHistoriaToAttach.getClass(), historiaListHistoriaToAttach.getIdHistoria());
                attachedHistoriaList.add(historiaListHistoriaToAttach);
            }
            horario.setHistoriaList(attachedHistoriaList);
            em.persist(horario);
            if (idMat != null) {
                idMat.getHorarioList().add(horario);
                idMat = em.merge(idMat);
            }
            for (Historia historiaListHistoria : horario.getHistoriaList()) {
                Horario oldIdHorOfHistoriaListHistoria = historiaListHistoria.getIdHor();
                historiaListHistoria.setIdHor(horario);
                historiaListHistoria = em.merge(historiaListHistoria);
                if (oldIdHorOfHistoriaListHistoria != null) {
                    oldIdHorOfHistoriaListHistoria.getHistoriaList().remove(historiaListHistoria);
                    oldIdHorOfHistoriaListHistoria = em.merge(oldIdHorOfHistoriaListHistoria);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHorario(horario.getIdHor()) != null) {
                throw new PreexistingEntityException("Horario " + horario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Horario horario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Horario persistentHorario = em.find(Horario.class, horario.getIdHor());
            Materia idMatOld = persistentHorario.getIdMat();
            Materia idMatNew = horario.getIdMat();
            List<Historia> historiaListOld = persistentHorario.getHistoriaList();
            List<Historia> historiaListNew = horario.getHistoriaList();
            List<String> illegalOrphanMessages = null;
            for (Historia historiaListOldHistoria : historiaListOld) {
                if (!historiaListNew.contains(historiaListOldHistoria)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Historia " + historiaListOldHistoria + " since its idHor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idMatNew != null) {
                idMatNew = em.getReference(idMatNew.getClass(), idMatNew.getIdMat());
                horario.setIdMat(idMatNew);
            }
            List<Historia> attachedHistoriaListNew = new ArrayList<Historia>();
            for (Historia historiaListNewHistoriaToAttach : historiaListNew) {
                historiaListNewHistoriaToAttach = em.getReference(historiaListNewHistoriaToAttach.getClass(), historiaListNewHistoriaToAttach.getIdHistoria());
                attachedHistoriaListNew.add(historiaListNewHistoriaToAttach);
            }
            historiaListNew = attachedHistoriaListNew;
            horario.setHistoriaList(historiaListNew);
            horario = em.merge(horario);
            if (idMatOld != null && !idMatOld.equals(idMatNew)) {
                idMatOld.getHorarioList().remove(horario);
                idMatOld = em.merge(idMatOld);
            }
            if (idMatNew != null && !idMatNew.equals(idMatOld)) {
                idMatNew.getHorarioList().add(horario);
                idMatNew = em.merge(idMatNew);
            }
            for (Historia historiaListNewHistoria : historiaListNew) {
                if (!historiaListOld.contains(historiaListNewHistoria)) {
                    Horario oldIdHorOfHistoriaListNewHistoria = historiaListNewHistoria.getIdHor();
                    historiaListNewHistoria.setIdHor(horario);
                    historiaListNewHistoria = em.merge(historiaListNewHistoria);
                    if (oldIdHorOfHistoriaListNewHistoria != null && !oldIdHorOfHistoriaListNewHistoria.equals(horario)) {
                        oldIdHorOfHistoriaListNewHistoria.getHistoriaList().remove(historiaListNewHistoria);
                        oldIdHorOfHistoriaListNewHistoria = em.merge(oldIdHorOfHistoriaListNewHistoria);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = horario.getIdHor();
                if (findHorario(id) == null) {
                    throw new NonexistentEntityException("The horario with id " + id + " no longer exists.");
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
            Horario horario;
            try {
                horario = em.getReference(Horario.class, id);
                horario.getIdHor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The horario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Historia> historiaListOrphanCheck = horario.getHistoriaList();
            for (Historia historiaListOrphanCheckHistoria : historiaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Horario (" + horario + ") cannot be destroyed since the Historia " + historiaListOrphanCheckHistoria + " in its historiaList field has a non-nullable idHor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Materia idMat = horario.getIdMat();
            if (idMat != null) {
                idMat.getHorarioList().remove(horario);
                idMat = em.merge(idMat);
            }
            em.remove(horario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Horario> findHorarioEntities() {
        return findHorarioEntities(true, -1, -1);
    }

    public List<Horario> findHorarioEntities(int maxResults, int firstResult) {
        return findHorarioEntities(false, maxResults, firstResult);
    }

    private List<Horario> findHorarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Horario.class));
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

    public Horario findHorario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Horario.class, id);
        } finally {
            em.close();
        }
    }

    public int getHorarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Horario> rt = cq.from(Horario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
