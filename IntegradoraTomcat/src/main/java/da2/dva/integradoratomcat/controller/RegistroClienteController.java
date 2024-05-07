package da2.dva.integradoratomcat.controller;

import da2.dva.integradoratomcat.model.auxiliar.Direccion;
import da2.dva.integradoratomcat.model.entities.Cliente;
import da2.dva.integradoratomcat.model.entities.UsuarioCliente;
import da2.dva.integradoratomcat.services.ServicioCliente;
import da2.dva.integradoratomcat.services.ServicioColecciones;
import da2.dva.integradoratomcat.services.ServicioUsuario;
import da2.dva.integradoratomcat.utils.DatosCliente;
import da2.dva.integradoratomcat.utils.DatosContacto;
import da2.dva.integradoratomcat.utils.DatosPersonales;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Controller
@RequestMapping("registro/cliente")
public class RegistroClienteController {
    @Autowired
    ServicioColecciones servicio;
    @Autowired
    ServicioUsuario servicioUsuario;
    @Autowired
    ServicioCliente servicioCliente;
    ModelAndView mv = new ModelAndView("/registro/cliente");

    @Bean
    public void descargarColeccionesRC(){
        servicio.cargarPaises();
        servicio.cargarGeneros();
        servicio.devuelveAdministradores();
        servicio.cargarTiposVia();
        servicio.cargarTiposDocumentos();
    }

    @GetMapping("paso1")
    public ModelAndView registroCliente(@ModelAttribute("cliente") Cliente cliente, HttpSession sesion, BindingResult result) {
        ModelAndView mv = new ModelAndView("/registro/cliente");
        //Paso de parámetros para resolución de la vista
        mv.addObject("titulo","Registro de cliente");
        mv.addObject("paso" ,"1");
        //Paso de colecciones necesarias
        mv.addObject("listaGeneros", servicio.getGeneros());
        mv.addObject("listaPaises", servicio.getPaises());
        mv.addObject("listaTiposDocumentos", servicio.getTiposDocumentos());
        //Recuperación de datos de otros pasos
        cliente = (Cliente) sesion.getAttribute("cliente");
        if (cliente != null) mv.addObject("cliente", cliente);

        return mv;
    }

    @PostMapping("paso1")
    public ModelAndView registrar(@Validated(DatosPersonales.class) @ModelAttribute("cliente") Cliente cliente, BindingResult resultado, HttpSession sesion) {
        ModelAndView mv = new ModelAndView("/registro/cliente");
        //Validación de los datos ingresados
        if (resultado.hasErrors()) {
            mv.addObject("error", "Por favor, rellene los campos obligatorios");
            mv.addObject("paso" ,"1");
            //Paso de colecciones necesarias
            mv.addObject("listaGeneros", servicio.getGeneros());
            mv.addObject("listaPaises", servicio.getPaises());
            mv.addObject("listaTiposDocumentos", servicio.getTiposDocumentos());

            return mv;
        }
        //Recuperación de datos de otros pasos
        Cliente clienteSesion = (Cliente) sesion.getAttribute("cliente");
        if (clienteSesion == null) clienteSesion = new Cliente();
        //Guardado de los datos ingresados
        clienteSesion.setNombre(cliente.getNombre());
        clienteSesion.setApellidos(cliente.getApellidos());
        clienteSesion.setGenero(cliente.getGenero());
        clienteSesion.setFechaNacimiento(cliente.getFechaNacimiento());
        clienteSesion.setPaisNacimiento(cliente.getPaisNacimiento());
        clienteSesion.setTipoDocumento(cliente.getTipoDocumento());
        clienteSesion.setDocumento(cliente.getDocumento());

        sesion.setAttribute("cliente", clienteSesion);
        //Paso de parámetros para resolución de la vista
        mv.addObject("paso" ,"2");
        mv.setViewName("redirect:/registro/cliente/paso2");
        return mv;
    }

    @GetMapping("paso2")
    public ModelAndView paso2(@ModelAttribute("cliente") Cliente cliente, HttpSession sesion, BindingResult result) {
        ModelAndView mv = new ModelAndView("/registro/cliente");
        //Paso de parámetros para resolución de la vista
        mv.addObject("paso" ,"2");
        //Paso de colecciones necesarias
        mv.addObject("listaTiposVia", servicio.getTiposVia());
        //Recuperación de datos de otros pasos
        cliente = (Cliente) sesion.getAttribute("cliente");
        if (cliente != null) mv.addObject("cliente", cliente);

        return mv;
    }

    @PostMapping("paso2")
    public ModelAndView registrar2(@Validated(DatosContacto.class) @ModelAttribute("cliente") Cliente cliente, BindingResult resultado, HttpSession sesion) {
        ModelAndView mv = new ModelAndView("/registro/cliente");
        if (resultado.hasErrors()) {
            mv.addObject("error", "Por favor, rellene los campos obligatorios");
            mv.addObject("paso" ,"2");
            //Paso de colecciones necesarias
            mv.addObject("listaTiposVia", servicio.getTiposVia());

            return mv;
        }

        //Recuperación de datos de otros pasos
        Cliente clienteSesion = (Cliente) sesion.getAttribute("cliente");
        if (clienteSesion == null) clienteSesion = new Cliente();

        //Guardado de los datos ingresados
        Direccion direccion = new Direccion();
        direccion.setTipo_via(cliente.getDireccion().getTipo_via());
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

        sesion.setAttribute("cliente", clienteSesion);
        //Paso de parámetros para resolución de la vista
        mv.addObject("paso" ,"3");
        mv.setViewName("redirect:/registro/cliente/paso3");
        return mv;
    }

