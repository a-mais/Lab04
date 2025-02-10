package si.labbd.service;

import si.labbd.models.Locacao;
import si.labbd.models.Imoveis;
import si.labbd.models.Clientes;
import si.labbd.repository.LocacaoRepository;
import si.labbd.repository.ImoveisRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

public class LocacaoService {
    private final LocacaoRepository locacaoRepository;
    private final ImoveisRepository imoveisRepository;

    public LocacaoService() {
        this.locacaoRepository = new LocacaoRepository();
        this.imoveisRepository = new ImoveisRepository();
    }

    // Registrar uma nova locação
    public void registrarLocacao(Clientes inquilino, Imoveis imovel, Date dataInicio, Date dataFim, Double valorAluguel) {

        // Verificar se já existe uma locação ativa para o imóvel
        Optional<Locacao> locacaoExistente = locacaoRepository.findById(imovel.getId());
        if (locacaoExistente.isPresent() && locacaoExistente.get().isAtivo()) {
            throw new RuntimeException("Erro: O imóvel já está alugado e não pode ser locado no momento.");
        }

        // Criar nova locação
        Locacao novaLocacao = new Locacao();
        novaLocacao.setInquilino(inquilino);
        novaLocacao.setImovel(imovel);
        novaLocacao.setDataInicio(dataInicio);
        novaLocacao.setDataFim(dataFim);
        novaLocacao.setValorAluguel(BigDecimal.valueOf(valorAluguel));
        novaLocacao.setAtivo(true); // A locação agora está ativa

        // Salvar no banco de dados
        locacaoRepository.save(novaLocacao);
    }
}