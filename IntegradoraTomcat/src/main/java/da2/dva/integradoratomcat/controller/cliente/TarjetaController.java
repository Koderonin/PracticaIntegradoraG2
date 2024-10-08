package da2.dva.integradoratomcat.controller.cliente;

import da2.dva.integradoratomcat.model.auxiliar.TarjetaCredito;
import da2.dva.integradoratomcat.model.entities.Cliente;
import da2.dva.integradoratomcat.services.ServicioCliente;
import da2.dva.integradoratomcat.services.ServicioColecciones;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cliente")
public class TarjetaController {
    @Autowired
    ServicioCliente servicioCliente;
    @Autowired
    ServicioColecciones servicio;

    @Bean
    public void descargarColeccionesTC(){
        servicio.cargarTiposTarjeta();
        servicio.cargarIdiomas();
    }

    @GetMapping("/nueva-tarjeta")
    public ModelAndView agregarTarjeta(@ModelAttribute("tarjeta") TarjetaCredito tarjeta, HttpSession sesion){
        ModelAndView mv = new ModelAndView("/cliente/nueva-tarjeta");
        //Recuperamos las paginas visitadas y si es la primera vez iniciamos el contador
        if (sesion.getAttribute("paginas_visitadas") == null)
            sesion.setAttribute("paginas_visitadas", 0);
        //Convertimos el atributo en entero para poder incrementarlo
        int pags_visitadas = (int) sesion.getAttribute("paginas_visitadas");
        sesion.setAttribute("paginas_visitadas", pags_visitadas + 1);
        mv.addObject("visitas", pags_visitadas);
        mv.addObject("listaTiposTarjeta", servicio.getTiposTarjeta());
        mv.addObject("listaIdiomas", servicio.getIdiomas());

        return mv;
    }
    @PostMapping("/nueva-tarjeta")
    public ModelAndView insertarTarjeta(@Valid @ModelAttribute("tarjeta") TarjetaCredito tarjeta, HttpSession sesion, BindingResult resultado){
        ModelAndView mv = new ModelAndView("/cliente/nueva-tarjeta");
        /*if(resultado.hasErrors()){
            mv.addObject("error","Por favor, rellene los campos obligatorios");
            if (sesion.getAttribute("paginas_visitadas") == null)
                sesion.setAttribute("paginas_visitadas", 0);
            //Convertimos el atributo en entero para poder incrementarlo
            int pags_visitadas = (int) sesion.getAttribute("paginas_visitadas");
            sesion.setAttribute("paginas_visitadas", pags_visitadas + 1);
            mv.addObject("visitas", pags_visitadas);
            mv.addObject("listaTiposTarjeta", servicio.getTiposTarjeta());
            mv.addObject("listaIdiomas", servicio.getIdiomas());
            return mv;
        }*/
        try {
            servicioCliente.agregarTarjeta((Cliente) sesion.getAttribute("cliente"), tarjeta);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mv.setViewName("redirect:http://apache.da2.dva/cliente/area-cliente.html");
        return mv;
    }
}
