package arkham.knight.practica13.Controllers;

import arkham.knight.practica13.Services.EncuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EncuestaController {

    @Autowired
    private EncuestaService encuestaService;

}
