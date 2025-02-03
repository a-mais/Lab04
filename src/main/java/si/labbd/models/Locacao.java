package si.labbd.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "locacao")
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_imovel", nullable = false,
            foreignKey = @ForeignKey(name = "imoveis_imovel"))
    private Imoveis imovel;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_inquilino", nullable = false,
            foreignKey = @ForeignKey(name = "cliente_inquilino"))
    private Clientes inquilino;

    @Column(precision = 10, scale = 4)
    private BigDecimal valorAluguel;

    @Column(precision = 5, scale = 2)
    private BigDecimal percentualMulta;

    @Column(name = "dia_vencimento", columnDefinition = "TINYINT")
    private Byte diaVencimento;

    @Column(name = "data_inicio")
    private Date dataInicio;

    @Column(name = "data_fim")
    private Date dataFim;

    private boolean ativo;

    @Column(columnDefinition = "TEXT")
    private String obs;


}
