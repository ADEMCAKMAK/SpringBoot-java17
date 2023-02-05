package com.cakmak.SpringBootJava17.core.rest;

import com.cakmak.SpringBootJava17.core.entity.Entity;
import com.cakmak.SpringBootJava17.core.model.Model;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

public interface CrudController<E extends Entity<ID>, ID extends Serializable, M extends Model<ID>>
        extends ReadOnlyController<E, ID, M> {

    ResponseEntity<M> create(M model);

    ResponseEntity<M> update(M model);

    ResponseEntity<M> delete(ID id, Boolean force);
}
