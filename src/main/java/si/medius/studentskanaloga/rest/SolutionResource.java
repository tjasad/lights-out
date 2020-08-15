package si.medius.studentskanaloga.rest;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import si.medius.studentskanaloga.beans.SolutionBean;
import si.medius.studentskanaloga.dto.SolutionDTO;
import si.medius.studentskanaloga.entities.SolutionEntity;
import si.medius.studentskanaloga.mappers.SolutionMapper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/solutions")
@Produces("application/json")
@Consumes("application/json")
@Tag(name = "solution", description = "Operations about solutions")
public class SolutionResource {

    @Inject
    SolutionBean solutionBean;

    @Inject
    SolutionMapper solutionMapper;

    @GET
    @APIResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            type = SchemaType.ARRAY,
                            implementation = SolutionDTO.class)))
    @Operation(
            summary = "List all solutions",
            description = "Returns player ID, problem ID and a list of steps of all solutions")
    public Response getAllSolutions() {
        List<SolutionDTO> solutionDTOList = solutionMapper.toDTOList(solutionBean.getAllSolutions());
        return Response.ok(solutionDTOList).build();
    }

    @GET
    @APIResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            type = SchemaType.ARRAY,
                            implementation = SolutionDTO.class)))
    @Operation(
            summary = "Find solution by username",
            description = "Returns all solution from user with username")
    @Path("/solver/{username}")
    public Response getSolutionsByUsername(
            @Parameter(
                    description = "username of Player",
                    required = true,
                    example = "Mica",
                    schema = @Schema(type = SchemaType.STRING))
            @PathParam("username") String username) {
        List<SolutionDTO> solutionDTOList = solutionMapper.toDTOList(solutionBean.getSolutionsByUsername(username));
        return Response.ok(solutionDTOList).build();
    }

    @GET
    @APIResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            type = SchemaType.ARRAY,
                            implementation = SolutionDTO.class)))
    @Operation(
            summary = "Find solutions by problem ID",
            description = "Returns all solutions of a particular problem")
    @Path("/problem/{id}")
    public Response getSolutionsByProblem(
            @Parameter(
                    description = "ID of Problem",
                    required = true,
                    example = "1",
                    schema = @Schema(type = SchemaType.INTEGER))
            @PathParam("id") int id) {
        List<SolutionDTO> solutionDTOList = solutionMapper.toDTOList(solutionBean.getSolutionsByProblem(id));
        return Response.ok(solutionDTOList).build();
    }

    @POST
    @APIResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            type = SchemaType.OBJECT,
                            implementation = SolutionDTO.class)))
    @Operation(
            summary = "Create new solution",
            description = "Adds solution object to database")
    public Response createSolution(SolutionDTO s) {
        SolutionEntity solutionEntity = solutionBean.addNewSolution(solutionMapper.toEntity(s), s.getPlayerId(), s.getProblemId());
        return Response.ok(solutionMapper.toDTO(solutionEntity)).build();
    }

}
