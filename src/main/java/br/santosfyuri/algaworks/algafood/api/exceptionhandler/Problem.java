package br.santosfyuri.algaworks.algafood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@Schema(name = "Problem")
@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {

    @Schema(example = "400")
    private Integer status;

    @Schema(example = "http://localhost:8080/invalid-data")
    private String type;

    @Schema(example = "Invalid data")
    private String title;

    @Schema(example = "One or more fields are wrong. Please fill in correctly and try again.")
    private String detail;

    @Schema(example = "One or more fields are wrong. Please fill in correctly and try again.")
    private String userMessage;

    @Schema(example = "2022-07-03T22:46:04.70844Z")
    private OffsetDateTime timestamp;

    @Schema(description = "Objects or fields that generated the error.")
    private List<Constraints> constraints;

    @Schema(name = "Problem")
    @Getter
    @Builder
    public static class Constraints {

        @Schema(example = "Price")
        private String name;

        @Schema(example = "Price is required")
        private String userMessage;
    }
}
