package da2.dva.integradoratomcat.controller;

import da2.dva.integradoratomcat.model.auxiliar.TarjetaCredito;
import da2.dva.integradoratomcat.model.entities.Cliente;
import da2.dva.integradoratomcat.services.ServicioCliente;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cliente/tarjeta")
public class TarjetaController {
    @Autowired
    ServicioCliente servicioCliente;

    ModelAndView mv = new ModelAndView("/add-tarjeta");

    @GetMapping("/add-tarjeta")
    public ModelAndView agregarTarjeta(@ModelAttribute("tarjeta") TarjetaCredito tarjeta, HttpSession sesion){
        //Recuperamos las paginas visitadas y si es la primera vez iniciamos el contador
        if (sesion.getAttribute("paginas_visitadas") == null)
            sesion.setAttribute("paginas_visitadas", 0);
        //Convertimos el atributo en entero para poder incrementarlo
        int pags_visitadas = (int) sesion.getAttribute("paginas_visitadas");
        sesion.setAttribute("paginas_visitadas", pags_visitadas + 1);

        return mv;
    }
    @PostMapping("/add-tarjeta")
    public ModelAndView insertarTarjeta(@ModelAttribute("tarjeta") TarjetaCredito tarjeta, HttpSession sesion){
        servicioCliente.agregarTarjeta((Cliente) sesion.getAttribute("cliente"), tarjeta);
        return mv;
    }


}
