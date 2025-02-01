package si.labbd.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "clientes")
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(nullable = false, length = 100)
    private String nome;

    @NonNull
    @Column(nullable = false, length = 15, unique = true)
    private String cpf;

    @NonNull
    @Column(nullable = false, length = 12)
    private String telefone;

     @Column(length = 100)
    private String email;

    @Column(name = "dt_nascimento")
    private Date dataNascimento;

}