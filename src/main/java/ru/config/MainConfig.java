package ru.config;


import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;

@Configuration
@ComponentScan(value = {"ru.model", "ru.repository", "ru.service", "ru.config"})
@EnableJpaRepositories(basePackages = "ru.repository")
@EnableTransactionManagement
@PropertySource("classpath:spring/postgresql.properties")
public class MainConfig {
    private final Environment environment;


    public MainConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public BasicDataSource getDataSource() {

        BasicDataSource dataSource = new BasicDataSource();

         dataSource.setDriverClassName(environment.getProperty("database.driver"));
         dataSource.setUrl(environment.getProperty("database.url"));
         dataSource.setUsername(environment.getProperty("database.username"));
         dataSource.setPassword(environment.getProperty("database.password"));
         dataSource.setMinIdle(convertToInt(environment.getProperty("database.pool.minIdle")));
         dataSource.setMaxIdle(convertToInt(environment.getProperty("database.pool.maxIdle")));
         dataSource.setMaxOpenPreparedStatements(convertToInt(environment.getProperty("database.pool.maxOpenPreparedStatements")));

        return dataSource;
    }

    private Integer convertToInt(String prop) {
        return Integer.parseInt(prop);
    }


    @Bean
    public Properties getHibernateProperties() {
        Properties properties = new Properties();

        properties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        properties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        properties.setProperty("hibernate.jpa.compliance.proxy", environment.getProperty("hibernate.jpa.compliance.proxy"));
        properties.setProperty("hibernate.format_sql", environment.getProperty("hibernate.format_sql"));
        properties.setProperty("hibernate.use_sql_comments", environment.getProperty("hibernate.use_sql_comments"));

        return properties;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("ru.model.**");
        factory.setDataSource(getDataSource());
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }

}
