package se.saltside.interfaces;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import se.saltside.beans.Bird;

@Transactional
@Repository
public interface MongoRepo extends MongoRepository<Bird, String> {

	public List<Bird> findByVisible(boolean value);
}
