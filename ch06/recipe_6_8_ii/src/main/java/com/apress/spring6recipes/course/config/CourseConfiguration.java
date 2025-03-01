package com.apress.spring6recipes.course.config;

import com.apress.spring6recipes.course.Course;
import com.apress.spring6recipes.course.CourseDao;
import com.apress.spring6recipes.course.hibernate.HibernateCourseDao;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class CourseConfiguration {

  @Bean
  public CourseDao courseDao(SessionFactory sessionFactory) {
    return new HibernateCourseDao(sessionFactory);
  }

  @Bean
  public DataSource dataSource() {

    HikariDataSource dataSource = new HikariDataSource();
    dataSource.setUsername("postgres");
    dataSource.setPassword("password");
    dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/course");
    dataSource.setMinimumIdle(2);
    dataSource.setMaximumPoolSize(5);
    return dataSource;
  }

  @Bean
  public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {

    var sessionFactoryBean = new LocalSessionFactoryBean();
    sessionFactoryBean.setDataSource(dataSource);
    sessionFactoryBean.setHibernateProperties(hibernateProperties());
    sessionFactoryBean.setAnnotatedClasses(Course.class);
    return sessionFactoryBean;
  }

  private Properties hibernateProperties() {

    var properties = new Properties();
    properties.setProperty(AvailableSettings.SHOW_SQL, String.valueOf(true));
    properties.setProperty(AvailableSettings.HBM2DDL_AUTO, "update");
    return properties;
  }

  @Bean
  public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
    return new HibernateTransactionManager(sessionFactory);
  }

}
