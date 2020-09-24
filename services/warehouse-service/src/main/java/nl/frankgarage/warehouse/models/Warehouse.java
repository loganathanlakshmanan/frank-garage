package nl.frankgarage.warehouse.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Value
@Builder(toBuilder = true)
public class Warehouse implements Serializable {
    private int _id;
    private String name;
    private Location location;
    @JsonProperty("cars")
    private Car car;
}
