package cz.lundegaard.backend.repository;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ContactFormTypeRepositoryTest {

    @Autowired
    ContactFormTypeRepository contactFormTypeRepository;

    @Test
    public void findAllTypes() {

        var data = contactFormTypeRepository.findAll().collectList().block();
        assertThat(data).hasSize(3);

    }
}