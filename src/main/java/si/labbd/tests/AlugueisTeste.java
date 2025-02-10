package si.labbd.tests;

import si.labbd.models.Alugueis;
import si.labbd.models.Locacao;
import si.labbd.repository.AlugueisRepository;
import si.labbd.repository.LocacaoRepository;
import si.labbd.service.PagamentoAluguelService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class AlugueisTeste {
    public static void main(String[] args) {
        AlugueisRepository alugueisRepo = new AlugueisRepository();
        LocacaoRepository locacaoRepo = new LocacaoRepository();

        // Criar um novo aluguel
        Alugueis novoAluguel = new Alugueis();

        // Buscar uma locação no banco (supondo que o ID 1 já exista)
        Optional<Locacao> locacaoOpt = locacaoRepo.findById(1);
        if (locacaoOpt.isPresent()) {
            novoAluguel.setLocacao(locacaoOpt.get());
        } else {
            System.out.println("Erro: Locação não encontrada!");
            return;
        }

        // Definir os detalhes do aluguel
        novoAluguel.setDataVencimento(new Date()); // Vencimento na data atual
        novoAluguel.setObs("Aluguel pago no prazo sem atrasos.");

        // Salvando o aluguel no banco
        alugueisRepo.save(novoAluguel);
        System.out.println("Aluguel cadastrado com sucesso!");

        // Buscar um aluguel pelo ID
        System.out.println("\nBuscando aluguel com ID 1...");
        Optional<Alugueis> aluguelOpt = alugueisRepo.findById(1);
        aluguelOpt.ifPresentOrElse(
            aluguel -> System.out.println("✔ Aluguel encontrado: Locação " + aluguel.getLocacao().getId() + " - Valor Pago: R$" + aluguel.getValorPago()),
            () -> System.out.println("Aluguel não encontrado.")
        );

        //Registrar Pagamento no Banco
         System.out.println("\nRegistrando pagamento do aluguel...");
         PagamentoAluguelService pagamentoAluguelService = new PagamentoAluguelService();
         pagamentoAluguelService.registrarPagamento(1, BigDecimal.valueOf(3300.50), new Date());

        // Listar todos os aluguéis cadastrados
        System.out.println("\nLista de aluguéis cadastrados:");
        List<Alugueis> listaAlugueis = alugueisRepo.findAll();
        if (!listaAlugueis.isEmpty()) {
            listaAlugueis.forEach(aluguel ->
                System.out.println(aluguel.getId() + " - Locação: " + aluguel.getLocacao().getId() + " - Valor Pago: R$" + aluguel.getValorPago())
            );
        } else {
            System.out.println("Nenhum aluguel cadastrado.");
        }

        // Atualizar um aluguel
        if (aluguelOpt.isPresent()) {
            Alugueis aluguelAtualizado = aluguelOpt.get();
            aluguelAtualizado.setObs("Valor atualizado devido a reajuste.");
            alugueisRepo.update(aluguelAtualizado);
            System.out.println("\nAluguel atualizado com sucesso!");
        }

//        // Deletar um aluguel
//        System.out.println("\nDeletando aluguel com ID 1...");
//        alugueisRepo.deleteById(1);
//        System.out.println("Aluguel deletado com sucesso!");
//
//        // Listar novamente para verificar a exclusão
//        System.out.println("\nLista de aluguéis após exclusão:");
//        List<Alugueis> listaAposExclusao = alugueisRepo.findAll();
//        if (listaAposExclusao.isEmpty()) {
//            System.out.println("Nenhum aluguel cadastrado após a exclusão.");
//        } else {
//            listaAposExclusao.forEach(aluguel ->
//                System.out.println(aluguel.getId() + " - Locação: " + aluguel.getLocacao().getId() + " - Valor Pago: R$" + aluguel.getValorPago())
//            );
//        }
    }
}
