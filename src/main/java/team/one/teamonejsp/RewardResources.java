package team.one.teamonejsp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import team.one.teamonejsp.models.Reward;

import java.lang.reflect.Type;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Path("/rewards")
public class RewardResources {

    public RewardResources() {
        try {
            DriverManager.registerDriver(new org.mariadb.jdbc.Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllRewards() {
        try (
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
             EntityManager em = emf.createEntityManager()) {

            Query query = em.createQuery("SELECT r FROM Reward r");
            List<Reward> rewards = query.getResultList();
            Gson gson = new Gson();
            Type type = new TypeToken<List<Reward>>() {
            }.getType();
            return gson.toJson(rewards, type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}