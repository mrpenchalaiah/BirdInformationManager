package se.saltside.processor;

import java.util.List;

import se.saltside.beans.Bird;

public interface BirdInformationRetriver {

	public List<Bird> getAllBirds();
	
	public Bird getBird(String id);
	
	public boolean deleteBird(String id);
	
	public Bird storeBird(Bird bird);
}
