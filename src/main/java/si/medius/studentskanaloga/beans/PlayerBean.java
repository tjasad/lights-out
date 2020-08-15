package si.medius.studentskanaloga.beans;

import si.medius.studentskanaloga.entities.PlayerEntity;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * PlayerBean is used to handle business logic regarding the {@link PlayerEntity}
 *
 * @author Tjasa Domadenik
 * @since 1.0.0
 */
@RequestScoped
public class PlayerBean implements Serializable {

    private static final long serialVersionUID = 4374009727566665152L;

    @PersistenceContext
    private EntityManager em;

    /**
     * Gets all players from database.
     *
     * @return list of Player entities
     */
    public List<PlayerEntity> getAllPlayers() {
        return em.createNamedQuery(PlayerEntity.GET_ALL_PLAYERS, PlayerEntity.class)
                .getResultList();
    }

    /**
     * Gets player by id from database.
     *
     * @param id id of Player object
     * @return entity of a Player
     */
    public PlayerEntity getPlayerById(int id) {
        return em.find(PlayerEntity.class, id);
    }

    /**
     * Adds a new player to the database.
     *
     * @param playerEntity entity of Player object
     * @return persisted entity of a Player
     */
    @Transactional
    public PlayerEntity addNewPlayer(PlayerEntity playerEntity) {
        em.persist(playerEntity);
        return playerEntity;
    }

}
