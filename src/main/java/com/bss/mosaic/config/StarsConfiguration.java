package com.bss.mosaic.config;


import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.dbcp2.BasicDataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableJpaRepositories(
    entityManagerFactoryRef = "starsEntityManagerFactory",
    transactionManagerRef = "starsTransactionManager",
    basePackages = { "com.bss.mosaic.stars.repository" })
@EnableTransactionManagement
public class StarsConfiguration {

    private final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

    @Inject
    private Environment env;


    @Bean(destroyMethod = "close", name="starsDataSource")
    @ConfigurationProperties(prefix = "stars.datasource")
    public DataSource starsDataSource() {
        log.debug("Configuring Stars Datasource");

        RelaxedPropertyResolver dataSourcePropertyResolver = new RelaxedPropertyResolver(env, "stars.datasource.");
        if (dataSourcePropertyResolver.getProperty("url") == null) {
            log.error("Your database connection pool configuration is incorrect! The application" +
                    " cannot start. Please check your Spring profile, current profiles are: {}",
                Arrays.toString(env.getActiveProfiles()));

            throw new ApplicationContextException("Database connection pool is not configured correctly");
        }

        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName(dataSourcePropertyResolver.getProperty("driverClassName"));
        dataSource.setUrl(dataSourcePropertyResolver.getProperty("url"));
        dataSource.setUsername(dataSourcePropertyResolver.getProperty("username"));
        dataSource.setPassword(dataSourcePropertyResolver.getProperty("password"));

        if(dataSourcePropertyResolver.getProperty("initialSize") != null)
            dataSource.setInitialSize(Integer.valueOf(dataSourcePropertyResolver.getProperty("initialSize")));
        if(dataSourcePropertyResolver.getProperty("minIdle") != null)
            dataSource.setMinIdle(Integer.valueOf(dataSourcePropertyResolver.getProperty("minIdle")));
        if(dataSourcePropertyResolver.getProperty("maxIdle") != null)
            dataSource.setMaxIdle(Integer.valueOf(dataSourcePropertyResolver.getProperty("maxIdle")));
        if(dataSourcePropertyResolver.getProperty("maxTotal") != null)
            dataSource.setMaxTotal(Integer.valueOf(dataSourcePropertyResolver.getProperty("maxTotal")));

        return dataSource;
    }

    @Bean(name = "starsEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean starsEntityManagerFactory(
        EntityManagerFactoryBuilder builder,
        @Qualifier("starsDataSource") DataSource starsDataSource) {
        return builder
            .dataSource(starsDataSource)
            .packages("com.bss.mosaic.stars.domain")
            .persistenceUnit("stars")
            .build();
    }

    @Bean(name = "starsTransactionManager")
    public PlatformTransactionManager starsTransactionManager(
        @Qualifier("starsEntityManagerFactory") EntityManagerFactory starsEntityManagerFactory) {
        return new JpaTransactionManager(starsEntityManagerFactory);
    }


    @Bean
    public Hibernate4Module hibernate4Module() {
        return new Hibernate4Module();
    }
}
