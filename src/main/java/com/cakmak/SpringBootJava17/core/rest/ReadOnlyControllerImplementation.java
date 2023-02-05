package com.cakmak.SpringBootJava17.core.rest;

import com.cakmak.SpringBootJava17.core.entity.Entity;
import com.cakmak.SpringBootJava17.core.model.Model;
import com.cakmak.SpringBootJava17.core.service.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.Serializable;

public abstract class ReadOnlyControllerImplementation<T extends Entity<ID>, ID extends Serializable, M extends Model<ID>>
        implements ReadOnlyController<T, ID, M> {

    private final Service<T, ID, M> service;

    protected ReadOnlyControllerImplementation(Service<T, ID, M> service) {
        this.service = service;
    }

    public Service<T, ID, M> getService() {
        return service;
    }

    @GetMapping(value = "{id}")
    @Override
    public ResponseEntity<M> findById(@PathVariable("id") ID id) {
        return ResponseEntity.ok(getService().findById(id));
    }

}
