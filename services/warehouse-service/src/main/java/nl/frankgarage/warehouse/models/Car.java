package nl.frankgarage.warehouse.models;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.io.Serializable;
import java.util.Collection;

@Value
@Builder(toBuilder = true)

public class Car implements Serializable {
    private String location;
    @Singular
    private Collection<Vehicle> vehicles;
}
