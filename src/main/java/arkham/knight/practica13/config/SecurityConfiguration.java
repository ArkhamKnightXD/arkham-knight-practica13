package arkham.knight.practica13.config;

import arkham.knight.practica13.Services.SeguridadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configurable
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private SeguridadService seguridadService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    // Esta clase sirve para cargar el usuario en memoria, es parecido a cuando se crea el usuario en aplication properties
    // pero esta es la forma correcta de hacer
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        auth
                .userDetailsService(seguridadService)
                .passwordEncoder(bCryptPasswordEncoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()

                .antMatchers("/css/**","/hazelcast/**", "/js/**").permitAll()
                .antMatchers("/dbconsole/**").permitAll()

                //.antMatchers("/usuario/**").hasAnyRole("ADMIN")
                //.antMatchers("/cliente/**").hasAnyRole("ADMIN", "USER")
                //.antMatchers("/equipo/**").hasAnyRole("ADMIN", "USER")
                //.antMatchers("/familia/**").hasAnyRole("ADMIN", "USER")
                //.antMatchers("/").hasAnyRole("ADMIN", "USER")
                // .anyRequest().authenticated() //cualquier llamada debe ser validada
                .and()
                .formLogin()// Esta se encarga de llamar al login por defecto
                .loginPage("/login") //indicando la ruta que estaremos utilizando, sino vamos a utilizar el login por defecto.
                //.failureUrl("/login?error") //en caso de fallar puedo indicar otra pagina, esta url la utilizare para
                // indicar cuando hay algun error en la aplicacion y mandare la pagina que aqui designe
                .permitAll()
                .and()
                .logout()
                .permitAll();

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
    
}
