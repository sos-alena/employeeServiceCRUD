package employeeServiceCRUD.repositories.bl;

import lombok.Data;
import org.hibernate.Session;
import org.hibernate.Transaction;
@Data
public class SessionWrapper {
    private Session session;
    private Transaction transaction;

    public Session openSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    public Session openTransactionSession() {
        session = openSession();
        transaction = session.beginTransaction();
        return session;

    }

    public void closeSession() {
        session.close();
    }

    public void closeTransactionSession() {
        transaction.commit();
        closeSession();
    }
}
