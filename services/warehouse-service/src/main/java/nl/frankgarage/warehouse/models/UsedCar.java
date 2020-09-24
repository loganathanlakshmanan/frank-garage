package nl.frankgarage.warehouse.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
@JsonPropertyOrder
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
}
