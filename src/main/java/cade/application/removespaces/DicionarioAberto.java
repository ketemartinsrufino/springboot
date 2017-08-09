package cade.application.removespaces;

/**
 * Created by cade on 02/08/17.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class DicionarioAberto {
    private Entry entry;
    private List<Entry> superEntry;
}