package si.medius.studentskanaloga.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NamedQueries({
        @NamedQuery(name = PlayerEntity.GET_ALL_PLAYERS,
                query = "select p from PlayerEntity p")
})
@Table(name = "player", schema = "public", catalog = "postgres")
public class PlayerEntity {

    public static final String GET_ALL_PLAYERS = "PlayerEntity.getAllPlayers";

    private int id;
    private String username;
    private Integer age;
    private Collection<ProblemEntity> problemsById;
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
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerEntity that = (PlayerEntity) o;

        if (id != that.id) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "playerByPlayerId")
    public Collection<ProblemEntity> getProblemsById() {
        return problemsById;
    }

    public void setProblemsById(Collection<ProblemEntity> problemsById) {
        this.problemsById = problemsById;
    }

    @OneToMany(mappedBy = "playerByPlayerId")
    public Collection<SolutionEntity> getSolutionsById() {
        return solutionsById;
    }

    public void setSolutionsById(Collection<SolutionEntity> solutionsById) {
        this.solutionsById = solutionsById;
    }

}
