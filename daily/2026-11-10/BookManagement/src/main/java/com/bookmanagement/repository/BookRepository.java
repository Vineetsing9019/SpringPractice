// Learning repositories in Spring Data JPA - practice class from BookManagement, pushed 2026-10-24
package com.bookmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.bookmanagement.models.Book;

@RepositoryRestResource(collectionResourceRel = "books", path = "books")
public interface BookRepository extends JpaRepository<Book, Integer> {

}
