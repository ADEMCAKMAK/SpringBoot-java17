package com.cakmak.SpringBootJava17.core.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.MappingTarget;

import java.util.Objects;
import java.util.Optional;

public interface EntityModelMapper<E, M> {

    M fromEntityToModel(final E entity);

    E updateFromModelToEntity(M model, @MappingTarget E entity);

    @InheritInverseConfiguration
    E fromModelToEntity(final M model);

    default Optional<E> fromModelToEntity(final Optional<M> optionalModel) {
        return Objects.nonNull(optionalModel) && optionalModel.isPresent() ? optionalModel.map(this::fromModelToEntity) : Optional.empty();
    }

    default Optional<M> fromEntityToModel(final Optional<E> optionalEntity) {
        return Objects.nonNull(optionalEntity) && optionalEntity.isPresent() ? optionalEntity.map(this::fromEntityToModel) : Optional.empty();
    }

}
