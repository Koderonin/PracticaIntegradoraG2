package da2.dva.integradoratomcat.controller;

import da2.dva.integradoratomcat.services.Servicio;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {
    ModelAndView mv = new ModelAndView("/login/login");

    @Autowired
    Servicio servicio;
    @Bean
    public void conseguirColecciones(){
        mv.addObject("listaUsuarios",servicio.devuelveUsuarios());
    }

    @GetMapping("paso1")
    public ModelAndView login(HttpSession sesion) {
        mv.addObject("titulo","Login de usuario");
        mv.addObject("paso" ,"1");
        if(!sesion.isNew()){
            mv.setViewName("redirect:/area-cliente");
        }
        return mv;
    }


    @PostMapping("paso1")
    public ModelAndView login(@RequestAttribute("usario") String email,HttpSession sesion) {
        if(servicio.devuelveUsuarios().containsKey(email)){
            sesion.setAttribute("email",email);
            mv.addObject("paso" ,"2");
        }else{
            mv.addObject("error","El usuario no existe");
        }
        return mv;
    }

    @GetMapping("paso2")
    public ModelAndView clave() {
        mv.addObject("titulo","Login de usuario");
        mv.addObject("paso" ,"2");
        return mv;
    }

    @PostMapping("paso2")
    public ModelAndView clave(@RequestAttribute("clave") String clave,HttpSession sesion) {
        String email = (String) sesion.getAttribute("usuario");
        if(servicio.devuelveUsuarios().get(email).getClave().equals(clave)){
            sesion.setAttribute("usuario",email);
            mv.setViewName("redirect:/area-cliente");
        }else{
            mv.addObject("error","La clave no es correcta");
        }
        return mv;
    }





}
