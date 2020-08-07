package model.dao.departmentdao;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection connection;

    public DepartmentDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Department obj) {

    }

    @Override
    public void update(Department obj) {

    }

    @Override
    public Department deleteById(Integer id) {
        return null;
    }

    @Override
    public Department findById(Integer id) {
        return null;
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
