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
public class Vehicle implements Serializable {
    private int _id;
    private String make;
    private String model;
    @JsonProperty("year_model")
    private int yearModel;
    private double price;
    private boolean licensed;
    @JsonProperty("date_added")
    private String dateAdded;

}
