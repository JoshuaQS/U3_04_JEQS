package mx.edu.utez.almacenes;
import mx.edu.utez.almacenes.model.Usuario;
import mx.edu.utez.almacenes.model.Rol;
import mx.edu.utez.almacenes.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AlmacenesApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlmacenesApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Crear ADMIN si no existe
            if (usuarioRepository.findByEmail("admin@utez.mx") == null) {
                Usuario admin = Usuario.builder()
                        .email("admin@utez.mx")
                        .password(passwordEncoder.encode("admin123"))
                        .rol(Rol.ADMIN)
                        .build();
                usuarioRepository.save(admin);
                System.out.println("Usuario ADMIN creado: admin@utez.mx / admin123");
            }

            // Crear USER si no existe
            if (usuarioRepository.findByEmail("user@utez.mx") == null) {
                Usuario user = Usuario.builder()
                        .email("user@utez.mx")
                        .password(passwordEncoder.encode("user123"))
                        .rol(Rol.USER)
                        .build();
                usuarioRepository.save(user);
                System.out.println("Usuario USER creado: user@utez.mx / user123");
            }
        };
    }

}
