/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 /*TODO: Only creating create programslot service as of now */
package sg.edu.nus.iss.phoenix.schedule.restful;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.service.ScheduleService;

/**
 *
 * @author Vipul Zambare.
 */
@Path("schedule")
@RequestScoped
public class ScheduleRESTService {

    @Context
    private UriInfo context;
    private final ScheduleService scheduleService;

    /**
     * Creates a new instance of RadioProgramRESTService
     */
    public ScheduleRESTService() {
        scheduleService = new ScheduleService();
    }
/**
     * Get Method. This method is used for getScheduledPrograms in an ArrayList
     * of ProgramSlot instances
     * @return ScheduledPrograms
     * @throws java.sql.SQLException     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<ProgramSlot> getScheduledPrograms() throws SQLException {
      ArrayList<ProgramSlot> scheduledPrograms = scheduleService.loadAll();
        return scheduledPrograms;
    }
    
     /**
     * POST method for updating or creating an instance of resource
     *
     * @param programSlot
     * @throws sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException
     */
    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateProgramSlot(ProgramSlot programSlot) throws NotFoundException {
        scheduleService.processModify(programSlot);
    }
    
 /**
     * POST method for creating an instance of resource
     *
     * @param programSlot
     * @throws java.sql.SQLException
     */
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createProgramSlot(ProgramSlot programSlot) throws SQLException {
        scheduleService.processCreate(programSlot);
    }

    /**
     * DELETE method for deleting an instance of resource
     * @param programSlot
     * @throws sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException
     * @throws java.sql.SQLException
     */
    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteProgramSlot(ProgramSlot programSlot) throws NotFoundException, SQLException 
    {
        scheduleService.processDelete(programSlot);
    }
    
  /**
     * PUT method for updating or creating an instance of resource
     *
     * @param programSlot
     * @throws java.sql.SQLException
     */
    @POST
    @Path("/copy")
    @Consumes(MediaType.APPLICATION_JSON)
    public void copySchedule(ProgramSlot programSlot) throws SQLException {
        scheduleService.copySchedule(programSlot);
    }
}
