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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.edu.nus.iss.phoenix.core.dao.DBConstants;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.dao.ProgramSlotDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 *
 * @author default
 */
public class ProgramSlotDaoImpl implements ProgramSlotDAO {

    private static final String DELIMITER = ":";
    private static final Logger logger = Logger.getLogger(ProgramSlotDaoImpl.class.getName());

    Connection connection;

    public ProgramSlotDaoImpl() {
        super();
        // TODO Auto-generated constructor stub
        connection = openConnection();
    }

    @Override
    public ProgramSlot createValueObject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ProgramSlot getObject(String name) throws NotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void load(ProgramSlot valueObject) throws NotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProgramSlot> loadAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(ProgramSlot valueObject) throws SQLException {
        /* insert into `phoenix`.`program-slot` values(003000,'2011-10-24', '011-10-24 00:30:00', 'dance floor');*/
        String sql = "";
        PreparedStatement stmt = null;

        try {
            sql = "INSERT INTO `program-slot` ( duration, dateOfProgram, startTime, `program-name`, presenter, producer) VALUES (?, ?, ?, ?) ";
            stmt = this.connection.prepareStatement(sql);

            stmt.setString(1, valueObject.getDuration());
            stmt.setString(2, valueObject.getDateOfProgram());
            stmt.setString(3, valueObject.getStartTime());
           // stmt.setString(4, valueObject.r());

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
    public void save(ProgramSlot valueObject) throws NotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(ProgramSlot valueObject) throws NotFoundException, SQLException {
        /*logic to delete program slot from program-slot table*/
        if (valueObject.getDuration() == null && valueObject.getDateOfProgram()== null) {
			// System.out.println("Can not delete without Primary-Key!");
			throw new NotFoundException("Can not delete without Primary-Key!");
		}

		String sql = "DELETE FROM `program-slot` WHERE (`duration` = ? ) AND ('dateOfProgram' = ?); ";
		PreparedStatement stmt = null;
		openConnection();
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, valueObject.getDuration());
                        stmt.setString(2, valueObject.getDateOfProgram());
                        

			int rowcount = databaseUpdate(stmt);
			if (rowcount == 0) {
				// System.out.println("Object could not be deleted (PrimaryKey not found)");
				throw new NotFoundException(
						"Object could not be deleted! (PrimaryKey not found)");
			}
			if (rowcount > 1) {
				// System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
				throw new SQLException(
						"PrimaryKey Error when updating DB! (Many objects were deleted!)");
			}
		} finally {
			if (stmt != null)
				stmt.close();
			closeConnection();
		}
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
    public List<ProgramSlot> searchMatching(ProgramSlot valueObject) throws SQLException {
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
