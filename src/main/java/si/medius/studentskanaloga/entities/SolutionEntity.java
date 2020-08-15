package si.medius.studentskanaloga.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = SolutionEntity.GET_ALL_SOLUTIONS,
                query = "select s from SolutionEntity s"),
        @NamedQuery(name = SolutionEntity.GET_SOLUTIONS_BY_USERNAME,
                query = "select s from SolutionEntity  s where s.playerByPlayerId.username = :username"),
        @NamedQuery(name = SolutionEntity.GET_SOLUTIONS_BY_PROBLEM,
                query = "select s from SolutionEntity s where s.problemByProblemId.id = :id")
})
@Table(name = "solution", schema = "public", catalog = "postgres")
public class SolutionEntity {

    public static final String GET_ALL_SOLUTIONS = "SolutionEntity.getAllSolutions";
    public static final String GET_SOLUTIONS_BY_USERNAME = "SolutionEntity.getSolutionsByUsername";
    public static final String GET_SOLUTIONS_BY_PROBLEM = "SolutionEntity.getSolutionsByProblem";


    private int id;
    private PlayerEntity playerByPlayerId;
    private ProblemEntity problemByProblemId;
    private List<SolutionStepEntity> solutionStepsById;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SolutionEntity that = (SolutionEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    public PlayerEntity getPlayerByPlayerId() {
        return playerByPlayerId;
    }

    public void setPlayerByPlayerId(PlayerEntity playerByPlayerId) {
        this.playerByPlayerId = playerByPlayerId;
    }

    @ManyToOne
    @JoinColumn(name = "problem_id", referencedColumnName = "id")
    public ProblemEntity getProblemByProblemId() {
        return problemByProblemId;
    }

    public void setProblemByProblemId(ProblemEntity problemByProblemId) {
        this.problemByProblemId = problemByProblemId;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "solution_id")
    public List<SolutionStepEntity> getSolutionStepsById() {
        return solutionStepsById;
    }

    public void setSolutionStepsById(List<SolutionStepEntity> solutionStepsById) {
        this.solutionStepsById = solutionStepsById;
    }
}
