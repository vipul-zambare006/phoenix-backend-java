package sg.edu.nus.iss.phoenix.user.controller;
import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Rach
 */
@Action("addedituser")
public class AddEditUserCmd implements Perform {
     @Override
    public String perform(String path, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        return "/pages/setupuser.jsp";
    }
}
