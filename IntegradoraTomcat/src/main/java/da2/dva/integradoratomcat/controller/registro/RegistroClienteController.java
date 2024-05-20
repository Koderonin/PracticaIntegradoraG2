package da2.dva.integradoratomcat.controller.registro;

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
    @Autowired
    private ServicioColecciones servicioColecciones;

    @Bean
    public void descargarColeccionesRC(){
        servicio.cargarPaises();
        servicio.cargarGeneros();
        servicio.devuelveAdministradores();
        servicio.cargarTiposVia();
        servicio.cargarTiposDocumentos();
        servicio.cargarIdiomas();
    }

    @GetMapping("paso1")
    public ModelAndView registroCliente(@ModelAttribute("cliente") Cliente cliente, HttpSession sesion, BindingResult result) {
        //Recuperamos las paginas visitadas y si es la primera vez iniciamos el contador
        if (sesion.getAttribute("paginas_visitadas") == null)
            sesion.setAttribute("paginas_visitadas", 0);
        //Convertimos el atributo en entero para poder incrementarlo
        int pags_visitadas = (int) sesion.getAttribute("paginas_visitadas");
        sesion.setAttribute("paginas_visitadas", pags_visitadas + 1);
        ModelAndView mv = new ModelAndView();
        if (checkUserSession(sesion)) {
            mv.setViewName("/registro/cliente");
            //Paso de parámetros para resolución de la vista
            mv.addObject("titulo","Registro de cliente");
            mv.addObject("paso" ,"1");
            //Paso de colecciones necesarias
            mv.addObject("listaGeneros", servicio.getGeneros());
            mv.addObject("listaPaises", servicio.getPaises());
            mv.addObject("listaTiposDocumentos", servicio.getTiposDocumentos());
            mv.addObject("listaIdiomas", servicio.getIdiomas());

            //Recuperación de datos de otros pasos
            cliente = (Cliente) sesion.getAttribute("cliente");
            if (cliente != null) mv.addObject("cliente", cliente);
        } else
            mv.setViewName("redirect:/registro/usuario/");

        return mv;
    }

    @PostMapping("paso1")
    public ModelAndView registrar(@Validated(DatosPersonales.class) @ModelAttribute("cliente") Cliente cliente, BindingResult resultado, HttpSession sesion) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(checkUserSession(sesion) ? "/registro/cliente" : "redirect:/registro/usuario");
        mv.addObject("titulo","Registro de cliente");
        //Validación de los datos ingresados
        if (resultado.hasErrors()) {
            mv.addObject("error", "Por favor, rellene los campos obligatorios");
            mv.addObject("paso" ,"1");
            //Paso de colecciones necesarias
            mv.addObject("listaGeneros", servicio.getGeneros());
            mv.addObject("listaPaises", servicio.getPaises());
            mv.addObject("listaTiposDocumentos", servicio.getTiposDocumentos());
            mv.addObject("listaIdiomas", servicio.getIdiomas());

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
        ModelAndView mv = new ModelAndView();
        mv.setViewName(checkUserSession(sesion) ? "/registro/cliente" : "redirect:/registro/usuario/");
        mv.addObject("titulo","Registro de cliente");
        //Paso de parámetros para resolución de la vista
        mv.addObject("paso" ,"2");
        //Paso de colecciones necesarias
        mv.addObject("listaTiposVia", servicio.getTiposVia());
        mv.addObject("listaIdiomas", servicio.getIdiomas());
        //Recuperación de datos de otros pasos
        cliente = (Cliente) sesion.getAttribute("cliente");
        if (cliente != null) mv.addObject("cliente", cliente);

        return mv;
    }

    @PostMapping("paso2")
    public ModelAndView registrar2(@Validated(DatosContacto.class) @ModelAttribute("cliente") Cliente cliente, BindingResult resultado, HttpSession sesion) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(checkUserSession(sesion) ? "/registro/cliente" : "redirect:/registro/usuario");
        mv.addObject("titulo","Registro de cliente");
        if (resultado.hasErrors()) {
            mv.addObject("error", "Por favor, rellene los campos obligatorios");
            mv.addObject("paso" ,"2");
            //Paso de colecciones necesarias
            mv.addObject("listaTiposVia", servicio.getTiposVia());
            mv.addObject("listaIdiomas", servicio.getIdiomas());

            return mv;
        }

        //Recuperación de datos de otros pasos
        Cliente clienteSesion = (Cliente) sesion.getAttribute("cliente");
        if (clienteSesion == null) clienteSesion = new Cliente();

        //Guardado de los datos ingresados
        Direccion direccion = new Direccion();
        direccion.setTipoVia(cliente.getDireccion().getTipoVia());
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
        ModelAndView mv = new ModelAndView();
        mv.addObject("listaIdiomas", servicio.getIdiomas());
        mv.setViewName(checkUserSession(sesion) ? "/registro/cliente" : "redirect:/registro/usuario/");
        mv.addObject("titulo","Registro de cliente");
        //Paso de parámetros para resolución de la vista
        mv.addObject("paso" ,"3");
        //Recuperación de datos de otros pasos
        cliente = (Cliente) sesion.getAttribute("cliente");
        if (cliente != null) mv.addObject("cliente", cliente);

        return mv;
    }

    @PostMapping("paso3")
    public ModelAndView registrar3(@Validated(DatosCliente.class) @ModelAttribute("cliente") Cliente cliente, BindingResult resultado, HttpSession sesion) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(checkUserSession(sesion) ? "/registro/cliente" : "redirect:/registro/usuario/");
        mv.addObject("titulo","Registro de cliente");
        mv.addObject("listaIdiomas", servicio.getIdiomas());
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
        ModelAndView mv = new ModelAndView();
        mv.addObject("listaIdiomas", servicio.getIdiomas());
        mv.addObject("titulo","Registro de cliente");
        mv.setViewName(checkUserSession(sesion) ? "/registro/cliente" : "redirect:/registro/usuario/");
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
        ModelAndView mv = new ModelAndView();
        mv.addObject("listaIdiomas", servicio.getIdiomas());
        mv.addObject("titulo","Registro de cliente");
        //Obtener el cliente de la sesión
        Cliente cliente = (Cliente) sesion.getAttribute("cliente");
        //Si en el paso anterior no hay errores se inserta el cliente
        if (sesion.getAttribute("hayErrores").equals(false)) {
            //VINCULO EL CLIENTE CON EL USUARIO
            UsuarioCliente usuario = (UsuarioCliente)sesion.getAttribute("usuario");
        //    cliente.setUsuarioCliente(usuario);
            servicioCliente.insertarNuevoCliente(cliente);
            mv.setViewName("redirect:/cliente/area-cliente");
            sesion.removeAttribute("usuario"); // ¿¿por qué??

            // no soy fan, pero la comprobación para quedarse en el area del cliente la hace con el usuario, así que si
            // se tiene que volver a loguear por segunda vez, que pida el mail, no la contraseña, no?
            /* A este respecto, propongo una de las siguientes, de más fav a menos fav:
            * - Mantener en sesión tanto usuario como cliente (área cliente chequea que haya cliente en sesión; en el login se mete cliente en sesión)
            * - No eliminar usuario AQUÍ, sí eliminar cliente (área cliente chequea que haya usuario en sesión; el login se queda igual)
            *   Cualquiera de las dos hace que no haya que loguearse de nuevo
            */
            // La ñapa siguiente es para que no mande al paso 2 del login, sino al "1"
            sesion.removeAttribute("email");
        } else {
            //Redirigir al getMapping del paso 4
            mv.setViewName("redirect:paso4");
        }

        return mv;
    }
    // Métodos auxiliares

    private Boolean checkUserSession(HttpSession sesion) {
        return sesion.getAttribute("usuario") != null;
    }
}



