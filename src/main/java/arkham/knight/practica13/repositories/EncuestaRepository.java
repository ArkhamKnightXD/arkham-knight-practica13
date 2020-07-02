package arkham.knight.practica13.repositories;

import arkham.knight.practica13.models.Encuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncuestaRepository extends JpaRepository<Encuesta, Long> {

    Encuesta findEncuestaById(Long id);

}
