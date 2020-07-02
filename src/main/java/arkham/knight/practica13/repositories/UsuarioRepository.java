package arkham.knight.practica13.repositories;

import arkham.knight.practica13.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findUsuarioById(Long id);

    Usuario findByUsername(String username);

    Usuario findByUsernameAndPassword(String usuario, String password);

}
