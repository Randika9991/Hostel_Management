package repositrory.impl;


import config.SessionFactoryConfig;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repositrory.StudentRepository;

import java.util.List;

public class StudentRepositoryimpl implements StudentRepository {
    private Session session;

    public StudentRepositoryimpl() {}

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public Integer save(Student customer) {
        return Integer.valueOf((String) session.save(customer));
    }

    @Override
    public Student getId(Integer id) {
        return session.get(Student.class, id);
    }

    @Override
    public void update(Student student) {
        session.update(student);
    }

    @Override
    public void delete(Student student) {
        session.delete(student);
    }

    /*public List<Student> getCustomersHQL() {
        String sql = "FROM Student";
        Query query = session.createQuery(sql);
        List list = query.list();
        session.close();
        return list;
    }*/
}

