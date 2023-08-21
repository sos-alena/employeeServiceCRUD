package bl;

import lombok.Data;
import org.hibernate.Session;
import org.hibernate.Transaction;
@Data
public class SessionUtil {
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
