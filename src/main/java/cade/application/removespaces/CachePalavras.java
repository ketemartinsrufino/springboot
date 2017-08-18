package cade.application.removespaces;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by kete on 16/08/17.
 */
@Data
@Entity
@Table(name = "cache_palavras")
public class CachePalavras {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String form;

    public CachePalavras() {}

    public CachePalavras(String form) {
        this.form = form;
    }

}
