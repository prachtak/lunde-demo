package cz.lundegaard.backend.service;

import cz.lundegaard.backend.domain.ContactFormType;
import cz.lundegaard.backend.model.form.ContactUsDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Service;

@Service
public interface FormService {
    Mono<Void> saveContactUsForm(ContactUsDto contactUsDto);
    Flux<ContactFormType> getContactFormType();
}
