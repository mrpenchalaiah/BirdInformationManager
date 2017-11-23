package se.saltside.birdapi;

import java.util.Arrays;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import se.saltside.beans.BirdDetailResponse;
import se.saltside.beans.BirdListResponse;
import se.saltside.beans.BirdRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureDataMongo
public class BirdapiApplicationTests {

	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	TestRestTemplate restTemplate;
	
	@Test
	public void testInsert() {
		BirdRequest birdReq = new BirdRequest();
		birdReq.setName("Parrot");
		birdReq.setFamily("FlyFamily");
		birdReq.setVisible(true);
		birdReq.setContinents(Arrays.asList("Fly","test"));
		HttpStatus res = restTemplate.postForEntity("/birds", birdReq, String.class).getStatusCode();
		assertThat(res).isEqualTo(HttpStatus.CREATED);
	}
	
	@Test
	public void testRetriveAll() {
		String body = restTemplate.getForEntity("/birds", String.class).getBody();
		assertThat(body).contains("FlyFamily");
		assertThat(body).contains("Parrot");
		BirdListResponse listR = restTemplate.getForEntity("/birds", BirdListResponse.class).getBody();
		assertThat(listR.getData().get(0).getFamily()).contains("FlyFamily");
		assertThat(listR.getData().get(0).getName()).contains("Parrot");
	}
	
	@Test
	public void testRetriveById() {
		testInsert();
		String body = restTemplate.getForEntity("/birds", String.class).getBody();
		assertThat(body).contains("FlyFamily");
		assertThat(body).contains("Parrot");
		BirdListResponse listR = restTemplate.getForEntity("/birds", BirdListResponse.class).getBody();
		String id = listR.getData().get(0).getId();
		BirdDetailResponse detailR = restTemplate.getForEntity("/birds/"+id, BirdDetailResponse.class).getBody();
		assertThat(detailR.getData().getFamily()).contains("FlyFamily");
		assertThat(detailR.getData().getName()).contains("Parrot");
	}
	
	
	@Test
	public void testDelete() {
		testInsert();
		String body = restTemplate.getForEntity("/birds", String.class).getBody();
		assertThat(body).contains("FlyFamily");
		assertThat(body).contains("Parrot");
		BirdListResponse listR = restTemplate.getForEntity("/birds", BirdListResponse.class).getBody();
		String id = listR.getData().get(0).getId();
		System.out.println("deleting the id "+id);
		restTemplate.delete("/birds/"+id);
		HttpStatus res = restTemplate.getForEntity("/birds/"+id, BirdDetailResponse.class).getStatusCode();
		assertThat(res).isEqualTo(HttpStatus.NOT_FOUND);
	}
	
	

}
