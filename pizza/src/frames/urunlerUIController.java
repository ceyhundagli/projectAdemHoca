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
import javafx.scene.control.*;
import model.Products;
import model.ProductsDAO;
import util.ConnectionUtil;

/**
 * FXML Controller class
 *
 * @author Mehmet
 */
public class urunlerUIController  {

    public TableView productTable;
    @FXML
    private TextField idText;
    @FXML
    private TextField nameText;
    @FXML
    private TextField priceText;
    @FXML
    private TextField stockText;
    @FXML
    private TextField categoryText;
    @FXML
    private TextArea resultArea;
    @FXML
    private TextField proidText;
    @FXML
    private TextField pronameText;
    @FXML
    private TextField propriceText;
    @FXML
    private TextField prostockText;
    @FXML
    private TextField procategoryText;
    @FXML
    private Button searchProductBtn;
    @FXML
    private Button updateProductBtn;
    @FXML
    private Button deleteProductBtn;
    @FXML
    private Button searchProductsBtn;
    @FXML
    private TableColumn<Products,Integer> proIdColumn;
    @FXML
    private TableColumn<Products, String> proNameColumn;
    @FXML
    private TableColumn<Products, Integer> proPriceColumn;
    @FXML
    private TableColumn<Products, Integer> proStockColumn;
    @FXML
    private TableColumn<Products, String> proCategoryColumn;

    /**
     * Initializes the controller class.
     */


    @FXML
    private void searchProduct(ActionEvent event) throws ClassNotFoundException, SQLException {
        try{
            Products pro = ProductsDAO.searchProduct(proidText.getText());
           // Integer.parseInt(proidText.getText())
            //proidText.setText(pro.getProductid().toString());
           // pronameText.setText(pro.getProductname());
            //propriceText.setText(pro.getProductprice().toString());
            //prostockText.setText(pro.getProductstock().toString());
            //procategoryText.setText(pro.getProductcategory());


            populateAndShowProduct(pro);
        }
        catch (SQLException e){
            e.printStackTrace();
            resultArea.setText("error occured while gettin info from db");
            throw e;
        }
    }
    @FXML
    private void searchProducts(ActionEvent event) throws SQLException, ClassNotFoundException {
        try{
            ObservableList<Products> proData = ProductsDAO.searchProducts();
            populateProducts(proData);

        }
        catch (SQLException e){
            System.out.println("error ");
            throw e;
        }
    }
    @FXML
    private void initialize () {
        /*
        The setCellValueFactory(...) that we set on the table columns are used to determine
        which field inside the Employee objects should be used for the particular column.
        The arrow -> indicates that we're using a Java 8 feature called Lambdas.
        (Another option would be to use a PropertyValueFactory, but this is not type-safe

        We're only using StringProperty values for our table columns in this example.
        When you want to use IntegerProperty or DoubleProperty, the setCellValueFactory(...)
        must have an additional asObject():
        */
        proIdColumn.setCellValueFactory(cellData -> cellData.getValue().productidProperty().asObject());
        proNameColumn.setCellValueFactory(cellData -> cellData.getValue().productnameProperty());
        proPriceColumn.setCellValueFactory(cellData -> cellData.getValue().productpriceProperty().asObject());
        proStockColumn.setCellValueFactory(cellData -> cellData.getValue().productstockProperty().asObject());
        proCategoryColumn.setCellValueFactory(cellData -> cellData.getValue().productcategoryProperty());

    }
@FXML
private void populateProduct(Products pro ) throws ClassNotFoundException{
        ObservableList<Products> proData = FXCollections.observableArrayList();

        proData.add(pro);
        productTable.setItems(proData);


}
    @FXML
    private void setProInfoToTextArea (Products pro){
        resultArea.setText("ID:"+ pro.getProductid()+"\n"+ "Name:"+pro.getProductname());
    }
    @FXML
    private void populateAndShowProduct(Products pro) throws ClassNotFoundException{
        if(pro != null){
            populateProduct(pro);
            setProInfoToTextArea(pro);

        }
        else{
            resultArea.setText("No Product Like this!\n");
        }
    }
    @FXML
    private void populateProducts(ObservableList<Products> proData) throws ClassNotFoundException
    {
        productTable.setItems(proData);
    }

    @FXML
    private void updateProductInfo(ActionEvent event) throws SQLException {
        try{
            String id = proidText.getText();
            String name = pronameText.getText();
            int price = Integer.parseInt(propriceText.getText());
           int stock = Integer.parseInt(prostockText.getText());
            String cat = procategoryText.getText();
            ProductsDAO.updateProductInfo(id,name,price,stock,cat);
            resultArea.setText("updated");
        }
        catch (SQLException e){
            resultArea.setText("problem with db");
        }
    }
    @FXML
    public void insertProduct(ActionEvent actionEvent) throws SQLException {
        try {

            int id = Integer.parseInt(idText.getText());
            String name = nameText.getText();
            int price = Integer.parseInt(priceText.getText());
            int stock = Integer.parseInt(stockText.getText());
            String cat = categoryText.getText();
            ProductsDAO.insertProducts(id,name,price,stock,cat);
            resultArea.setText("Product inserted");
        }
        catch (SQLException e){
            resultArea.setText("problem with db" + e);
            throw e;
        }
    }
    @FXML
    private void deleteProduct(ActionEvent event) throws SQLException {
        try{
            int x = Integer.parseInt(proidText.getText());
            ProductsDAO.deleteProductWid(x);
            resultArea.setText("Product deleted"+proidText.getText());

        } catch (SQLException e){
            resultArea.setText("problem with db" + e);
            throw e;
        }
    }


    public void setProinf (ActionEvent actionEvent) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizza", "root", "samit0713");
        int id = Integer.parseInt(proidText.getText());
        Statement stmt = conn.createStatement();
                //"SELECT productname FROM products Where productid ="+id;
        //PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(stmt);

        try{
            ResultSet rs = stmt.executeQuery( "SELECT productname,productprice,productstock,productcategory FROM products Where productid ="+id);
while (rs.next()){
    String name = rs.getString("productname");
    int price = rs.getInt("productprice");
    int stock = rs.getInt("productstock");
    String cat = rs.getString("productcategory");

    pronameText.setText(name);
    propriceText.setText(String.valueOf(price));
    prostockText.setText(String.valueOf(stock));
    procategoryText.setText(cat);

}
                    //preparedStatement.executeUpdate();

        }
        catch (SQLException e){
            throw e;
        }
    }
}
