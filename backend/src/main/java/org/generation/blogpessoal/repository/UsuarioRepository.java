package org.generation.blogpessoal.repository;

import java.util.Optional;
import java.util.*;

import org.generation.blogpessoal.model.Usuario;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Optional<Usuario> findByUsuario(String usuario);
    public List <Usuario> findAllByNomeContainingIgnoreCase(String nome); 
}
