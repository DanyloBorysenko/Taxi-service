package taxi.controller.driver;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import taxi.exception.DataProcessingException;
import taxi.lib.Injector;
import taxi.model.Driver;
import taxi.service.DriverService;

public class AddDriverController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("taxi");
    private static final Logger logger = LogManager.getLogger(AddDriverController.class);
    private static final String PATH = "/WEB-INF/views/drivers/add.jsp";
    private final DriverService driverService = (DriverService) injector
            .getInstance(DriverService.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(PATH).forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String name = req.getParameter("name");
        String licenseNumber = req.getParameter("license_number");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            Driver driver = new Driver(name, licenseNumber, login, password);
            driverService.create(driver);
            resp.sendRedirect(req.getContextPath() + "/drivers/add");
        } catch (DataProcessingException e) {
            logger.error("Can't register new driver. Params: "
                    + "name = {}, license number = {}, login = {}",name, licenseNumber, login, e);
            req.setAttribute("errorMsg", "Login and password must be unique");
            req.getRequestDispatcher(PATH).forward(req, resp);
        }
    }
}
