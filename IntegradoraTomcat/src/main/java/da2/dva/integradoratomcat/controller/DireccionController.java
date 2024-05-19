package da2.dva.integradoratomcat.controller;

import da2.dva.integradoratomcat.model.auxiliar.Direccion;
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
@RequestMapping("/cliente/direccion")
public class DireccionController {
    @Autowired
    ServicioCliente servicioCliente;
    @Autowired
    ServicioColecciones servicio;

    @Bean
    public void descargarColeccionesDIR(){
        servicio.cargarTiposVia();
        servicio.cargarIdiomas();
    }
    ModelAndView mv = new ModelAndView("/add-direccion");


    @GetMapping("/add-direccion")
    public ModelAndView agregarDireccion(@ModelAttribute("direccion") Direccion direccion, HttpSession sesion){
        //Recuperamos las paginas visitadas y si es la primera vez iniciamos el contador
        if (sesion.getAttribute("paginas_visitadas") == null)
            sesion.setAttribute("paginas_visitadas", 0);
        //Convertimos el atributo en entero para poder incrementarlo
        int pags_visitadas = (int) sesion.getAttribute("paginas_visitadas");
        sesion.setAttribute("paginas_visitadas", pags_visitadas + 1);
        mv.addObject("visitas", pags_visitadas);
        mv.addObject("listaTiposVia",servicio.getTiposVia());
        mv.addObject("listaIdiomas", servicio.getIdiomas());

        return mv;
    }

    @PostMapping("/add-direccion")
    public ModelAndView insertarTarjeta(@Valid @ModelAttribute("direccion") Direccion direccion, HttpSession sesion,
                                        BindingResult result){
        if(result.hasErrors()){
            mv.addObject("error","Por favor, rellene los campos obligatorios");
            return mv;
        }
        //servicioCliente.agregarTarjeta((Cliente) sesion.getAttribute("cliente"), tarjeta);
        servicioCliente.agregarDireccion((Cliente) sesion.getAttribute("cliente"), direccion);
        mv.setViewName("redirect:http://apache.da2.dva/area-cliente.html");
        return mv;
    }

}
