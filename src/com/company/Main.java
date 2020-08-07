package com.company;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Seller> list = new ArrayList<>();
        System.out.println("==== Test SellerDao findByID=====");
        SellerDao sellerDao = DaoFactory.creatSellerDao();
        Seller seller  = sellerDao.findById(7);
        System.out.println(seller);
        System.out.println("==== Test SellerDao findByDepartment =====");
         list  = sellerDao.findByDepartment(new Department(2,"Eletronics"));
        list.forEach(System.out::println);
    }
}
