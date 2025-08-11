package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {

    private Connection connection;
    private Scanner scanner;

    public Patient(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public void addPatient() {
        System.out.print("Enter the Patient's name :");
        String name = scanner.next();

        System.out.print("Enter the age : ");
        int age = scanner.nextInt();

        System.out.print("Enter the Patient's Gender :");
        String gender = scanner.next();

        try {
            String query = "INSERT INTO patients(name, age, gender) VALUES(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);

            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows > 0) {
                System.out.print("Patient added successfully !");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void viewPatients(){
        String query = "select * from patients";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Patients: ");
            System.out.println("+----------+------------+------------+---------+");
            System.out.println("| Patient   |   Name     |  Age      | Gender  |" );
            System.out.println("+----------+------------+------------+---------+");

            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                System.out.printf("| %-12s | %-20s | %-10s | %-12s \n", id,name, age , gender);  //format he
                System.out.println("+----------+------------+------------+---------+");


            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//user present or not
    public boolean getPatientByID(int id){
//ResultSet is an interface
        String query = "Select * from patients where id =?";
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
