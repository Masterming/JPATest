package com.mycompany.jpatest;

import java.io.Serializable;
import javax.persistence.*;

/**
 * @author Rene
 */

@Entity
@Table(name = "customer")
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "custID", unique = true)
    private int id;

    @Column(name = "firstName", nullable = false)
    private String fName;

    @Column(name = "lastName", nullable = false)
    private String lName;
    
    public Customer(){}

    public Customer(int id, String fName, String lName){
        this.id = id;
        this.fName = fName;
        this.lName = lName;
    }

    public int getID() {
        return id;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String toString(){
        return fName + " " + lName;
    }
}