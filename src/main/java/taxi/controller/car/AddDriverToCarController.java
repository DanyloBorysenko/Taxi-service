package taxi.controller.car;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import taxi.lib.Injector;
import taxi.model.Car;
import taxi.model.Driver;
import taxi.service.CarService;
import taxi.service.DriverService;

public class AddDriverToCarController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("taxi");
    private static final Logger logger = LogManager.getLogger(AddDriverToCarController.class);
    private static final String PATH = "/WEB-INF/views/cars/drivers/add.jsp";
    private final CarService carService = (CarService) injector.getInstance(CarService.class);
    private final DriverService driverService = (DriverService) injector
            .getInstance(DriverService.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Car> carList = carService.getAll();
        List<Driver> driverList = driverService.getAll();
        req.setAttribute("cars", carList);
        req.setAttribute("drivers", driverList);
        req.getRequestDispatcher(PATH).forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        long driverId = Long.parseLong(req.getParameter("driver_id"));
        long carId = Long.parseLong(req.getParameter("car_id"));
        Driver driver = null;
        Car car = null;
        try {
            driver = driverService.get(driverId);
        } catch (NoSuchElementException e) {
            logger.error("Can't add driver to the car. "
                    + "Params: driver Id = {}", driverId);
            req.setAttribute("errorMsg", "Driver with such Id not exist");
            req.getRequestDispatcher(PATH).forward(req, resp);
        }
        try {
            car = carService.get(carId);
        } catch (NoSuchElementException e) {
            logger.error("Can't add driver to the car. "
                    + "Params: car Id = {}", carId);
            req.setAttribute("errorMsg", "Car with such Id not exist");
            req.getRequestDispatcher(PATH).forward(req, resp);
        }
        carService.addDriverToCar(driver, car);
        resp.sendRedirect(req.getContextPath() + "/cars/drivers/add");
    }
}
