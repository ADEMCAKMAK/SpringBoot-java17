package com.cakmak.SpringBootJava17.core.repository;

import com.cakmak.SpringBootJava17.core.entity.Entity;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface Repository<E extends Entity<ID>, ID extends Serializable>
        extends JpaRepositoryImplementation<E, ID> {

    EntityManager getEntityManager();

}
