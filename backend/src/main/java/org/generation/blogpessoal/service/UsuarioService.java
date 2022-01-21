package org.generation.blogpessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.generation.blogpessoal.model.UserLogin;
import org.generation.blogpessoal.model.Usuario;
import org.generation.blogpessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository repository;

    public Usuario CadastrarUsuario(Usuario usuario)
    {
        // inicializar o BCrypt
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // String onde ira converte a senha em critografia
        String senhaEncoder = encoder.encode(usuario.getSenha());
        // ira mudar para senha encriptografada
        usuario.setSenha(senhaEncoder);
        // salvara no banco de dados
        return repository.save(usuario);
    }

    public Optional<UserLogin> Logar(Optional<UserLogin> user)
    {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario());

        // metodo para comparar as senhas
        if(usuario.isPresent())
        {
            // ira comparar a senha que o usuario digitar com a cryptografada no banco de dados
            if(encoder.matches(user.get().getSenha(), usuario.get().getSenha()))
            {
            // ira concatenar o usuario e a senha
            String auth = user.get().getUsuario() + ":" + user.get().getSenha();

            byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
            //vai converter o byte para string
            String authHeader = "Basic " + new String(encodedAuth);

            user.get().setToken(authHeader);
            user.get().setNome(usuario.get().getNome());

            //se achar o usuario ira retorna-lo
            return user;
            }
        }
        //caso ao contrario retorne null
        return null;
    }
}
