package com.apress.spring6recipes.course.hibernate;

import com.apress.spring6recipes.course.Course;
import com.apress.spring6recipes.course.CourseDao;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateCourseDao implements CourseDao {

  private final SessionFactory sessionFactory;

  public HibernateCourseDao() {

    var configuration = new Configuration()
      .setProperty(AvailableSettings.URL, "jdbc:postgresql://localhost:5432/course")
      .setProperty(AvailableSettings.USER, "postgres")
      .setProperty(AvailableSettings.PASS, "password")
      .setProperty(AvailableSettings.SHOW_SQL, String.valueOf(true))
      .setProperty(AvailableSettings.HBM2DDL_AUTO, "update")
      .addAnnotatedClass(Course.class);
    this.sessionFactory = configuration.buildSessionFactory();
  }

  @Override
  public Course store(Course course) {
    var session = sessionFactory.openSession();
    try {
      session.getTransaction().begin();
      if (course.getId() == null) {
        session.persist(course);
      } else {
        course = session.merge(course);
      }
      session.getTransaction().commit();
      return course;
    } catch (RuntimeException e) {
      session.getTransaction().rollback();
      throw e;
    } finally {
      session.close();
    }

  }

  @Override
  public void delete(Long courseId) {
    var session = sessionFactory.openSession();
    try {
      session.getTransaction().begin();
      var course = session.getReference(Course.class, courseId);
      session.remove(course);
      session.getTransaction().commit();
    } catch (RuntimeException e) {
      session.getTransaction().rollback();
      throw e;
    } finally {
      session.close();
    }
  }

  @Override
  public Course findById(Long courseId) {
    try (var session = sessionFactory.openSession()) {
      return session.find(Course.class, courseId);
    }
  }

  @Override
  public List<Course> findAll() {
    try (var session = sessionFactory.openSession()) {
      return session.createQuery("SELECT c FROM Course c", Course.class).getResultList();
    }
  }

}
