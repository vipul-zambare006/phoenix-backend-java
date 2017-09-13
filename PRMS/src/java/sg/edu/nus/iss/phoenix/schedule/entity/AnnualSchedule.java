/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.entity;

import java.io.Serializable;
import java.sql.Time;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;

/**
 *
 * @author default
 */
public class AnnualSchedule implements Cloneable, Serializable
{
     /**
	 * eclipse identifier
	 */
	private static final long serialVersionUID = -3300118812568493553L;
	
	/** 
     * Persistent Instance variables. This data is directly 
     * mapped to the columns of database table.
     */
    private int year;
    private String assignedBy;
    
     /** 
     * Constructors. 
     * The first one takes no arguments and provides the most simple
     * way to create object instance. The another one takes two
     * argument, which is the primary key of the corresponding table and assignedBy person
     */

    public AnnualSchedule () {

    }

    public AnnualSchedule (int year, String assignedBy) {
          this.year = year;
          this.assignedBy = assignedBy;
    }

    /** 
     * Get- and Set-methods for persistent variables. The default
     * behavior does not make any checks against malformed data,
     * so these might require some manual additions.
     * @return 
     */

    public int getYear() {
          return this.year;
    }
    public void setYear(int year) {
          this.year = year;
    }

    public String getAssignedBy() {
          return this.assignedBy;
    }
    public void setAssignedBy(String assignedBy) {
          this.assignedBy = assignedBy;
    }

    /** 
     * setAll allows to set all persistent variables in one method call.
     * This is useful, when all data is available and it is needed to 
     * set the initial state of this object. Note that this method will
     * directly modify instance variables, without going trough the 
     * individual set-methods.
     * @param year
     * @param assignedBy
     */

      public void setAll (int year, String assignedBy) {
          this.year = year;
          this.assignedBy = assignedBy;
    }
      
    /** 
     * hasEqualMapping-method will compare two AnnualSchedule instances
     * and return true if they contain same values in all persistent instance 
     * variables. If hasEqualMapping returns true, it does not mean the objects
     * are the same instance. However it does mean that in that moment, they 
     * are mapped to the same row in database.
     * @param valueObject
     * @return 
     */
    public boolean hasEqualMapping(AnnualSchedule valueObject) {

          if(this.year != valueObject.getYear())
                return false;
        
          if (this.assignedBy == null) {
                    if (valueObject.getAssignedBy()!= null)
                           return(false);
          } else if (!this.assignedBy.equals(valueObject.getAssignedBy())) {
                    return(false);
          }
          return true;
    }

    /**
     * toString will return String object representing the state of this 
     * valueObject. This is useful during application development, and 
     * possibly when application is writing object states in text log.
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("AnnualSchedule class, mapping to table Annual-Schedule \n");
        out.append("Persistent attributes: \n"); 
        out.append("year = ").append(this.year).append("\n"); 
        out.append("assigned by = ").append(this.assignedBy).append("\n"); 
        return out.toString();
    }

    /**
     * Clone will return identical deep copy of this valueObject.
     * Note, that this method is different than the clone() which
     * is defined in java.lang.Object. Here, the returned cloned object
     * will also have all its attributes cloned.
     * @return 
     * @throws java.lang.CloneNotSupportedException 
     */
        @Override
    public Object clone() throws CloneNotSupportedException {
        AnnualSchedule cloned = new AnnualSchedule();
        
        cloned.setYear(this.year); 
        if (this.assignedBy != null)
             cloned.setAssignedBy(this.assignedBy); 
       
        return cloned;
    }
}
