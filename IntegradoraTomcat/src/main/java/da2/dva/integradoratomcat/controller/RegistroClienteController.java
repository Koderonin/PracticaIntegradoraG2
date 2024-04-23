package da2.dva.integradoratomcat.controller;

import da2.dva.integradoratomcat.services.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/registro/cliente")
public class RegistroClienteController {
    @Autowired
    Servicio servicio;
    @Bean
    public void conseguirColecciones(){
        ModelAndView mv = new ModelAndView("/login/login");

        mv.addObject("listaPaises",servicio.devuelvePaises());
        mv.addObject("listaGeneros",servicio.devuelveGeneros());
        mv.addObject("listaTiposDocumentos",servicio.devuelveTiposDocumentos());
        mv.addObject("listaPreguntas",servicio.devuelvePreguntas());
        mv.addObject("listaTiposVia",servicio.devuelveTiposVia());
        mv.addObject("listaUsuarios",servicio.devuelveUsuarios());
    }


}
