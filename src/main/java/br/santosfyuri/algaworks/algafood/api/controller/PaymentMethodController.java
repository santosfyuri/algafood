package br.santosfyuri.algaworks.algafood.api.controller;

import br.santosfyuri.algaworks.algafood.api.assembler.BasicAssembler;
import br.santosfyuri.algaworks.algafood.api.assembler.BasicDisassembler;
import br.santosfyuri.algaworks.algafood.api.openapi.controller.PaymentMethodControllerOpenApi;
import br.santosfyuri.algaworks.algafood.api.representation.request.PaymentMethodRequest;
import br.santosfyuri.algaworks.algafood.api.representation.response.PaymentMethodResponse;
import br.santosfyuri.algaworks.algafood.domain.model.PaymentMethod;
import br.santosfyuri.algaworks.algafood.domain.repository.PaymentMethodRepository;
import br.santosfyuri.algaworks.algafood.domain.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/payment-methods")
public class PaymentMethodController implements PaymentMethodControllerOpenApi {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private BasicAssembler assembler;

    @Autowired
    private BasicDisassembler disassembler;

    @GetMapping
    public ResponseEntity<List<PaymentMethodResponse>> list(ServletWebRequest request) {
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";
        OffsetDateTime lastUpdatedAt = paymentMethodRepository.getLastUpdatedAt();
        if (Objects.nonNull(lastUpdatedAt)) {
            eTag = String.valueOf(lastUpdatedAt.toEpochSecond());
        }

        if (request.checkNotModified(eTag)) {
            return null;
        }

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
                .eTag(eTag)
                .body(assembler.<PaymentMethod, PaymentMethodResponse>get(PaymentMethodResponse.class)
                        .entityToRepresentation(paymentMethodRepository.findAll()));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<PaymentMethodResponse> find(@PathVariable Long id,
                                                      ServletWebRequest request) {
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";
        OffsetDateTime updatedAt = paymentMethodRepository.getUpdatedAtById(id);
        if (Objects.nonNull(updatedAt)) {
            eTag = String.valueOf(updatedAt.toEpochSecond());
        }

        if (request.checkNotModified(eTag)) {
            return null;
        }

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .eTag(eTag)
                .body(assembler.<PaymentMethod, PaymentMethodResponse>get(PaymentMethodResponse.class)
                        .entityToRepresentation(paymentMethodService.findOrNull(id)));
    }

    @PostMapping
    public PaymentMethodResponse save(@RequestBody @Valid PaymentMethodRequest input) {
        PaymentMethod paymentMethod = disassembler.<PaymentMethod, PaymentMethodRequest>get(PaymentMethod.class)
                .representationToEntity(input);
        return assembler.<PaymentMethod, PaymentMethodResponse>get(PaymentMethodResponse.class)
                .entityToRepresentation(paymentMethodService.save(paymentMethod));
    }

    @PutMapping(path = "{id}")
    public PaymentMethodResponse update(@PathVariable Long id,
                                        @RequestBody @Valid PaymentMethodRequest input) {
        PaymentMethod currentPaymentMethod = paymentMethodService.findOrNull(id);
        disassembler.<PaymentMethod, PaymentMethodRequest>get(PaymentMethod.class).copyToEntity(input, currentPaymentMethod);
        return assembler.<PaymentMethod, PaymentMethodResponse>get(PaymentMethodResponse.class)
                .entityToRepresentation(paymentMethodService.save(currentPaymentMethod));
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        paymentMethodService.delete(id);
    }
}
