package da2.dva.integradoratomcat.controller;

import da2.dva.integradoratomcat.model.entities.Usuario;
import da2.dva.integradoratomcat.model.entities.UsuarioCliente;
import da2.dva.integradoratomcat.services.Servicio;
import da2.dva.integradoratomcat.services.ServicioCliente;
import da2.dva.integradoratomcat.services.ServicioUsuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("login")
public class LoginController {
    ModelAndView mv = new ModelAndView("/login/login");

    //@Autowired
    //Servicio servicio;
    @Autowired
    ServicioUsuario servicioUsuario;

    @Autowired
    ServicioCliente servicioCliente;

    @GetMapping("paso1")
    public ModelAndView login(HttpSession sesion) {
        mv.addObject("titulo","Login de usuario");
        mv.addObject("paso" ,"1");
        System.out.println(sesion.getAttribute("email"));
        sesion.removeAttribute("email");
        if(sesion.getAttribute("email")!=null){
            mv.setViewName("redirect:/area-cliente");
        }
        return mv;
    }


    @PostMapping("paso1")
    public ModelAndView login(@RequestParam("usuario") String email, HttpSession sesion) {
        /*for(Usuario usuario : servicio.devuelveUsuarios())  {
            if(email.equals(usuario.getEmail())){
                sesion.setAttribute("OBJusuario", usuario); //TODO: REVISAR
                sesion.setAttribute("email",email);
                break;
            }
        }*/

        // esto ahora mismo sólo chequea vs lista de UsuarioCliente!!
        if(servicioUsuario.devuelveUsuarios().containsKey(email)){
           if (servicioUsuario.devuelveUsuarios().get(email).getFechaBloqueo() != null) {
               mv.addObject("error", "Este usuario se encuentra bloqueado");
               return mv;
           }
           sesion.setAttribute("email", email);
           mv.addObject("paso" ,"2");
          //  mv.setViewName("redirect:/login/paso2");

        }else{
            sesion.removeAttribute("email");
            mv.addObject("error","El usuario no existe");
        }
        // añadir el objeto en el else al mv hace que se quede ahí pa los restos; si te equivocas al meter el mail, ya siempre pone eso
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
    public ModelAndView clave(@RequestParam("clave") String clave, HttpSession sesion) {
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
            mv.setViewName("redirect:/area-cliente");

            //ACTUALIZAR EN LA BBDD EL NUMERO DE CONEXIONES EXITOSAS DE ESTE USUARIO
            servicioUsuario.actualizarNumAccesos(usuario);
        }else{
            mv.addObject("error","La clave no es correcta");
        }
        return mv;
    }





}
