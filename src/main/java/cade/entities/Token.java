package cade.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Token")
public class Token {

    @Id
    private Long id;
    private String form;
    private String lemma;
    private String tag;

    @Column(name = "periodo_pos")
    private Long periodoPos;

    private Long position;
    private Long item_id;

    @Override
    public String toString() {
        return form;
    }
}
