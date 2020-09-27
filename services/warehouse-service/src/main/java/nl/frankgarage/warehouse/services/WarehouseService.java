package nl.frankgarage.warehouse.services;

import lombok.RequiredArgsConstructor;
import nl.frankgarage.warehouse.clients.WarehouseClient;
import nl.frankgarage.warehouse.exceptions.UsedCarNotFoundException;
import nl.frankgarage.warehouse.exceptions.WarehouseNotFoundException;
import nl.frankgarage.warehouse.models.LocationDetails;
import nl.frankgarage.warehouse.models.UsedCar;
import nl.frankgarage.warehouse.models.WarehouseDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WarehouseService {
    private final WarehouseClient warehouseClient;

    public Collection<WarehouseDetails> retrieveAllWarehouses() {
        return warehouseClient.getWarehouses()
                              .stream()
                              .map(warehouse -> WarehouseDetails.builder()
                                                                .id(warehouse.get_id())
                                                                .locationDetails(LocationDetails.builder()
                                                                                                .latitude(warehouse.getLocation()
                                                                                                                   .getLatitude())
                                                                                                .longitude(warehouse.getLocation()
                                                                                                                    .getLongitude())
                                                                                                .build())
                                                                .name(warehouse.getName())
                                                                .build())
                              .collect(Collectors.toList());

    }

    public WarehouseDetails retrieveWarehouseById(String id) {

        return warehouseClient.getWarehouses()
                              .stream()
                              .filter(warehouse -> String.valueOf(warehouse.get_id()).equals(id))
                              .map(warehouse -> WarehouseDetails.builder()
                                                                .id(warehouse.get_id())
                                                                .locationDetails(LocationDetails.builder()
                                                                                                .latitude(warehouse.getLocation()
                                                                                                                   .getLatitude())
                                                                                                .longitude(warehouse.getLocation()
                                                                                                                    .getLongitude())
                                                                                                .build())
                                                                .name(warehouse.getName())
                                                                .build())
                              .findFirst()
                              .orElseThrow(() -> new WarehouseNotFoundException("warehouse not found"));
    }

    public Collection<UsedCar> retrieveAllCarsFromWarhouseById(String id) {

        return warehouseClient.getWarehouses()
                              .stream()
                              .filter(warehouse -> String.valueOf(warehouse.get_id()).equals(id))
                              .flatMap(cars -> cars.getCar()
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
                                                                          .build()))
                              .collect(Collectors.toList());
    }

    public UsedCar retrieveCarByIdFromWarhouseById(String id, String carId) {

        return warehouseClient.getWarehouses()
                              .stream()
                              .filter(warehouse -> String.valueOf(warehouse.get_id()).equals(id))
                              .flatMap(cars -> cars.getCar()
                                                   .getVehicles()
                                                   .stream()
                                                   .filter(vehicle -> String.valueOf(vehicle.get_id()).equals(carId))
                                                   .map(vehicle -> UsedCar.builder()
                                                                          .id(String.valueOf(vehicle.get_id()))
                                                                          .manufacturerName(vehicle.getMake())
                                                                          .modelName(vehicle.getModel())
                                                                          .modelYear(vehicle.getYearModel())
                                                                          .price(BigDecimal.valueOf(vehicle.getPrice()))
                                                                          .licensed(vehicle.isLicensed())
                                                                          .dateAdded(vehicle.getDateAdded())
                                                                          .build()))

                              .findFirst()
                              .orElseThrow(() -> new UsedCarNotFoundException("car not found"));
    }
}
