package si.medius.studentskanaloga.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import si.medius.studentskanaloga.entities.ProblemEntity;

import java.io.Serializable;
import java.util.Objects;

/**
 *ProblemDTO is the data transfer object for {@link ProblemEntity}.
 *
 * @since 1.0.0
 * @author Tjasa Domadenik
 */
@Schema(name="Problem", description="POJO that represents a Problem.")
public class ProblemDTO implements Serializable {

    private static final long serialVersionUID = -2977779560583254702L;

    @Schema(hidden = true)
    private int id;
    @Schema(required = true, example = "001011111")
    private String definition;
    @Schema(required = true, example = "1")
    private int playerId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProblemDTO that = (ProblemDTO) o;
        return id == that.id &&
                playerId == that.playerId &&
                Objects.equals(definition, that.definition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, definition, playerId);
    }
}
