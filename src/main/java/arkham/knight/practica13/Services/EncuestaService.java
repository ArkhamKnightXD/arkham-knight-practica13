package arkham.knight.practica13.Services;

import arkham.knight.practica13.Repositories.EncuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EncuestaService {

    @Autowired
    private EncuestaRepository encuestaRepository;

}
