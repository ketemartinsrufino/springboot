package cade.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class Item {

    @Id
    private Long id;

    private String descricao;

    private String especie;

    private String descricao_corrigida;

    @OneToMany
    @JoinTable(name="Token")
    private List<Token> tokens;

}
