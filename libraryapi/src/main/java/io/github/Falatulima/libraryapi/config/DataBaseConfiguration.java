package io.github.Falatulima.libraryapi.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataBaseConfiguration {

    //tazendo os valores imputados no application.yml pra dentro do código
    //O @Value é usado pra injetar valores de fora do código dentro de variáveis java
    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver-class-name}")
    String driver;

    //hikariDataSource recomendado para projetos mais robustos******
    @Bean
    public DataSource hikariDataSource() {
        HikariConfig config = new HikariConfig();
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);
        config.setJdbcUrl(url);

        //numero máximo de conexões/requisições simultaneas no banco
        //caso hajam 11 requisições simultaneas, o sistema aguarda uma das 10 requisições serem liberadas
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(1); //numero inicial de conexões
        config.setPoolName("library-db-pool");
        config.setMaxLifetime(600000); //conexão irá durar no máximo 10 min
        config.setConnectionTimeout(200000); //timeout para conseguir conexão
        config.setConnectionTestQuery("select 1"); //teste no banco pra ver se a conexão está acontecendo

        return new HikariDataSource(config);
    }

    // @Bean //"crie um objeto e o Spring gerencie ele pra mim"
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driver);
        return ds;
    }
}
