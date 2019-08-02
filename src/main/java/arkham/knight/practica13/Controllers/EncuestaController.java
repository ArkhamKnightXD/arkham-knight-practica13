package arkham.knight.practica13.Controllers;

import arkham.knight.practica13.Models.Encuesta;
import arkham.knight.practica13.Services.EncuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("")
public class EncuestaController {

    @Autowired
    private EncuestaService encuestaService;



    //Aqui manejo indico el login de mi aplicacion y trabajo en conjunto con la configuracion de seguridad
//    @RequestMapping("/login")
    public String login(Model model){

        return "/freemarker/login";
    }

//    @RequestMapping("/index")
    public String index(Model model){

        model.addAttribute("titulo", "Encuestas BarCamp");

        model.addAttribute("encuestas", encuestaService.listarEncuestas());


        return "/freemarker/encuestas";
    }


//    @RequestMapping("/")
    public String creacionEncuesta(Model model){

     //   seguridadService.crearUsuarioAdmin();

        model.addAttribute("titulo", "Encuesta Bar Camp");

        return "/freemarker/crearEncuesta";
    }


    //Me da error a la hora de crear y me parece que el error esta aqui dentro
    @RequestMapping(value = "/crear", method = RequestMethod.POST)
    public String crearEncuesta(Model model, @RequestParam(name = "cumplieronExpectativas") int cumplieronExpectativas, @RequestParam(name = "dominioDelTema") int dominioDelTema, @RequestParam(name = "instalacionesConfortables") int instalacionesConfortables, @RequestParam(name = "comentario") String comentario ){


        Encuesta encuestaToCreate = new Encuesta(cumplieronExpectativas,dominioDelTema,instalacionesConfortables,comentario);

        encuestaService.crearEncuesta(encuestaToCreate);

        model.addAttribute("titulo", "Encuesta Bar Camp");



        // Debo mandarle aqui una pagina que diga felicidades haz completado la encuesta
        return "/freemarker/encuestaCompletada";
    }

}
