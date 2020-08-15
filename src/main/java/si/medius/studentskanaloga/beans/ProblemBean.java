package si.medius.studentskanaloga.beans;

import si.medius.studentskanaloga.entities.PlayerEntity;
import si.medius.studentskanaloga.entities.ProblemEntity;
import si.medius.studentskanaloga.exceptions.NotSolvableException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;


/**
 * ProblemBean is used to handle bussiness logic regarding {@link ProblemEntity}
 *
 * @author Tjasa Domadenik
 * @since 1.0.0
* */
@RequestScoped
public class ProblemBean implements Serializable {

    private static final long serialVersionUID = -4816030252257442042L;

    @PersistenceContext
    private EntityManager em;

    @Inject
    SolverBean solverBean;

    /**
     * Gets all problems form database.
     *
     * @return list of Problem entities
     */
    public List<ProblemEntity> getAllProblems() {
        return em.createNamedQuery(ProblemEntity.GET_ALL_PROBLEMS, ProblemEntity.class)
                .getResultList();
    }

    /**
     * Gets problems by players username from database.
     *
     * @param username username of Players object
     * @return list of Problem entities
     */
    public List<ProblemEntity> getProblemsByUsername(String username) {
        return em.createNamedQuery(ProblemEntity.GET_PROBLEMS_BY_USERNAME, ProblemEntity.class)
                .setParameter("username", username)
                .getResultList();
    }

    /**
     * Gets problem by id from database.
     *
     * @param id id of Problem object
     * @return list of Problem entities
     */
    public ProblemEntity getProblemById(int id) {
        return em.find(ProblemEntity.class, id);
    }

    /**
     * Adds a new Problem to database.
     *
     * @param problem entity of Problem object
     * @param id id of Problem object
     * @return persisted entity of Problem
     */
    @Transactional
    public ProblemEntity addNewProblem(ProblemEntity problem, int id) throws NotSolvableException {

        //check if problem is solvable
        if (!solverBean.isSolvable(problem.getDefinition())) {
            throw new NotSolvableException("Problem doesn't have a solution");
        }

        PlayerEntity player = em.find(PlayerEntity.class, id);
        problem.setPlayerByPlayerId(player);
        em.persist(problem);
        return problem;

    }

}
