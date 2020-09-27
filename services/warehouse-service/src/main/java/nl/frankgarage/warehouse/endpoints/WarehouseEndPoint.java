package nl.frankgarage.warehouse.endpoints;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.frankgarage.warehouse.exceptions.UserHeaderNotFoundException;
import nl.frankgarage.warehouse.models.UsedCar;
import nl.frankgarage.warehouse.models.WarehouseDetails;
import nl.frankgarage.warehouse.services.WarehouseService;
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
public class WarehouseEndPoint {

    private final WarehouseService warehouseService;

    @GetMapping
    public Collection<WarehouseDetails> getAllWarehouses(
            @RequestHeader(value = "user-token", required = false) String userToken)
    {
        checkUserToken(userToken);
        return warehouseService.retrieveAllWarehouses();
    }

    @GetMapping("/{id}")
    public WarehouseDetails getWarehouseById(@RequestHeader(value = "user-token", required = false) String userToken,
                                             @PathVariable String id)
    {
        checkUserToken(userToken);
        return warehouseService.retrieveWarehouseById(id);
    }

    @GetMapping("/{id}/cars")
    public Collection<UsedCar> getAllCarsFromWarehouseById(
            @RequestHeader(value = "user-token", required = false) String userToken, @PathVariable String id)
    {
        checkUserToken(userToken);
        return warehouseService.retrieveAllCarsFromWarhouseById(id);
    }

    @GetMapping("/{id}/cars/{carId}")
    public UsedCar getCarByIdFromWarehouseById(@RequestHeader(value = "user-token", required = false) String userToken,
                                               @PathVariable String id,
                                               @PathVariable String carId)
    {
        checkUserToken(userToken);
        return warehouseService.retrieveCarByIdFromWarhouseById(id, carId);
    }

    private void checkUserToken(String userToken) {
        Optional.ofNullable(userToken).orElseThrow(() -> new UserHeaderNotFoundException("token is missing"));
    }
}
