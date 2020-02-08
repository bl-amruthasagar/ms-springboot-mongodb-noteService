package com.ams.noteapi.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ams.noteapi.dao.NoteRepo;
import com.ams.noteapi.model.NoteModel;
import com.ams.noteapi.service.SequenceGeneratorNoteService;




@RestController
@RequestMapping("/noteapi")
public class NoteController {

	
	@Autowired
	NoteRepo repo;
	
	@Autowired
	SequenceGeneratorNoteService seqGeneratorNoteService;
	
	@Autowired
	RestTemplate rTemp;
	
	@PostMapping("/create")
	public NoteModel create(@RequestBody NoteModel newObject) {
		newObject.setId(seqGeneratorNoteService.generateSequence(NoteModel.SEQUENCE_NAME));
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
		Boolean status = rTemp.exchange("http://localhost:8081/userapi/verifyuser/"+newObject.getUserId(), HttpMethod.GET ,entity, Boolean.class).getBody();
		if(status) {
			System.out.println("status of user:"+status);
		return repo.save(newObject);
		}else {
			return null;
		}
	}
	
	@GetMapping("/read")
	public List<NoteModel> read(){
		return repo.findAll();
	}
	
	@GetMapping("/read/{id}")
	public NoteModel read(@PathVariable Long id) {
		Optional<NoteModel> userObj = repo.findById(id);
		if(userObj.isPresent()) {
			return userObj.get();
		}else {
			throw new RuntimeException("Note not found with id "+id);
		}
	}
	
	@PutMapping("/update")
	public NoteModel update(@RequestBody NoteModel modifiedObject) {
		return repo.save(modifiedObject);
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		Optional<NoteModel> userObj = repo.findById(id);
		if(userObj.isPresent()) {
			repo.delete(userObj.get());
			return "Note deleted with id "+id;
		}else {
			throw new RuntimeException("Note not found for id "+id);
		}
	}
	
}


