package da2.dva.integradoratomcat.controller;

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

import java.util.Map;

@Controller
@RequestMapping("login")
public class LoginController {
    ModelAndView mv = new ModelAndView("/login/login");

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
        mv.addObject("titulo","Login de usuario");
        mv.addObject("paso" ,"1");
        sesion.removeAttribute("email");
        if(sesion.getAttribute("email")!=null){
            mv.setViewName("redirect:/area-cliente");
        }
        return mv;
    }


    @PostMapping("paso1")
    public ModelAndView login(@RequestParam("usuario") String email, HttpSession sesion) {

        // esto ahora mismo sólo chequea vs lista de UsuarioCliente!!
        if(servicioUsuario.devuelveUsuarios().containsKey(email)){
           if (servicioUsuario.devuelveUsuarios().get(email).getFechaBloqueo() != null) {
               mv.addObject("error","Usuario bloqueado hasta " + servicioUsuario.devuelveUsuarios().get(email).getFechaBloqueo());
           } else {
               sesion.setAttribute("email", email);
               mv.addObject("paso" ,"2");
               mv.setViewName("redirect:/login/paso2");
           }
        } else {
            sesion.removeAttribute("email");
            mv.addObject("error","El usuario no existe");
        }
        return mv;
    }

    @GetMapping("paso2")
    public ModelAndView clave() {
        mv.addObject("titulo","Login de usuario");
        mv.setViewName("login/login");
        mv.addObject("paso" ,"2");
        return mv;
    }

    @PostMapping("paso2")
    public ModelAndView clave(@RequestParam("clave") String clave, HttpSession sesion,
                              @CookieValue(name ="accesosUsuarios", defaultValue ="none" )String contenidoCookie) {
        String email = (String) sesion.getAttribute("email");
        // Quizá sería interesante en este punto vincular a la sesión el objeto de usuario, o al menos crearlo aquí pa trabajar con él?
        UsuarioCliente usuario = servicioUsuario.devuelveUsuarios().get(email);
        // sesion.setAttribute("usuario", usuario);
        Boolean passCheck = usuario.getClave().equals(clave);
        System.out.println("CLAVE CORRECTA: " + passCheck);
        // esto ahora mismo sólo carga los usuarios clientes!!
        if(passCheck){
            // no habría que cargar el objeto de cliente como tal? Pa no estar haciendo queries luego a sus datos. Algo como:
            // sesion.setAttribute("cliente", servicioCliente.getClienteByUsuario(servicioUsuario.devuelveUsuarios().get(email)));
            sesion.setAttribute("usuario", email);
            // TODO: Añadir query JPA para comprobar si el usuario tiene un cliente vinculado
//            if(servicio.getUsuarios().get(email).getId_usuario()!=null) {
            mv.setViewName("redirect:/area-cliente");

//            } else {
//                mv.setViewName("redirect:/registro/cliente/paso1");
//            }

            //ACTUALIZAR EN LA BBDD EL NUMERO DE CONEXIONES EXITOSAS DE ESTE USUARIO
            servicioUsuario.actualizarNumAccesos(usuario);
        } else {
            mv.addObject("error","La clave no es correcta");
        }
        return mv;
    }





}
