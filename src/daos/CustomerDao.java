package daos;

import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;
import java.sql.ResultSet;
import entities.Customer;

public class CustomerDao implements Dao<Customer, Integer> {
    Connection connection;
    public CustomerDao(Connection connection)
    {
        this.connection = connection;
    }
    //This function is used to display all the customers found in the table. 
    public List<Customer> findAll()
    {
        List<Customer> customers = new ArrayList<Customer>();
        try (Statement statement = connection.createStatement())
        {
            ResultSet result = statement.executeQuery("SELECT * FROM customer");
            while(result.next())
            {
                Customer customer = new Customer();
                customer.setID(result.getInt("id"));
                customer.setName(result.getString("name"));
                customer.setAddress(result.getString("address"));
                customer.setCity(result.getString("city"));
                customer.setState(result.getString("state"));
                customer.setZip(result.getString("zip"));
                customers.add(customer);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
    // This function is used to find a specific customer by their ID 
    public Customer findByID(Integer pk)
    {
        Customer customer = new Customer();
        String select = "SELECT * FROM customer WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(select);)
        {
            ps.setInt(1,pk);
            ResultSet result = ps.executeQuery();
            if(result.next())
            {
                customer.setID(result.getInt("id"));
                customer.setName(result.getString("name"));
                customer.setAddress(result.getString("address"));
                customer.setCity(result.getString("city"));
                customer.setState(result.getString("state"));
                customer.setZip(result.getString("zip"));
            }
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return customer;
    }
    //This function is creating a customer for the mysql table. The keys are calculated automatically
    public void insert(Customer customer)
    {
        try (Statement statement = connection.createStatement())
        {
            String insert = "INSERT INTO customer VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, null);
            ps.setString(2, customer.getName());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getCity());
            ps.setString(5, customer.getState());
            ps.setString(6, customer.getZip());
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if(keys.next()) {
                customer.setID(keys.getInt(1));
            }
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
    } 
    //This function is used to update address in the Table. 
    public Boolean update(Customer cutomer)
    {
        Boolean sucess = true;
        String update = "UPDATE customer SET address=? WHERE id=?";
        try(PreparedStatement ps = connection.prepareStatement(update))
        {
          ps.setString(1, cutomer.getAddress());
          ps.setInt(2,cutomer.getID());
          ps.executeUpdate();
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
            sucess = false;
        }
        return sucess;
    }
    //This function uses the pk (ID) to delete any customer. 
    public Boolean delete(Integer pk)
    {
        Boolean success = false;
        String delete = "DELETE FROM customer WHERE id=?";
        try(PreparedStatement ps = connection.prepareStatement(delete);)
        {
            ps.setInt(1, pk);
            success = true;
        } catch (SQLException e) 
        {
            System.err.println(e.getMessage());
        }
        return success;
    }
   

}


