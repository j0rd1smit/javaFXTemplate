package nl.smit.scheduler.referee.spring.config;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Responsible for the creation of the persistence related beans.
 *
 * @author Jordi Smit, 8-2-2018.
 */
@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:persistence.properties"})
@ComponentScan({"nl.smit.scheduler.referee.entity"})
@RequiredArgsConstructor(onConstructor = @__({ @Autowired, @Lazy}))
public class PersistenceJPAConfig {

    private final Environment env;

    /**
     * @return DataSource bean.
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(getEnvProperty("jdbc.driverClassName"));
        dataSource.setUrl(getEnvProperty("jdbc.url"));
        dataSource.setUsername(getEnvProperty("jdbc.user"));
        dataSource.setPassword(getEnvProperty("jdbc.pass"));

        return dataSource;
    }

    private String getEnvProperty(String property) {
        return Preconditions.checkNotNull(env.getProperty(property));
    }

    /**
     * @return a EntityMangerFactory bean.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("nl.smit.scheduler.referee.entity");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }


    /**
     * @param emf The EntityMangerFactory.
     * @return A EntityManager bean.
     */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    /**
     * @return a PersistenceExceptionTranslationPostProcessor bean.
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();

        properties.setProperty("hibernate.dialect", getEnvProperty("hibernate.dialect"));

        return properties;
    }
}
