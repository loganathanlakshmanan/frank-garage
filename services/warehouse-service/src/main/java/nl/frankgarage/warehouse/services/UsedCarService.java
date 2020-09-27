package nl.frankgarage.warehouse.services;

import lombok.RequiredArgsConstructor;
import nl.frankgarage.warehouse.clients.WarehouseClient;
import nl.frankgarage.warehouse.exceptions.UsedCarNotFoundException;
import nl.frankgarage.warehouse.models.UsedCar;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsedCarService {
    private final WarehouseClient warehouseClient;

    public Collection<UsedCar> retrieveAllUsedCars() {
        return warehouseClient.getWarehouses()
                              .stream()
                              .flatMap(warehouse -> warehouse.getCar()
                                                             .getVehicles()
                                                             .stream()
                                                             .map(vehicle -> UsedCar.builder()
                                                                                    .id(String.valueOf(vehicle.get_id()))
                                                                                    .manufacturerName(vehicle.getMake())
                                                                                    .modelName(vehicle.getModel())
                                                                                    .modelYear(vehicle.getYearModel())
                                                                                    .price(BigDecimal.valueOf(vehicle.getPrice()))
                                                                                    .licensed(vehicle.isLicensed())
                                                                                    .dateAdded(vehicle.getDateAdded())
                                                                                    .warehouseId(String.valueOf(
                                                                                            warehouse.get_id()))
                                                                                    .locationName(warehouse.getCar()
                                                                                                           .getLocation())
                                                                                    .build()))
                              .collect(Collectors.toList());

    }

    public UsedCar retrieveUsedCar(String id) {

        return warehouseClient.getWarehouses()
                              .stream()
                              .flatMap(warehouse -> warehouse.getCar()
                                                             .getVehicles()
                                                             .stream()
                                                             .filter(findCar -> String.valueOf(findCar.get_id())
                                                                                      .equals(id))
                                                             .map(vehicle -> UsedCar.builder()
                                                                                    .id(String.valueOf(vehicle.get_id()))
                                                                                    .manufacturerName(vehicle.getMake())
                                                                                    .modelName(vehicle.getModel())
                                                                                    .modelYear(vehicle.getYearModel())
                                                                                    .price(BigDecimal.valueOf(vehicle.getPrice()))
                                                                                    .licensed(vehicle.isLicensed())
                                                                                    .dateAdded(vehicle.getDateAdded())
                                                                                    .warehouseId(String.valueOf(
                                                                                            warehouse.get_id()))
                                                                                    .locationName(warehouse.getCar()
                                                                                                           .getLocation())
                                                                                    .build()))
                              .findFirst()
                              .orElseThrow(() -> new UsedCarNotFoundException("vehicle not available"));

    }
}
