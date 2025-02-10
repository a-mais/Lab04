package si.labbd.tests;

import si.labbd.models.Imoveis;
import si.labbd.models.Profissionais;
import si.labbd.models.ServicosImovel;
import si.labbd.repository.ImoveisRepository;
import si.labbd.repository.ProfissionaisRepository;
import si.labbd.repository.ServicosImovelRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ServicosImovelTeste {
    public static void main(String[] args) {
        ServicosImovelRepository servicosRepo = new ServicosImovelRepository();
        ImoveisRepository imoveisRepo = new ImoveisRepository();
        ProfissionaisRepository profissionaisRepo = new ProfissionaisRepository();

        // Criar um novo serviço de imóvel
        ServicosImovel novoServico = new ServicosImovel();

        // Buscar um profissional no banco (supondo que o ID 1 já exista)
        Optional<Profissionais> profissionalOpt = profissionaisRepo.findById(1);
        if (profissionalOpt.isPresent()) {
            novoServico.setProfissional(profissionalOpt.get());
        } else {
            System.out.println("Erro: Profissional não encontrado!");
            return;
        }

        // Buscar um imóvel no banco (supondo que o ID 1 já exista)
        Optional<Imoveis> imovelOpt = imoveisRepo.findById(1);
        if (imovelOpt.isPresent()) {
            novoServico.setImovel(imovelOpt.get());
        } else {
            System.out.println("Erro: Imóvel não encontrado!");
            return;
        }

        // Definir os detalhes do serviço
        novoServico.setDataServico(new Date()); // Data atual
        novoServico.setValorTotal(BigDecimal.valueOf(500.00));
        novoServico.setObs("Serviço de manutenção elétrica realizado no apartamento.");

        // Salvando o serviço no banco
        servicosRepo.save(novoServico);
        System.out.println("Serviço cadastrado com sucesso!");

        // Buscar um serviço pelo ID
        System.out.println("\nBuscando serviço com ID 1...");
        Optional<ServicosImovel> servicoOpt = servicosRepo.findById(1);
        servicoOpt.ifPresentOrElse(
            servico -> System.out.println("✔ Serviço encontrado: " + servico.getProfissional().getNome() + " - Imóvel: " + servico.getImovel().getLogradouro()),
            () -> System.out.println("Serviço não encontrado.")
        );

        // Listar todos os serviços cadastrados
        System.out.println("\nLista de serviços cadastrados:");
        List<ServicosImovel> listaServicos = servicosRepo.findAll();
        if (!listaServicos.isEmpty()) {
            listaServicos.forEach(servico ->
                System.out.println(servico.getId() + " - Profissional: " + servico.getProfissional().getNome() + " - Imóvel: " + servico.getImovel().getLogradouro() + " - Valor: R$" + servico.getValorTotal())
            );
        } else {
            System.out.println("Nenhum serviço cadastrado.");
        }

        // Atualizar um serviço
        if (servicoOpt.isPresent()) {
            ServicosImovel servicoAtualizado = servicoOpt.get();
            servicoAtualizado.setValorTotal(BigDecimal.valueOf(550.00));
            servicoAtualizado.setObs("Serviço atualizado com acréscimo no valor.");
            servicosRepo.update(servicoAtualizado);
            System.out.println("\nServiço atualizado com sucesso!");
        }

//        // Deletar um serviço
//        System.out.println("\nDeletando serviço com ID 1...");
//        servicosRepo.deleteById(1);
//        System.out.println("Serviço deletado com sucesso!");
//
//        // Listar novamente para verificar a exclusão
//        System.out.println("\nLista de serviços após exclusão:");
//        List<ServicosImovel> listaAposExclusao = servicosRepo.findAll();
//        if (listaAposExclusao.isEmpty()) {
//            System.out.println("Nenhum serviço cadastrado após a exclusão.");
//        } else {
//            listaAposExclusao.forEach(servico ->
//                System.out.println(servico.getId() + " - Profissional: " + servico.getProfissional().getNome() + " - Imóvel: " + servico.getImovel().getLogradouro() + " - Valor: R$" + servico.getValorTotal())
//            );
//        }
    }
}
