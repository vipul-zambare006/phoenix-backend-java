/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.entity;

import java.io.Serializable;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;

/**
 *
 * @author default
 */
public class ProgramSlot  implements Cloneable, Serializable {
    private String duration;
    private String dateOfProgram;
    private String startTime;
    private RadioProgram radioProgram;
    private User presenter;
    private User producer;
    private User assignedBy;
    
    public ProgramSlot () {

    }

    public ProgramSlot (String duration, String dateOfProgram, String startTime, RadioProgram radioProgram, User presenter, User producer, User assignedBy) 
    {
       this.duration = duration;
       this.dateOfProgram = dateOfProgram;
       this.radioProgram = radioProgram;
       this.startTime = startTime;
       this.presenter = presenter;
       this.producer = producer;
       this.assignedBy = assignedBy;
    }

    /** 
     * Get- and Set-methods for persistent variables. The default
     * behavior does not make any checks against malformed data,
     * so these might require some manual additions.
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
    
    public String getStartTime(){
        return this.startTime;
    }
    
    public void setStartTime(String startTime){
        this.startTime = startTime;
    }
    
     public String getPresenter(){
        return this.presenter.getName();
    }
    
//    public void setPresenter(String startTime){
//        this.startTime = startTime;
//    }
    
     public String getProducer(){
        return this.producer.getName();
    }
    
//    public void setProducer(String startTime){
//        this.startTime = startTime;
//    }
    
     public String getRadioProgramName(){
        return this.radioProgram.getName();
    }
    
//    public void setStartTime(String startTime){
//        this.startTime = startTime;
//    }

//    public String getProgramName(){
//      return  this.programName ;
//    }
//    
//    public void setProgramName(String programName){
//            this.programName = programName;
//        }
    
    /** 
     * setAll allows to set all persistent variables in one method call.
     * This is useful, when all data is available and it is needed to 
     * set the initial state of this object. Note that this method will
     * directly modify instance variables, without going trough the 
     * individual set-methods.
     * @param Time duration, Date dateOfProgram, Time startTime, String programName
     */

      public void setAll (String duration, String dateOfProgram, String startTime, RadioProgram radioProgram) {
        this.duration = duration;
        this.dateOfProgram = dateOfProgram;
        this.radioProgram = radioProgram;
        this.startTime = startTime;
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
     * valueObject. This is useful during application development, and 
     * possibly when application is writing object states in text log.
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
        
       /* cloned.setYear(this.year); 
        if (this.assignedBy != null)
             cloned.setAssignedBy(this.assignedBy); 
       */
        return cloned;
    }
}
