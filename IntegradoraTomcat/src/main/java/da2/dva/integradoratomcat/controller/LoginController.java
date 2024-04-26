package da2.dva.integradoratomcat.controller;

import da2.dva.integradoratomcat.model.entities.Usuario;
import da2.dva.integradoratomcat.services.Servicio;
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

    @Autowired
    Servicio servicio;

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
        if(sesion.getAttribute("OBJusuario")!=null){
            sesion.setAttribute("email", email);
            //mv.addObject("paso" ,"2");
            mv.setViewName("redirect:/login/paso2");

        }else{
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
    public ModelAndView clave(@RequestParam("clave") String clave,HttpSession sesion) {
        String email = (String) sesion.getAttribute("usuario");
        Usuario usuario = (Usuario) sesion.getAttribute("OBJusuario");
        if(usuario.getClave().equals(clave)){
            sesion.setAttribute("usuario",email);
            mv.setViewName("redirect:/area-cliente");
            //TODO: METER EN LA BBD
        }else{
            mv.addObject("error","La clave no es correcta");
        }
        return mv;
    }





}
