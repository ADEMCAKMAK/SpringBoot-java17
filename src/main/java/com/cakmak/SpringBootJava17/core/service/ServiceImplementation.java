package com.cakmak.SpringBootJava17.core.service;

import com.cakmak.SpringBootJava17.core.entity.Entity;
import com.cakmak.SpringBootJava17.core.mapper.EntityModelMapper;
import com.cakmak.SpringBootJava17.core.model.Model;
import com.cakmak.SpringBootJava17.core.repository.Repository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

@Transactional(readOnly = true)
public abstract class ServiceImplementation<E extends Entity<ID>, ID extends Serializable, M extends Model<ID>>
        implements Service<E, ID, M> {

    private final Repository<E, ID> repository;
    private final EntityModelMapper<E, M> entityModelMapper;

    protected ServiceImplementation(Repository<E, ID> repository, EntityModelMapper<E, M> entityModelMapper) {
        this.repository = repository;
        this.entityModelMapper = entityModelMapper;
    }

    public Repository<E, ID> getRepository() {
        return repository;
    }

    public EntityModelMapper<E, M> getEntityModelMapper() {
        return entityModelMapper;
    }

    @Override
    public Optional<M> optionalFindById(final ID id) {
        return getEntityModelMapper().fromEntityToModel(getRepository().findById(id));
    }

    @Override
    public M findById(final ID id) {
        return optionalFindById(id).orElseThrow(
                () -> new EntityNotFoundException(this.getClass().getSimpleName() + " id: " + id)
        );
    }

    @Override
    @Transactional
    public M create(M model) {
        E saveEntity = getEntityModelMapper().fromModelToEntity(model);
        return getEntityModelMapper().fromEntityToModel(getRepository().save(saveEntity));
    }

    @Override
    @Transactional
    public M update(M model) {
        Optional<E> optionalEntity = getRepository().findById(model.getId());
        E entity = optionalEntity.orElseThrow(() -> new EntityNotFoundException(this.getClass().getSimpleName() + " id: " + model.getId()));
        E updatedEntity = getEntityModelMapper().updateFromModelToEntity(model, entity);
        return getEntityModelMapper().fromEntityToModel(getRepository().save(updatedEntity));
    }

    @Override
    @Transactional
    public void delete(final ID id) {
        this.delete(id, Boolean.FALSE);
    }

    @Override
    @Transactional
    public void delete(final ID id, final Boolean force) {
        if (Objects.equals(force, Boolean.TRUE)) {
            getRepository().deleteById(id);
        } else {
            getRepository().findById(id).ifPresent(entity -> {
                entity.setDeleted(Boolean.TRUE);
                getRepository().save(entity);
            });
        }
    }
}
