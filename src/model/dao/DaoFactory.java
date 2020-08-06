package model.dao;

import model.dao.sellerdao.SellerDaoJDBC;

public class DaoFactory {

    public static SellerDao creatSellerDao(){
        return new SellerDaoJDBC();
    }
}
