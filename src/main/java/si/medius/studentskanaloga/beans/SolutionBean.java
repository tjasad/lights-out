package si.medius.studentskanaloga.beans;

import si.medius.studentskanaloga.entities.PlayerEntity;
import si.medius.studentskanaloga.entities.ProblemEntity;
import si.medius.studentskanaloga.entities.SolutionEntity;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 *SolutionBean is used to handle business logic regarding the {@link SolutionEntity}
 *
 * @author Tjasa Domadenik
 * @since 1.0.0
 **/
@RequestScoped
public class SolutionBean implements Serializable {

    private static final long serialVersionUID = 1212745608884195778L;

    @PersistenceContext
    private EntityManager em;

    /**
     * Gets all Solutions form database.
     *
     * @return list of Solution entities
     */
    public List<SolutionEntity> getAllSolutions() {
        return em.createNamedQuery(SolutionEntity.GET_ALL_SOLUTIONS, SolutionEntity.class).getResultList();
    }

    /**
     * Gets solutions by players username from database.
     *
     * @param username username of Players object
     * @return list of Solution entities
     */
    public List<SolutionEntity> getSolutionsByUsername(String username) {
        return em.createNamedQuery(SolutionEntity.GET_SOLUTIONS_BY_USERNAME, SolutionEntity.class).setParameter("username", username).getResultList();
    }

    /**
     * Gets solutions by problem id from database.
     *
     * @param id id of Problem object
     * @return list of Solution entities
     */
    public List<SolutionEntity> getSolutionsByProblem(int id) {
        return em.createNamedQuery(SolutionEntity.GET_SOLUTIONS_BY_PROBLEM, SolutionEntity.class).setParameter("id", id).getResultList();
    }

    /**
     * Adds a new Solution to database.
     *
     * @param solution entity of Solution object
     * @param playerId id of Player object
     * @param problemId id of Problem object
     * @return persisted entity of Solution
     */
    @Transactional
    public SolutionEntity addNewSolution(SolutionEntity solution, int playerId, int problemId) {

        PlayerEntity player = em.find(PlayerEntity.class, playerId);
        ProblemEntity problem = em.find(ProblemEntity.class, problemId);

        solution.setPlayerByPlayerId(player);
        solution.setProblemByProblemId(problem);
        em.persist(solution);
        return solution;
    }

}
