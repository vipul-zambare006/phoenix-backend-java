/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
 * @author Vipul Zambare.
 */
public class ScheduleDaoImpl implements ScheduleDAO {

    private static final String DELIMITER = ":";
    private static final Logger logger = Logger.getLogger(ScheduleDaoImpl.class.getName());
    private static final String user = "PointyHead";
    Connection connection;

    public ScheduleDaoImpl() {
        super();
        connection = openConnection();
    }
/**
 * createValueObject. This method creates a ValueObject of type AnnualSchedule 
 * @return AnnualSchedule
 */
    @Override
    public AnnualSchedule createValueObject() {
          return new AnnualSchedule();
    }
/**
 * getObject. This method is used to get the instances of AnnualSchedule
 * @param year
 * @return
 * @throws NotFoundException
 * @throws SQLException 
 */
    @Override
    public AnnualSchedule getObject(int year) throws NotFoundException, SQLException {
        AnnualSchedule valueObject = createValueObject();
        valueObject.setYear(year);
        load(valueObject);
        return valueObject;
    }
/**
 * load. This method takes in valueObject of instance type Program Slot 
 * @param annualSchedule
 * @throws NotFoundException
 * @throws SQLException 
 */
    @Override
    public void load(AnnualSchedule annualSchedule) throws NotFoundException, SQLException  {
        
        String sql = "SELECT * FROM `annual-schedule` WHERE (`year` = ? ); ";
        PreparedStatement stmt = null;
        openConnection();
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, annualSchedule.getYear());
            stmt.setString(1, annualSchedule.getAssignedBy());
            singleQuery(stmt, annualSchedule);
        } 
        finally {
            if (stmt != null) {
                stmt.close();
            }
            closeConnection();
        }
    }
/**
 * loadAll. This method gets the list of Annual Schedule
 * @return ListOf Annual Schedules
 * @throws SQLException 
 */
    @Override
    public List<AnnualSchedule> loadAll() throws SQLException {
        openConnection();
        String sql = "SELECT * FROM `annual-schedule` ORDER BY `year` ASC; ";
        List<AnnualSchedule> searchResults = listQuery(connection
                .prepareStatement(sql));
        closeConnection();
        System.out.println("record size" + searchResults.size());
        return searchResults;
    }
/**
 * create. This method takes in valueObject of instance type ProgramSlot 
 * @param valueObject
 * @throws SQLException 
 */
    @Override
    public void create(ProgramSlot valueObject) throws SQLException {
        try {
            createAnnualSchedule(valueObject);
            createWeeklySchedule(valueObject);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
    }
/**
 * save. This method is used to save the instances of ProgramSlot valueObject 
 * @param valueObject
 * @throws NotFoundException
 * @throws SQLException 
 */
    @Override
    public void save(AnnualSchedule valueObject) throws NotFoundException, SQLException {
         String sql = "UPDATE `annual-schedule` SET `assingedBy` = ? WHERE (`year` = ? ); ";

        PreparedStatement stmt = null;
        openConnection();
        try {
            stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, valueObject.getAssignedBy());
            stmt.setInt(2, valueObject.getYear());

            int rowcount = databaseUpdate(stmt);
            if (rowcount == 0) {
                throw new NotFoundException(
                        "Object could not be saved! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                throw new SQLException(
                        "PrimaryKey Error when updating DB! (Many objects were affected!)");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            closeConnection();
        }
    }
/**
 * delete. This method is used to delete the programSlot it takes in the valueObject instances of it.
 * @param valueObject
 * @throws NotFoundException
 * @throws SQLException 
 */
    @Override
    public void delete(AnnualSchedule valueObject) throws NotFoundException, SQLException 
    {
        String sql = "DELETE FROM `annual-schedule` WHERE (`year` = ? ) ; ";
        PreparedStatement stmt = null;
        openConnection();
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, valueObject.getYear());
         
            int rowcount = databaseUpdate(stmt);
            if (rowcount == 0) {
                throw new NotFoundException(
                        "Object could not be deleted! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                throw new SQLException(
                        "PrimaryKey Error when updating DB! (Many objects were deleted!)");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            closeConnection();
        }
    }
/**
 * openConnection. This method is used for open connection to the database.
 * @return 
 */
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
/**
 * closeConnection. This method is used to close connection for the database
 */
    private void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
