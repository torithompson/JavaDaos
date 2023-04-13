import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import daos.ItemDao;
import entities.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        //Create a list for the findAll() function
        List<Item> itemList;
        //using try/catch to ensure database connection successful before running statements
        try(Connection connection = Database.getDatabaseConnection();
                Statement statement = connection.createStatement();) {
            //Create new itemDao instance using connection to db
            ItemDao itemDao = new ItemDao(connection);
            //Run and print out using for each loop each item in table
            itemList = itemDao.findAll();
            System.out.println("Printing items: ");
            for (Item item : itemList) {
                System.out.println(item);
            }
            //Create new item record in table and insert
            Item insertItem = new Item();
            insertItem.setName("8 year old boy");
            insertItem.setDescription("Especially willfull, willing to do trades");
            itemDao.insert(insertItem);
            //Find an item in the table using a passed in ID and print result
            Item itemUpdate = itemDao.findById(5);
            System.out.println("Item returned from findByID(5)" + itemUpdate);
            //Update item description of previously found item and print result
            itemUpdate.setDescription("Never mind, I will keep him");
            Boolean success = itemDao.update(itemUpdate);
            if (success) {
                System.out.println("Item description after update: " + itemUpdate.getDescription());
            } else {
                System.out.println("Description update did not work.");
            }
            //Delete previously added record and ensure update was successful
            Boolean deleteSucess = itemDao.delete(5);
            if (deleteSucess) {
                System.out.println("Just kidding, not for sale");
            } else {
                System.out.println("Delete did not work.");
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
