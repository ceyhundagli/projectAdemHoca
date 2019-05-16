package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cart {
    private int cartid;
    private String cartProName;

    public int getCartid() {
        return cartid;
    }

    public void setCartid(int cartid) {
        this.cartid = cartid;
    }

    public String getCartProName() {
        return cartProName;
    }

    public void setCartProName(String cartProName) {
        this.cartProName = cartProName;
    }

    public int getCartProPrice() {
        return cartProPrice;
    }

    public void setCartProPrice(int cartProPrice) {
        this.cartProPrice = cartProPrice;
    }

    public int getCartProQuantity() {
        return cartProQuantity;
    }

    public void setCartProQuantity(int cartProQuantity) {
        this.cartProQuantity = cartProQuantity;
    }

    public int getCartTotalQuantity() {
        return cartTotalQuantity;
    }

    public void setCartTotalQuantity(int cartTotalQuantity) {
        this.cartTotalQuantity = cartTotalQuantity;
    }

    private int cartProPrice;
    private int cartProQuantity;
    private int cartTotalQuantity;

    public Cart(int cartid, String cartProName, int cartProPrice, int cartProQuantity, int cartTotalQuantity) {
        this.cartid = cartid;
        this.cartProName = cartProName;
        this.cartProPrice = cartProPrice;
        this.cartProQuantity = cartProQuantity;
        this.cartTotalQuantity = cartTotalQuantity;
    }
    public Cart(){

    }

   /* public Cart(int cartid, String,) {
        this.cartid = new SimpleIntegerProperty();
        this.cartProName = new SimpleStringProperty();
        this.cartProPrice = new SimpleIntegerProperty();
        this.cartProQuantity = new SimpleIntegerProperty();
        this.cartTotalQuantity = new SimpleIntegerProperty();
    } */






}
