package daniel.prueba.Proyecto.java1.config;


import net.bytebuddy.matcher.StringMatcher;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean("cursoMapper")
    public ModelMapper cursoMapper(){
        return new ModelMapper();
    }

    @Bean("estudianteMapper")
    public ModelMapper estudianteMapper(){
        return new ModelMapper();
    }

    @Bean("matriculaMapper")
    public ModelMapper matriculaMapper(){
        return new ModelMapper();
    }



}
