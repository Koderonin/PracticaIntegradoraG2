package da2.dva.integradoratomcat.controller;

import da2.dva.integradoratomcat.services.ServicioColecciones;
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
    ServicioColecciones servicio;

    @GetMapping("paso1")
    public ModelAndView login(HttpSession sesion) {
        mv.addObject("titulo","Login de usuario");
        mv.addObject("paso" ,"1");
        sesion.removeAttribute("email");
        if(sesion.getAttribute("email") != null){
            mv.setViewName("redirect:/area-cliente");
        }
        return mv;
    }


    @PostMapping("paso1")
    public ModelAndView login(@RequestParam("usuario") String email, HttpSession sesion) {

        if(servicio.getUsuarios().containsKey(email)){
            if (servicio.getUsuarios().get(email).getFechaBloqueo()!=null){
                mv.addObject("error","Usuario bloqueado hasta " + servicio.getUsuarios().get(email).getFechaBloqueo());
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
    public ModelAndView clave(@RequestParam("clave") String clave, HttpSession sesion) {
        String email = (String) sesion.getAttribute("email");
        if(servicio.getUsuarios().get(email).getClave().equals(clave)){
            sesion.setAttribute("email", email);
            // TODO: Añadir query JPA para comprobar si el usuario tiene un cliente vinculado
//            if(servicio.getUsuarios().get(email).getId_usuario()!=null) {
                mv.setViewName("redirect:/area-cliente");
//            } else {
//                mv.setViewName("redirect:/registro/cliente/paso1");
//            }
        } else {
            mv.addObject("error","La clave no es correcta");
        }
        return mv;
    }





}
