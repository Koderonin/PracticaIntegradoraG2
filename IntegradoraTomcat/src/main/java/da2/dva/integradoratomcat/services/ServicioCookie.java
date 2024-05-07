package da2.dva.integradoratomcat.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ServicioCookie {

    public void actualizaOCreaCookieUsuario(HttpServletResponse response, String email, String accesosUsuario) {
        // Deserializa la cookie existente si se proporciona
        Map<String, Integer> accesos = deserializar(accesosUsuario);

        // Actualiza el conteo para el usuario específico
        int conteoActual = accesos.getOrDefault(email, 0) + 1;
        accesos.put(email, conteoActual);

        // Serializa el mapa actualizado a una cadena para la cookie
        String cookieValor = serializar(accesos);

        // Crea/actualiza la cookie con el nuevo valor
        Cookie cookie = new Cookie("accesosUsuario", cookieValor);
        cookie.setPath("/");
        cookie.setMaxAge(7 * 24 * 60 * 60); // por ejemplo, para una validez de 1 semana
        response.addCookie(cookie);
    }

    private String serializar(Map<String, Integer> accesosUsuarios) {
        // Concatenación simple para demostración, considerar codificación URL o Base64 para caracteres especiales
        StringBuilder sb = new StringBuilder();
        accesosUsuarios.forEach((email, accesos) -> sb.append(email).append("&").append(accesos).append("#"));
        return sb.toString();
    }

    private Map<String, Integer> deserializar(String data) {
        Map<String, Integer> accesosUsuario = new HashMap<>();
        String[] usuarios = data.split("#");
        for (String usuario : usuarios) {
            if (!usuario.isEmpty()) {
                String[] detalles = usuario.split("&");
                if (detalles.length == 2) {
                    accesosUsuario.put(detalles[0], Integer.parseInt(detalles[1]));
                }
            }
        }
        return accesosUsuario;
    }

    //PRUEBAS
    /*

    public void actualizarConteoDeVisitasUsuario(HttpServletRequest request, HttpServletResponse response, String emailUsuario) {
        // Intentamos obtener la cookie que contiene los accesos de los usuarios
        Cookie[] cookies = request.getCookies();
        Cookie cookieAccesosUsuarios = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("accesosUsuarios".equals(cookie.getName())) {
                    cookieAccesosUsuarios = cookie;
                    break;
                }
            }
        }

        Map<String, Integer> accesosUsuarios = new HashMap<>();
        // Si encontramos la cookie, deserializamos su contenido para trabajar con él
        if (cookieAccesosUsuarios != null) {
            String valorCookie = cookieAccesosUsuarios.getValue();
            accesosUsuarios = deserializarAccesos(valorCookie); // Implementar este método según el formato elegido.
        }

        // Actualizamos el número de visitas para el usuario actual
        int visitas = accesosUsuarios.getOrDefault(emailUsuario, 0) + 1;
        accesosUsuarios.put(emailUsuario, visitas);

        // Serializamos nuevamente el mapa para guardar en la cookie
        String nuevoValorCookie = serializarAccesos(accesosUsuarios); // Implementar este método según el formato elegido.
        Cookie nuevaCookie = new Cookie("accesosUsuarios", nuevoValorCookie);
        nuevaCookie.setPath("/");
        response.addCookie(nuevaCookie);
    }

    public void actualizarPaginasVisitadas(HttpServletRequest request, HttpServletResponse response, String paginaActual) {
    Cookie[] cookies = request.getCookies();
    Cookie cookiePaginasVisitadas = null;

    // Buscamos la cookie de páginas visitadas entre las cookies del request
    for(Cookie cookie : cookies){
        if("paginasVisitadas".equals(cookie.getName())){
            cookiePaginasVisitadas = cookie;
            break;
        }
    }

    String paginasVisitadas = "";
    try {
        // Si la cookie ya existe, obtenemos y decodificamos su valor
        if (cookiePaginasVisitadas != null) {
            paginasVisitadas = URLDecoder.decode(cookiePaginasVisitadas.getValue(), "UTF-8");
        }

        // Agregamos la página actual a la lista de páginas visitadas
        if(!paginasVisitadas.isEmpty()){
            paginasVisitadas += ","; // Usamos coma como separador
        }
        paginasVisitadas += paginaActual;

        // Codificamos el valor para asegurarnos de que caracteres especiales sean manejados correctamente
        String valorCodificado = URLEncoder.encode(paginasVisitadas, "UTF-8");

        // Creamos o actualizamos la cookie con la lista de páginas visitadas
        Cookie nuevaCookiePaginasVisitadas = new Cookie("paginasVisitadas", valorCodificado);
        nuevaCookiePaginasVisitadas.setPath("/");
        response.addCookie(nuevaCookiePaginasVisitadas);
    } catch (UnsupportedEncodingException e) {
        // Manejo de la excepción de codificación
        e.printStackTrace();
    }
}


     */
}