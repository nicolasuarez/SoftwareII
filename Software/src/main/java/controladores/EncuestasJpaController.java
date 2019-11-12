/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.IllegalOrphanException;
import controladores.exceptions.NonexistentEntityException;
import controladores.exceptions.PreexistingEntityException;
import entidades.Encuestas;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Preguntas;
import entidades.Respuestas;
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
public class EncuestasJpaController implements Serializable {

    public EncuestasJpaController() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_JPA_bases_jar_1.0-SNAPSHOTPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Encuestas encuestas) throws PreexistingEntityException, Exception {
        if (encuestas.getUsuencuestaList() == null) {
            encuestas.setUsuencuestaList(new ArrayList<Usuencuesta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Preguntas idPre = encuestas.getIdPre();
            if (idPre != null) {
                idPre = em.getReference(idPre.getClass(), idPre.getIdPre());
                encuestas.setIdPre(idPre);
            }
            Respuestas idRes = encuestas.getIdRes();
            if (idRes != null) {
                idRes = em.getReference(idRes.getClass(), idRes.getIdRes());
                encuestas.setIdRes(idRes);
            }
            List<Usuencuesta> attachedUsuencuestaList = new ArrayList<Usuencuesta>();
            for (Usuencuesta usuencuestaListUsuencuestaToAttach : encuestas.getUsuencuestaList()) {
                usuencuestaListUsuencuestaToAttach = em.getReference(usuencuestaListUsuencuestaToAttach.getClass(), usuencuestaListUsuencuestaToAttach.getIdUsuEnc());
                attachedUsuencuestaList.add(usuencuestaListUsuencuestaToAttach);
            }
            encuestas.setUsuencuestaList(attachedUsuencuestaList);
            em.persist(encuestas);
            if (idPre != null) {
                idPre.getEncuestasList().add(encuestas);
                idPre = em.merge(idPre);
            }
            if (idRes != null) {
                idRes.getEncuestasList().add(encuestas);
                idRes = em.merge(idRes);
            }
            for (Usuencuesta usuencuestaListUsuencuesta : encuestas.getUsuencuestaList()) {
                Encuestas oldIdEncOfUsuencuestaListUsuencuesta = usuencuestaListUsuencuesta.getIdEnc();
                usuencuestaListUsuencuesta.setIdEnc(encuestas);
                usuencuestaListUsuencuesta = em.merge(usuencuestaListUsuencuesta);
                if (oldIdEncOfUsuencuestaListUsuencuesta != null) {
                    oldIdEncOfUsuencuestaListUsuencuesta.getUsuencuestaList().remove(usuencuestaListUsuencuesta);
                    oldIdEncOfUsuencuestaListUsuencuesta = em.merge(oldIdEncOfUsuencuestaListUsuencuesta);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEncuestas(encuestas.getIdEnc()) != null) {
                throw new PreexistingEntityException("Encuestas " + encuestas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Encuestas encuestas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Encuestas persistentEncuestas = em.find(Encuestas.class, encuestas.getIdEnc());
            Preguntas idPreOld = persistentEncuestas.getIdPre();
            Preguntas idPreNew = encuestas.getIdPre();
            Respuestas idResOld = persistentEncuestas.getIdRes();
            Respuestas idResNew = encuestas.getIdRes();
            List<Usuencuesta> usuencuestaListOld = persistentEncuestas.getUsuencuestaList();
            List<Usuencuesta> usuencuestaListNew = encuestas.getUsuencuestaList();
            List<String> illegalOrphanMessages = null;
            for (Usuencuesta usuencuestaListOldUsuencuesta : usuencuestaListOld) {
                if (!usuencuestaListNew.contains(usuencuestaListOldUsuencuesta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usuencuesta " + usuencuestaListOldUsuencuesta + " since its idEnc field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idPreNew != null) {
                idPreNew = em.getReference(idPreNew.getClass(), idPreNew.getIdPre());
                encuestas.setIdPre(idPreNew);
            }
            if (idResNew != null) {
                idResNew = em.getReference(idResNew.getClass(), idResNew.getIdRes());
                encuestas.setIdRes(idResNew);
            }
            List<Usuencuesta> attachedUsuencuestaListNew = new ArrayList<Usuencuesta>();
            for (Usuencuesta usuencuestaListNewUsuencuestaToAttach : usuencuestaListNew) {
                usuencuestaListNewUsuencuestaToAttach = em.getReference(usuencuestaListNewUsuencuestaToAttach.getClass(), usuencuestaListNewUsuencuestaToAttach.getIdUsuEnc());
                attachedUsuencuestaListNew.add(usuencuestaListNewUsuencuestaToAttach);
            }
            usuencuestaListNew = attachedUsuencuestaListNew;
            encuestas.setUsuencuestaList(usuencuestaListNew);
            encuestas = em.merge(encuestas);
            if (idPreOld != null && !idPreOld.equals(idPreNew)) {
                idPreOld.getEncuestasList().remove(encuestas);
                idPreOld = em.merge(idPreOld);
            }
            if (idPreNew != null && !idPreNew.equals(idPreOld)) {
                idPreNew.getEncuestasList().add(encuestas);
                idPreNew = em.merge(idPreNew);
            }
            if (idResOld != null && !idResOld.equals(idResNew)) {
                idResOld.getEncuestasList().remove(encuestas);
                idResOld = em.merge(idResOld);
            }
            if (idResNew != null && !idResNew.equals(idResOld)) {
                idResNew.getEncuestasList().add(encuestas);
                idResNew = em.merge(idResNew);
            }
            for (Usuencuesta usuencuestaListNewUsuencuesta : usuencuestaListNew) {
                if (!usuencuestaListOld.contains(usuencuestaListNewUsuencuesta)) {
                    Encuestas oldIdEncOfUsuencuestaListNewUsuencuesta = usuencuestaListNewUsuencuesta.getIdEnc();
                    usuencuestaListNewUsuencuesta.setIdEnc(encuestas);
                    usuencuestaListNewUsuencuesta = em.merge(usuencuestaListNewUsuencuesta);
                    if (oldIdEncOfUsuencuestaListNewUsuencuesta != null && !oldIdEncOfUsuencuestaListNewUsuencuesta.equals(encuestas)) {
                        oldIdEncOfUsuencuestaListNewUsuencuesta.getUsuencuestaList().remove(usuencuestaListNewUsuencuesta);
                        oldIdEncOfUsuencuestaListNewUsuencuesta = em.merge(oldIdEncOfUsuencuestaListNewUsuencuesta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = encuestas.getIdEnc();
                if (findEncuestas(id) == null) {
                    throw new NonexistentEntityException("The encuestas with id " + id + " no longer exists.");
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
            Encuestas encuestas;
            try {
                encuestas = em.getReference(Encuestas.class, id);
                encuestas.getIdEnc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The encuestas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Usuencuesta> usuencuestaListOrphanCheck = encuestas.getUsuencuestaList();
            for (Usuencuesta usuencuestaListOrphanCheckUsuencuesta : usuencuestaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Encuestas (" + encuestas + ") cannot be destroyed since the Usuencuesta " + usuencuestaListOrphanCheckUsuencuesta + " in its usuencuestaList field has a non-nullable idEnc field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Preguntas idPre = encuestas.getIdPre();
            if (idPre != null) {
                idPre.getEncuestasList().remove(encuestas);
                idPre = em.merge(idPre);
            }
            Respuestas idRes = encuestas.getIdRes();
            if (idRes != null) {
                idRes.getEncuestasList().remove(encuestas);
                idRes = em.merge(idRes);
            }
            em.remove(encuestas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Encuestas> findEncuestasEntities() {
        return findEncuestasEntities(true, -1, -1);
    }

    public List<Encuestas> findEncuestasEntities(int maxResults, int firstResult) {
        return findEncuestasEntities(false, maxResults, firstResult);
    }

    private List<Encuestas> findEncuestasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Encuestas.class));
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

    public Encuestas findEncuestas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Encuestas.class, id);
        } finally {
            em.close();
        }
    }

    public int getEncuestasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Encuestas> rt = cq.from(Encuestas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
