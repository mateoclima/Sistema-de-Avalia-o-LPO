package br.com.ufpr.sistemaAvaliacao.repository;

import jakarta.persistence.EntityManager;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;


public abstract class GenericDAO<T, ID extends Serializable> {

    @Getter
    private EntityManager entityManager;
    private final Class<T> entityClass;

    public GenericDAO(EntityManager entityManager, Class<T> entityClass) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public T update(T entity) {
        return entityManager.merge(entity);
    }

    public T findById(ID id) {
        return entityManager.find(entityClass, id);
    }

    public void delete(T entity) {
        if (entityManager.contains(entity)) {
            entityManager.remove(entity);
        } else {
            T mergedEntity = entityManager.merge(entity);
            entityManager.remove(mergedEntity);
        }
    }

    public List<T> findAll() {
        String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e";
        return entityManager.createQuery(jpql, entityClass).getResultList();
    }

}