package nl.frankgarage.warehouse.endpoints;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.frankgarage.warehouse.exceptions.UserHeaderNotFoundException;
import nl.frankgarage.warehouse.models.UsedCar;
import nl.frankgarage.warehouse.services.UsedCarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/frank-garage/warehouses", produces = "application/json;charset=UTF-8")
@RequiredArgsConstructor
public class UsedCarEndPoint {

    private final UsedCarService usedCarService;

    @GetMapping("/used-cars")
    public Collection<UsedCar> getAllUsedCars(@RequestHeader(value = "user-token", required = false) String userToken) {
        checkUserToken(userToken);
        return usedCarService.retrieveAllUsedCars();
    }

    @GetMapping("/used-cars/{id}")
    public UsedCar getUsedCarById(@RequestHeader(value = "user-token", required = false) String userToken,
                                  @PathVariable String id)
    {
        checkUserToken(userToken);
        return usedCarService.retrieveUsedCar(id);
    }

    private void checkUserToken(String userToken) {
        Optional.ofNullable(userToken).orElseThrow(() -> new UserHeaderNotFoundException("token is missing"));
    }

}
