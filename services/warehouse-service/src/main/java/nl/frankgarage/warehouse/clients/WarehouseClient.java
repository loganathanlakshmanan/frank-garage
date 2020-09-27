package nl.frankgarage.warehouse.clients;

import nl.frankgarage.warehouse.models.Warehouse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Service
@FeignClient(name = "warehouses", url = "${warehouse.service.url}")
public interface WarehouseClient {
    @RequestMapping(method = RequestMethod.GET, value = "/", consumes = "application/json")
    List<Warehouse> getWarehouses();
}
