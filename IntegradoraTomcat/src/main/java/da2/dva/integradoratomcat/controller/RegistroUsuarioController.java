package da2.dva.integradoratomcat.controller;

import da2.dva.integradoratomcat.model.entities.Usuario;
import da2.dva.integradoratomcat.model.entities.UsuarioAdministrador;
import da2.dva.integradoratomcat.model.entities.UsuarioEmpleado;
import da2.dva.integradoratomcat.services.Servicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("registro/usuario")
public class RegistroUsuarioController {
    ModelAndView mv = new ModelAndView("/registro/usuario");

    @Autowired
    Servicio servicio;

    @Bean
    public void conseguirColeccionesRU(){
        mv.addObject("listaUsuarios",servicio.devuelveUsuarios());
    }

    @GetMapping("administrador")//TODO:INVESTIGAR PARA PONER LA IP Y EL REGISTRO
    public ModelAndView registroUsuario(@ModelAttribute("usuario") UsuarioAdministrador usuario) {
        mv.addObject("titulo","Registro de usuario");
        mv.addObject("tipoUsuario","administrador");
        return mv;
    }
    @PostMapping("administrador")
    public ModelAndView registrar(@Valid @ModelAttribute("usuario") UsuarioAdministrador usuario
                                  ,BindingResult resultado
    ) {
       // mv.addObject("usuario", usuario);
        return mv;
    }

    @GetMapping("empleado")
    public ModelAndView registroUsuarioEmpleado(@ModelAttribute("usuario") UsuarioEmpleado usuario) {
        mv.addObject("titulo","Registro de usuario");
        mv.addObject("tipoUsuario","empleado");

        return mv;
    }
    @PostMapping("empleado")
    public ModelAndView registrarEmpleado(@Valid @ModelAttribute("usuario") UsuarioEmpleado usuario,
                                  BindingResult resultado
    ) {
        if (resultado.hasErrors()) {
            mv.addObject("error", "Por favor, rellene los campos obligatorios");
            System.out.println(usuario.getEmail());
            return mv;
        }else{
            mv.setViewName("redirect:/login/paso1");

            //TODO: METER EN LA BBD
        }
        // mv.addObject("usuario", usuario);
        return mv;
    }


}


