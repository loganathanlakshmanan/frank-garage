package nl.frankgarage.warehouse.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Builder(toBuilder = true)
@Value
public class Location implements Serializable {
    @JsonProperty("lat")
    private String latitude;
    @JsonProperty("long")
    private String longtitude;
}
