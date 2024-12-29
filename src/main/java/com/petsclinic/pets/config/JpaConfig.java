package com.petsclinic.pets.config;

//import javax.sql.DataSource;
//
//import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.petsclinic.pets.repository")
public class JpaConfig {
	/**
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder, DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.petsclinic.pets.models")  // Your entity package
                .persistenceUnit("myJpaUnit")
                .build();
    }
    **/
}

