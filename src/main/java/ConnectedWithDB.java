import javax.annotation.Resource;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "connection")
@ApplicationScoped
public class ConnectedWithDB {

    private EntityManager em;

    @PostConstruct
    public void init() throws NamingException, SQLException {
        connectionDB();
    }

    private void connectionDB() throws NamingException, SQLException {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("p");
        em = entityManagerFactory.createEntityManager();

    }

    public void add(Point point) {
        em.getTransaction().begin();
        em.persist(point);
        em.getTransaction().commit();
    }

    public List<Point> getPointList() throws SQLException, NamingException {
        em.getTransaction().begin();
        List<Point> list = em.createQuery("SELECT c FROM results AS c", Point.class).getResultList();
        em.getTransaction().commit();
        return list;
    }

}
