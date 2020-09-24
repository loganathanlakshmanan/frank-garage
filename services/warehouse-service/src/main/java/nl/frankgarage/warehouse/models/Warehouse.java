package nl.frankgarage.warehouse.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse implements Serializable {
    private int _id;
    private String name;
    private Location location;
    @JsonProperty("cars")
    private Car car;
}
