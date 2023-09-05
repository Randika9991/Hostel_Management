package repositrory.custom.impl;


import entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repositrory.custom.StudentRepository;

import java.util.List;

public class StudentRepositoryimpl implements StudentRepository {
    private Session session;

    public StudentRepositoryimpl() {}

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public List<Student> getDetailsToTableView() {
        String hql = "SELECT S FROM Student AS S";
        Query query = session.createQuery(hql);
        List<Student> studentList = query.list();
        return studentList;
    }

    @Override
    public String save(Student customer) {
        return ((String) session.save(customer));
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

