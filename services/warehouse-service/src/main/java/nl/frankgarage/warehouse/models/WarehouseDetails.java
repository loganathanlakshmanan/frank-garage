package nl.frankgarage.warehouse.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.util.Collection;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class WarehouseDetails {
    private int id;
    private String name;
    @JsonUnwrapped
    private LocationDetails locationDetails;
    @Singular("cars")
    private Collection<UsedCar> usedCar;
}
