package com.cakmak.SpringBootJava17.core.service;

import com.cakmak.SpringBootJava17.core.entity.Entity;
import com.cakmak.SpringBootJava17.core.model.Model;

import java.io.Serializable;
import java.util.Optional;

public interface Service<E extends Entity<ID>, ID extends Serializable, M extends Model<ID>> {

    Optional<M> optionalFindById(final ID id);

    M findById(final ID id);

    M create(M model);

    M update(M model);

    void delete(final ID id);

    void delete(final ID id, final Boolean force);
}
