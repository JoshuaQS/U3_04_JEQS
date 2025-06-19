package mx.edu.utez.almacenes.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // 🔓 Archivos estáticos del frontend
                        .requestMatchers(
                                "/",
                                "/index.html",
                                "/dashboard.html",
                                "/js/**",
                                "/css/**",
                                "/images/**"
                        ).permitAll()

                        // 🔓 Endpoint de autenticación
                        .requestMatchers("/auth/**").permitAll()

                        // 🔐 Rutas protegidas por rol
                        .requestMatchers("/api/clientes/**").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers("/api/almacenes/**").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers("/api/sedes/**").hasAuthority("ADMIN")

                        // 🔐 Todo lo demás requiere autenticación
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
    }
}
