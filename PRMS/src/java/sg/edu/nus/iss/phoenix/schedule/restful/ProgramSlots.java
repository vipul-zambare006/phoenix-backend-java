/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.restful;

import java.util.List;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 *
 * @author default
 */
public class ProgramSlots {
      private List <ProgramSlot> rpList;

    public List<ProgramSlot> getPSList() {
        return rpList;
    }
 
    public void setPsList(List<ProgramSlot> rpList) {
        this.rpList = rpList;
    }
}
