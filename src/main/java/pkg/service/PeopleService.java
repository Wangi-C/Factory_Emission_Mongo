package pkg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;
import pkg.entity.People;

import java.util.List;

@Service
public class PeopleService {
    /*@Autowired private MongoTemplate mongoTemplate;

    public List<People> getPeopleList() {
        System.out.println("getPeople");
        return mongoTemplate.findAll(People.class);
//        return null;
    }*/

    @Autowired private MongoRepository mongoRepository;

    public List<People> getPeopleList() {
        return mongoRepository.findAll();
    }
}
