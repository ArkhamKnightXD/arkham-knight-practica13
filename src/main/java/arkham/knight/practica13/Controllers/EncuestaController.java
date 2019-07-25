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
public class EncuestaController {

    @Autowired
    private EncuestaService encuestaService;


    @RequestMapping("/inicio")
    public String index(Model model){

        model.addAttribute("titulo", "Encuestas BarCamp");

        model.addAttribute("encuestas", encuestaService.listarEncuestas());


        return "/freemarker/encuestas";
    }


    @RequestMapping("/")
    public String creacionEncuesta(Model model){

        model.addAttribute("titulo", "Encuesta Bar Camp");

        return "/freemarker/crearEncuesta";
    }


    @RequestMapping( value = "/crear", method = RequestMethod.POST)
    public String crearEncuesta(@RequestParam(name = "cumplieronExpectativas") int cumplieronExpectativas, @RequestParam(name = "dominioDelTema") int dominioDelTema, @RequestParam(name = "instalacionConfortable") int instalacionConfortable, @RequestParam(name = "comentario") String comentario ){


        Encuesta encuestaToCreate = new Encuesta(cumplieronExpectativas,dominioDelTema,instalacionConfortable,comentario);

        encuestaService.crearEncuesta(encuestaToCreate);

        // Debo mandarle aqui una pagina que diga felicidades haz completado la encuesta
        return "/freemarker/encuestaCompletada";
    }

}
