package nl.frankgarage.warehouse.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsedCar {
    private String id;
    @JsonProperty("manufacturer_name")
    private String manufacturerName;
    @JsonProperty("model_name")
    private String modelName;
    @JsonProperty("model_year")
    private int modelYear;
    private BigDecimal price;
    private boolean licensed;
    @JsonProperty("date_added")
    private String dateAdded;
    @JsonProperty("warehouse_id")
    private String warehouseId;
    @JsonProperty("location_name")
    private String locationName;
}
