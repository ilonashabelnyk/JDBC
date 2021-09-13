import org.junit.Assert;
import org.junit.jupiter.api.*;

import java.sql.*;
import java.util.ArrayList;

public class TestSQL {
    private ConnectJDBC connectJDBC = new ConnectJDBC();

    public ArrayList<String> createData() {
        ArrayList<String> expectedFilmTitles = new ArrayList<>();
        expectedFilmTitles.add("ALADDIN CALENDAR");
        expectedFilmTitles.add("ARACHNOPHOBIA ROLLERCOASTER");
        expectedFilmTitles.add("BALLROOM MOCKINGBIRD");
        expectedFilmTitles.add("CYCLONE FAMILY");
        expectedFilmTitles.add("DROP WATERFRONT");
        expectedFilmTitles.add("GUNFIGHTER MUSSOLINI");
        expectedFilmTitles.add("MODERN DORADO");
        expectedFilmTitles.add("MUSSOLINI SPOILERS");
        expectedFilmTitles.add("NASH CHOCOLAT");
        expectedFilmTitles.add("QUEST MUSSOLINI");
        expectedFilmTitles.add("RINGS HEARTBREAKERS");
        expectedFilmTitles.add("ROCKETEER MOTHER");
        expectedFilmTitles.add("RUGRATS SHAKESPEARE");
        expectedFilmTitles.add("SOLDIERS EVOLUTION");
        expectedFilmTitles.add("TROUBLE DATE");
        return expectedFilmTitles;
    }

    @Test
    public void test1() throws SQLException {
        Connection connectionDb = connectJDBC.connectDB();
        Statement statement = connectionDb.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT first_name FROM sakila.actor WHERE actor_id=1;");
        while (resultSet.next()) {
            Assert.assertEquals("Incorrect first name!", "PENELOPE", resultSet.getString("first_name"));
            System.out.println("Expected result of Test 1: " + "PENELOPE");
            System.out.println("Actual result of Test 1: " + resultSet.getString("first_name"));
        }
        connectJDBC.disconnectDB();
    }

    @Test
    public void test2() throws SQLException {
        Connection connectionDb = connectJDBC.connectDB();
        Statement statement = connectionDb.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT film.title, film_actor.actor_id, film_actor.film_id FROM sakila.film JOIN sakila.film_actor ON film_actor.film_id = film.film_id WHERE film_actor.actor_id = 35;");
        ArrayList<String> filmTitles = new ArrayList<>();
        while (resultSet.next()) {
            filmTitles.add(resultSet.getString("title"));
        }
        Assert.assertEquals("Incorrect title of the first film!", createData().get(0), filmTitles.get(0));
        System.out.println("Expected result of Test 2: " + createData().get(0));
        System.out.println("Actual result of Test 2: " + filmTitles.get(0));
        connectJDBC.disconnectDB();
    }
}