/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Time;
import java.time.LocalTime;

/**
 *
 * @author default
 */
public class ProgramSlot implements Cloneable, Serializable {

    /**
     * eclipse identifier
     */
    private static final long serialVersionUID = -5600218812568593553L;

    private String duration;
    private String dateOfProgram;
    private String startTime;
    private String radioProgramId;
    private String presenterId;
    private String producerId;
    private String assignedBy;

    public ProgramSlot() {

    }

    public ProgramSlot(String duration, String dateOfProgram, String startTime,
            String radioProgramId, String presenterId,
            String producerId, String assignedBy) {
        this.duration = duration;
        this.dateOfProgram = dateOfProgram;
        this.startTime = startTime;
        this.radioProgramId = radioProgramId;
        this.presenterId = presenterId;
        this.producerId = producerId;
        this.assignedBy = assignedBy;
    }

    /**
     * Get- and Set-methods for persistent variables. The default behavior does
     * not make any checks against malformed data, so these might require some
     * manual additions.
     *
     * @return
     */
    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDateOfProgram() {
        return this.dateOfProgram;
    }

    public void setDateOfProgram(String dateOfProgram) {
        this.dateOfProgram = dateOfProgram;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getPresenterId() {
        return this.presenterId;
    }

    public void setPresenterId(String presenterId) {
        this.presenterId = presenterId;
    }

    public String getProducerId() {
        return this.producerId;
    }

    public void setProducerId(String producerId) {
        this.producerId = producerId;
    }

    public String getRadioProgramId() {
        return this.radioProgramId;
    }

    public void setRadioProgramId(String radioProgramId) {
        this.radioProgramId = radioProgramId;
    }

    public String getassignedBy() {
        return this.assignedBy;
    }

    public void setassignedBy() {
        this.assignedBy = assignedBy;
    }

    public String getEndTime() {
        java.sql.Time myTime = java.sql.Time.valueOf(this.startTime);
        
        LocalTime localtime = myTime.toLocalTime();
        
        java.sql.Time durationUpdated = java.sql.Time.valueOf(this.duration);
        Time durationTime = Time.valueOf(durationUpdated.toString());
        LocalTime durationLocaltime = durationTime.toLocalTime();
          
        
        long durationLong = durationLocaltime.getHour() + durationLocaltime.getMinute() + durationLocaltime.getSecond();
        
        localtime = localtime.plusHours(durationLong);
        return localtime.toString();

//        java.sql.Time myTime = java.sql.Time.valueOf(this.startTime);
//        java.sql.Time durationUpdated = java.sql.Time.valueOf(this.duration);
//
//        Time durationTime = Time.valueOf(durationUpdated.toString());
//        LocalTime durationLocaltime = durationTime.toLocalTime();
//        long durationtimeLong1 = durationLocaltime.;
//
//        long stTimeLong = myTime.getTime();
//        long durationLong = durationUpdated.getTime();
//
//        long endTimeLong = stTimeLong + durationLong;
//
//        return new Time(endTimeLong).toString();
    }

    /**
     * setAll allows to set all persistent variables in one method call. This is
     * useful, when all data is available and it is needed to set the initial
     * state of this object. Note that this method will directly modify instance
     * variables, without going trough the individual set-methods.
     *
     * @param duration
     * @param dateOfProgram
     * @param startTime
     * @param radioProgramId
     * @param producerId
     * @param presenterId
     * @param assignedBy
     */
    public void setAll(String duration, String dateOfProgram, String startTime,
            String radioProgramId, String presenterId,
            String producerId, String assignedBy) {
        this.duration = duration;
        this.dateOfProgram = dateOfProgram;
        this.startTime = startTime;
        this.radioProgramId = radioProgramId;
        this.presenterId = presenterId;
        this.producerId = producerId;
        this.assignedBy = assignedBy;
    }

    /**
     * hasEqualMapping-method will compare two AnnualSchedule instances and
     * return true if they contain same values in all persistent instance
     * variables. If hasEqualMapping returns true, it does not mean the objects
     * are the same instance. However it does mean that in that moment, they are
     * mapped to the same row in database.
     *
     * @param valueObject
     * @return
     */
    public boolean hasEqualMapping(ProgramSlot valueObject) {

        /* if(this.year != valueObject.getYear())
                return false;
        
          if (this.assignedBy == null) {
                    if (valueObject.getAssignedBy()!= null)
                           return(false);
          } else if (!this.assignedBy.equals(valueObject.getAssignedBy())) {
                    return(false);
          }*/
        return true;
    }

    /**
     * toString will return String object representing the state of this
     * valueObject. This is useful during application development, and possibly
     * when application is writing object states in text log.
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        /*  out.append("AnnualSchedule class, mapping to table Annual-Schedule \n");
        out.append("Persistent attributes: \n"); 
        out.append("year = ").append(this.year).append("\n"); 
        out.append("assigned by = ").append(this.assignedBy).append("\n"); */
        return out.toString();
    }

    /**
     * Clone will return identical deep copy of this valueObject. Note, that
     * this method is different than the clone() which is defined in
     * java.lang.Object. Here, the returned cloned object will also have all its
     * attributes cloned.
     *
     * @return
     * @throws java.lang.CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        AnnualSchedule cloned = new AnnualSchedule();

        /* cloned.setYear(this.year); 
        if (this.assignedBy != null)
             cloned.setAssignedBy(this.assignedBy); 
         */
        return cloned;
    }
}
