package com.cakmak.SpringBootJava17.core.rest;

import com.cakmak.SpringBootJava17.core.entity.Entity;
import com.cakmak.SpringBootJava17.core.model.Model;
import com.cakmak.SpringBootJava17.core.service.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

public abstract class CrudControllerImplementation<E extends Entity<ID>, ID extends Serializable, M extends Model<ID>>
        extends ReadOnlyControllerImplementation<E, ID, M>
        implements CrudController<E, ID, M>, ReadOnlyController<E, ID, M> {

    protected CrudControllerImplementation(Service<E, ID, M> service) {
        super(service);
    }

    @PostMapping
    @Override
    public ResponseEntity<M> create(@RequestBody M model) {
        return ResponseEntity.ok(getService().create(model));
    }

    @PutMapping
    @Override
    public ResponseEntity<M> update(@RequestBody M model) {
        return ResponseEntity.ok(getService().update(model));
    }

    @DeleteMapping(value = "{id:.+}")
    @Override
    public ResponseEntity<M> delete(@PathVariable("id") ID id, @RequestParam(defaultValue = "false", required = false) Boolean force) {
        getService().delete(id, force);
        return ResponseEntity.ok().build();
    }
}
