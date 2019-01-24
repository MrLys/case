package com.example.ContractSystem;

import com.example.ContractSystem.model.Contract;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContractSystemApplicationTests {


	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void incorrectMethod() {
		ResponseEntity<String> entity = this.restTemplate.getForEntity("/api/contract/new", String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.METHOD_NOT_ALLOWED);
	}

    @Test
    public void newContractPOSTXML(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        Contract contract = new Contract(123123,"Fancy Science");
        Gson gson = new Gson();
        String s = gson.toJson(contract);
        HttpEntity<String> entity = new HttpEntity<String>(s, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("/api/contract/new", entity,String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    class Dummy {
        String notName;
        Double length;
        public Dummy(String notName, Double length){
            this.notName = notName;
            this.length = length;
        }
    }
    @Test
    public void newContractPOSTNoncontrectBody(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Dummy dummy = new Dummy("Incorrect class", 1.23123);
        Gson gson = new Gson();
        String s = gson.toJson(dummy);
        HttpEntity<String> entity = new HttpEntity<String>(s, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("/api/contract/new", entity,String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
    @Test
    public void newContractPOSTnull(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("/api/contract/new", entity,String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void newContractPost(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Contract contract = new Contract(123123,"Fancy Science");

	Gson gson = new Gson();
	String s = gson.toJson(contract);
        HttpEntity<String> entity = new HttpEntity<String>(s, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("/api/contract/new", entity,String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	ResponseEntity<String> entity = this.restTemplate.getForEntity("/api/contract/new/1/test", String.class);
	assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.METHOD_NOT_ALLOWED);
	}




}

