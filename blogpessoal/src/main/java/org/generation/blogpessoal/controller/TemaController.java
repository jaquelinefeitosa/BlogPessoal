package org.generation.blogpessoal.controller;

import java.util.List;

import org.generation.blogpessoal.model.Tema;
import org.generation.blogpessoal.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temas")
@CrossOrigin (origins = "*", allowedHeaders = "*")
public class TemaController {
    
    @Autowired
    private TemaRepository repository;

    @GetMapping 
    public ResponseEntity <List <Tema>> getALL() {
        return ResponseEntity.ok(repository.findAll());       
    }
    // retornar uma postagem pelo id
    @GetMapping("/{id}")
    public ResponseEntity<Tema> GetById(@PathVariable long id){
        return repository.findById(id)
                .map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
    }

    // procurar um tema pelo nome
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Tema>> GetByName(@PathVariable String nome){
        return ResponseEntity.ok(repository.findAllByDescricaoContaininglgnoreCase(nome));
    }

   // inserir dados no banco de dados
    @PostMapping
    public ResponseEntity<Tema> post(@RequestBody Tema tema)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
    }

    // atualizar um dado ja existente no banco de dados
    @PutMapping
    public ResponseEntity<Tema> put(@RequestBody Tema tema)
    {
        return ResponseEntity.ok(repository.save(tema));
    }

    //deletar dados do banco de dados.
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id)
    {
        repository.deleteById(id);
    }


    




}
