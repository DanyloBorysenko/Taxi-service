package taxi.controller.car;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import taxi.controller.driver.AddDriverController;
import taxi.lib.Injector;
import taxi.model.Car;
import taxi.model.Manufacturer;
import taxi.service.CarService;
import taxi.service.ManufacturerService;

public class AddCarController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("taxi");
    private static final Logger logger = LogManager.getLogger(AddDriverController.class);
    private static final String PATH = "/WEB-INF/views/cars/add.jsp";
    private final CarService carService = (CarService) injector.getInstance(CarService.class);
    private final ManufacturerService manufacturerService = (ManufacturerService) injector
            .getInstance(ManufacturerService.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(PATH).forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String model = req.getParameter("model");
        long manufacturerId = Long.parseLong(req.getParameter("manufacturer_id"));
        try {
            Manufacturer manufacturer = manufacturerService.get(manufacturerId);
            Car car = new Car(model, manufacturer);
            carService.create(car);
            resp.sendRedirect(req.getContextPath() + "/cars/add");
        } catch (RuntimeException e) {
            logger.error("Driver can't create new car. "
                    + "Params: model = {}, manufacturer ID = {}", model, manufacturerId);
            req.setAttribute("errorMsg", "Manufacturer doesn't exist");
            req.getRequestDispatcher(PATH).forward(req, resp);
        }
    }
}
