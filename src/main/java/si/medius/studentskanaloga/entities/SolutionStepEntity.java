package si.medius.studentskanaloga.entities;

import javax.persistence.*;

@Entity
@Table(name = "solution_step", schema = "public", catalog = "postgres")
public class SolutionStepEntity {
    private int solutionStepId;
    private Integer step;
    private Integer stepNumber;
    private SolutionEntity solutionBySolutionId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "solution_step_id")
    public int getSolutionStepId() {
        return solutionStepId;
    }

    public void setSolutionStepId(int solutionStepId) {
        this.solutionStepId = solutionStepId;
    }

    @Basic
    @Column(name = "step")
    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    @Basic
    @Column(name = "step_number")
    public Integer getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(Integer stepNumber) {
        this.stepNumber = stepNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SolutionStepEntity that = (SolutionStepEntity) o;

        if (solutionStepId != that.solutionStepId) return false;
        if (step != null ? !step.equals(that.step) : that.step != null) return false;
        if (stepNumber != null ? !stepNumber.equals(that.stepNumber) : that.stepNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = solutionStepId;
        result = 31 * result + (step != null ? step.hashCode() : 0);
        result = 31 * result + (stepNumber != null ? stepNumber.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "solution_id", referencedColumnName = "id")
    public SolutionEntity getSolutionBySolutionId() {
        return solutionBySolutionId;
    }

    public void setSolutionBySolutionId(SolutionEntity solutionBySolutionId) {
        this.solutionBySolutionId = solutionBySolutionId;
    }
}
