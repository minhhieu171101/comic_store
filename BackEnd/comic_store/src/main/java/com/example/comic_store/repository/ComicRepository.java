package com.example.comic_store.repository;

import com.example.comic_store.entity.Comic;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ComicRepository extends JpaRepository<Comic, Long> {
}
