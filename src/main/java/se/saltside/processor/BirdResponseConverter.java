package se.saltside.processor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;

import org.springframework.stereotype.Component;

import se.saltside.beans.BaseResponse;
import se.saltside.beans.Bird;
import se.saltside.beans.BirdDetailResponse;
import se.saltside.beans.BirdListResponse;
import se.saltside.beans.BirdRequest;
import se.saltside.beans.BirdResponse;
import se.saltside.beans.Meta;

@Component
public class BirdResponseConverter {

	public BirdDetailResponse convertToBirdResponse(Bird bird) {
		BirdDetailResponse birdL = new BirdDetailResponse();
		
		Meta meta = new Meta();
		meta.setCorrellationid(UUID.randomUUID().toString());
		meta.setMessage("operation success");
		meta.setStatus("true");
		birdL.setMeta(meta);
		
		if(null==bird) {
			meta.setCorrellationid(UUID.randomUUID().toString());
			meta.setMessage("data you are looking was not found.");
			meta.setStatus("false");
			return birdL;
		}
		SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd");
		BirdResponse birdRes = new BirdResponse();
		birdRes.setAdded(isoFormat.format(bird.getAdded()));
		birdRes.setContinents(bird.getContinents());
		birdRes.setFamily(bird.getFamily());
		birdRes.setId(bird.getId());
		birdRes.setName(bird.getName());
		birdRes.setVisible(bird.isVisible());
		birdL.setData(birdRes);
		return birdL;
	}
	
	public BirdListResponse convertToBirdResponse(List<Bird> birdList) {
		return populateResponseList(birdList);
	}
	
	public BirdListResponse convertToBirdResponse(Set<Bird> birdList) {
		return populateResponseList(birdList);
	}

	private BirdListResponse populateResponseList(Collection<Bird> birdList) {
		
		BirdListResponse birdL = new BirdListResponse();
		Meta meta = createMeta(true);
		birdL.setMeta(meta);
		if(null== birdList || birdList.size()==0) {
			meta = createMeta(false);
			birdL.setMeta(meta);
			return birdL;
		}
		
		
		List<BirdResponse> listOfResponses = new ArrayList<BirdResponse>();
		SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd");
		for(Bird bird:birdList) {
			BirdResponse birdRes = new BirdResponse();
			birdRes.setAdded(isoFormat.format(bird.getAdded()));
			birdRes.setContinents(bird.getContinents());
			birdRes.setFamily(bird.getFamily());
			birdRes.setId(bird.getId());
			birdRes.setName(bird.getName());
			birdRes.setVisible(bird.isVisible());
			listOfResponses.add(birdRes);
			
		}
		birdL.setData(listOfResponses);
		return birdL;
	}

	private Meta createMeta( boolean suc) {
		Meta meta = new Meta();
		meta.setCorrellationid(UUID.randomUUID().toString());
		meta.setMessage("operation success");
		meta.setStatus("true");
		
		if(!suc) {
			meta.setMessage("data you are looking was not found.");
			meta.setStatus("false");
		}
		return meta;
	}
	
	public Bird getBird(BirdRequest birdRequest) {
		Bird bird = new Bird();
		bird.setName(birdRequest.getName());
		bird.setFamily(birdRequest.getFamily());
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		bird.setAdded(cal.getTime());
		bird.setVisible(birdRequest.isVisible());
		bird.setContinents(birdRequest.getContinents());
		return bird;
		
	}
}
