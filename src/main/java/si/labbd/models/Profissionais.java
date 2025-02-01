package si.labbd.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "profissionais")
public class Profissionais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(nullable = false, length = 100)
    private String nome;

    @NonNull
    @Column(nullable = false, length = 100)
    private String profissao;

    @NonNull
    @Column(nullable = false, length = 12)
    private String telefone;

    @Column(length = 12)
    private String telefone2;

    @Column(precision = 10, scale = 2)
    private Double valor_hora;

    @Column(columnDefinition = "TEXT")
    private String obs;

}