package nl.frankgarage.warehouse.services;

import nl.frankgarage.warehouse.clients.WarehouseClient;
import nl.frankgarage.warehouse.exceptions.VehicleNotFoundException;
import nl.frankgarage.warehouse.models.Car;
import nl.frankgarage.warehouse.models.Location;
import nl.frankgarage.warehouse.models.UsedCar;
import nl.frankgarage.warehouse.models.Vehicle;
import nl.frankgarage.warehouse.models.Warehouse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserCarServiceTest {
    List<Warehouse> warehouses = new ArrayList<>();
    private UsedCarService usedCarService;
    @MockBean
    private WarehouseClient warehouseClient;

    @Before
    public void setUp() {

        warehouses.add(Warehouse.builder()
                                ._id(1)
                                .name("Warehouse A")
                                .location(Location.builder().latitude("47.13111").longtitude("-61.54801").build())
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
                                .location(Location.builder().latitude("15.95386").longtitude("7.06246").build())
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
        usedCarService = new UsedCarService(warehouseClient);
    }

    @Test
    public void shouldReturnCarsFromAllWarehouses() {
        Collection<UsedCar> usedCars = usedCarService.retrieveAllUsedCars();
        assertEquals(4, usedCars.size());
    }

    @Test
    public void shouldReturnCarById() {
        UsedCar usedCar = usedCarService.retrieveUsedCar("4");
        assertEquals("4", usedCar.getId());
        assertEquals("Nissan", usedCar.getManufacturerName());
        assertEquals("Altima", usedCar.getModelName());
        assertEquals(1994, usedCar.getModelYear());
        assertFalse(usedCar.isLicensed());
        assertEquals("2018-08-12", usedCar.getDateAdded());
    }

    @Test(expected = VehicleNotFoundException.class)
    public void shouldThrowNotFoundExceptionWhenCarIdIsInvalid() {
        usedCarService.retrieveUsedCar("5");
    }
}
