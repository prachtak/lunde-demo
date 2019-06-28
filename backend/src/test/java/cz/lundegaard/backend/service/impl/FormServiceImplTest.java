package cz.lundegaard.backend.service.impl;

import cz.lundegaard.backend.domain.ContactUs;
import cz.lundegaard.backend.repository.ContactFormRepository;
import cz.lundegaard.backend.repository.ContactFormTypeRepository;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FormServiceImplTest {

    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    @Mock
    private ContactFormRepository contactFormRepository;

    @Mock
    private ContactFormTypeRepository contactFormTypeRepository;

    @Test
    public void saveContactUsForm() {

        when(contactFormTypeRepository.existsByCode("A")).thenReturn(Mono.just(true));
        when(contactFormRepository.save(any())).thenReturn(Mono.empty());

        var createContactUs = new ContactUs();
        createContactUs.setName("NAME");
        StepVerifier.create(contactFormRepository.save(createContactUs)).verifyComplete();
        verify(contactFormRepository).save(createContactUs);
    }
}