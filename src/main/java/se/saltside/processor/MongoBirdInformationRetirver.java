package se.saltside.processor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Component;

import se.saltside.beans.Bird;
import se.saltside.interfaces.MongoRepo;

@Component
public class MongoBirdInformationRetirver implements BirdInformationRetriver {
	
	@Autowired
    MongoRepo mongo;
	
	@Override
	public List<Bird> getAllBirds() {
		return mongo.findByVisible(true);
	}

	@Override
	public Bird getBird(String id) {
		return mongo.findOne(id);
	}

	@Override
	public boolean deleteBird(String id) {
		try{
			 mongo.delete(id);
		}catch(Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public Bird storeBird(Bird bird) {
		return mongo.save(bird);
	}

}
