package si.medius.studentskanaloga.rest;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import si.medius.studentskanaloga.beans.PlayerBean;
import si.medius.studentskanaloga.dto.PlayerDTO;
import si.medius.studentskanaloga.entities.PlayerEntity;
import si.medius.studentskanaloga.mappers.PlayerMapper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/players")
@Produces("application/json")
@Consumes("application/json")
@Tag(name = "player", description = "Operations about player")
public class PlayerResource {

    @Inject
    public PlayerBean playerBean;

    @Inject
    PlayerMapper playerMapper;

    @GET
    @APIResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            type = SchemaType.ARRAY,
                            implementation = PlayerDTO.class)))
    @Operation(
            summary = "List all players",
            description = "Returns ID, username and age of all players")
    public Response getAllPlayers() {
        List<PlayerDTO> playerDTOList = playerMapper.toDTOList(playerBean.getAllPlayers());
        return Response.ok(playerDTOList).build();
    }

    @GET
    @APIResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            type = SchemaType.OBJECT,
                            implementation = PlayerDTO.class)))
    @Operation(
            summary = "Find player by ID",
            description = "Returns ID, username and age of a particular player")
    @Path("/{id}")
    public Response getPlayerById(
            @Parameter(
            description = "ID of Player to return",
            required = true,
            example = "1",
            schema = @Schema(type = SchemaType.INTEGER))
            @PathParam("id") int id) {
        PlayerDTO player = playerMapper.toDTO(playerBean.getPlayerById(id));
        if (player == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(String.format("Player with ID %s does not exist.", id)).build();
        }
        return Response.ok(player).build();
    }

    @POST
    @APIResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            type = SchemaType.OBJECT,
                            implementation = PlayerDTO.class)))
    @Operation(
            summary = "Create new player",
            description = "Adds player object to database")
    public Response createPlayer(PlayerDTO p) {
        PlayerEntity playerEntity = playerBean.addNewPlayer(playerMapper.toEntitiy(p));
        return Response.ok(playerMapper.toDTO(playerEntity)).build();
    }

}
