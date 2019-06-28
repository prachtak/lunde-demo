package cz.lundegaard.backend.repository;

import cz.lundegaard.backend.domain.ContactUs;
import org.bson.types.ObjectId;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactFormRepository extends ReactiveMongoRepository<ContactUs, ObjectId> {

}
