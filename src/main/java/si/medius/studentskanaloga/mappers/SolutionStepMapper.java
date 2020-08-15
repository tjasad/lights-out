package si.medius.studentskanaloga.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import si.medius.studentskanaloga.dto.SolutionStepDTO;
import si.medius.studentskanaloga.entities.SolutionStepEntity;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface SolutionStepMapper {

    SolutionStepDTO toDTO(SolutionStepEntity solutionStep);

    @InheritInverseConfiguration
    SolutionStepEntity toEntity(SolutionStepDTO solutionStep);

    List<SolutionStepDTO> toDTOList(List<SolutionStepEntity> solutionStep);

    List<SolutionStepEntity> toEntityList(List<SolutionStepDTO> solutionStep);
}
