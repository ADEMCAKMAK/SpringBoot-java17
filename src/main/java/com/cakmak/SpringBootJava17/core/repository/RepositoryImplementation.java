package com.cakmak.SpringBootJava17.core.repository;

import com.cakmak.SpringBootJava17.core.entity.Entity;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.io.Serializable;


public class RepositoryImplementation<T extends Entity<ID>, ID extends Serializable>
        extends SimpleJpaRepository<T, ID>
        implements Repository<T, ID> {

    private final EntityManager entityManager;

    public RepositoryImplementation(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

}
