package pruebas;

import daoClasesSQL.UsuarioDAO;
import modelClasesTablas.Usuario;

public class Pruebas {

    public static void main(String[] args) {
        System.out.println("=== INICIANDO PRUEBA DEL DAO DE USUARIOS ===");
        System.out.println("Conectando a AWS...");

        // 1. Instanciamos tu DAO (El "trabajador")
        UsuarioDAO dao = new UsuarioDAO();
        
        // 2. Le pedimos que intente hacer login con datos inventados
        System.out.println("Buscando al usuario 'profe@ies.es'...");
        Usuario user = dao.login("idiota1234@aday.es", "1234");
        
        // 3. Evaluamos la respuesta
        System.out.println("\n=== RESULTADO DE LA PRUEBA ===");
        
        if (user != null) {
            // Esto saldrá el día de mañana cuando metáis datos reales
            System.out.println("✅ ¡Login Correcto! Bienvenido, " + user.getNombre());
            System.out.println("Tu rol es: " + user.getRol());
        } else {
            // ESTO ES LO QUE ESPERAMOS HOY
            System.out.println("⚠️ Login fallido: Credenciales incorrectas o usuario inexistente.");
            System.out.println("💡 NOTA: ¡Esto es un ÉXITO! Significa que Java ha ido a AWS, ha hecho el SELECT con PreparedStatement, ha visto que la tabla está vacía y ha vuelto sin dar errores de código.");
        }
    }
}