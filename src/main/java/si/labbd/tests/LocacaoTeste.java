package si.labbd.tests;

import si.labbd.models.Clientes;
import si.labbd.models.Imoveis;
import si.labbd.models.Locacao;
import si.labbd.repository.ClientesRepository;
import si.labbd.repository.ImoveisRepository;
import si.labbd.repository.LocacaoRepository;
import si.labbd.service.LocacaoService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class LocacaoTeste {
    public static void main(String[] args) {
        LocacaoRepository locacaoRepo = new LocacaoRepository();
        ImoveisRepository imoveisRepo = new ImoveisRepository();
        ClientesRepository clientesRepo = new ClientesRepository();
        LocacaoService locacaoService = new LocacaoService();

        // Buscar um imóvel disponível (supondo que o ID 1 já exista)
        Optional<Imoveis> imovelOpt = imoveisRepo.findById(1);
        if (imovelOpt.isEmpty()) {
            System.out.println("Erro: Imóvel não encontrado!");
            return;
        }
        Imoveis imovel = imovelOpt.get();

        // Buscar um cliente (supondo que o ID 1 já exista)
        Optional<Clientes> clienteOpt = clientesRepo.findById(1);
        if (clienteOpt.isEmpty()) {
            System.out.println("Erro: Cliente não encontrado!");
            return;
        }
        Clientes cliente = clienteOpt.get();

        // Definir detalhes da locação
        Date dataInicio = new Date(); // Data atual
        Date dataFim = null; // Ainda sem prazo definido
        Double valorAluguel = 3500.00;

        // Testar o registro de locação usando o service
        locacaoService.registrarLocacao(cliente, imovel, dataInicio, dataFim, valorAluguel);

        // Buscar uma locação pelo ID
        System.out.println("\nBuscando locação com ID 1...");
        Optional<Locacao> locacaoOpt = locacaoRepo.findById(1);
        locacaoOpt.ifPresentOrElse(
            locacao -> System.out.println("Locação encontrada: Imóvel " + locacao.getImovel().getLogradouro() + " - Inquilino: " + locacao.getInquilino().getNome()),
            () -> System.out.println("Locação não encontrada.")
        );

        //TIP Buscar locações de um cliente específico
        System.out.println("\nBuscando locações do cliente ID 1...");
        List<Locacao> locacoesCliente = locacaoRepo.findByClienteId(1);
        if (!locacoesCliente.isEmpty()) {
            locacoesCliente.forEach(locacao ->
                System.out.println("Cliente possui locação no imóvel: " + locacao.getImovel().getLogradouro())
            );
        } else {
            System.out.println("Nenhuma locação encontrada para o cliente.");
        }

        // Listar todas as locações ativas
        System.out.println("\nLista de locações ativas:");
        List<Locacao> listaLocacoes = locacaoRepo.findAll();
        if (!listaLocacoes.isEmpty()) {
            listaLocacoes.forEach(locacao -> System.out.println(locacao.getId() + " - Imóvel: " + locacao.getImovel().getLogradouro() + " - Inquilino: " + locacao.getInquilino().getNome()));
        } else {
            System.out.println("Nenhuma locação cadastrada.");
        }

        // Atualizar uma locação
        if (locacaoOpt.isPresent()) {
            Locacao locacaoAtualizada = locacaoOpt.get();
            locacaoAtualizada.setValorAluguel(BigDecimal.valueOf(3200.00));
            locacaoAtualizada.setObs("Contrato atualizado com reajuste de aluguel.");
            locacaoRepo.update(locacaoAtualizada);
            System.out.println("\nLocação atualizada com sucesso!");
        }

        // Deletar uma locação
        System.out.println("\nDeletando locação com ID 1...");
        locacaoRepo.deleteById(1);
        System.out.println("Locação deletada com sucesso!");

        // Listar novamente para verificar a exclusão
        System.out.println("\nLista de locações após exclusão:");
        List<Locacao> listaAposExclusao = locacaoRepo.findAll();
        if (listaAposExclusao.isEmpty()) {
            System.out.println("Nenhuma locação cadastrada após a exclusão.");
        } else {
            listaAposExclusao.forEach(locacao -> System.out.println(locacao.getId() + " - Imóvel: " + locacao.getImovel().getLogradouro() + " - Inquilino: " + locacao.getInquilino().getNome()));
        }
    }
}
