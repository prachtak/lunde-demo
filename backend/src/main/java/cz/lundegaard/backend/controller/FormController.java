package cz.lundegaard.backend.controller;

import cz.lundegaard.backend.constant.Mappings;
import cz.lundegaard.backend.mapper.FormMapper;
import cz.lundegaard.backend.model.form.ContactFormTypeDto;
import cz.lundegaard.backend.model.form.ContactUsDto;
import cz.lundegaard.backend.service.FormService;
import cz.lundegaard.backend.support.Validations;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static cz.lundegaard.backend.support.Validations.BINDING_RESULT_EXCEPTION;

@Slf4j
@RestController
@RequestMapping(Mappings.FORMS)
public class FormController {

    private final FormService formService;
    private final FormMapper formMapper;

    public FormController(FormService formService, FormMapper formMapper) {
        this.formService = formService;
        this.formMapper = formMapper;
    }

    @CrossOrigin
    @PostMapping("/contact-us")
    public Mono<ResponseEntity<Void>> saveContactUsForm(@Valid @RequestBody Mono<ContactUsDto> contactUs) {
        return contactUs
                .onErrorMap(BINDING_RESULT_EXCEPTION, Validations::validationException)
                .flatMap(formService::saveContactUsForm)
                .thenReturn(ResponseEntity.noContent().build());
    }

    @CrossOrigin
    @GetMapping("/contact-form-type")
    public Flux<ContactFormTypeDto> getContactFormType() {
        return formService.getContactFormType()
                .map(formMapper::fromDomain);
    }
}

