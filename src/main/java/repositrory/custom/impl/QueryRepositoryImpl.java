package repositrory.custom.impl;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repositrory.custom.QueryRepository;

import java.util.List;

public class QueryRepositoryImpl implements QueryRepository {
    private Session session;
    public QueryRepositoryImpl() {}
    @Override
    public List<String> loadStudentIds() {
        String sql = "SELECT student.student_id " +
                "FROM student " +
                "LEFT JOIN reservation " +
                "ON student.student_id = reservation.student_id " +
                "WHERE reservation.student_id IS NULL";
        Query query = session.createNativeQuery(sql);
        List list = query.list();
        return list;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

}
