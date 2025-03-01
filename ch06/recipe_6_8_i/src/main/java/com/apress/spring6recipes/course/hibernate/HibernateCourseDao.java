package com.apress.spring6recipes.course.hibernate;

import com.apress.spring6recipes.course.Course;
import com.apress.spring6recipes.course.CourseDao;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class HibernateCourseDao implements CourseDao {

  private final SessionFactory sessionFactory;

  public HibernateCourseDao(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  @Transactional
  public Course store(Course course) {
    var session = sessionFactory.getCurrentSession();
    if (course.getId() == null) {
      session.persist(course);
    } else {
      course = session.merge(course);
    }
    return course;
  }

  @Override
  @Transactional
  public void delete(Long courseId) {
    var session = sessionFactory.getCurrentSession();
    var course = session.getReference(Course.class, courseId);
    session.remove(course);
  }

  @Override
  @Transactional(readOnly = true)
  public Course findById(Long courseId) {
    var session = sessionFactory.getCurrentSession();
    return session.get(Course.class, courseId);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Course> findAll() {
    var session = sessionFactory.getCurrentSession();
    return session.createQuery("from Course", Course.class).list();
  }
}
