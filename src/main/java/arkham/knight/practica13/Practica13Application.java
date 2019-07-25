package arkham.knight.practica13;

import arkham.knight.practica13.Models.Encuesta;
import arkham.knight.practica13.Services.EncuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Practica13Application implements CommandLineRunner {

    @Autowired
    private EncuestaService encuestaService;

    public static void main(String[] args) {
        SpringApplication.run(Practica13Application.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        encuestaService.borrarTodasLasEncuestas();

        Encuesta encuestaToTest = new Encuesta(5,4,3,"Buen expositor");

        encuestaService.crearEncuesta(encuestaToTest);


    }

    }
