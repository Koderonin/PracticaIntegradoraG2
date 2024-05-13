package da2.dva.integradoratomcat.controller;

import da2.dva.integradoratomcat.model.entities.Cliente;
import da2.dva.integradoratomcat.model.entities.Usuario;
import da2.dva.integradoratomcat.model.entities.UsuarioCliente;
import da2.dva.integradoratomcat.services.ServicioCliente;
import da2.dva.integradoratomcat.services.ServicioColecciones;
import da2.dva.integradoratomcat.services.ServicioCookie;
import da2.dva.integradoratomcat.services.ServicioUsuario;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

import java.util.Map;

@Controller
@RequestMapping("login")
public class LoginController {
    // OJO: mv es un atributo de la instancia de LoginController; si luego vuelves a ella y no reasignas el valor de mv,
    // te dirige a la última vista que te enviara (por ej. vas a área cliente, cierras sesión, vas a paso 1 y te manda a
    // área cliente de nuevo)
    ModelAndView mv = new ModelAndView("/login/login");

    int intentos;

    @Autowired
    ServicioUsuario servicioUsuario;

    @Autowired
    ServicioCliente servicioCliente;

    @Autowired
    ServicioColecciones servicio;

    @Autowired
    ServicioCookie servicioCookie;

    @GetMapping("paso1")
    public ModelAndView login(HttpSession sesion) {
        //Limpiamos el error para que no aparezca cuando se recarga la página
        mv.addObject("error",null);
        mv.addObject("errorClave",null);
        sesion.removeAttribute("email");
        /* Aquí comprobamos si ya hay un usuario en sesion, y de dónde viene este usuario:
        * si viene de area-cliente, redirigimos al area-cliente
        * si viene de registro-usuario, redirigimos al registro-cliente (no tiene Cliente asociado)
        * */
        if(sesion.getAttribute("usuario")!=null){
            // si ya hay un usuario en sesión y no tiene Cliente asociado, redirigimos al registro de cliente
            if (servicioCliente.getClienteByUsuario((UsuarioCliente) sesion.getAttribute("usuario")) == null) {
                mv.setViewName("redirect:/registro/cliente/paso1");
                return mv;
            }
            // si ya hay un usuario en sesion y tiene Cliente asociado, redirigimos al area-cliente
            mv.setViewName("redirect:/area-cliente");
            return mv;
        }
        mv.addObject("titulo","Login de usuario");
        mv.addObject("paso" ,"1");
        mv.setViewName("/login/login");
        return mv;
    }


    @PostMapping("paso1")
    public ModelAndView login(@RequestParam("usuario") String email, HttpSession sesion) {
        // esto ahora mismo sólo chequea vs lista de UsuarioCliente!!
        if(servicioUsuario.devuelveUsuarios().containsKey(email)){
            //Si el usuario tiene fecha de bloqueo mandamos un error
            UsuarioCliente usuario = servicioUsuario.devuelveUsuarios().get(email);
           if (usuario.getFechaBloqueo() != null) {
               mv.addObject("error","Usuario bloqueado hasta " + usuario.getFechaBloqueo());
           } else {
               sesion.setAttribute("email", email);
               mv.addObject("paso" ,"2");
               mv.setViewName("redirect:/login/paso2");
           }
        } else {
            sesion.removeAttribute("email");
            mv.addObject("error","El usuario no existe");
            //limpiamos el error para que no aparezca cuando se recarga la página
            mv.addObject("errorUsuario",null);
        }
        return mv;
    }

    @GetMapping("paso2")
    public ModelAndView clave(HttpSession sesion) {
        intentos = 0;
        if(sesion.getAttribute("usuario")!=null){
            mv.setViewName("redirect:/area-cliente");
            return mv;
        }
        //Si no se ha introducido un email mandamos un error para que el usuario lo introduzca y devolvemos a paso 1
        if(sesion.getAttribute("email")==null){
            mv.setViewName("/login/login");
            mv.addObject("error",null);
            mv.addObject("errorUsuario","Debe introducir su email");
            mv.addObject("paso" ,"1");
            return mv;
        }
        //Si se ha enviado un error desde el area de cliente lo mostramos : no es un error desde el paso 2? Fallo de clave?
        if(sesion.getAttribute("errorLogin")!=null){
            mv.addObject("errorLogin",sesion.getAttribute("errorClave"));
        }
        //Limpiamos  errores para que no aparezcan cuando se recarga la página        sesion.removeAttribute("errorClave");
        mv.addObject("error",null);
        mv.addObject("errorUsuario",null);
        //Paso de parámetros para resolución de la vista
        mv.addObject("titulo","Login de usuario");
        mv.setViewName("login/login");
        mv.addObject("paso" ,"2");
        return mv;
    }

    @PostMapping("paso2")
    public ModelAndView clave(@RequestParam("clave") String clave, HttpSession sesion,
                              @CookieValue(name ="accesosUsuarios", defaultValue ="none" )String contenidoCookie) {
        String email = (String) sesion.getAttribute("email");
        UsuarioCliente usuario = servicioUsuario.devuelveUsuarios().get(email);

        Boolean passCheck = usuario.getClave().equals(clave);
        if (usuario.getFechaBloqueo() != null && LocalDateTime.now().toLocalDate().isAfter(usuario.getFechaBloqueo())) {
            servicioUsuario.actualizarFechaBloqueo(usuario, null);
        }
        if (intentos < 3 && usuario.getFechaBloqueo() == null) {
            if(passCheck){ //Si la clave es correcta se redirecciona a la area de cliente y se guarda en la sesión
                sesion.setAttribute("usuario", usuario);
                Cliente cliente = servicioCliente.getClienteByUsuario(usuario);
                if(cliente!=null) {
                    mv.setViewName("redirect:/area-cliente");
                }else{
                    Cliente nuevoCliente = new Cliente();
                    nuevoCliente.setUsuarioCliente(usuario);
                    mv.setViewName("redirect:/registro/cliente/paso1");
                }
                //ACTUALIZAR EN LA BBDD EL NUMERO DE CONEXIONES EXITOSAS DE ESTE USUARIO
                servicioUsuario.actualizarNumAccesos(usuario);
            } else {
                mv.addObject("error","La clave no es correcta");
                mv.addObject("errorClave",null);
                intentos++;
                if (intentos == 3) {
                    servicioUsuario.actualizarFechaBloqueo(usuario, LocalDateTime.now().plusMinutes(15).toLocalDate());
                }
                mv.setViewName("/login/login");
                mv.addObject("paso" ,"2");
            }
        } else {
            intentos = 0;
            mv.addObject("bloqueo", null);
            mv.addObject("bloqueo", "El usuario se encuentra bloqueado");
        }
        return mv;
    }





}
