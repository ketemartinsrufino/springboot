package cade.application.removespaces;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by cade on 04/08/17.
 */

@Data
public class SuperEntry {
    @JsonProperty("@id")
    private List<Entry> entries;

}
