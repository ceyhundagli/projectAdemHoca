package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Customer {
    private IntegerProperty customerid;
    private StringProperty customername;
    private StringProperty customercity;
    private IntegerProperty customerbalance;

    public Customer() {
        this.customerid = new SimpleIntegerProperty();
        this.customername = new SimpleStringProperty();
        this.customercity = new SimpleStringProperty();
        this.customerbalance = new SimpleIntegerProperty();
    }

    public int getCustomerid() {
        return customerid.get();
    }

    public IntegerProperty customeridProperty() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid.set(customerid);
    }

    public String getCustomername() {
        return customername.get();
    }

    public StringProperty customernameProperty() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername.set(customername);
    }

    public String getCustomercity() {
        return customercity.get();
    }

    public StringProperty customercityProperty() {
        return customercity;
    }

    public void setCustomercity(String customercity) {
        this.customercity.set(customercity);
    }

    public int getCustomerbalance() {
        return customerbalance.get();
    }

    public IntegerProperty customerbalanceProperty() {
        return customerbalance;
    }

    public void setCustomerbalance(int customerbalance) {
        this.customerbalance.set(customerbalance);
    }
}
