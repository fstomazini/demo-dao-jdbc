package model.dao.departmentdao;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection connection;

    public DepartmentDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st=connection.prepareStatement("INSERT INTO Department (Name) Values (?) ", Statement.RETURN_GENERATED_KEYS);
            st.setString(1,obj.getName());
            st.executeUpdate();
            rs=st.getGeneratedKeys();
             if(rs.next()){
                 obj.setId(rs.getInt(1));
             }


            } catch (SQLException e){
            throw new DbException("DB Error! "+e.getMessage());
        }finally {

            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Department obj) {
        PreparedStatement st = null;

        try{
            st=connection.prepareStatement("UPDATE Department  SET Name = ? WHERE Id = ?");
            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());
            st.executeUpdate();


            }catch (SQLException e){
            throw new DbException("DB Error! "+e.getMessage());
            }finally {

            DB.closeStatement(st);
            }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;

        try{
            st=connection.prepareStatement("DELETE FROM Department WHERE Id = ?");
            st.setInt(1, id);
            st.executeUpdate();


            } catch (SQLException e){
                throw new DbException("DB Error! "+e.getMessage());
        }finally {

            DB.closeStatement(st);
    }
}

    @Override
    public Department findByName(String name) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st=connection.prepareStatement("SELECT * FROM Department "+
                    "WHERE Name = ? ");
            st.setString(1, name);
            rs = st.executeQuery();
            if (rs.next()){
                return instantiateDepartment(rs);

            }
            return null;
        }catch (SQLException e){
            throw new DbException("DB Error! "+e.getMessage());
        }finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st=connection.prepareStatement("SELECT * FROM Department "+
                                                "WHERE Id = ? ");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()){
               return instantiateDepartment(rs);

            }
            return null;
        }catch (SQLException e){
            throw new DbException("DB Error! "+e.getMessage());
        }finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }

    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Department> list = new ArrayList<>();
        try{
            st=connection.prepareStatement("SELECT * FROM Department ");
            rs = st.executeQuery();
            while (rs.next()){
                list.add(instantiateDepartment(rs));
            }
            return list;
        }catch (SQLException e){
            throw new DbException("DB Error! "+e.getMessage());
        }finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department department = new Department();
        department.setId(rs.getInt("Id"));
        department.setName(rs.getString("Name"));
        return department;
    }
}
