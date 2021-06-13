package arkham.knight.practica13.services;

import arkham.knight.practica13.models.Usuario;
import arkham.knight.practica13.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepo;

    public UsuarioService(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }


    public void crearUsuario(Usuario usuario) {

        usuarioRepo.save(usuario);
    }


    public List<Usuario> listarUsuarios() {

        return usuarioRepo.findAll();
    }


    public Usuario login(String usuario, String password) {

        return usuarioRepo.findByUsernameAndPassword(usuario, password);
    }
}
