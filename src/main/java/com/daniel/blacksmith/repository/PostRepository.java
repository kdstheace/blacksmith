package com.daniel.blacksmith.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository {
    /**
     1. typeVariable: entity type, primary key type
     2. no need to explicitly write a code, cuz "JPARespository" already provides primary CRUD functions.
     3. JPARepository even extends "PagingAndSortingRepository"
     4. no need to add @Repository because of "SimpleJpaRepository"
     "SimpleJpaRepository" which has @Repository, @Transactional(readOnly=true) implements "JpaRepositoryImplementation"
     so that it internally implements all *Repository interfaces
     */
}