/**
 * isScheduleExists. This method is to check if the isScheduleExists in the program slot
 * it does the validation check.
 * @param valueObject
 * @return
 * @throws SQLException 
 */
    @Override
    public boolean isScheduleExists(ProgramSlot valueObject) throws SQLException {
        String sql = "";
        PreparedStatement stmt = null;
        ResultSet result = null;
        String year = valueObject.getDateOfProgram().substring(0, 4);

        try {
            sql = "SELECT * FROM `phoenix`.`annual-schedule` WHERE (year = ?);";
            stmt = this.connection.prepareStatement(sql);

            stmt.setString(1, year);

            result = stmt.executeQuery();
            if (result.next()) {
                return true;
                //throw new SQLException("New Program-slot is overlapping with existing program-slot");
            }
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return true;
        }
    }
/**
 * createAnnualSchedule. This method is used to create a Annual schedule.
 * @param valueObject. This takes the instance of program slot
 * @throws SQLException 
 */
    public void createAnnualSchedule(ProgramSlot valueObject) throws SQLException {
        /* logic to create annnual and weekly schedule based on program slot will come here. */
        String year = valueObject.getDateOfProgram().substring(0, 4);
        String assignedBy = valueObject.getassignby();

        String sql = "";
        PreparedStatement stmt = null;

        try {
            sql = "INSERT INTO `annual-schedule` ( year, assingedBy) VALUES (?, ?) ";

            stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, year);
            stmt.setString(2, assignedBy);

            int rowcount = databaseUpdate(stmt);
            if (rowcount != 1) {
                throw new SQLException("PrimaryKey Error when updating DB!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
/**
 * createWeeklySchedule. This method takes in programSlot and create weekly schedule.
 * @param valueObject
 * @throws SQLException 
 */
    public void createWeeklySchedule(ProgramSlot valueObject) throws SQLException {
        String sql = "";
        PreparedStatement stmt = null;
        String startDate = valueObject.getDateOfProgram();
        String assignedBy = valueObject.getassignby();

        try {
            sql = "INSERT INTO `weekly-schedule` ( startDate, assignedBy) VALUES (?, ?) ";

            stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, startDate);
            stmt.setString(2, assignedBy);

            int rowcount = databaseUpdate(stmt);
            if (rowcount != 1) {
                throw new SQLException("PrimaryKey Error when updating DB!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
     /**
     * databaseQuery-method. This method is a helper method for internal use. It
     * will execute all database queries that will return only one row. The
     * resultset will be converted to valueObject. If no rows were found,
     * NotFoundException will be thrown.
     *
     * @param stmt This parameter contains the SQL statement to be excuted.
     * @param valueObject Class-instance where resulting data will be stored.
     * @throws sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException
     * @throws java.sql.SQLException
     */
    protected void singleQuery(PreparedStatement stmt, AnnualSchedule valueObject)
            throws NotFoundException, SQLException {

        ResultSet result = null;
        openConnection();
        try {
            result = stmt.executeQuery();

            if (result.next()) {
                AnnualSchedule temp = createValueObject();
                temp.setYear(result.getInt("year"));
                temp.setAssignedBy(result.getString("assingnedBy"));
            } 
            else {
                throw new NotFoundException("AnnualSchedule Object Not Found!");
            }
        } finally {
            if (result != null) {
                result.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            closeConnection();
        }
    }
    
     /**
     * databaseQuery-method. This method is a helper method for internal use. It
     * will execute all database queries that will return multiple rows. The
     * resultset will be converted to the List of valueObjects. If no rows were
     * found, an empty List will be returned.
     *
     * @param stmt This parameter contains the SQL statement to be excuted.
     * @return
     * @throws java.sql.SQLException
     */
    protected List<AnnualSchedule> listQuery(PreparedStatement stmt) throws SQLException {

        ArrayList<AnnualSchedule> searchResults = new ArrayList<>();
        ResultSet result = null;
        openConnection();
        try {
            result = stmt.executeQuery();

            while (result.next()) {
                AnnualSchedule temp = createValueObject();
                temp.setYear(result.getInt("year"));
                temp.setAssignedBy(result.getString("assingnedBy"));
            
                searchResults.add(temp);
            }

        } finally {
            if (result != null) {
                result.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            closeConnection();
        }

        return (List<AnnualSchedule>) searchResults;
    }
}
