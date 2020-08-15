package si.medius.studentskanaloga.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import si.medius.studentskanaloga.entities.PlayerEntity;

import java.io.Serializable;
import java.util.Objects;

/**
 *PlayerDTO is the data transfer object for {@link PlayerEntity}.
 *
 * @since 1.0.0
 * @author Tjasa Domadenik
 */
@Schema(name="Player", description="POJO that represents a Player.")
public class PlayerDTO implements Serializable {

    private static final long serialVersionUID = 563620903932764907L;
    @Schema(hidden = true)
    private int id;
    @Schema(required = true, example = "mica")
    private String username;
    @Schema(required = true, example = "20")
    private Integer age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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
        PlayerDTO playerDTO = (PlayerDTO) o;
        return id == playerDTO.id &&
                Objects.equals(username, playerDTO.username) &&
                Objects.equals(age, playerDTO.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, age);
    }

    @Override
    public String toString() {
        return "PlayerDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
