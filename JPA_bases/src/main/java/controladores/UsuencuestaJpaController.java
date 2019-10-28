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
import entidades.Usuarios;
import entidades.Encuestas;
import entidades.Historia;
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
public class UsuencuestaJpaController implements Serializable {

    public UsuencuestaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_JPA_bases_jar_1.0-SNAPSHOTPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuencuesta usuencuesta) throws PreexistingEntityException, Exception {
        if (usuencuesta.getHistoriaList() == null) {
            usuencuesta.setHistoriaList(new ArrayList<Historia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios idUsu = usuencuesta.getIdUsu();
            if (idUsu != null) {
                idUsu = em.getReference(idUsu.getClass(), idUsu.getIdUsu());
                usuencuesta.setIdUsu(idUsu);
            }
            Encuestas idEnc = usuencuesta.getIdEnc();
            if (idEnc != null) {
                idEnc = em.getReference(idEnc.getClass(), idEnc.getIdEnc());
                usuencuesta.setIdEnc(idEnc);
            }
            List<Historia> attachedHistoriaList = new ArrayList<Historia>();
            for (Historia historiaListHistoriaToAttach : usuencuesta.getHistoriaList()) {
                historiaListHistoriaToAttach = em.getReference(historiaListHistoriaToAttach.getClass(), historiaListHistoriaToAttach.getIdHistoria());
                attachedHistoriaList.add(historiaListHistoriaToAttach);
            }
            usuencuesta.setHistoriaList(attachedHistoriaList);
            em.persist(usuencuesta);
            if (idUsu != null) {
                idUsu.getUsuencuestaList().add(usuencuesta);
                idUsu = em.merge(idUsu);
            }
            if (idEnc != null) {
                idEnc.getUsuencuestaList().add(usuencuesta);
                idEnc = em.merge(idEnc);
            }
            for (Historia historiaListHistoria : usuencuesta.getHistoriaList()) {
                Usuencuesta oldIdUsuEncOfHistoriaListHistoria = historiaListHistoria.getIdUsuEnc();
                historiaListHistoria.setIdUsuEnc(usuencuesta);
                historiaListHistoria = em.merge(historiaListHistoria);
                if (oldIdUsuEncOfHistoriaListHistoria != null) {
                    oldIdUsuEncOfHistoriaListHistoria.getHistoriaList().remove(historiaListHistoria);
                    oldIdUsuEncOfHistoriaListHistoria = em.merge(oldIdUsuEncOfHistoriaListHistoria);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuencuesta(usuencuesta.getIdUsuEnc()) != null) {
                throw new PreexistingEntityException("Usuencuesta " + usuencuesta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuencuesta usuencuesta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuencuesta persistentUsuencuesta = em.find(Usuencuesta.class, usuencuesta.getIdUsuEnc());
            Usuarios idUsuOld = persistentUsuencuesta.getIdUsu();
            Usuarios idUsuNew = usuencuesta.getIdUsu();
            Encuestas idEncOld = persistentUsuencuesta.getIdEnc();
            Encuestas idEncNew = usuencuesta.getIdEnc();
            List<Historia> historiaListOld = persistentUsuencuesta.getHistoriaList();
            List<Historia> historiaListNew = usuencuesta.getHistoriaList();
            List<String> illegalOrphanMessages = null;
            for (Historia historiaListOldHistoria : historiaListOld) {
                if (!historiaListNew.contains(historiaListOldHistoria)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Historia " + historiaListOldHistoria + " since its idUsuEnc field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idUsuNew != null) {
                idUsuNew = em.getReference(idUsuNew.getClass(), idUsuNew.getIdUsu());
                usuencuesta.setIdUsu(idUsuNew);
            }
            if (idEncNew != null) {
                idEncNew = em.getReference(idEncNew.getClass(), idEncNew.getIdEnc());
                usuencuesta.setIdEnc(idEncNew);
            }
            List<Historia> attachedHistoriaListNew = new ArrayList<Historia>();
            for (Historia historiaListNewHistoriaToAttach : historiaListNew) {
                historiaListNewHistoriaToAttach = em.getReference(historiaListNewHistoriaToAttach.getClass(), historiaListNewHistoriaToAttach.getIdHistoria());
                attachedHistoriaListNew.add(historiaListNewHistoriaToAttach);
            }
            historiaListNew = attachedHistoriaListNew;
            usuencuesta.setHistoriaList(historiaListNew);
            usuencuesta = em.merge(usuencuesta);
            if (idUsuOld != null && !idUsuOld.equals(idUsuNew)) {
                idUsuOld.getUsuencuestaList().remove(usuencuesta);
                idUsuOld = em.merge(idUsuOld);
            }
            if (idUsuNew != null && !idUsuNew.equals(idUsuOld)) {
                idUsuNew.getUsuencuestaList().add(usuencuesta);
                idUsuNew = em.merge(idUsuNew);
            }
            if (idEncOld != null && !idEncOld.equals(idEncNew)) {
                idEncOld.getUsuencuestaList().remove(usuencuesta);
                idEncOld = em.merge(idEncOld);
            }
            if (idEncNew != null && !idEncNew.equals(idEncOld)) {
                idEncNew.getUsuencuestaList().add(usuencuesta);
                idEncNew = em.merge(idEncNew);
            }
            for (Historia historiaListNewHistoria : historiaListNew) {
                if (!historiaListOld.contains(historiaListNewHistoria)) {
                    Usuencuesta oldIdUsuEncOfHistoriaListNewHistoria = historiaListNewHistoria.getIdUsuEnc();
                    historiaListNewHistoria.setIdUsuEnc(usuencuesta);
                    historiaListNewHistoria = em.merge(historiaListNewHistoria);
                    if (oldIdUsuEncOfHistoriaListNewHistoria != null && !oldIdUsuEncOfHistoriaListNewHistoria.equals(usuencuesta)) {
                        oldIdUsuEncOfHistoriaListNewHistoria.getHistoriaList().remove(historiaListNewHistoria);
                        oldIdUsuEncOfHistoriaListNewHistoria = em.merge(oldIdUsuEncOfHistoriaListNewHistoria);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuencuesta.getIdUsuEnc();
                if (findUsuencuesta(id) == null) {
                    throw new NonexistentEntityException("The usuencuesta with id " + id + " no longer exists.");
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
            Usuencuesta usuencuesta;
            try {
                usuencuesta = em.getReference(Usuencuesta.class, id);
                usuencuesta.getIdUsuEnc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuencuesta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Historia> historiaListOrphanCheck = usuencuesta.getHistoriaList();
            for (Historia historiaListOrphanCheckHistoria : historiaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuencuesta (" + usuencuesta + ") cannot be destroyed since the Historia " + historiaListOrphanCheckHistoria + " in its historiaList field has a non-nullable idUsuEnc field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuarios idUsu = usuencuesta.getIdUsu();
            if (idUsu != null) {
                idUsu.getUsuencuestaList().remove(usuencuesta);
                idUsu = em.merge(idUsu);
            }
            Encuestas idEnc = usuencuesta.getIdEnc();
            if (idEnc != null) {
                idEnc.getUsuencuestaList().remove(usuencuesta);
                idEnc = em.merge(idEnc);
            }
            em.remove(usuencuesta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuencuesta> findUsuencuestaEntities() {
        return findUsuencuestaEntities(true, -1, -1);
    }

    public List<Usuencuesta> findUsuencuestaEntities(int maxResults, int firstResult) {
        return findUsuencuestaEntities(false, maxResults, firstResult);
    }

    private List<Usuencuesta> findUsuencuestaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuencuesta.class));
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

    public Usuencuesta findUsuencuesta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuencuesta.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuencuestaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuencuesta> rt = cq.from(Usuencuesta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
