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
import sg.edu.nus.iss.phoenix.core.dao.DBConstants;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.dao.ProgramSlotDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 *
 * @author Vipul Zambare.
 */
public class ProgramSlotDaoImpl implements ProgramSlotDAO {

    private static final String DELIMITER = ":";
    private static final Logger logger = Logger.getLogger(ProgramSlotDaoImpl.class.getName());

    Connection connection;

    public ProgramSlotDaoImpl() {
        super();
        connection = openConnection();
    }

    @Override
    public ProgramSlot createValueObject() {
        return new ProgramSlot();
    }

    @Override
    public ProgramSlot getObject(String programDate, String startTime) throws NotFoundException, SQLException {
        ProgramSlot valueObject = createValueObject();
        valueObject.setDateOfProgram(programDate);
        valueObject.setStartTime(startTime);
        load(valueObject);
        return valueObject;
    }

    @Override
    public void load(ProgramSlot valueObject) throws NotFoundException, SQLException {
        if (valueObject.getDateOfProgram() == null || valueObject.getStartTime() == null) {
            throw new NotFoundException("Can not select without Primary-Key!");
        }

        String sql = "SELECT * FROM `program-slot` WHERE (`startTime` = ? )  AND (`dateOfProgram` = ?); ";
        PreparedStatement stmt = null;
        openConnection();
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, valueObject.getStartTime());
            stmt.setString(2, valueObject.getDateOfProgram());

            singleQuery(stmt, valueObject);

        } finally {
            if (stmt != null) {
                stmt.close();
            }
            closeConnection();
        }
    }

    @Override
    public List<ProgramSlot> loadAll() throws SQLException {
        openConnection();
        String sql = "SELECT * FROM `program-slot` ORDER BY `dateOfProgram` ASC; ";
        List<ProgramSlot> searchResults = listQuery(connection
                .prepareStatement(sql));
        closeConnection();
        System.out.println("record size" + searchResults.size());
        return searchResults;
    }

    @Override
    public void create(ProgramSlot valueObject) throws SQLException {

        String sql = "";
        PreparedStatement stmt = null;

        try {
            sql = "INSERT INTO `program-slot` ( duration, dateOfProgram, startTime, endTime, `program-name`, presenterId, producerId) VALUES (?, ?, ?, ?, ?, ?, ?) ";
            stmt = this.connection.prepareStatement(sql);

            stmt.setString(1, valueObject.getDuration());
            stmt.setString(2, valueObject.getDateOfProgram());
            stmt.setString(3, valueObject.getStartTime());
            stmt.setString(4, valueObject.getEndTime());
            stmt.setString(5, valueObject.getRadioProgramId());
            stmt.setString(6, valueObject.getPresenterId());
            stmt.setString(7, valueObject.getProducerId());

            int rowcount = databaseUpdate(stmt);
            if (rowcount != 1) {
                throw new SQLException("PrimaryKey Error when updating DB!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
                closeConnection();
            }
        }
    }

    @Override
    public void save(ProgramSlot valueObject) throws NotFoundException, SQLException {
        String sql = "UPDATE `program-slot` SET `duration` = ?, `endTime` = ?, `program-name` = ?, `presenterId` = ?, `producerId` = ? WHERE (`startTime` = ? ) AND (`dateOfProgram` = ?); ";

        PreparedStatement stmt = null;
        openConnection();
        try {
            stmt = this.connection.prepareStatement(sql);

            stmt.setString(1, valueObject.getDuration());

            stmt.setString(2, valueObject.getEndTime());
            stmt.setString(3, valueObject.getRadioProgramId());
            stmt.setString(4, valueObject.getPresenterId());
            stmt.setString(5, valueObject.getProducerId());
            stmt.setString(6, valueObject.getStartTime());
            stmt.setString(7, valueObject.getDateOfProgram());

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

    @Override
    public void delete(ProgramSlot valueObject) throws NotFoundException, SQLException {

        String sql = "";
        PreparedStatement stmt = null;

        if (valueObject.getStartTime() == null && valueObject.getDateOfProgram() == null) {
            throw new NotFoundException("Can not delete without Primary-Key!");
        }

        try {
            sql = "DELETE FROM `program-slot` WHERE (`startTime` = ? ) AND ( `dateOfProgram` = ? ) ; ";
            stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, valueObject.getStartTime());
            stmt.setString(2, valueObject.getDateOfProgram());

            int rowcount = databaseUpdate(stmt);
            if (rowcount == 0) {
                throw new NotFoundException(
                        "Object could not be deleted! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                throw new SQLException(
                        "PrimaryKey Error when updating DB! (Many objects were deleted!)");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
                closeConnection();
            }
        }
    }

    @Override
    public int countAll() throws SQLException {

        String sql = "SELECT count(*) FROM `program-slot` ";
        PreparedStatement stmt = null;
        ResultSet result = null;
        int allRows = 0;
        openConnection();
        try {
            stmt = connection.prepareStatement(sql);
            result = stmt.executeQuery();

            if (result.next()) {
                allRows = result.getInt(1);
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
        return allRows;
    }

    @Override
    public void copy() throws NotFoundException, SQLException {
        /* Copy schedule code will come here */
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
            e.printStackTrace();
        }
    }

    /**
     *
     * @param valueObject
     * @return
     * @throws SQLException
     */
    @Override
    public boolean isProgramSlotExists(ProgramSlot valueObject) throws SQLException {
        String sql = "";
        PreparedStatement stmt = null;
        ResultSet result = null;

        try {
            sql = "SELECT * FROM `phoenix`.`program-slot` WHERE (dateOfProgram = ? AND ? BETWEEN startTime AND endTime ) ; ";
            stmt = this.connection.prepareStatement(sql);

            stmt.setString(1, valueObject.getDateOfProgram());
            stmt.setString(2, valueObject.getStartTime());

            result = stmt.executeQuery();
            if (result.next()) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return true;
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
    protected List<ProgramSlot> listQuery(PreparedStatement stmt) throws SQLException {

        ArrayList<ProgramSlot> searchResults = new ArrayList<>();
        ResultSet result = null;
        openConnection();
        try {
            result = stmt.executeQuery();

            while (result.next()) {
                ProgramSlot temp = createValueObject();

                temp.setDateOfProgram(result.getString("dateOfProgram"));
                temp.setStartTime(result.getString("startTime"));
                temp.setRadioProgramId(result.getString("program-name"));
                temp.setPresenterId(result.getString("presenterId"));
                temp.setProducerId(result.getString("producerId"));
                temp.setDuration(result.getString("duration"));

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

        return (List<ProgramSlot>) searchResults;
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
    protected void singleQuery(PreparedStatement stmt, ProgramSlot valueObject)
            throws NotFoundException, SQLException {

        ResultSet result = null;
        openConnection();
        try {
            result = stmt.executeQuery();

            if (result.next()) {

                ProgramSlot temp = createValueObject();

                temp.setDateOfProgram(result.getString("dateOfProgram"));
                temp.setStartTime(result.getString("startTime"));
                temp.setRadioProgramId(result.getString("program-name"));
                temp.setPresenterId(result.getString("presenterId"));
                temp.setProducerId(result.getString("producerId"));
                temp.setDuration(result.getString("duration"));

            } else {
                throw new NotFoundException("Program-Slot Object Not Found!");
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

}
