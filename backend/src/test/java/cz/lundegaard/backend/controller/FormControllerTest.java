package cz.lundegaard.backend.controller;

import cz.lundegaard.backend.domain.ContactFormType;
import cz.lundegaard.backend.exception.impl.ValidationException;
import cz.lundegaard.backend.mapper.FormMapper;
import cz.lundegaard.backend.model.form.ContactUsDto;
import cz.lundegaard.backend.service.FormService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.Mockito.when;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebTestClient
public class FormControllerTest {

    @Autowired
    private WebTestClient client;

    @MockBean
    private FormService formService;

    @MockBean
    private FormMapper mapper;


    @Before
    public void setUp() {
        client = client.mutate().baseUrl("/api/v1/forms").build();
    }

    @Test
    public void saveContactUsForm() {
        when(formService.saveContactUsForm(createContactUsDTO())).thenReturn(Mono.empty());

        client.post()
                .uri("/contact-us")
                .syncBody(createContactUsDTO())
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    public void saveContactUsForm_failValidation() {
        when(formService.saveContactUsForm(createContactUsDTO())).thenThrow(new ValidationException("ex"));

        client.post()
                .uri("/contact-us")
                .syncBody(new ContactUsDto())
                .exchange()
                .expectStatus().is4xxClientError();
    }

    @Test
    public void getContactFormType() {

        when(formService.getContactFormType())
                .thenReturn(Flux.just(contactFormType(), contactFormType()));

        client.get()
                .uri("/contact-form-type")
                .exchange()
                .expectStatus().isOk();
    }


    private static ContactUsDto createContactUsDTO() {
        ContactUsDto contactUsDto = new ContactUsDto();
        contactUsDto.setContactFormTypeCode("C");
        contactUsDto.setName("NAME");
        contactUsDto.setSurname("SURNAME");
        contactUsDto.setPolicyNumber("POLICENUMBER9");
        contactUsDto.setRequestMessage("MESSAGE");
        return contactUsDto;
    }

    private static ContactFormType contactFormType() {
        ContactFormType contactFormType = new ContactFormType();
        contactFormType.setCode("C");
        contactFormType.setDescription("DESC");

        return contactFormType;
    }
}