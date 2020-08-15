package si.medius.studentskanaloga.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import si.medius.studentskanaloga.entities.SolutionEntity;

import java.io.Serializable;
import java.util.List;

/**
 *SolutionDTO is the data transfer object for {@link SolutionEntity}.
 *
 * @since 1.0.0
 * @author Tjasa Domadenik
 */
@Schema(name="Solution", description="POJO that represents a Solution.")
public class SolutionDTO implements Serializable {

    private static final long serialVersionUID = -311455783277874418L;
    @Schema(required = true, example = "1")
    private int playerId;
    @Schema(required = true, example = "1")
    private int problemId;

    private List<SolutionStepDTO> steps;

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getProblemId() {
        return problemId;
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

    public List<SolutionStepDTO> getSteps() {
        return steps;
    }

    public void setSteps(List<SolutionStepDTO> steps) {
        this.steps = steps;
    }
}
