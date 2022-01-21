package org.generation.blogpessoal.repository;

import org.generation.blogpessoal.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface postagemRepository extends JpaRepository<Postagem, Long> {
    
public List<Postagem> findAllByTituloContainingIgnoreCase(String titulo);






}
