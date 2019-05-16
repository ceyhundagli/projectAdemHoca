/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

/**
 * FXML Controller class
 *
 * @author Mehmet
 */
public class SellController implements Initializable {

    public Label cSelectText;
    public Label pSelectText;
    public TableView cartTableView;
    public TableColumn<Cart,Integer> cartIdColumn;
    public TableColumn<Cart, String> cartNameColumn;
    public TableColumn<Cart, Integer> cartPriceColumn;
    public TableColumn<Cart, Integer> cartQuantityColumn;
    public TableColumn<Cart, Integer> cartTotalColumn;
    @FXML
    private TableView sellCustomerTable;
    @FXML
    private TableColumn<Customer, Integer> customerIdColumn;
    @FXML
    private TableColumn<Customer, String> customerNameColumn;
    @FXML
    private TableColumn<Customer, Integer> customerBalanceColumn;
    @FXML
    private TableView sellItemTable;
    @FXML
    private TableColumn<Products, Integer> productIdColumn;
    @FXML
    private TableColumn<Products, String> productNameColumn;
    @FXML
    private TableColumn<Products, String> productCategoryColumn;
    @FXML
    private TableColumn<Products,Integer> productPriceColumn;
    @FXML
    private TableColumn<Products, Integer> productStockColumn;
    @FXML
    private TextField customerIdText;
    @FXML
    private TextField productIdText;
    @FXML
    private TextField amountText;
    @FXML
    private Button addItemToChart;
    @FXML
    private Button loadDataBtn;

    /**
     * Initializes the controller class.
     */
   @FXML
    public void initialize(URL url, ResourceBundle rb) {
        customerIdColumn.setCellValueFactory(cellData-> cellData.getValue().customeridProperty().asObject());
        customerNameColumn.setCellValueFactory(cellData-> cellData.getValue().customernameProperty());
        customerBalanceColumn.setCellValueFactory(cellData-> cellData.getValue().customerbalanceProperty().asObject());

        productIdColumn.setCellValueFactory(cellData -> cellData.getValue().productidProperty().asObject());
        productNameColumn.setCellValueFactory(cellData -> cellData.getValue().productnameProperty());
        productPriceColumn.setCellValueFactory(cellData -> cellData.getValue().productpriceProperty().asObject());
        productStockColumn.setCellValueFactory(cellData -> cellData.getValue().productstockProperty().asObject());
        productCategoryColumn.setCellValueFactory(cellData -> cellData.getValue().productcategoryProperty());





   }

    @FXML
    private void addChart(ActionEvent event) {



        ObservableList<Products> xList;
        xList = sellItemTable.getSelectionModel().getSelectedItems();
        int c = Integer.parseInt(amountText.getText());
       //Cart cart = new Cart(1,"sads",3,4,3*4);
        String name2= xList.get(0).getProductname();

       Cart cart = new Cart(1,xList.get(0).getProductname(),xList.get(0).getProductprice(),c,c*xList.get(0).getProductprice());
       cartTableView.getItems().add(cart);
        cartIdColumn.setCellValueFactory(new PropertyValueFactory("cartid"));
        cartNameColumn.setCellValueFactory(new PropertyValueFactory("cartProName"));
        cartTotalColumn.setCellValueFactory(new PropertyValueFactory("cartTotalQuantity"));
        cartPriceColumn.setCellValueFactory(new PropertyValueFactory("cartProPrice"));
        cartQuantityColumn.setCellValueFactory(new PropertyValueFactory("cartProQuantity"));



        //cartTableView.setItems(cart);
       /* ObservableList<Products> proList;
        proList = sellItemTable.getSelectionModel().getSelectedItems();
       for (int s = 0 ;s <10; ++s ){
           int x[] = new int[10];
                  x[s] = proList.get(0).getProductid();
            String name = proList.get(0).getProductname();
            int price = proList.get(0).getProductprice();
            int quantity = s;
            int ctotal = price * quantity;
            final ObservableList<Cart> data = FXCollections.observableArrayList(
                    new Cart(x[s], name, price, quantity, ctotal)

            );
           cartIdColumn.setCellValueFactory(new PropertyValueFactory("cartid"));
           cartNameColumn.setCellValueFactory(new PropertyValueFactory("cartProName"));
           cartTotalColumn.setCellValueFactory(new PropertyValueFactory("cartTotalQuantity"));
           cartTableView.setItems(data);

System.out.println(x[s]);
       }
*/

    }

