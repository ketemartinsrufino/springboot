package cade.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Subclasse {

    @Id
    private Long idSubClasse;

    private Long idClasse;

    private String nameSubClasse;

}
