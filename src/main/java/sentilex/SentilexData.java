package sentilex;

import javax.persistence.*;

/**
 * Created by cade on 07/08/17.
 */
@lombok.Data
@Entity
@Table(name = "sentilex")
public class SentilexData {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String form;
    private String lemma;
    private String tipo;
    private String genero_numero;
    private String alvo_polaridade;
    private String polaridade;
    private String anotacao;

    public SentilexData(String[] data) {

        this.form = data[0];
        this.lemma = data[1];
        this.tipo = data[2];
        this.genero_numero = data[3];
        this.alvo_polaridade = data[4];
        this.polaridade = data[5];
        this.anotacao = data[6];

    }

    public SentilexData() {

    }
}