    @FXML
    private void loadData(ActionEvent event) throws SQLException, ClassNotFoundException {
       try{
           ObservableList<Customer> cData = CustomerDAO.searchCustomers();
           populateCustomers(cData);
           ObservableList<Products> proData = ProductsDAO.searchProducts();
           populateProducts(proData);
       }catch (SQLException e){
           System.out.println("load data error");
           throw e;
       }
    }

    @FXML
    private void populateCustomer(Customer cus){
       ObservableList<Customer> cusData = FXCollections.observableArrayList();
       cusData.add(cus);
       sellCustomerTable.setItems(cusData);
    }

    @FXML
    private void populateCustomers(ObservableList<Customer> cusData){
       sellCustomerTable.setItems(cusData);
    }

    @FXML
    private void populateProduct(Products pro){
       ObservableList<Products> proData = FXCollections.observableArrayList();

       proData.add(pro);
       sellItemTable.setItems(proData);
    }

    @FXML
    private void populateProducts(ObservableList<Products> proData){
       sellItemTable.setItems(proData);
    }

    public void selectCustomer(ActionEvent actionEvent) {
       ObservableList<Customer> customerList;
       customerList = sellCustomerTable.getSelectionModel().getSelectedItems();

       //System.out.println(customerList.get(0).getCustomername());
       cSelectText.setText("Selected Customer:"+" "+customerList.get(0).getCustomername());

    }

    public void selectProduct(ActionEvent actionEvent) {
       ObservableList<Products> proList;
       proList = sellItemTable.getSelectionModel().getSelectedItems();
       pSelectText.setText("Selected Product"+" "+proList.get(0).getProductname()+"\n"+ "Price ="+" "+proList.get(0).getProductprice());
    }

    public void removeSelectedFromChart(ActionEvent actionEvent) {
        cartTableView.getItems().removeAll(cartTableView.getSelectionModel().getSelectedItem());

    }

    public void saveToDb(ActionEvent actionEvent) throws SQLException {
        Cart cart = new Cart();
        int total = 0;
        ObservableList<Customer> customerList;
        customerList = sellCustomerTable.getSelectionModel().getSelectedItems();
        for (int j = 0; j < cartTableView.getItems().size(); j++) {
            ObservableList<Products> proList;
            proList = sellItemTable.getSelectionModel().getSelectedItems();

            cart = (Cart) cartTableView.getItems().get(j);

            String name = cart.getCartProName();
            int quantity = cart.getCartProQuantity();
            int x = cart.getCartProPrice();
            int cid =customerList.get(0).getCustomerid();
int proid = proList.get(j).getProductid();
            int y = cart.getCartProQuantity();
            int totalCost = y * x;

            total += totalCost;

            System.out.println(totalCost);
            System.out.println(name);
            System.out.println(cid);

            String updateStmt = "INSERT INTO cart ( customerid,productid, amount) VALUES (?,?,?)";
            String selectStmt = " update products set productstock = productstock -? where productid=?";
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizza", "root", "samit0713");
            PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(updateStmt);
            preparedStatement.setInt(1, cid);
            preparedStatement.setInt(2, proid);
            preparedStatement.setInt(3, quantity);
            PreparedStatement preparedStatement1 = (PreparedStatement) conn.prepareStatement(selectStmt);
            preparedStatement1.setInt(1,y);
            preparedStatement1.setInt(2,proid);

            try {
                preparedStatement1.executeUpdate();
                preparedStatement.executeUpdate();
            }
            catch (SQLException e){
                System.out.println("error insertProducts");
                throw e;
            }
        }
        String selectStmt1 = " update customers set customerbalance = customerbalance -? where customerid=?";
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizza", "root", "samit0713");
        PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(selectStmt1);
        preparedStatement.setInt(1, total);
        preparedStatement.setInt(2, customerList.get(0).getCustomerid());
        try {

            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            System.out.println("error insertProducts");
            throw e;
        }

        System.out.println(total);
        cartTableView.getItems().clear();
    }

}
