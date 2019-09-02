package arkham.knight.practica13.services;

import arkham.knight.practica13.models.Usuario;
import arkham.knight.practica13.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;


    public void crearUsuario(Usuario usuario) {

        usuarioRepo.save(usuario);
    }

    public boolean existe(Usuario usuario) {
        return !usuarioRepo.findByUsername(usuario.getUsername()).toString().isEmpty();
    }


    public List<Usuario> listarUsuarios() {

        return usuarioRepo.findAll();
    }

    public Usuario encontrarUsuarioPorId(long id) {

        return usuarioRepo.findUsuarioById(id);
    }

    public void eliminarUsuario(long id) {

        // Igualo  el cliente al cliente que buscamos mediante el id
        Usuario usuarioToDelete = usuarioRepo.findUsuarioById(id);

        // y aqui lo borro
        usuarioRepo.delete(usuarioToDelete);
    }

    public void borrarTodosLosUsuarios() {

        usuarioRepo.deleteAll();
    }

    public Usuario login(String usuario, String password) {
        return usuarioRepo.findByUsernameAndPassword(usuario, password);
    }


}
