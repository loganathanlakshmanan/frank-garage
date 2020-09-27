package nl.frankgarage.warehouse.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.io.Serializable;
import java.util.Collection;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car implements Serializable {
    private String location;
    @Singular
    private Collection<Vehicle> vehicles;
}
