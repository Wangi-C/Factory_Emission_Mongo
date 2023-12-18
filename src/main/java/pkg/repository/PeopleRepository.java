package pkg.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pkg.entity.People;

public interface PeopleRepository extends MongoRepository<People, String> {

}
