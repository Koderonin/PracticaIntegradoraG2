package da2.dva.integradoratomcat.controller;

import da2.dva.integradoratomcat.model.entities.UsuarioCliente;
import da2.dva.integradoratomcat.services.ServicioUsuario;
import da2.dva.integradoratomcat.services.ServicioColecciones;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("registro/usuario")
public class RegistroUsuarioController {

    @Autowired
    ServicioUsuario servicioUsuario;
    @Autowired
    ServicioColecciones servicio;

    @Bean
    public void descargarColeccionesRU(){
        servicio.cargarPreguntas();
        servicioUsuario.devuelveUsuarios(); //TODO: Cargar en estático ( a lo mejor no interesa)
    }

    @GetMapping("/")
    public ModelAndView registroUsuario(@ModelAttribute("usuario") UsuarioCliente usuario) {
        ModelAndView mv = new ModelAndView("registro/usuario");
        mv.setViewName("/registro/usuario");
        mv.addObject("titulo","Registro de usuario");
        mv.addObject("tipoUsuario","empleado");
        mv.addObject("listaPreguntas",servicio.getPreguntas());
        mv.addObject("listaIdiomas", servicio.getIdiomas());

        return mv;
    }

    @PostMapping("/")
    public ModelAndView registroUsuario(@Valid @ModelAttribute("usuario") UsuarioCliente usuario, BindingResult resultado) {
        ModelAndView mv = new ModelAndView("registro/usuario");
        mv.addObject("listaIdiomas", servicio.getIdiomas());
        if (servicioUsuario.devuelveUsuarios().containsKey(usuario.getEmail())) {
            mv.addObject("error", "El usuario ya existe");
            return mv;
        }
        if (resultado.hasErrors()) {
            mv.addObject("error", "Por favor, rellene los campos obligatorios");
            return mv;
        } else{
            mv.setViewName("redirect:/login/paso1");

            servicioUsuario.insertarUsuario(usuario);
        }
        return mv;
    }


}


