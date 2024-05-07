package da2.dva.integradoratomcat.controller;

import da2.dva.integradoratomcat.model.entities.UsuarioCliente;
import da2.dva.integradoratomcat.services.ServicioCliente;
import da2.dva.integradoratomcat.services.ServicioColecciones;
import da2.dva.integradoratomcat.services.ServicioUsuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("paso1")
    public ModelAndView login(HttpSession sesion) {
        mv.addObject("titulo","Login de usuario");
        mv.addObject("paso" ,"1");
        sesion.removeAttribute("email");
        if(sesion.getAttribute("email")!=null && sesion.getAttribute("clave")!=null){
            mv.setViewName("redirect:/area-cliente");
        }
        return mv;
    }


    @PostMapping("paso1")
    public ModelAndView login(@RequestParam("usuario") String email, HttpSession sesion) {

        // esto ahora mismo sólo chequea vs lista de UsuarioCliente!!
        if(servicioUsuario.devuelveUsuarios().containsKey(email)){
            //Si el usuario tiene fecha de bloqueo mandamos un error
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
    public ModelAndView clave(HttpSession sesion) {
        /*
        if(sesion.getAttribute("email")==null){
            mv.addObject("errorGlobal","Debe introducir su email");
            mv.addObject("paso" ,"1");
            mv.setViewName("redirect:/login/paso1");
        }

         */
        mv.addObject("titulo","Login de usuario");
        mv.setViewName("login/login");
        mv.addObject("paso" ,"2");
        return mv;
    }

    @PostMapping("paso2")
    public ModelAndView clave(@RequestParam("clave") String clave, HttpSession sesion) {
        String email = (String) sesion.getAttribute("email");
        // Quizá sería interesante en este punto vincular a la sesión el objeto de usuario, o al menos crearlo aquí pa trabajar con él?
        UsuarioCliente usuario = servicioUsuario.devuelveUsuarios().get(email);
        sesion.setAttribute("usuario",usuario);//TODO: REVISAR SI ES NECESARIO

        Boolean passCheck = usuario.getClave().equals(clave);
        // esto ahora mismo sólo carga los usuarios clientes!!!!!!!!!!!
        if(passCheck){
            sesion.setAttribute("usuario", email);
            if(servicioCliente.getClienteByUsuario(usuario)!=null) {
                mv.setViewName("redirect:/area-cliente");
            }else{
                mv.setViewName("redirect:/registro/cliente/paso1");
            }
            //ACTUALIZAR EN LA BBDD EL NUMERO DE CONEXIONES EXITOSAS DE ESTE USUARIO
            servicioUsuario.actualizarNumAccesos(usuario);
        } else {
            mv.addObject("error","La clave no es correcta");
        }
        return mv;
    }





}
