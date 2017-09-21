/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDaoImpl;
import sg.edu.nus.iss.phoenix.core.dao.DBConstants;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 *
 * @author default
 */
public class ScheduleDaoImpl implements ScheduleDAO {

    private static final String DELIMITER = ":";
    private static final Logger logger = Logger.getLogger(ScheduleDaoImpl.class.getName());
private static final String user = "PointyHead";
    Connection connection;

    public ScheduleDaoImpl() {
        super();
        // TODO Auto-generated constructor stub
        connection = openConnection();
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDao#createValueObject()
     */
    @Override
    public AnnualSchedule createValueObject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AnnualSchedule getObject(String name) throws NotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void load(AnnualSchedule valueObject) throws NotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AnnualSchedule> loadAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(ProgramSlot valueObject) throws SQLException {
        /* logic to create annnual and weekly schedule based on program slot will come here. */
       
//        String year = valueObject.getDateOfProgram().substring(0, 3);
//        String assignedBy = user;
        
        String sql = "";
        PreparedStatement stmt = null;

        try {
            sql = "INSERT INTO `annual-schedule` ( year, assignedBy) VALUES (?, ?) ";
            stmt = this.connection.prepareStatement(sql);

            stmt.setString(1, valueObject.getDuration());
            stmt.setString(2, valueObject.getDateOfProgram());
            stmt.setString(3, valueObject.getStartTime());
           // stmt.setString(4, valueObject.getProgramName());

            int rowcount = databaseUpdate(stmt);
            if (rowcount != 1) {
                throw new SQLException("PrimaryKey Error when updating DB!");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
                closeConnection();
            }
        }
    }

    @Override
    public void save(AnnualSchedule valueObject) throws NotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(AnnualSchedule valueObject) throws NotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll(Connection conn) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int countAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AnnualSchedule> searchMatching(AnnualSchedule valueObject) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Connection openConnection() {
        Connection conn = null;

        try {
            Class.forName(DBConstants.COM_MYSQL_JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
        }

        try {
            conn = DriverManager.getConnection(DBConstants.dbUrl,
                    DBConstants.dbUserName, DBConstants.dbPassword);
        } catch (SQLException e) {
        }
        return conn;
    }

    /**
     * databaseUpdate-method. This method is a helper method for internal use.
     * It will execute all database handling that will change the information in
     * tables. SELECT queries will not be executed here however. The return
     * value indicates how many rows were affected. This method will also make
     * sure that if cache is used, it will reset when data changes.
     *
     * @param stmt This parameter contains the SQL statement to be excuted.
     * @return
     * @throws java.sql.SQLException
     */
    protected int databaseUpdate(PreparedStatement stmt) throws SQLException {

        int result = stmt.executeUpdate();

        return result;
    }
    
    private void closeConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
