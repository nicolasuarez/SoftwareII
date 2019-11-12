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
import entidades.Notas;
import entidades.Horario;
import entidades.Materia;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author pc
 */
public class MateriaJpaController implements Serializable {

    public MateriaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_JPA_bases_jar_1.0-SNAPSHOTPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Materia materia) throws PreexistingEntityException, Exception {
        if (materia.getHorarioList() == null) {
            materia.setHorarioList(new ArrayList<Horario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Notas idNot = materia.getIdNot();
            if (idNot != null) {
                idNot = em.getReference(idNot.getClass(), idNot.getIdNot());
                materia.setIdNot(idNot);
            }
            List<Horario> attachedHorarioList = new ArrayList<Horario>();
            for (Horario horarioListHorarioToAttach : materia.getHorarioList()) {
                horarioListHorarioToAttach = em.getReference(horarioListHorarioToAttach.getClass(), horarioListHorarioToAttach.getIdHor());
                attachedHorarioList.add(horarioListHorarioToAttach);
            }
            materia.setHorarioList(attachedHorarioList);
            em.persist(materia);
            if (idNot != null) {
                idNot.getMateriaList().add(materia);
                idNot = em.merge(idNot);
            }
            for (Horario horarioListHorario : materia.getHorarioList()) {
                Materia oldIdMatOfHorarioListHorario = horarioListHorario.getIdMat();
                horarioListHorario.setIdMat(materia);
                horarioListHorario = em.merge(horarioListHorario);
                if (oldIdMatOfHorarioListHorario != null) {
                    oldIdMatOfHorarioListHorario.getHorarioList().remove(horarioListHorario);
                    oldIdMatOfHorarioListHorario = em.merge(oldIdMatOfHorarioListHorario);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMateria(materia.getIdMat()) != null) {
                throw new PreexistingEntityException("Materia " + materia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Materia materia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materia persistentMateria = em.find(Materia.class, materia.getIdMat());
            Notas idNotOld = persistentMateria.getIdNot();
            Notas idNotNew = materia.getIdNot();
            List<Horario> horarioListOld = persistentMateria.getHorarioList();
            List<Horario> horarioListNew = materia.getHorarioList();
            List<String> illegalOrphanMessages = null;
            for (Horario horarioListOldHorario : horarioListOld) {
                if (!horarioListNew.contains(horarioListOldHorario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Horario " + horarioListOldHorario + " since its idMat field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idNotNew != null) {
                idNotNew = em.getReference(idNotNew.getClass(), idNotNew.getIdNot());
                materia.setIdNot(idNotNew);
            }
            List<Horario> attachedHorarioListNew = new ArrayList<Horario>();
            for (Horario horarioListNewHorarioToAttach : horarioListNew) {
                horarioListNewHorarioToAttach = em.getReference(horarioListNewHorarioToAttach.getClass(), horarioListNewHorarioToAttach.getIdHor());
                attachedHorarioListNew.add(horarioListNewHorarioToAttach);
            }
            horarioListNew = attachedHorarioListNew;
            materia.setHorarioList(horarioListNew);
            materia = em.merge(materia);
            if (idNotOld != null && !idNotOld.equals(idNotNew)) {
                idNotOld.getMateriaList().remove(materia);
                idNotOld = em.merge(idNotOld);
            }
            if (idNotNew != null && !idNotNew.equals(idNotOld)) {
                idNotNew.getMateriaList().add(materia);
                idNotNew = em.merge(idNotNew);
            }
            for (Horario horarioListNewHorario : horarioListNew) {
                if (!horarioListOld.contains(horarioListNewHorario)) {
                    Materia oldIdMatOfHorarioListNewHorario = horarioListNewHorario.getIdMat();
                    horarioListNewHorario.setIdMat(materia);
                    horarioListNewHorario = em.merge(horarioListNewHorario);
                    if (oldIdMatOfHorarioListNewHorario != null && !oldIdMatOfHorarioListNewHorario.equals(materia)) {
                        oldIdMatOfHorarioListNewHorario.getHorarioList().remove(horarioListNewHorario);
                        oldIdMatOfHorarioListNewHorario = em.merge(oldIdMatOfHorarioListNewHorario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = materia.getIdMat();
                if (findMateria(id) == null) {
                    throw new NonexistentEntityException("The materia with id " + id + " no longer exists.");
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
            Materia materia;
            try {
                materia = em.getReference(Materia.class, id);
                materia.getIdMat();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The materia with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Horario> horarioListOrphanCheck = materia.getHorarioList();
            for (Horario horarioListOrphanCheckHorario : horarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Materia (" + materia + ") cannot be destroyed since the Horario " + horarioListOrphanCheckHorario + " in its horarioList field has a non-nullable idMat field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Notas idNot = materia.getIdNot();
            if (idNot != null) {
                idNot.getMateriaList().remove(materia);
                idNot = em.merge(idNot);
            }
            em.remove(materia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Materia> findMateriaEntities() {
        return findMateriaEntities(true, -1, -1);
    }

    public List<Materia> findMateriaEntities(int maxResults, int firstResult) {
        return findMateriaEntities(false, maxResults, firstResult);
    }

    private List<Materia> findMateriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Materia.class));
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

    public Materia findMateria(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Materia.class, id);
        } finally {
            em.close();
        }
    }

    public int getMateriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Materia> rt = cq.from(Materia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
