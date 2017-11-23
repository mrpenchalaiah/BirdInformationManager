package se.saltside.controllers;

import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import se.saltside.beans.Bird;
import se.saltside.beans.BirdDetailResponse;
import se.saltside.beans.BirdListResponse;
import se.saltside.beans.BirdRequest;
import se.saltside.exceptions.BirdNotFoundException;
import se.saltside.processor.BirdInformationRetriver;
import se.saltside.processor.BirdResponseConverter;

@RestController
@RequestMapping("/")
public class BirdAPIController {

	@Autowired
	private BirdInformationRetriver birdRetriver;
	@Autowired
	private BirdResponseConverter birdResponseconverter;
	
	@RequestMapping(path="/birds",method=RequestMethod.GET)
	public BirdListResponse getAllBirds() {
		List<Bird> birds = birdRetriver.getAllBirds();
		if(null== birds || birds.size()==0) {
			 throw new BirdNotFoundException("The bird you deleting was not found");
		 }
		return birdResponseconverter.convertToBirdResponse(new HashSet<Bird>(birds));
	}
	
	@RequestMapping(path="/birds",method=RequestMethod.POST)
	public ResponseEntity<Void> saveBird(@Valid @RequestBody BirdRequest bird) {
		birdRetriver.storeBird(birdResponseconverter.getBird(bird));
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@RequestMapping(path="/birds/{birdid}",method=RequestMethod.DELETE)
	public ResponseEntity<Void>  deleteBird(@PathVariable("birdid") String id) {
		Bird bird = birdRetriver.getBird(id);
		 if(null== bird) {
			 throw new BirdNotFoundException("The bird you deleting was not found");
		 }
		birdRetriver.deleteBird(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(path="/birds/{birdid}",method=RequestMethod.GET)
	public BirdDetailResponse getBird(@PathVariable("birdid") String birdid) {
		 Bird bird = birdRetriver.getBird(birdid);
		 System.out.println( birdid+" the results found "+bird);
		 if(null == bird) {
			 throw new BirdNotFoundException("The bird you looking was not found");
		 }
		return birdResponseconverter.convertToBirdResponse(bird);
	}
	
	
}
