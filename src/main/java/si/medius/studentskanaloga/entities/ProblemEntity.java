package si.medius.studentskanaloga.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NamedQueries({
        @NamedQuery(name = ProblemEntity.GET_ALL_PROBLEMS,
                query = "select p from ProblemEntity p"),
        @NamedQuery(name = ProblemEntity.GET_PROBLEMS_BY_USERNAME,
                query = "select p from ProblemEntity p where p.playerByPlayerId.username = :username")
})
@Table(name = "problem", schema = "public", catalog = "postgres")
public class ProblemEntity {

    public static final String GET_ALL_PROBLEMS = "ProblemEntity.getAllProblems";
    public static final String GET_PROBLEMS_BY_USERNAME = "ProblemEntity.getProblemsFromUsername";

    private int id;
    private String definition;
    private PlayerEntity playerByPlayerId;
    private Collection<SolutionEntity> solutionsById;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "definition")
    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProblemEntity that = (ProblemEntity) o;

        if (id != that.id) return false;
        if (definition != null ? !definition.equals(that.definition) : that.definition != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (definition != null ? definition.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    public PlayerEntity getPlayerByPlayerId() {
        return playerByPlayerId;
    }

    public void setPlayerByPlayerId(PlayerEntity playerByPlayerId) {
        this.playerByPlayerId = playerByPlayerId;
    }

    @OneToMany(mappedBy = "problemByProblemId")
    public Collection<SolutionEntity> getSolutionsById() {
        return solutionsById;
    }

    public void setSolutionsById(Collection<SolutionEntity> solutionsById) {
        this.solutionsById = solutionsById;
    }
}
