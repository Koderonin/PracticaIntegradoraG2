package da2.dva.integradoratomcat.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ServicioCookie {

    public void actualizaOCreaCookieUsuarios( HttpServletResponse response, String email, String accesosUsuario) {
        // LLamamos al método que deserializa el valor de la cookie y lo guardamos en un mapa
        Map<String, Integer> accesos = deserializar(accesosUsuario);

        // Actualizamos el conteo cogiendo del mapa los accesos que corresponda al email y le sumamos uno , tomando por defecto el valor 0
        //en caso de que no exista
        int conteoActual = accesos.getOrDefault(email, 0) + 1;
        accesos.put(email, conteoActual);

        //Llamamos a la funcion que serializa el valor del mapa y lo convierte en un String
        String cookieValor = serializar(accesos);

        // Creamos una cookie que contenga el email y el conteo de accesos

        Cookie cookie = new Cookie("accesosUsuarios", cookieValor);
//        cookie.setPath("/");
 //       cookie.setMaxAge(7 * 24 * 60 * 60); //TODO: NO SE QUE EDAD DEBE TENER LA COOKIE
        response.addCookie(cookie);


    }

    public  String serializar(Map<String, Integer> accesosUsuarios) {
        //Serializamos el mapa con los accesos de los usuarios con String Builder
        StringBuilder sb = new StringBuilder();
        //concatenamos el email y el conteo de usuarios para que tenga este formato email&accesos#
        accesosUsuarios.forEach((email, accesos) -> sb.append(email).append("&").append(accesos).append("#"));
        return sb.toString();
    }

    public Map<String, Integer> deserializar(String data) {
        //Creamos un mapa donde almacenar los accesos de los usuarios
        Map<String, Integer> accesosUsuario = new HashMap<>();
        //Creamos un array en el que guardaremos los usuarios con sus accesos de forma separada por #
        String[] usuarios = data.split("#");
        for (String usuario : usuarios) {
            if (!usuario.isEmpty()) {
                //Para cada usuario, separamos el email y el conteo de accesos
                String[] detalles = usuario.split("&");
                //Cuando hay dos detalles, el email y el conteo, se almacenan en el mapa
                if (detalles.length == 2) {
                    //la clave es el email y el valor es el conteo que estan guardados en el array detalles
                    accesosUsuario.put(detalles[0], Integer.parseInt(detalles[1]));
                }
            }
        }
        return accesosUsuario;
    }



    public String buscarCookie(HttpServletRequest request, String nombreCookie) {
        //Creamos un array con las cookies que existen
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            //Recorremos el array de cookies
            for (Cookie cookie : cookies) {
                //Si el nombre de la cookie coincide con el que nos mandaron, retornamos el valor
                if (cookie.getName().equals(nombreCookie)) {
                    return cookie.getValue();
                }
            }
        }
        return null;

    }

    //usuario&accesos
    public   Map<String, Integer> deserializarAccesos(String accesos) {
        Map<String, Integer> mapaAccesos = new HashMap<>();
        String[] accesosArray = accesos.split("&");
        mapaAccesos.put(accesosArray[0], Integer.parseInt(accesosArray[1]));
        return mapaAccesos;
    }
    public Map<String, Integer> actualizarConteoDeVisitasUsuario(HttpServletRequest request, HttpServletResponse response, String emailUsuario) {
        // Intentamos obtener la cookie que contiene los accesos de los usuarios

        String cookieAccesosUsuarios = buscarCookie(request, "accesosUsuarios");

        Map<String, Integer> visitasUsuarios = deserializarAccesos(cookieAccesosUsuarios);

        int visitas = visitasUsuarios.getOrDefault(emailUsuario, 0) + 1;

        visitasUsuarios.put(emailUsuario, visitas);



        StringBuilder sb = new StringBuilder();
        //concatenamos el email y el conteo de usuarios para que tenga este formato email&accesos#
        visitasUsuarios.forEach((email, visitasUsuario) -> sb.append(email).append("&").append(visitasUsuario));

        String nuevoValorCookie = sb.toString();

        Cookie nuevaCookie = new Cookie(emailUsuario, nuevoValorCookie);
        nuevaCookie.setPath("/");
        response.addCookie(nuevaCookie);

        return visitasUsuarios;
    }




}