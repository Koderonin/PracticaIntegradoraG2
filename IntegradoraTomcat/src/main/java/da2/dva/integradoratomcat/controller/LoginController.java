package da2.dva.integradoratomcat.controller;

import da2.dva.integradoratomcat.model.entities.Cliente;
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
        //Limpiamos el error para que no aparezca cuando se recarga la página
        mv.addObject("error",null);
        mv.addObject("errorClave",null);
        mv.addObject("titulo","Login de usuario");
        mv.addObject("paso" ,"1");
        sesion.removeAttribute("email");
        if(sesion.getAttribute("usuario")!=null && sesion.getAttribute("clave")!=null){
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
            //limpiamos el error para que no aparezca cuando se recarga la página
            mv.addObject("errorUsuario",null);
        }
        return mv;
    }

    @GetMapping("paso2")
    public ModelAndView clave(HttpSession sesion) {
        //Si no se ha introducido un email mandamos un error para que el usuario lo introduzca
        if(sesion.getAttribute("email")==null){
            mv.addObject("errorUsuario","Debe introducir su email");
            mv.addObject("paso" ,"1");
            return mv;
        }
        //Si se ha enviado un error desde el area de cliente lo mostramos
        if(sesion.getAttribute("errorClave")!=null){
            mv.addObject("errorClave",sesion.getAttribute("errorClave"));
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
    public ModelAndView clave(@RequestParam("clave") String clave, HttpSession sesion) {
        String email = (String) sesion.getAttribute("email");
        UsuarioCliente usuario = servicioUsuario.devuelveUsuarios().get(email);
        sesion.setAttribute("usuario",usuario);//TODO: REVISAR SI ES NECESARIO

        Boolean passCheck = usuario.getClave().equals(clave);
        // esto ahora mismo sólo carga los usuarios clientes!!!!!!!!!!!
        if(passCheck){ //Si la clave es correcta se redirecciona a la area de cliente y se guarda en la sesión
            //sesion.setAttribute("usuario", email);
            if(servicioCliente.getClienteByUsuario(usuario)!=null) {
                mv.setViewName("redirect:/area-cliente");
            }else{
                Cliente cliente = new Cliente();
                cliente.setUsuarioCliente(usuario);
                mv.setViewName("redirect:/registro/cliente/paso1");
            }
            //ACTUALIZAR EN LA BBDD EL NUMERO DE CONEXIONES EXITOSAS DE ESTE USUARIO
            servicioUsuario.actualizarNumAccesos(usuario);
        }else{
            mv.addObject("error","La clave no es correcta");
            mv.addObject("errorClave",null);
        }
        return mv;
    }





}
