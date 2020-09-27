package nl.frankgarage.warehouse.endpoints;

import nl.frankgarage.warehouse.models.WarehouseDetails;
import nl.frankgarage.warehouse.services.WarehouseService;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = WarehouseEndPoint.class)
@AutoConfigureMockMvc
public class WarehouseEndPointTest {
    List<WarehouseDetails> warehouseDetails = new ArrayList<>();

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private WarehouseService warehouseService;

    @Before
    public void setup() {
        warehouseDetails.add(WarehouseDetails.builder().id(1).name("Warehouse A")

                                             .build());
        warehouseDetails.add(WarehouseDetails.builder().id(2).name("Warehouse B").build());

        when(warehouseService.retrieveAllWarehouses()).thenReturn(warehouseDetails);
        when(warehouseService.retrieveWarehouseById(any())).thenReturn(warehouseDetails.get(0));
    }

    @Test
    public void getAllWarehouseCollection() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/frank-garage/warehouses")
                                              .header("user-token", "abc")
                                              .accept(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
               .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id", Matchers.is(1)))
               .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name", Matchers.is("Warehouse A")));
    }

    @Test
    public void getUsedCarById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/frank-garage/warehouses/1")
                                              .header("user-token", "abc")
                                              .accept(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
               .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Warehouse A")));

    }

}
