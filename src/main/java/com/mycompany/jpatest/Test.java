package com.mycompany.jpatest;

import java.util.logging.*;

/**
 * @author Rene
 */
public class Test {

	public static void main(String[] args) {
            LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE);
            
            CustomerDBMapper dao = new CustomerDBMapper();

            // dao.addCustomer(1, "Sue", "Smith");
            // dao.addCustomer(2, "Sam", "Smith");
            // dao.addCustomer(3, "Sid", "Smith");
            // dao.addCustomer(4, "Sally", "Smith");
            // dao.changeFName(4, "Mark");
            // dao.deleteCustomer(3);
            dao.getCustomers();
	}

}
