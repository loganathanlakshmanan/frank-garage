package nl.frankgarage.warehouse.services;

import nl.frankgarage.warehouse.clients.WarehouseClient;
import nl.frankgarage.warehouse.exceptions.WarehouseNotFoundException;
import nl.frankgarage.warehouse.models.Car;
import nl.frankgarage.warehouse.models.Location;
import nl.frankgarage.warehouse.models.UsedCar;
import nl.frankgarage.warehouse.models.Vehicle;
import nl.frankgarage.warehouse.models.Warehouse;
import nl.frankgarage.warehouse.models.WarehouseDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WarehouseServiceTest {
    List<Warehouse> warehouses = new ArrayList<>();
    private WarehouseService warehouseService;
    @MockBean
    private WarehouseClient warehouseClient;

    @Before
    public void setUp() {

        warehouses.add(Warehouse.builder()
                                ._id(1)
                                .name("Warehouse A")
                                .location(Location.builder()
                                                  .latitude(BigDecimal.valueOf(47.13111))
                                                  .longitude(BigDecimal.valueOf(-61.54801))
                                                  .build())
                                .car(Car.builder()
                                        .location("West wing")
                                        .vehicle(Vehicle.builder()
                                                        ._id(1)
                                                        .make("Volkswagen")
                                                        .model("Jetta III")
                                                        .yearModel(1995)
                                                        .price(12947.52)
                                                        .licensed(true)
                                                        .dateAdded("2018-09-18")
                                                        .build())
                                        .vehicle(Vehicle.builder()
                                                        ._id(2)
                                                        .make("Chevrolet")
                                                        .model("Corvette")
                                                        .yearModel(2004)
                                                        .price(20019.64)
                                                        .licensed(true)
                                                        .dateAdded("2018-01-27")
                                                        .build())
                                        .build())
                                .build());
        warehouses.add(Warehouse.builder()
                                ._id(2)
                                .name("Warehouse B")
                                .location(Location.builder()
                                                  .latitude(BigDecimal.valueOf(15.95386))
                                                  .longitude(BigDecimal.valueOf(7.06246))
                                                  .build())
                                .car(Car.builder()
                                        .location("West wing")
                                        .vehicle(Vehicle.builder()
                                                        ._id(3)
                                                        .make("Maserati")
                                                        .model("Coupe")
                                                        .yearModel(2005)
                                                        .price(19957.71)
                                                        .licensed(false)
                                                        .dateAdded("2017-11-14")
                                                        .build())
                                        .vehicle(Vehicle.builder()
                                                        ._id(4)
                                                        .make("Nissan")
                                                        .model("Altima")
                                                        .yearModel(1994)
                                                        .price(8252.66)
                                                        .licensed(false)
                                                        .dateAdded("2018-08-12")
                                                        .build())
                                        .build())
                                .build());

        when(warehouseClient.getWarehouses()).thenReturn(warehouses);
        warehouseService = new WarehouseService(warehouseClient);
    }

    @Test
    public void shouldReturnAllWarehouses() {
        Collection<WarehouseDetails> warehouseDetailsCollection = warehouseService.retrieveAllWarehouses();
        assertEquals(2, warehouseDetailsCollection.size());
    }

    @Test
    public void shouldReturnWarehouseById() {
        WarehouseDetails warehouseDetails = warehouseService.retrieveWarehouseById("2");
        assertEquals(2, warehouseDetails.getId());
        assertEquals("Warehouse B", warehouseDetails.getName());
    }

    @Test
    public void shouldReturnAllCarsForGivenWarehouseId() {
        Collection<UsedCar> usedCarCollection = warehouseService.retrieveAllCarsFromWarhouseById("1");
        assertEquals(2, usedCarCollection.size());
    }

    @Test
    public void shouldReturnCarByIdForGivenWarehouseById() {
        UsedCar usedCar = warehouseService.retrieveCarByIdFromWarhouseById("1", "1");
        assertEquals("1", usedCar.getId());
        assertEquals("Volkswagen", usedCar.getManufacturerName());
    }

    @Test(expected = WarehouseNotFoundException.class)
    public void shouldThrowNotFoundExceptionWhenWarehouseIdIsInvalid() {
        warehouseService.retrieveWarehouseById("5");
    }
}
