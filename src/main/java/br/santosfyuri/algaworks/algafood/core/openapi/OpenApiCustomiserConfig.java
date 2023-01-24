package br.santosfyuri.algaworks.algafood.core.openapi;

import br.santosfyuri.algaworks.algafood.api.exceptionhandler.Problem;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.filter.SpecFilter;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class OpenApiCustomiserConfig extends SpecFilter implements OpenApiCustomiser {

    private static final String BAD_REQUEST_MESSAGE = "Invalid request";
    private static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal server error";
    private static final String NOT_ACCEPTABLE_MESSAGE = "Resource has no representation that could be accepted by the consumer";
    private static final String UNSUPPORTED_MEDIA_TYPE_MESSAGE = "Request refused because the body is in an unsupported format";

    @Override
    public void customise(OpenAPI openApi) {
        openApi.getPaths()
                .values()
                .forEach(pathItem -> {
                    if (Objects.nonNull(pathItem.getPost())) {
                        var postResponses = pathItem.getPost().getResponses();
                        buildPostApiResponses(postResponses);
                    }
                    if (Objects.nonNull(pathItem.getPut())) {
                        var putResponses = pathItem.getPut().getResponses();
                        buildPostApiResponses(putResponses);
                    }
                    if (Objects.nonNull(pathItem.getDelete())) {
                        var deleteResponses = pathItem.getDelete().getResponses();
                        buildDeleteApiResponses(deleteResponses);
                    }
                    if (Objects.nonNull(pathItem.getGet())) {
                        var getResponses = pathItem.getGet().getResponses();
                        buildGetApiResponses(getResponses);
                    }
                });
    }

    private void buildPostApiResponses(ApiResponses apiResponses) {
        apiResponses.addApiResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()),
                        new ApiResponse().description(BAD_REQUEST_MESSAGE)
                                .content(new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE, setProblemMediaType())))
                .addApiResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                        new ApiResponse().description(INTERNAL_SERVER_ERROR_MESSAGE)
                                .content(new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE, setProblemMediaType())))
                .addApiResponse(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()),
                        new ApiResponse().description(NOT_ACCEPTABLE_MESSAGE))
                .addApiResponse(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()),
                        new ApiResponse().description(UNSUPPORTED_MEDIA_TYPE_MESSAGE)
                                .content(new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE, setProblemMediaType())));
    }

    private void buildDeleteApiResponses(ApiResponses apiResponses) {
        apiResponses.addApiResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()),
                        new ApiResponse().description(BAD_REQUEST_MESSAGE)
                                .content(new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE, setProblemMediaType())))
                .addApiResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                        new ApiResponse().description(INTERNAL_SERVER_ERROR_MESSAGE)
                                .content(new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE, setProblemMediaType())));
    }

    private void buildGetApiResponses(ApiResponses apiResponses) {
        apiResponses.addApiResponse(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()),
                        new ApiResponse().description(NOT_ACCEPTABLE_MESSAGE))
                .addApiResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                        new ApiResponse().description(INTERNAL_SERVER_ERROR_MESSAGE)
                                .content(new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE, setProblemMediaType())));
    }

    private static io.swagger.v3.oas.models.media.MediaType setProblemMediaType() {
        return new io.swagger.v3.oas.models.media.MediaType()
                .schema(resolveProblemSchema());
    }

    private static Schema<?> resolveProblemSchema() {
        final var constraintsSchema = ModelConverters.getInstance()
                .resolveAsResolvedSchema(new AnnotatedType(Problem.Constraints.class));

        return ModelConverters.getInstance()
                .resolveAsResolvedSchema(new AnnotatedType(Problem.class))
                .schema.addProperty("constraints", constraintsSchema.schema);
    }
}
