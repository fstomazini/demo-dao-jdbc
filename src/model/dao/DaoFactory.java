package model.dao;

import db.DB;
import model.dao.departmentdao.DepartmentDaoJDBC;
import model.dao.sellerdao.SellerDaoJDBC;
import model.entities.Department;

public class DaoFactory {

    public static SellerDao creatSellerDao(){
        return new SellerDaoJDBC(DB.getConnection());
    }

    public static DepartmentDao creatDepartmentDao(){
        return new DepartmentDaoJDBC(DB.getConnection());
    }
}
