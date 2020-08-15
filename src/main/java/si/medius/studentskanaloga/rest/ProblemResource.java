package si.medius.studentskanaloga.rest;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import si.medius.studentskanaloga.beans.ProblemBean;
import si.medius.studentskanaloga.dto.ProblemDTO;
import si.medius.studentskanaloga.entities.ProblemEntity;
import si.medius.studentskanaloga.exceptions.NotSolvableException;
import si.medius.studentskanaloga.mappers.ProblemMapper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/problems")
@Produces("application/json")
@Consumes("application/json")
@Tag(name = "problem", description = "Operations about problem")
public class ProblemResource {

    @Inject
    public ProblemBean problemBean;

    @Inject
    ProblemMapper problemMapper;

    @GET
    @APIResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            type = SchemaType.ARRAY,
                            implementation = ProblemDTO.class)))
    @Operation(
            summary = "List all Problems",
            description = "Returns ID, definition and player ID of all problems")
    public Response getProblems() {
        List<ProblemDTO> problemDTOList = problemMapper.toDTOList(problemBean.getAllProblems());
        return Response.ok(problemDTOList).build();
    }

    @GET
    @APIResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            type = SchemaType.ARRAY,
                            implementation = ProblemDTO.class)))
    @Operation(
            summary = "Find problem by username",
            description = "Returns all problems from user with username.")
    @Path("/creator/{username}")
    public Response getProblemsFromUsername(
            @Parameter(
                    description = "username of Player",
                    required = true,
                    example = "mica",
                    schema = @Schema(type = SchemaType.STRING))
            @PathParam("username") String username) {
        List<ProblemDTO> problemDTOList = problemMapper.toDTOList(problemBean.getProblemsByUsername(username));
        return Response.ok(problemDTOList).build();
    }

    @GET
    @APIResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            type = SchemaType.OBJECT,
                            implementation = ProblemDTO.class)))
    @Operation(
            summary = "Find problem by ID",
            description = "Returns ID, definition and player ID of a particular problem")
    @Path("/{id}")
    public Response getProblem(
            @Parameter(
                    description = "ID of Problem to return",
                    required = true,
                    example = "1",
                    schema = @Schema(type = SchemaType.INTEGER))
            @PathParam("id") int id) {
        ProblemDTO problemDTO = problemMapper.toDTO(problemBean.getProblemById(id));
        return Response.ok(problemDTO).build();
    }

    @POST
    @APIResponses(
            value = {
                @APIResponse(
                        responseCode = "400",
                        description = "Problem doesn't have a solution",
                        content = @Content(mediaType = "text/plain")),
                @APIResponse(
                responseCode = "200",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(
                                type = SchemaType.OBJECT,
                                implementation = ProblemDTO.class))) })
    @Operation(
            summary = "Create new problem",
            description = "Adds problem object to database")
    public Response createProblem(ProblemDTO p) {
        try {
            ProblemEntity problemEntity = problemBean.addNewProblem(problemMapper.toEntity(p), p.getPlayerId());
            return Response.ok(problemMapper.toDTO(problemEntity)).build();
        } catch (NotSolvableException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

}
