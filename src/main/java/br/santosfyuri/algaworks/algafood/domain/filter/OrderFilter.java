package br.santosfyuri.algaworks.algafood.domain.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

import static org.springframework.format.annotation.DateTimeFormat.ISO;

@Setter
@Getter
public class OrderFilter {

    private Long clientId;
    private Long restaurantId;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private OffsetDateTime createdAtBegin;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private OffsetDateTime createdAtEnd;
}
