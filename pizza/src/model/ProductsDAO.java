package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.ConnectionUtil;

import java.sql.*;

public class ProductsDAO {

        public static Products searchProduct (String id) throws SQLException, ClassNotFoundException {
            String selectStmt = "SELECT * FROM products WHERE productid = "+id;
            //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/swingapp", "root", "1234");

           // PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(selectStmt);
           // preparedStatement.setString(1, id);

            try {
                ResultSet rsEmp = ConnectionUtil.dbExecuteQuery(selectStmt);
               // ResultSet rsEmp = preparedStatement.executeQuery(selectStmt);

                Products product = getProductFromResultSet(rsEmp);

                return product;
            } catch (SQLException e) {
                System.out.println("error searchproduct");
                //e.printStackTrace();
                throw e;

            }

        }

        private static Products getProductFromResultSet(ResultSet rs) throws SQLException {

            Products pro = null;
            if(rs.next()){
                pro = new Products();
                pro.setProductid(rs.getInt("productid"));
                pro.setProductname(rs.getString("productname"));
                pro.setProductprice(rs.getInt("productprice"));
                pro.setProductstock(rs.getInt("productstock"));
                pro.setProductcategory(rs.getString("productcategory"));

            }
            return pro;

        }



        public static ObservableList<Products> searchProducts() throws ClassNotFoundException, SQLException {
            String selectStmt = "SELECT * FROM products";

            try {
                ResultSet rsProducts = ConnectionUtil.dbExecuteQuery(selectStmt);

                ObservableList<Products>  productsList = getProductList(rsProducts);

                return productsList;
            } catch (SQLException e) {
                System.out.println("searchProducts error");
                throw e;

                //e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw e;
            }

        }


        private static ObservableList<Products> getProductList(ResultSet rs) throws SQLException {
            ObservableList<Products> productsList = FXCollections.observableArrayList();

            while(rs.next()){
                Products pro = new Products();
                pro.setProductid(rs.getInt(1));
                pro.setProductname(rs.getString(2));
                pro.setProductprice(rs.getInt(3));
                pro.setProductstock(rs.getInt(4));
                pro.setProductcategory(rs.getString(5));

                productsList.add(pro);
            }
            return productsList;
        }

        public static void updateProductInfo (String proid, String proname,int proprice,
                                              int prostock, String procategory) throws SQLException {
            String updateStmt = "UPDATE products SET productname = ?, productprice = ?, productstock = ?, productcategory = ?  WHERE productid = ?";
            //and productprice=? and productstock=? and productcategory=?
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizza", "root", "samit0713");
            PreparedStatement preparedStatement = (PreparedStatement)conn.prepareStatement(updateStmt);
            preparedStatement.setString(1,proname);
            preparedStatement.setInt(2,proprice);
            preparedStatement.setInt(3,prostock);
            preparedStatement.setString(4,procategory);
            preparedStatement.setString(5,proid);

            try {
                preparedStatement.executeUpdate();
            }
            catch (SQLException e){
                System.out.println("error updateproductinfo");
                throw e;
            }

        }

        public static void deleteProductWid (int id) throws SQLException {
            String updateStmt = "DELETE FROM products WHERE productid= ?";
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizza", "root", "samit0713");

            PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(updateStmt);
            preparedStatement.setInt(1, id);
            try{
                preparedStatement.executeUpdate();

            }
            catch (SQLException e){
                System.out.println("error deleteProductwid");
                throw  e;
            }
        }

        public static void insertProducts(int id,String name,int price, int stock,String category) throws SQLException {
            String updateStmt = "INSERT INTO products (productid , productname, productprice, productstock, productcategory) VALUES (?,?,?,?,?)";
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pizza", "root", "samit0713");
            PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(updateStmt);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, price);
            preparedStatement.setInt(4, stock);
            preparedStatement.setString(5, category);
            try {
                preparedStatement.executeUpdate();
            }
            catch (SQLException e){
                System.out.println("error insertProducts");
                throw e;
            }
        }



}
