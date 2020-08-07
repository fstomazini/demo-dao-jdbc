package com.company;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        List<Seller> list = new ArrayList<>();
        List<Department> list2 = new ArrayList<>();
        System.out.println("==== Test 1:  SellerDao findByID=====");
        SellerDao sellerDao = DaoFactory.creatSellerDao();
        DepartmentDao departmentDao = DaoFactory.creatDepartmentDao();
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
        System.out.println("==== Test 5:  SellerDao Update =====");
        seller = sellerDao.findById(1);
        seller.setName("Martha Wayne");
        sellerDao.update(seller);
        System.out.println("Update Completed");
        System.out.println("==== Test 6:  SellerDao Delete =====");
        System.out.print("Enter id to delete: ");
        int id = reader.nextInt();
        sellerDao.deleteById(id);
        System.out.println("Delete  Completed");
        System.out.println("==== Test 7:  SellerDao Delete =====");
        list2 = departmentDao.findAll();
        list2.forEach(System.out::println);

        reader.close();

    }
}
