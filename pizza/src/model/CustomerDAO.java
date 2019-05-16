package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.ConnectionUtil;

import java.sql.*;

public class CustomerDAO {
        public static Customer searchCustomer (String cid) throws ClassNotFoundException, SQLException {
            String selectStmt = "SELECT * FROM customers WHERE customerid=" + cid;

            try {
                ResultSet rsCus = ConnectionUtil.dbExecuteQuery(selectStmt);

                Customer customer = getCustomerFromResultSet(rsCus);

                return customer;
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("searchCustomer error");
                throw e;
            }



        }

        private static Customer getCustomerFromResultSet(ResultSet rs) throws SQLException {
            Customer cus = null;
            if(rs.next()){
                cus = new Customer();
                cus.setCustomerid(rs.getInt("customerid"));
                cus.setCustomername(rs.getString("customername"));
                cus.setCustomercity(rs.getString("customercity"));
                cus.setCustomerbalance(rs.getInt("customerbalance"));
            }
            return cus;
        }

        public static ObservableList<Customer> searchCustomers() throws ClassNotFoundException, SQLException {
            String selectStmt = "SELECT * FROM customers";

            try {
                ResultSet rsCuss = ConnectionUtil.dbExecuteQuery(selectStmt);

                ObservableList<Customer> cusList = getCustomerList(rsCuss);

                return cusList;
            } catch (SQLException e) {
                System.out.println("searchCustomers error");
                e.printStackTrace();
                throw e;
            }

        }


        public static ObservableList<Customer> getCustomerList(ResultSet rs) throws SQLException {
            ObservableList<Customer> cusList = FXCollections.observableArrayList();

            while(rs.next()){
                Customer cus = new Customer();
                cus.setCustomerid(rs.getInt("customerid"));
                cus.setCustomername(rs.getString("customername"));
                cus.setCustomercity(rs.getString("customercity"));
                cus.setCustomerbalance(rs.getInt("customerbalance"));

                cusList.add(cus);
            }
            return cusList;
        }


        public static void updateCustomer (int cid, String cname ,String ccity ,int cbalance) throws SQLException {
            String updateStmt = "update customers set customername=?, customercity=?, customerbalance=? where customerid= ?";

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizza", "root", "samit0713");
            PreparedStatement preparedStatement = (PreparedStatement)conn.prepareStatement(updateStmt);
            preparedStatement.setInt(4,cid);
            preparedStatement.setString(1,cname);
            preparedStatement.setString(2,ccity);
            preparedStatement.setInt(3,cbalance);

            try {
                preparedStatement.executeUpdate();
            }catch (SQLException e){
                System.out.println("updateCustomer error");
                throw e;
            }
        }

        public static void deleteCustomerWid(int id) throws SQLException {
            String updateStmt = "delete from customers where customerid = ?";
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizza", "root", "samit0713");

            PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(updateStmt);
            preparedStatement.setInt(1, id);

            try{
                preparedStatement.executeUpdate();
            }catch (SQLException e){
                System.out.println("deleteCustomerWid error");
                throw e;
            }
        }

        public static void insertCustomer(String name , String city) throws SQLException {
            String updateStmt = "insert into customers(customername,customercity) values(?,?)";
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizza", "root", "samit0713");
            PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(updateStmt);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, city);
            try{
                preparedStatement.executeUpdate();

            }catch (SQLException e){
                System.out.println("insertCustomer Error");
                throw e;
            }
        }


}

