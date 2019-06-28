package cz.lundegaard.backend.service.impl;

import cz.lundegaard.backend.domain.ContactFormType;
import cz.lundegaard.backend.exception.impl.InvalidContactUsFornTypeException;
import cz.lundegaard.backend.mapper.FormMapper;
import cz.lundegaard.backend.model.form.ContactUsDto;
import cz.lundegaard.backend.repository.ContactFormRepository;
import cz.lundegaard.backend.repository.ContactFormTypeRepository;
import cz.lundegaard.backend.service.FormService;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FormServiceImpl implements FormService {

    private final ContactFormRepository contactFormRepository;
    private final ContactFormTypeRepository contactFormTypeRepository;
    private final FormMapper formMapper;

    public FormServiceImpl(ContactFormRepository contactFormRepository, ContactFormTypeRepository contactFormTypeRepository, FormMapper formMapper) {
        this.contactFormRepository = contactFormRepository;
        this.contactFormTypeRepository = contactFormTypeRepository;
        this.formMapper = formMapper;
    }

    /**
     * Method save Contact Us form request
     * @param contactUsDto
     * @return
     */
    @Override
    public Mono<Void> saveContactUsForm(ContactUsDto contactUsDto) {
        log.debug("Trying to save ContactUs form: {}", contactUsDto);
        var mapToDomain = formMapper.toDomain(contactUsDto);
        var saveContactUs = contactFormRepository.save(mapToDomain)
                .doOnSuccess(form -> log.debug("Form saved: {}", form))
                .doOnError(throwable -> log.error("ContactUs save fail: {}", throwable.getMessage()));
        return contactFormTypeRepository.existsByCode(contactUsDto.getContactFormTypeCode()).filter(Boolean.TRUE::equals)
                .switchIfEmpty(Mono.error(new InvalidContactUsFornTypeException("ContactFormTypeCode is not supported.")))
                .then(saveContactUs)
                .then();
    }

    /**
     * Method return available ContactFormType
     * @return
     */
    @Override
    public Flux<ContactFormType> getContactFormType() {
        return contactFormTypeRepository.findAll();
    }
}
