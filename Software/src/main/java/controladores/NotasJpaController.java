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
import entidades.Notas;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author pc
 */
public class NotasJpaController implements Serializable {

    public NotasJpaController() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_JPA_bases_jar_1.0-SNAPSHOTPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Notas notas) throws PreexistingEntityException, Exception {
        if (notas.getMateriaList() == null) {
            notas.setMateriaList(new ArrayList<Materia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Materia> attachedMateriaList = new ArrayList<Materia>();
            for (Materia materiaListMateriaToAttach : notas.getMateriaList()) {
                materiaListMateriaToAttach = em.getReference(materiaListMateriaToAttach.getClass(), materiaListMateriaToAttach.getIdMat());
                attachedMateriaList.add(materiaListMateriaToAttach);
            }
            notas.setMateriaList(attachedMateriaList);
            em.persist(notas);
            for (Materia materiaListMateria : notas.getMateriaList()) {
                Notas oldIdNotOfMateriaListMateria = materiaListMateria.getIdNot();
                materiaListMateria.setIdNot(notas);
                materiaListMateria = em.merge(materiaListMateria);
                if (oldIdNotOfMateriaListMateria != null) {
                    oldIdNotOfMateriaListMateria.getMateriaList().remove(materiaListMateria);
                    oldIdNotOfMateriaListMateria = em.merge(oldIdNotOfMateriaListMateria);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findNotas(notas.getIdNot()) != null) {
                throw new PreexistingEntityException("Notas " + notas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Notas notas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Notas persistentNotas = em.find(Notas.class, notas.getIdNot());
            List<Materia> materiaListOld = persistentNotas.getMateriaList();
            List<Materia> materiaListNew = notas.getMateriaList();
            List<String> illegalOrphanMessages = null;
            for (Materia materiaListOldMateria : materiaListOld) {
                if (!materiaListNew.contains(materiaListOldMateria)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Materia " + materiaListOldMateria + " since its idNot field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Materia> attachedMateriaListNew = new ArrayList<Materia>();
            for (Materia materiaListNewMateriaToAttach : materiaListNew) {
                materiaListNewMateriaToAttach = em.getReference(materiaListNewMateriaToAttach.getClass(), materiaListNewMateriaToAttach.getIdMat());
                attachedMateriaListNew.add(materiaListNewMateriaToAttach);
            }
            materiaListNew = attachedMateriaListNew;
            notas.setMateriaList(materiaListNew);
            notas = em.merge(notas);
            for (Materia materiaListNewMateria : materiaListNew) {
                if (!materiaListOld.contains(materiaListNewMateria)) {
                    Notas oldIdNotOfMateriaListNewMateria = materiaListNewMateria.getIdNot();
                    materiaListNewMateria.setIdNot(notas);
                    materiaListNewMateria = em.merge(materiaListNewMateria);
                    if (oldIdNotOfMateriaListNewMateria != null && !oldIdNotOfMateriaListNewMateria.equals(notas)) {
                        oldIdNotOfMateriaListNewMateria.getMateriaList().remove(materiaListNewMateria);
                        oldIdNotOfMateriaListNewMateria = em.merge(oldIdNotOfMateriaListNewMateria);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = notas.getIdNot();
                if (findNotas(id) == null) {
                    throw new NonexistentEntityException("The notas with id " + id + " no longer exists.");
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
            Notas notas;
            try {
                notas = em.getReference(Notas.class, id);
                notas.getIdNot();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The notas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Materia> materiaListOrphanCheck = notas.getMateriaList();
            for (Materia materiaListOrphanCheckMateria : materiaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Notas (" + notas + ") cannot be destroyed since the Materia " + materiaListOrphanCheckMateria + " in its materiaList field has a non-nullable idNot field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(notas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Notas> findNotasEntities() {
        return findNotasEntities(true, -1, -1);
    }

    public List<Notas> findNotasEntities(int maxResults, int firstResult) {
        return findNotasEntities(false, maxResults, firstResult);
    }

    private List<Notas> findNotasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Notas.class));
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

    public Notas findNotas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Notas.class, id);
        } finally {
            em.close();
        }
    }

    public int getNotasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Notas> rt = cq.from(Notas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
