package model.dao.sellerdao;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {
   private Connection connection;

    public SellerDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Seller obj) {
        PreparedStatement st = null;
        try{
             st=connection.prepareStatement("INSERT INTO seller " +
                     "(Name, Email, BirthDate, BaseSalary, DepartmentId) " +
                     "VALUES " +
                     "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

              st.setString(1, obj.getName());
              st.setString(2, obj.getEmail());
              st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
              st.setDouble(4, obj.getBaseSalary());
              st.setInt(5,obj.getDepartment().getId());

              int rowsAffected = st.executeUpdate();

              if (rowsAffected > 0){
                  ResultSet rs = st.getGeneratedKeys();
                  if (rs.next()){
                      int id = rs.getInt(1);
                      obj.setId(id);
                      DB.closeResultSet(rs);
                  }

              }else{
                  throw new DbException("Unespected error! no rows affected!");
              }
        }catch(SQLException e){
            throw new DbException("DB error: "+ e.getMessage());
        }finally {

            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps =  connection.prepareStatement("SELECT seller.*,department.Name as DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON seller.DepartmentId = department.Id " +
                    "WHERE seller.Id = ?");

            ps.setInt(1,id);
            rs = ps.executeQuery();
            if(rs.next()){
                Department department = instantiateDepartment(rs);
                Seller seller = instantiateSeller(rs, department);
                return seller;

            }return null;

        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Seller> list = new ArrayList<>();
        try{
            ps =  connection.prepareStatement("SELECT seller.*,department.Name as DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON seller.DepartmentId = department.Id " +
                    "WHERE department.id = ? "+
                    "ORDER BY NAME ");

            ps.setInt(1,department.getId());
            rs = ps.executeQuery();
            Map<Integer,Department> map = new HashMap<>();

                while(rs.next()) {
                    Department dep = map.get(rs.getInt("DepartmentId"));
                            if(dep == null){
                                dep=instantiateDepartment(rs);
                                map.put(rs.getInt("DepartmentId"), dep);
                            }


                    Seller seller = instantiateSeller(rs, dep);
                    list.add(seller);
                }
                return list;

        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }

    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Seller> list = new ArrayList<>();
        try{
            ps =  connection.prepareStatement("SELECT seller.*,department.Name as DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON seller.DepartmentId = department.Id " +
                    "ORDER BY NAME ");


            rs = ps.executeQuery();
            Map<Integer,Department> map = new HashMap<>();

            while(rs.next()) {
                Department dep = map.get(rs.getInt("DepartmentId"));
                if(dep == null){
                    dep=instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }


                Seller seller = instantiateSeller(rs, dep);
                list.add(seller);
            }
            return list;

        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeResultSet(rs);
            DB.closeStatement(ps);
        }
    }

    private Seller instantiateSeller(ResultSet rs, Department department) throws SQLException{
        Seller seller = new Seller();
        seller.setId(rs.getInt("Id"));
        seller.setName(rs.getString("Name"));
        seller.setEmail(rs.getString("Email"));
        seller.setBirthDate(rs.getDate("BirthDate"));
        seller.setBaseSalary(rs.getDouble("BaseSalary"));
        seller.setDepartment(department);
        return seller;
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department department = new Department();
        department.setId(rs.getInt("DepartmentId"));
        department.setName(rs.getString("DepName"));
        return department;
    }

}
