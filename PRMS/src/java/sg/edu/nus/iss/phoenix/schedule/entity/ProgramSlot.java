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
 * @author VipulZambare
 */
public class ProgramSlot implements Cloneable, Serializable {

    /**
     * eclipse identifier
     */
    private static final long serialVersionUID = -5600218812568593553L;

    private String duration;
    private String dateOfProgram;
    private String assignby;
    private String startTime;
    private String radioProgramId;
    private String presenterId;
    private String producerId;

    public ProgramSlot() {

    }

    public ProgramSlot(String duration, String dateOfProgram, String assignby, String startTime,
            String radioProgramId, String presenterId,
            String producerId) {
        this.duration = duration;
        this.dateOfProgram = dateOfProgram;
        this.assignby = assignby;
        this.startTime = startTime;
        this.radioProgramId = radioProgramId;
        this.presenterId = presenterId;
        this.producerId = producerId;
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

    public String getassignby() {
        return assignby;
    }

    public void setassignby(String assignby) {
        this.assignby = assignby;
    }
    
    public String getEndTime() {
        int durationInt = Integer.parseInt(duration);
        java.sql.Time myTime = java.sql.Time.valueOf(this.startTime);
        LocalTime localtime = myTime.toLocalTime();
        return localtime.plusMinutes(durationInt).toString();
    }
}
