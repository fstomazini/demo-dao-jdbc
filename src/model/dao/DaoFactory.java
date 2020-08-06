package model.dao;

import db.DB;
import model.dao.sellerdao.SellerDaoJDBC;

public class DaoFactory {

    public static SellerDao creatSellerDao(){
        return new SellerDaoJDBC(DB.getConnection());
    }
}
