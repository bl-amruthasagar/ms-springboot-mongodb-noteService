package com.ams.noteapi.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ams.noteapi.model.NoteModel;


@Repository
public interface NoteRepo extends MongoRepository<NoteModel, Long>{

}
