package arkham.knight.practica13.Services;

import arkham.knight.practica13.Models.Rol;
import arkham.knight.practica13.Models.Usuario;
import arkham.knight.practica13.Repositories.RolRepository;
import arkham.knight.practica13.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class SeguridadService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private RolRepository rolRepo;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Transactional
    public void crearUsuarioAdmin() {

        List<Rol> listaDeRoles = new ArrayList<>();

        Rol rolUser = new Rol();
        rolUser.setRole("ROLE_USER");
        rolRepo.save(rolUser);

        Rol rolAdmin = new Rol("ROLE_ADMIN");
        rolRepo.save(rolAdmin);

        listaDeRoles.add(rolAdmin);

        Usuario usuarioAdmin = new Usuario();
        usuarioAdmin.setUsername("admin");
        //El error con el login debe de ser en los roles, pues este metodo es diferente al de la practica 10
        usuarioAdmin.setRoles(listaDeRoles);

        usuarioAdmin.setPassword(passwordEncoder.encode("1234"));

        usuarioRepo.save(usuarioAdmin);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario user = usuarioRepo.findByUsername(username);

        // Codigo de camacho para reoorrer roles
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        for (Rol role : user.getRoles()) {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        }

        //
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);

        // Hay que retornar un objeto de tipo userdetails por lo tanto hacemos esto y le mandamos los datos del usuario
        // UserDetails userDetails = new Usuario(user.getUsername(),user.getPassword(),user.isAdmin(),roles);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isAdmin(), true, true, true, grantedAuthorities);


    }
}