package com.company;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class Main {

    public static void main(String[] args) {

        System.out.println("==== Test SellerDao =====");
        SellerDao sellerDao = DaoFactory.creatSellerDao();
        Seller seller  = sellerDao.findById(7);
        System.out.println(seller);

    }
}
