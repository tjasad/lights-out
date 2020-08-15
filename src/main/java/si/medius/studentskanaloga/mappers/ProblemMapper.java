package si.medius.studentskanaloga.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import si.medius.studentskanaloga.dto.ProblemDTO;
import si.medius.studentskanaloga.entities.ProblemEntity;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface ProblemMapper {

    @Mapping(target = "playerId", source = "playerByPlayerId.id")
    ProblemDTO toDTO(ProblemEntity problem);

    @InheritInverseConfiguration
    ProblemEntity toEntity(ProblemDTO problem);

    List<ProblemDTO> toDTOList(List<ProblemEntity> problem);

    List<ProblemEntity> toEntityList(List<ProblemDTO> problem);

}

