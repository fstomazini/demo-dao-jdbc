package com.company;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Department obj = new Department(1,"Devolop");
        Seller seller  = new Seller(1,"Felipe","felipestomazini@gmail.com", new Date(), 2000.00, obj);
        System.out.println(seller);
        SellerDao sellerDao = DaoFactory.creatSellerDao();
    }
}
