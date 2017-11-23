package se.saltside.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
@RequestMapping("/*")
@Api(value="/", description="birds information store and retrival")
public class BirdAPIController {

	@Autowired
	private BirdInformationRetriver birdRetriver;
	@Autowired
	private BirdResponseConverter birdResponseconverter;
	
	
	@ApiOperation(value = "View a available bird stored", response = BirdListResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
	@RequestMapping(path="/birds",method=RequestMethod.GET)
	public BirdListResponse getAllBirds() {
		List<Bird> birds = birdRetriver.getAllBirds();
		return birdResponseconverter.convertToBirdResponse(new HashSet<Bird>(birds));
	}
	
	
	@ApiOperation(value = "store the bird information in database", response = HttpStatus.class)
	 @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Successfully retrieved list"),
	            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	    })
	@RequestMapping(path="/birds",method=RequestMethod.POST)
	public ResponseEntity<Void> saveBird(@Valid @RequestBody BirdRequest bird) {
		birdRetriver.storeBird(birdResponseconverter.getBird(bird));
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	
	@ApiOperation(value = "delete the bird information from database based on id", response = HttpStatus.class)
	 @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Successfully retrieved list"),
	            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	    })
	@RequestMapping(path="/birds/{birdid}",method=RequestMethod.DELETE)
	public ResponseEntity<Void>  deleteBird(@PathVariable("birdid") String id) {
		Bird bird = birdRetriver.getBird(id);
		 if(null== bird) {
			 throw new BirdNotFoundException("The bird you deleting was not found");
		 }
		birdRetriver.deleteBird(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "retrive the bird information from database based on id", response = BirdDetailResponse.class)
	 @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Successfully retrieved list"),
	            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	    })
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
