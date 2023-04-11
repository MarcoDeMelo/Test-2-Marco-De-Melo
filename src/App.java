import java.sql.Statement;
import java.sql.Connection;
import java.util.List;
import daos.CustomerDao;
import java.sql.*;
import entities.Customer;
import entities.Database;
//Project By Marco De Melo
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        List<Customer> customerList;
        try (Connection connection = Database.getDatabaseConnection(); 
             Statement statement = connection.createStatement();) {
                CustomerDao customerDao =  new CustomerDao(connection);
                customerList = customerDao.findAll();
                System.out.println("Printing cities: ");
                for (Customer customer : customerList)
                {
                    System.out.println(customer);
                }
                Customer insertCustomer = new Customer();
                insertCustomer.setName("Marco");
                insertCustomer.setAddress("123 Test Street");
                insertCustomer.setCity("Kingston");
                insertCustomer.setState("CA");
                insertCustomer.setZip("K7M3M9");
                customerDao.insert(insertCustomer);
                System.out.println("New Customer: " + insertCustomer);
                Customer customer = customerDao.findByID(3);
                System.out.println("Customer returned from findByID (3): " + customer);
                customer.setAddress("321 Test Street");
                Boolean success = customerDao.update(customer);
                if(success == true)
                {
                    System.out.println("Customer after update: " + customerDao.findByID(3));
                }
                else 
                {
                    System.out.println("Customer update didn't work");
                }
                Boolean sucess = customerDao.delete(3);
                if (sucess == true)
                {
                    System.out.println("Customer (3) Deleted");
                }
                else {
                    System.out.println("Update failed");
                }
                

             } catch (SQLException e)
             {
               System.out.println("Exception: " + e.getMessage());
             }

       
    }
}
