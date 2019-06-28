package cz.lundegaard.backend;

import cz.lundegaard.backend.domain.ContactFormType;
import cz.lundegaard.backend.repository.ContactFormTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;

import javax.annotation.PostConstruct;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
@Slf4j
public class BackendApplication {

    @Autowired
    ContactFormTypeRepository contactFormTypeRepository;

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @PostConstruct
    public void initData() {
        List<ContactFormType> contactFormTypes = Stream.of(
                ContactFormType.builder().id(new ObjectId()).code("CA").description("Contract Adjustment").build(),
                ContactFormType.builder().id(new ObjectId()).code("DC").description("Damage Case").build(),
                ContactFormType.builder().id(new ObjectId()).code("C").description("Complaint").build()).collect(Collectors.toList());
        contactFormTypeRepository.saveAll(contactFormTypes).subscribe();
    }
}
