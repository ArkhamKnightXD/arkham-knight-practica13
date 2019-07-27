package arkham.knight.practica13;

import arkham.knight.practica13.Models.Encuesta;
import arkham.knight.practica13.Models.Rol;
import arkham.knight.practica13.Models.Usuario;
import arkham.knight.practica13.Repositories.RolRepository;
import arkham.knight.practica13.Services.EncuestaService;
import arkham.knight.practica13.Services.SeguridadService;
import arkham.knight.practica13.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Practica13Application implements CommandLineRunner {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SeguridadService seguridadService;

    public static void main(String[] args) {
        SpringApplication.run(Practica13Application.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        usuarioService.borrarTodosLosUsuarios();

        seguridadService.crearUsuarioAdmin();

    }

    }
