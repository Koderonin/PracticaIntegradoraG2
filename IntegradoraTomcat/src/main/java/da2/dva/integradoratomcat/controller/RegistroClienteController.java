package da2.dva.integradoratomcat.controller;

import da2.dva.integradoratomcat.model.entities.Cliente;
import da2.dva.integradoratomcat.model.entities.UsuarioAdministrador;
import da2.dva.integradoratomcat.services.Servicio;
import da2.dva.integradoratomcat.utils.DatosPersonales;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("registro/cliente")
public class RegistroClienteController {
    @Autowired
    Servicio servicio;
    ModelAndView mv = new ModelAndView("/registro/cliente");

    @Bean
    public void conseguirColeccionesRC(){

        mv.addObject("listaPaises",servicio.devuelvePaises());
        mv.addObject("listaGeneros",servicio.devuelveGeneros());
        mv.addObject("listaTiposDocumentos",servicio.devuelveTiposDocumentos());
        mv.addObject("listaPreguntas",servicio.devuelvePreguntas());
        mv.addObject("listaTiposVia",servicio.devuelveTiposVia());
        mv.addObject("listaUsuarios",servicio.devuelveUsuarios());
    }

    @GetMapping("paso1")
    public ModelAndView registroCliente(@ModelAttribute("cliente") Cliente cliente, HttpSession sesion) {
        mv.addObject("titulo","Registro de cliente");
        mv.addObject("paso" ,"1");
        cliente = (Cliente) sesion.getAttribute("usuario");
        if (cliente != null) mv.addObject("usuario", cliente);
        return mv;
    }

    @PostMapping("paso1")
    public ModelAndView registrar(@Validated(DatosPersonales.class) @ModelAttribute("cliente") Cliente cliente, BindingResult resultado, HttpSession sesion) {
        if (resultado.hasErrors()) {
            mv.addObject("error", "Por favor, rellene los campos obligatorios");
            return mv;
        }
        Cliente clienteSesion = (Cliente) sesion.getAttribute("usuario");
        if (clienteSesion == null) {
            clienteSesion = new Cliente();
        }
        clienteSesion.setNombre(cliente.getNombre());
        clienteSesion.setApellidos(cliente.getApellidos());
        clienteSesion.setGenero(cliente.getGenero());
        clienteSesion.setFechaNacimiento(cliente.getFechaNacimiento());
        clienteSesion.setPais(cliente.getPais());
        clienteSesion.setTipoDocumento(cliente.getTipoDocumento());
        clienteSesion.setDocumento(cliente.getDocumento());
        mv.setViewName("redirect:/cliente/paso2");
        return mv;
    }

    @GetMapping("paso2")
    public ModelAndView paso2(@ModelAttribute("cliente") Cliente cliente, HttpSession sesion) {
        cliente = (Cliente) sesion.getAttribute("usuario");
        if (cliente != null) mv.addObject("usuario", cliente);
        return mv;
    }
}
