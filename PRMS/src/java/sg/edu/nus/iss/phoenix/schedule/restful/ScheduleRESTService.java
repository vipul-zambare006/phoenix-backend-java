/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 /*TODO: Only creating create programslot service as of now */
package sg.edu.nus.iss.phoenix.schedule.restful;

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
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.schedule.service.ScheduleService;
import sg.edu.nus.iss.phoenix.schedule.service.ReviewSelectPresentorProducerService;

/**
 *
 * @author Vipul Zambare.
 */
@Path("schedule")
@RequestScoped
public class ScheduleRESTService {

    @Context
    private UriInfo context;
    private ScheduleService scheduleService;

    /**
     * Creates a new instance of RadioProgramRESTService
     */
    public ScheduleRESTService() {
        scheduleService = new ScheduleService();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<ProgramSlot> getScheduledPrograms() {
      ArrayList<ProgramSlot> scheduledPrograms = scheduleService.findAll();
        return scheduledPrograms;
    }
    
    /**
     * PUT method for updating or creating an instance of resource
     *
     * @param programSlot
     */
    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateProgramSlot(ProgramSlot programSlot) {
        scheduleService.processModify(programSlot);
    }
    
    /**
     * POST method for creating an instance of resource
     *
     * @param programSlot
     */
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createProgramSlot(ProgramSlot programSlot) {
        scheduleService.processCreate(programSlot);
    }

    /**
     * DELETE method for deleting an instance of resource
     *
     * @param programSlot
     */
    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteProgramSlot(ProgramSlot programSlot) 
    {
        scheduleService.processDelete(programSlot);
    }
    
   /**
     * PUT method for updating or creating an instance of resource
     *
     * @param programSlot
     */
    @PUT
    @Path("/copy")
    @Consumes(MediaType.APPLICATION_JSON)
    public void copySchedule(ProgramSlot programSlot) {
        scheduleService.copySchedule(programSlot);
    }
    
   
}
