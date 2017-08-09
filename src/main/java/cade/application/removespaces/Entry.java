package cade.application.removespaces;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by cade on 04/08/17.
 */

@Data
public class Entry {
    @JsonProperty("@id")
    private String id;

}
