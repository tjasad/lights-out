package si.medius.studentskanaloga.mappers;

import org.mapstruct.*;
import si.medius.studentskanaloga.dto.SolutionDTO;
import si.medius.studentskanaloga.entities.SolutionEntity;

import java.util.List;

@Mapper(componentModel = "cdi", uses = SolutionStepMapper.class)
public interface SolutionMapper {


    @Mapping(target = "playerId", source = "playerByPlayerId.id")
    @Mapping(target = "problemId", source = "problemByProblemId.id")
    @Mapping(target = "steps", source = "solutionStepsById")
    SolutionDTO toDTO(SolutionEntity solution);

    @InheritInverseConfiguration
    SolutionEntity toEntity(SolutionDTO solution);

    List<SolutionDTO> toDTOList(List<SolutionEntity> solution);

    List<SolutionEntity> toEntityList(List<SolutionDTO> solution);
}
