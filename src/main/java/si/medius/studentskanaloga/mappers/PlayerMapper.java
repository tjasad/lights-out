package si.medius.studentskanaloga.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import si.medius.studentskanaloga.dto.PlayerDTO;
import si.medius.studentskanaloga.entities.PlayerEntity;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface PlayerMapper {


    PlayerDTO toDTO(PlayerEntity player);

    @InheritInverseConfiguration
    PlayerEntity toEntitiy(PlayerDTO player);

    List<PlayerDTO> toDTOList(List<PlayerEntity> players);

    List<PlayerEntity> toEntityList(List<PlayerDTO> player);
}
