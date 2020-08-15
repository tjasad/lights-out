package si.medius.studentskanaloga.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import si.medius.studentskanaloga.entities.SolutionStepEntity;

import java.io.Serializable;

/**
 *SolutionStepDTO is the data transfer object for {@link SolutionStepEntity}.
 *
 * @since 1.0.0
 * @author Tjasa Domadenik
 */
@Schema(name="Solution step", description="POJO that represents a SolutionStep.")
public class SolutionStepDTO implements Serializable {

    private static final long serialVersionUID = -3643916406524916351L;

    @Schema(required = true, example = "1")
    private int step;
    @Schema(required = true, example = "1")
    private int stepNumber;

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }
}
