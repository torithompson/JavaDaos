package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import entities.Item;

/**
 * Class to implement item Dao. This class implements all functions from Dao.java including
 * findAll(), findById(), insert(), update() and delete()
 */
public class ItemDao implements Dao<Item, Integer> {
    Connection connection;
    public ItemDao(Connection connection) {
        this.connection = connection;
    }
    //Function to retrieve all items in the item table
    public List<Item> findAll() {
        List<Item> items = new ArrayList<Item>();
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("Select * FROM item");
            while (result.next()) {
                Item item = new Item();
                item.setId(result.getInt("id"));
                item.setName(result.getString("name"));
                item.setDescription(result.getString("description"));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
    //Function to find an item that matches the id passed in
    public Item findById(Integer pk) {
        Item item = new Item();
        String select = "SELECT * FROM item WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(select);) {
            ps.setInt(1, pk);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                item.setId(result.getInt("id"));
                item.setName(result.getString("name"));
                item.setDescription(result.getString("description"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return item;
    }
    //Function to insert a new record into the table using prepared statement to ensure no sql injection
    public void insert(Item item) {
        try (Statement statement = connection.createStatement()) {
            String insert = "INSERT INTO item VALUES(?,?,?)";
            PreparedStatement ps = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, null);
            ps.setString(2, item.getName());
            ps.setString(3, item.getDescription());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                item.setId(keys.getInt(1));
            }
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
    //Function to update the description in the table that matches a specific id
    public Boolean update(Item item) {
        Boolean success = true;
        String update = "UPDATE item SET description=? WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(update)) {
            ps.setString(1, item.getDescription());
            ps.setInt(2, item.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            success = false;
        }
        return success;
    }
    //Function to delete a record from the table that matches a given id
    public Boolean delete(Integer pk) {
        Boolean success = false;
        String delete = "DELETE FROM item WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(delete);) {
            ps.setInt(1, pk);
            ps.executeUpdate();
            success = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return success;
    }
}
