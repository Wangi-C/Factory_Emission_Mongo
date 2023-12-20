package pkg.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pkg.entity.FactoryEmission;

public interface FactoryRepository extends MongoRepository<FactoryEmission, String> {

}