    @GetMapping("paso3")
    public ModelAndView paso3(@ModelAttribute("cliente") Cliente cliente, HttpSession sesion, BindingResult result) {
        ModelAndView mv = new ModelAndView("/registro/cliente");
        //Paso de parámetros para resolución de la vista
        mv.addObject("paso" ,"3");
        //Recuperación de datos de otros pasos
        cliente = (Cliente) sesion.getAttribute("cliente");
        if (cliente != null) mv.addObject("cliente", cliente);

        return mv;
    }

    @PostMapping("paso3")
    public ModelAndView registrar3(@Validated(DatosCliente.class) @ModelAttribute("cliente") Cliente cliente, BindingResult resultado, HttpSession sesion) {
        ModelAndView mv = new ModelAndView("/registro/cliente");
        //Validación de los datos ingresados
        if (resultado.hasErrors()) {
            mv.addObject("error", "Por favor, rellene los campos obligatorios");
            mv.addObject("paso" ,"3");

            return mv;
        }
        //Recuperación de datos de otros pasos
        Cliente clienteSesion = (Cliente) sesion.getAttribute("cliente");
        if (clienteSesion == null) clienteSesion = new Cliente();

        //Guardado de los datos ingresados
        clienteSesion.setComentarios(cliente.getComentarios());
        clienteSesion.setAceptacionLicencia(cliente.getAceptacionLicencia());

        sesion.setAttribute("cliente", clienteSesion);
        //Paso de parámetros para resolución de la vista
        mv.addObject("paso" ,"4");
        mv.setViewName("redirect:/registro/cliente/paso4");
        return mv;
    }

    @GetMapping("paso4")
    public ModelAndView resumen(@ModelAttribute("cliente") Cliente cliente, HttpSession sesion, BindingResult result) {
        //TODO: REVISAR CÓDIGO POR SI SE PUEDE MEJORAR, ES MU LARGO

        //Creación de ModelAndView
        ModelAndView mv = new ModelAndView("/registro/cliente");
        //Recuperación de datos de otros pasos
        cliente = (Cliente) sesion.getAttribute("cliente");
        //Si el cliente es nulo, se crea uno
        if (cliente == null) cliente = new Cliente();
        mv.addObject("cliente", cliente);

        //Creamos una fabrica de validaciiones y un validador a partir de ella
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        // Se realiza la validación del objeto cliente, aplicando restricciones de los diferentes grupos
        Set<ConstraintViolation<Cliente>> violations = validator.validate(cliente, DatosPersonales.class, DatosContacto.class, DatosCliente.class);
        //Para cada violación se crea un error y se agrega al modelo
        for(ConstraintViolation<Cliente> violation : violations) {
            String error = violation.getMessage();
            String name = violation.getPropertyPath().toString();
            mv.addObject(name, error);
        }

        //Si el cliente no tiene una dirección, se crea una nueva
        if(cliente.getDireccion()==null) cliente.setDireccion(new Direccion());

        //Hacemos lo mismo para la dirección
        Set<ConstraintViolation<Direccion>> violationDir = validator.validate(cliente.getDireccion(), DatosContacto.class);
        for(ConstraintViolation<Direccion> violation : violationDir) {
            String error = violation.getMessage();
            String name = violation.getPropertyPath().toString();
            mv.addObject(name,  error);
        }

        //Paso de parámetros para resolución de la vista
        mv.addObject("paso" ,"4");

        //Si violations y violationDir NO están vacías, hay errores
        if (!violations.isEmpty() && !violationDir.isEmpty()) {
            sesion.setAttribute("hayErrores", true);
            mv.addObject("error", "Hay errores");
        } else {
            sesion.setAttribute("hayErrores", false);
            //paso al modelo los errores para guardarlo en un hidden y ver si se puede registrar en el siguiente paso
            mv.addObject("error", "No hay errores");
            sesion.setAttribute("cliente", cliente);
        }

        return mv;
    }

    @PostMapping("paso4")
    public ModelAndView paso4( HttpSession sesion) {
        //Obtener el cliente de la sesión
        Cliente cliente = (Cliente) sesion.getAttribute("cliente");
        //Si en el paso anterior no hay errores se inserta el cliente
        if (sesion.getAttribute("hayErrores").equals(false)) {
            //VINCULO EL CLIENTE CON EL USUARIO
             UsuarioCliente usuario = (UsuarioCliente)sesion.getAttribute("usuario");
            cliente.setUsuarioCliente(usuario);
            servicioCliente.insertarNuevoCliente(cliente);
            mv.setViewName("redirect:/area-cliente");
            sesion.removeAttribute("usuario");
        } else {
            //Redirigir al getMapping del paso 4
            mv.setViewName("redirect:paso4");
        }
        return mv;
    }
}
