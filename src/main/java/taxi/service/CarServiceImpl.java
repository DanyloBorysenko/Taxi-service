package taxi.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import taxi.dao.CarDao;
import taxi.lib.Inject;
import taxi.lib.Service;
import taxi.model.Car;
import taxi.model.Driver;

@Service
public class CarServiceImpl implements CarService {
    private static final Logger logger = LogManager.getLogger(CarServiceImpl.class);
    @Inject
    private CarDao carDao;

    @Override
    public void addDriverToCar(Driver driver, Car car) {
        logger.info("Method addDriverToCar was called. "
                + "Params: driver Id = {}, car Id = {}", driver.getId(), car.getId());
        car.getDrivers().add(driver);
        carDao.update(car);
    }

    @Override
    public void removeDriverFromCar(Driver driver, Car car) {
        logger.info("Method removeDriverFromCar was called. "
                + "Params: driver Id = {}, car Id = {}", driver.getId(), car.getId());
        car.getDrivers().remove(driver);
        carDao.update(car);
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        logger.info("Method getAllByDriver was called. "
                + "Params: driver Id = {}", driverId);
        return carDao.getAllByDriver(driverId);
    }

    @Override
    public Car create(Car car) {
        logger.info("Method create was called. "
                + "Params: model ={}, manufacturer = {}",
                car.getModel(), car.getManufacturer());
        return carDao.create(car);
    }

    @Override
    public Car get(Long id) {
        logger.info("Method get was called. "
                + "Params: car Id = {}", id);
        return carDao.get(id).orElseThrow(() ->
            new NoSuchElementException("Can't get car by id: " + id)
        );
    }

    @Override
    public List<Car> getAll() {
        logger.info("Method getAll was called");
        return carDao.getAll();
    }

    @Override
    public Car update(Car car) {
        logger.info("Method update was called. "
                + "Params: car Id = {}, model ={}, manufacturer = {}",
                car.getId(), car.getModel(), car.getManufacturer());
        return carDao.update(car);
    }

    @Override
    public boolean delete(Long id) {
        logger.info("Method delete was called. Params: car Id = {}", id);
        return carDao.delete(id);
    }
}
