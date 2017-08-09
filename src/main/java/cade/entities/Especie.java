package cade.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Especie {

    @Id
    private Long idEspecie;

    private Long idSubClasse;

    private Long idCatmat;

    private String nameEspecie;

}
