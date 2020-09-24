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
public class Location implements Serializable {
    @JsonProperty("lat")
    private String latitude;
    @JsonProperty("long")
    private String longtitude;
}
