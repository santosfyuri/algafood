package br.santosfyuri.algaworks.algafood.api.controller;

import br.santosfyuri.algaworks.algafood.api.assembler.BasicAssembler;
import br.santosfyuri.algaworks.algafood.api.assembler.BasicDisassembler;
import br.santosfyuri.algaworks.algafood.api.representation.request.PaymentMethodRequest;
import br.santosfyuri.algaworks.algafood.api.representation.response.PaymentMethodResponse;
import br.santosfyuri.algaworks.algafood.domain.model.PaymentMethod;
import br.santosfyuri.algaworks.algafood.domain.repository.PaymentMethodRepository;
import br.santosfyuri.algaworks.algafood.domain.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/payment-methods")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private BasicAssembler assembler;

    @Autowired
    private BasicDisassembler disassembler;

    @GetMapping
    public List<PaymentMethodResponse> list() {
        return assembler.<PaymentMethod, PaymentMethodResponse>get(PaymentMethodResponse.class)
                .entityToRepresentation(paymentMethodRepository.findAll());
    }

    @GetMapping(path = "{id}")
    public PaymentMethodResponse find(@PathVariable Long id) {
        return assembler.<PaymentMethod, PaymentMethodResponse>get(PaymentMethodResponse.class)
                .entityToRepresentation(paymentMethodService.findOrNull(id));
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
