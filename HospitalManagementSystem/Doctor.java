package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {
    private Connection connection;

    public Doctor(Connection connection) {
        this.connection = connection;

    }


    public void viewDoctors(){
        String query = "select * from Doctors";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Doctors: ");
            System.out.println("+----------+------------+-----------------+");
            System.out.println("| Doctor   |   Name     | Specialisation  |" );
            System.out.println("+----------+------------+-----------------+");

            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialisation = resultSet.getString("specialisation");

                System.out.printf("|%-9s| %-13s| %-15s|\n", id, name, specialisation);  //format he
                System.out.println("+----------+------------+-----------------+");

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //user present or not
    public boolean getDoctorByID(int id){
//ResultSet is an interface
        String query = "Select * from doctors where id =?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id); //1st jagya(?) varti id chi value set kara.
            ResultSet resultSet = preparedStatement.executeQuery();

//            💡 What rs.next() does:
//            Moves to the next row in the ResultSet
//
//            Returns false when no more rows are left

            if(resultSet.next()) {
                return true;
            }
            else{
                return false;
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
