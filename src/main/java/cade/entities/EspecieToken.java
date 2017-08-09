package cade.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "EspecieToken")
public class EspecieToken {

    @Id
    @Column(name = "especie_token_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long especieTokenId;

    private Long especieId;
    private Long tokenId;

    private String lemma;

}
