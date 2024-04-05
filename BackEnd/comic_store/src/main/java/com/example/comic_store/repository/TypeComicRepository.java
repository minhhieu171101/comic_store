package com.example.comic_store.repository;

import com.example.comic_store.entity.TypeComic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeComicRepository extends JpaRepository<TypeComic, Long> {
    TypeComic findTypeComicByTypeName(String typeName);
}
