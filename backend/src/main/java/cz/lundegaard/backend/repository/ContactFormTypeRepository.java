package cz.lundegaard.backend.repository;

import cz.lundegaard.backend.domain.ContactFormType;
import org.bson.types.ObjectId;
import reactor.core.publisher.Mono;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactFormTypeRepository extends ReactiveMongoRepository<ContactFormType, ObjectId> {

    Mono<Boolean> existsByCode(String code);
}

