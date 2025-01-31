package si.labbd.models;
import jakarta.persistence.*;

@Entity
@Table (name = "servicos_imovel")
public class ServicosImovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ForeignKey()

    private int idProfissional
    @ForeignKey()

    private int idProfissional
}
