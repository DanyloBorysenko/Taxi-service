package taxi.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import taxi.dao.DriverDao;
import taxi.lib.Inject;
import taxi.lib.Service;
import taxi.model.Driver;

@Service
public class DriverServiceImpl implements DriverService {
    private static final Logger logger = LogManager.getLogger(DriverServiceImpl.class);
    @Inject
    private DriverDao driverDao;

    @Override
    public Driver create(Driver driver) {
        logger.info("Method create was called. Params: "
                + "name = {}, license number = {}, login = {}",
                driver.getName(), driver.getLicenseNumber(), driver.getLogin());
        return driverDao.create(driver);
    }

    @Override
    public Driver get(Long id) {
        logger.info("Method get was called. "
                + "Params: driver Id = {}", id);
        return driverDao.get(id).orElseThrow(() ->
            new NoSuchElementException("Can't get driver by id: " + id)
        );
    }

    @Override
    public List<Driver> getAll() {
        logger.info("Method getAll was called");
        return driverDao.getAll();
    }

    @Override
    public Driver update(Driver driver) {
        logger.info("Method update was called. "
                        + "Params: driver Id = {}, name ={}, license number = {}, login = {}",
                driver.getId(), driver.getName(), driver.getLicenseNumber(), driver.getLogin());
        return driverDao.update(driver);
    }

    @Override
    public boolean delete(Long id) {
        logger.info("Method delete was called. Params: driver Id = {}", id);
        return driverDao.delete(id);
    }

    @Override
    public Optional<Driver> findByLogin(String login) {
        logger.info("Method findByLogin was called. Params: login = {}", login);
        return driverDao.findByLogin(login);
    }
}
