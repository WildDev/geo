package fun.wilddev.geo.entities;

import fun.wilddev.geo.enums.ImportType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.lang.NonNull;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.*;

@Setter
@Getter
@ToString
@Document("registered_imports")
public class RegisteredImport {

    @Id
    private String id;

    @Indexed(unique = true)
    @Field
    private ImportType type;

    @Field
    private String elapsed;

    public RegisteredImport(@NonNull ImportType type, @NonNull String elapsed) {

        this.type = type;
        this.elapsed = elapsed;
    }
}
