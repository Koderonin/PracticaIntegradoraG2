package da2.dva.integradoratomcat.utils;

import da2.dva.integradoratomcat.model.entities.Cliente;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidadorCheckDocumento implements ConstraintValidator<CheckDocumento, Cliente> {

    private static final String LETRAS = "TRWAGMYFPDXBNJZSQVHLCKE";

    @Override
    public boolean isValid(Cliente cliente, ConstraintValidatorContext context) {
        String tipo = cliente.getTipoDocumento();
        String documento = cliente.getDocumento();

        switch (tipo) {
            case "D":
                // Validar formato
                if (!documento.matches("\\d{8}[TRWAGMYFPDXBNJZSQVHLCKE]")) return false;
                // Validar DNI
                return isValidDNI(documento);
            case "N":
                // Validar formato
                if (!documento.matches("[XYZ]\\d{7}[TRWAGMYFPDXBNJZSQVHLCKE]")) return false;
                // Calcular numero
                if (documento.charAt(0) == 'X') documento = "0" + documento;
                if (documento.charAt(0) == 'Y') documento = "1" + documento;
                if (documento.charAt(0) == 'Z') documento = "2" + documento;
                // Validar NIE como DNI
                return isValidDNI(documento);
            case "P":
                // Validar formato
                return documento.matches("[a-zA-Z]{3}\\d{6}");
            case "S":
                // Validar formato
                if (!documento.matches("\\d{2}/\\d{7,8}/\\d{2}")) return false;
                // Separa partes
                String[] partes = documento.split("/");
                int provincia = Integer.parseInt(partes[0]);
                int numero = Integer.parseInt(partes[1]);
                int control = Integer.parseInt(partes[2]);
                // Validar provincia
                if ((provincia < 2 || provincia > 53) && provincia != 66) return false; // Si provincia no está entre 2 y 52, no es otro territorio (53) o no es extranjero (66)
                // Validar control
                long resto = Long.parseLong(String.valueOf(provincia) + numero) % 97;
                return resto == control;
            default: return false;
        }
    }

    private static boolean isValidDNI(String dni) {
        int resto = Integer.parseInt(dni.substring(0, 8)) % 23;
        return LETRAS.charAt(resto) == dni.charAt(8);
    }

}
