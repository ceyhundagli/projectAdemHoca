/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Customer;
import model.CustomerDAO;

/**
 * FXML Controller class
 *
 * @author Mehmet
 */
public class CustomersController  {

    public TableColumn<Customer,Integer> cBalanceColumn;
    @FXML
    private TableView customerTable;
    @FXML
    private TableColumn<Customer, Integer> cIdColumn;
    @FXML
    private TableColumn<Customer, String> cNameColumn;
    @FXML
    private TableColumn<Customer, String> cCityColumn;
    @FXML
    private TextField nameText;
    @FXML
    private TextField cityText;
    @FXML
    private Button addCustomerBtn;
    @FXML
    private TextField cNameText;
    @FXML
    private TextField cCityText;
    @FXML
    private TextField cBalanceText;
    @FXML
    private TextField cIdText;
    @FXML
    private Button setCustomerBtn;
    @FXML
    private Button updateProductBtn;
    @FXML
    private Button deleteCustomerBtn;
    @FXML
    private Button searchCustomersBtn;

    /**
     * Initializes the controller class.
     */
   @FXML
    public void initialize() {
        cIdColumn.setCellValueFactory(cellData-> cellData.getValue().customeridProperty().asObject());
        cNameColumn.setCellValueFactory(cellData-> cellData.getValue().customernameProperty());
        cCityColumn.setCellValueFactory(cellData-> cellData.getValue().customercityProperty());
        cBalanceColumn.setCellValueFactory(cellData-> cellData.getValue().customerbalanceProperty().asObject());
    }

    @FXML
    private void insertCustomer(ActionEvent event) throws SQLException {
       try {
           String name = nameText.getText();
           String city = cityText.getText();
           CustomerDAO.insertCustomer(name,city);
           System.out.println("Product inserted");
       }catch (SQLException e){
           System.out.println("Problem with db");
           throw e;
       }

    }

    @FXML
    private void setCustomerInfo(ActionEvent event) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizza", "root", "samit0713");
        int id = Integer.parseInt(cIdText.getText());
        Statement stmt = conn.createStatement();
        try{
            ResultSet rs = stmt.executeQuery("select customername,customercity,customerbalance from customers where customerid="+id);
            while (rs.next()){
                String name = rs.getString("customername");
                String city = rs.getString("customercity");
                int balance = rs.getInt("customerbalance");

                cNameText.setText(name);
                cCityText.setText(city);
                cBalanceText.setText(String.valueOf(balance));
            }
        }catch (SQLException e){
            throw e;
        }
    }

    @FXML
    private void updateProductInfo(ActionEvent event) throws SQLException {
       try{
           int id = Integer.parseInt(cIdText.getText());
           String name = cNameText.getText();
           String city = cCityText.getText();
           int balance = Integer.parseInt(cBalanceText.getText());
           CustomerDAO.updateCustomer(id,name,city,balance);
       }catch (SQLException e){
           System.out.println("problem with db");
       }
    }

    @FXML
    private void deleteCustomer(ActionEvent event) throws SQLException {
       int x = Integer.parseInt(cIdText.getText());
        try {
            CustomerDAO.deleteCustomerWid(x);
            System.out.println("Product inserted"+cIdText.getText());
        } catch (SQLException e) {
            throw e;
        }
    }

    @FXML
    private void searchCustomers(ActionEvent event) throws SQLException, ClassNotFoundException {

        try {
            ObservableList<Customer> cusData = CustomerDAO.searchCustomers();
            populateCustomers(cusData);
        }catch (SQLException e){
            System.out.println("searchCustomers error");
            throw e;
        }

    }
@FXML
    private void populateCustomer(Customer cus) {
       ObservableList<Customer> cusData = FXCollections.observableArrayList();

       cusData.add(cus);
       customerTable.setItems(cusData);
    }

    @FXML
    private void populateCustomers(ObservableList<Customer> cusData){
       customerTable.setItems(cusData);
    }

   private void populateAndShowCustomer (Customer cus){
       if(cus != null){
           populateCustomer(cus);

       }else{
           System.out.println("no customer like this");
       }
   }


}
