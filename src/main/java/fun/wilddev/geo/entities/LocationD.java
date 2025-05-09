package fun.wilddev.geo.entities;

import fun.wilddev.geo.models.Country;

import org.springframework.data.annotation.Id;

import lombok.*;

import org.springframework.data.mongodb.core.index.*;
import org.springframework.data.mongodb.core.mapping.*;

@Setter
@Getter
@ToString(exclude = "id")
@CompoundIndex(name = "country.code_1_city_1", def = "{ 'country.code': 1, 'city': 1 }", unique = true)
@Document("locations")
public class LocationD {

    @Id
    private String id;

    @Field
    private Country country;

    @Field
    private String city;

    @Indexed(name = "qualifier_1")
    @Field
    private String qualifier;

    public LocationD(@NonNull Country country, @NonNull String city) {

        this.country = country;
        this.city = city;
        this.qualifier = String.format("%s %s", city, country.name());
    }
}
