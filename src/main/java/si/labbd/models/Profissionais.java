package si.labbd.models;

import jakarta.persistence.*;
import lombok.NonNull;

@Entity
@Table (name = "profissionais")
public class Profissionais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String nome;

    @NonNull
    private String profissao;

    @NonNull
    private String telefone;
    private String telefone2;
    private Double valor_hora;
    private String obs;
}