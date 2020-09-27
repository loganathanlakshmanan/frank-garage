package nl.frankgarage.warehouse.endpoints;

import nl.frankgarage.warehouse.models.UsedCar;
import nl.frankgarage.warehouse.services.UsedCarService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UsedCarEndPoint.class)
@AutoConfigureMockMvc
public class UsedCarEndPointTest {
    List<UsedCar> usedCars = new ArrayList<>();

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UsedCarService usedCarService;

    @Before
    public void setup() {
        usedCars.add(UsedCar.builder()
                            .id("1")
                            .manufacturerName("Volkswagen")
                            .modelName("Jetta III")
                            .modelYear(1995)
                            .price(BigDecimal.valueOf(12947.52))
                            .licensed(true)
                            .dateAdded("2018-09-18")
                            .warehouseId("1")
                            .build());
        usedCars.add(UsedCar.builder()
                            .id("2")
                            .manufacturerName("Chevrolet")
                            .modelName("Corvette")
                            .modelYear(2004)
                            .price(BigDecimal.valueOf(20019.64))
                            .licensed(true)
                            .dateAdded("2018-01-27")
                            .warehouseId("1")
                            .build());

        usedCars.add(UsedCar.builder()
                            .id("3")
                            .manufacturerName("Chevrolet")
                            .modelName("Corvette")
                            .modelYear(2004)
                            .price(BigDecimal.valueOf(20019.64))
                            .licensed(true)
                            .dateAdded("2018-01-27")
                            .warehouseId("2")
                            .build());

        when(usedCarService.retrieveAllUsedCars()).thenReturn(usedCars);
        when(usedCarService.retrieveUsedCar(any())).thenReturn(usedCars.get(0));

    }

    @Test
    public void getAllUsedCars() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/frank-garage/used-cars")
                                              .header("user-token", "abc")
                                              .accept(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
               .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id", Matchers.is("1")))
               .andExpect(MockMvcResultMatchers.jsonPath("$.[0].manufacturer_name", Matchers.is("Volkswagen")))
               .andExpect(MockMvcResultMatchers.jsonPath("$.[0].model_name", Matchers.is("Jetta III")))
               .andExpect(MockMvcResultMatchers.jsonPath("$.[0].model_year", Matchers.is(1995)))
               .andExpect(MockMvcResultMatchers.jsonPath("$.[0].warehouse_id", Matchers.is("1")));
    }

    @Test
    public void getUsedCarById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/frank-garage/used-cars/1")
                                              .header("user-token", "abc")
                                              .accept(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is("1")))
               .andExpect(MockMvcResultMatchers.jsonPath("$.manufacturer_name", Matchers.is("Volkswagen")))
               .andExpect(MockMvcResultMatchers.jsonPath("$.model_name", Matchers.is("Jetta III")))
               .andExpect(MockMvcResultMatchers.jsonPath("$.model_year", Matchers.is(1995)))
               .andExpect(MockMvcResultMatchers.jsonPath("$.warehouse_id", Matchers.is("1")));

    }
}
