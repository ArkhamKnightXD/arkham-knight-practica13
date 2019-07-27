package arkham.knight.practica13.Repositories;

import arkham.knight.practica13.Models.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    Rol findRolById(Long id);
}
