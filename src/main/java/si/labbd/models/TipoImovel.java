package si.labbd.models;
import jakarta.persistence.*;

@Entity
@Table (name = "tipo_imovel")
public class TipoImovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String descricao;


}
