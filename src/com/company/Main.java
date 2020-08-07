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
        System.out.println("==== Test 1:  SellerDao findByID=====");
        SellerDao sellerDao = DaoFactory.creatSellerDao();
        Seller seller  = sellerDao.findById(7);
        System.out.println(seller);
        System.out.println("==== Test 2:  SellerDao findByDepartment =====");
        Department department = new Department(2,null);
        list  = sellerDao.findByDepartment(department);
        list.forEach(System.out::println);
        System.out.println("==== Test 3:  SellerDao findAll =====");
        list  = sellerDao.findAll();
        list.forEach(System.out::println);
        System.out.println("==== Test 4:  SellerDao Insert =====");
        Seller newSeller = new Seller(null,"Murilo Feijo","murilo@hotmail.com", new Date(), 2000.00, department);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! new id = " + newSeller.getId());

    }
}
