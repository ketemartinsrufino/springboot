package cade.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Classe {

    @Id
    private Long idClasse;

    private String nameClasse;

}
