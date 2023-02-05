package com.cakmak.SpringBootJava17.core.rest;

import com.cakmak.SpringBootJava17.core.entity.Entity;
import com.cakmak.SpringBootJava17.core.model.Model;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

public interface ReadOnlyController<E extends Entity<ID>, ID extends Serializable, M extends Model<ID>> {

    ResponseEntity<M> findById(ID id);

}
