package si.labbd.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "servicos_imovel")
public class ServicosImovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_profissional", nullable = false,
            foreignKey = @ForeignKey(name = "profissionais_profissional"))
    private Profissionais profissional;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_imovel", nullable = false,
            foreignKey = @ForeignKey(name = "imoveis_imovel"))
    private Imoveis imovel;

    @Column(name = "data_servico")
    private Date dataServico;

    @Column(name = "valor_total", precision = 10, scale = 2)
    private Double valorTotal;

    @Column(columnDefinition = "TEXT")
    private String obs;

}
