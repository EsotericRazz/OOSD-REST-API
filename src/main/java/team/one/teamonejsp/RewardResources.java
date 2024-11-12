package team.one.teamonejsp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.ws.rs.*;
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

    @Path("get/{rewardId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String packages(@PathParam("rewardId") int rewardId) {
        try (
                EntityManagerFactory EMF = Persistence.createEntityManagerFactory("default");
                EntityManager EM = EMF.createEntityManager()) {
            Reward rwd = EM.find(Reward.class, rewardId);

            // Convert the list of packages to JSON
            Gson gson = new Gson();
            return gson.toJson(rwd);
        } catch (Exception e) {
            // Handle exceptions (e.g., logging)
            throw new RuntimeException(e);
        }
    }

    @Path("post")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String addReward(String rewardJson) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
             EntityManager em = emf.createEntityManager()) {
            Gson gson = new Gson();
            Reward reward = gson.fromJson(rewardJson, Reward.class);
            em.getTransaction().begin();
            em.persist(reward);
            em.getTransaction().commit();
            int rewardId = reward.getId();
            return "{ \"msg\": \"Reward was created\", \"RewardId\": " + rewardId + " }";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Path("put")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateReward(String rewardJson) {
        try (
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
                EntityManager em = emf.createEntityManager()) {
            Gson gson = new Gson();
            Reward reward = gson.fromJson(rewardJson, Reward.class);
            em.getTransaction().begin();
            Reward mergedReward = em.merge(reward);
            em.getTransaction().commit();
            return gson.toJson(mergedReward);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Path("delete/{rewardId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteReward(@PathParam("rewardId") int rewardId) {
        try (
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
                EntityManager em = emf.createEntityManager()) {
            Reward reward = em.find(Reward.class, rewardId);
            if (reward == null) {
                return "{ \"msg\": \"Reward not found\" }";
            }
            em.getTransaction().begin();
            em.remove(reward);
            em.getTransaction().commit();
            return "{ \"msg\": \"Reward was deleted\" }";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}