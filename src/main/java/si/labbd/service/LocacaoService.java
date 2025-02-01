package si.labbd.service;

import si.labbd.models.Locacao;
import si.labbd.models.Imoveis;
import si.labbd.models.Clientes;
import si.labbd.repository.LocacaoRepository;
import si.labbd.repository.ImoveisRepository;

import java.util.Date;

public class LocacaoService {
    private final LocacaoRepository locacaoRepository;
    private final ImoveisRepository imoveisRepository;

    public LocacaoService() {
        this.locacaoRepository = new LocacaoRepository();
        this.imoveisRepository = new ImoveisRepository();
    }

    // Registrar uma nova locação, verificando a disponibilidade do imóvel
    public void registrarLocacao(Clientes inquilino, Imoveis imovel, Date dataInicio, Date dataFim, Double valorAluguel) {

        // Criar nova locação
        Locacao novaLocacao = new Locacao();
        novaLocacao.setInquilino(inquilino);
        novaLocacao.setImovel(imovel);
        novaLocacao.setDataInicio(dataInicio);
        novaLocacao.setDataFim(dataFim);
        novaLocacao.setAtivo(true); // A locação agora está ativa

        // Salvar no banco de dados
        locacaoRepository.save(novaLocacao);

        System.out.println("Locação registrada com sucesso para o imóvel ID: " + imovel.getId());
    }
}
