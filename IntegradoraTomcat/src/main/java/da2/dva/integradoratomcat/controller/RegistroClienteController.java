package da2.dva.integradoratomcat.controller;

import da2.dva.integradoratomcat.model.auxiliar.Direccion;
import da2.dva.integradoratomcat.model.entities.Cliente;
import da2.dva.integradoratomcat.services.Servicio;
import da2.dva.integradoratomcat.utils.DatosCliente;
import da2.dva.integradoratomcat.utils.DatosContacto;
import da2.dva.integradoratomcat.utils.DatosPersonales;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.Validator;
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

import java.util.Set;

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
    public ModelAndView registroCliente(@ModelAttribute("cliente") Cliente cliente, HttpSession sesion, BindingResult result) {
        mv.addObject("titulo","Registro de cliente");
        mv.addObject("paso" ,"1");
        cliente = (Cliente) sesion.getAttribute("cliente");
        if (cliente != null) mv.addObject("cliente", cliente);
        return mv;
    }

    @PostMapping("paso1")
    public ModelAndView registrar(@Validated(DatosPersonales.class) @ModelAttribute("cliente") Cliente cliente, BindingResult resultado, HttpSession sesion) {
        if (resultado.hasErrors()) {
            mv.addObject("error", "Por favor, rellene los campos obligatorios");
            return mv;
        }
        Cliente clienteSesion = (Cliente) sesion.getAttribute("cliente");
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
        mv.addObject("paso" ,"2");
        return mv;
    }

    @GetMapping("paso2")
    public ModelAndView paso2(@ModelAttribute("cliente") Cliente cliente, HttpSession sesion, BindingResult result) {
        cliente = (Cliente) sesion.getAttribute("cliente");
        if (cliente != null) mv.addObject("cliente", cliente);
        mv.addObject("paso" ,"2");
        return mv;
    }

    @PostMapping("paso2")
    public ModelAndView registrar2(@Validated(DatosContacto.class) @ModelAttribute("cliente") Cliente cliente, BindingResult resultado, HttpSession sesion) {
        if (resultado.hasErrors()) {
            mv.addObject("error", "Por favor, rellene los campos obligatorios");
            return mv;
        }
        Cliente clienteSesion = (Cliente) sesion.getAttribute("cliente");
        if (clienteSesion == null) {
            clienteSesion = new Cliente();
        }

        Direccion direccion = new Direccion();
        //direccion.setTipo_via(cliente.getDireccion().getTipo_via());
        direccion.setNombre_via(cliente.getDireccion().getNombre_via());
        direccion.setNumero_via(cliente.getDireccion().getNumero_via());
        direccion.setPlanta(cliente.getDireccion().getPlanta());
        direccion.setPuerta(cliente.getDireccion().getPuerta());
        direccion.setPortal(cliente.getDireccion().getPortal());
        direccion.setLocalidad(cliente.getDireccion().getLocalidad());
        direccion.setCp(cliente.getDireccion().getCp());
        direccion.setRegion(cliente.getDireccion().getRegion());
        direccion.setPais(cliente.getDireccion().getPais());

        clienteSesion.setDireccion(direccion);
        clienteSesion.getDireccionEntrega().add(direccion);
        clienteSesion.setTelefonoMovil(cliente.getTelefonoMovil());
        mv.addObject("paso" ,"3");
        return mv;
    }

    @GetMapping("paso3")
    public ModelAndView paso3(@ModelAttribute("cliente") Cliente cliente, HttpSession sesion, BindingResult result) {
        cliente = (Cliente) sesion.getAttribute("cliente");
        if (cliente != null) mv.addObject("cliente", cliente);
        mv.addObject("paso" ,"3");
        return mv;
    }

    @PostMapping("paso3")
    public ModelAndView registrar3(@Validated(DatosCliente.class) @ModelAttribute("cliente") Cliente cliente, BindingResult resultado, HttpSession sesion) {
        if (resultado.hasErrors()) {
            mv.addObject("error", "Por favor, rellene los campos obligatorios");
            return mv;
        }
        Cliente clienteSesion = (Cliente) sesion.getAttribute("cliente");
        if (clienteSesion == null) {
            clienteSesion = new Cliente();
        }

        clienteSesion.setComentarios(cliente.getComentarios());
        clienteSesion.setAceptacionLicencia(cliente.getAceptacionLicencia());

        mv.addObject("paso" ,"4");
        return mv;
    }

    @GetMapping("paso4")
    public ModelAndView resumen(@ModelAttribute("cliente") Cliente cliente, HttpSession sesion, BindingResult result) {

        cliente = (Cliente) sesion.getAttribute("cliente");
        if (cliente == null) {
            cliente = new Cliente();
        }

        //Validar el usaurio para que aparezcan los errores en la vista
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Cliente>> violations = validator.validate(cliente, DatosPersonales.class, DatosContacto.class, DatosCliente.class);
        for(ConstraintViolation<Cliente> violation : violations) {
            String error = violation.getMessage();
            String name = violation.getPropertyPath().toString();
            mv.addObject(name, error);
            System.out.println(name);
            // sesion.setAttribute("error", error);
        }

        mv.addObject("cliente", cliente);
        //paso al modelo los errores para guardarlo en un hidden y ver si se puede registrar en el siguiente paso
        if(!violations.isEmpty()){
            mv.addObject("error", "Hay errores");
        }else{
            mv.addObject("error", "No hay errores");
        }
        mv.addObject("paso" ,"4");
        return mv;
    }

    @PostMapping("paso4")
    public ModelAndView paso4(HttpSession sesion){
        if (mv.getModel().get("error").equals("No hay errores")) {
            mv.setViewName("redirect:/area-cliente");
        }
        return mv;
    }
}
