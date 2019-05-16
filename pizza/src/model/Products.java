package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Products {
    private IntegerProperty productid;
    private StringProperty productname;
    private IntegerProperty productprice;
    private IntegerProperty productstock;
    private StringProperty productcategory;




    public Products() {
        this.productid = new SimpleIntegerProperty();
        this.productname = new SimpleStringProperty();
        this.productprice = new SimpleIntegerProperty();
        this.productstock = new SimpleIntegerProperty();
        this.productcategory = new SimpleStringProperty();
    }
    public int getProductid() {
        return productid.get();
    }

    public IntegerProperty productidProperty() {
        return productid;
    }

    public void setProductid(int productId) {
        this.productid.set(productId);
    }

    public String getProductname() {
        return productname.get();
    }

    public StringProperty productnameProperty() {
        return productname;
    }

    public void setProductname(String productName) {
        this.productname.set(productName);
    }

    public int getProductprice() {
        return productprice.get();
    }

    public IntegerProperty productpriceProperty() {
        return productprice;
    }

    public void setProductprice(int productPrice) {
        this.productprice.set(productPrice);
    }

    public int getProductstock() {
        return productstock.get();
    }

    public IntegerProperty productstockProperty() {
        return productstock;
    }

    public void setProductstock(int productStock) {
        this.productstock.set(productStock);
    }

    public String getProductcategory() {
        return productcategory.get();
    }

    public StringProperty productcategoryProperty() {
        return productcategory;
    }

    public void setProductcategory(String productCategory) {
        this.productcategory.set(productCategory);
    }
}
