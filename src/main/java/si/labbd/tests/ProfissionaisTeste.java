package si.labbd.tests;

import si.labbd.models.Profissionais;
import si.labbd.repository.ProfissionaisRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ProfissionaisTeste {
    public static void main(String[] args) {
        ProfissionaisRepository profissionaisRepo = new ProfissionaisRepository();

        // Criar um novo profissional
        Profissionais novoProfissional = new Profissionais();
        novoProfissional.setNome("Carlos Andrade");
        novoProfissional.setProfissao("Eletricista");
        novoProfissional.setTelefone("11987654321");
        novoProfissional.setTelefone2("1187654321");
        novoProfissional.setValor_hora(BigDecimal.valueOf(50.00));
        novoProfissional.setObs("Profissional altamente qualificado para serviços elétricos residenciais.");

        // Salvando o profissional no banco
        profissionaisRepo.save(novoProfissional);
        System.out.println("Profissional cadastrado com sucesso!");

        // Buscar um profissional pelo ID
        System.out.println("\nBuscando profissional com ID 1...");
        Optional<Profissionais> profissionalOpt = profissionaisRepo.findById(1);
        profissionalOpt.ifPresentOrElse(
            profissional -> System.out.println("✔ Profissional encontrado: " + profissional.getNome() + " - Profissão: " + profissional.getProfissao()),
            () -> System.out.println("Profissional não encontrado.")
        );

        // Listar todos os profissionais cadastrados
        System.out.println("\nLista de profissionais cadastrados:");
        List<Profissionais> listaProfissionais = profissionaisRepo.findAll();
        if (!listaProfissionais.isEmpty()) {
            listaProfissionais.forEach(profissional ->
                System.out.println(profissional.getId() + " - " + profissional.getNome() + " - Profissão: " + profissional.getProfissao())
            );
        } else {
            System.out.println("Nenhum profissional cadastrado.");
        }

        // Atualizar um profissional
        if (profissionalOpt.isPresent()) {
            Profissionais profissionalAtualizado = profissionalOpt.get();
            profissionalAtualizado.setValor_hora(BigDecimal.valueOf(60.00));
            profissionalAtualizado.setObs("Profissional atualizado com aumento no valor da hora trabalhada.");
            profissionaisRepo.update(profissionalAtualizado);
            System.out.println("\nProfissional atualizado com sucesso!");
        }

//        // Deletar um profissional
//        System.out.println("\nDeletando profissional com ID 1...");
//        profissionaisRepo.deleteById(1);
//        System.out.println("Profissional deletado com sucesso!");
//
//        // Listar novamente para verificar a exclusão
//        System.out.println("\nLista de profissionais após exclusão:");
//        List<Profissionais> listaAposExclusao = profissionaisRepo.findAll();
//        if (listaAposExclusao.isEmpty()) {
//            System.out.println("Nenhum profissional cadastrado após a exclusão.");
//        } else {
//            listaAposExclusao.forEach(profissional ->
//                System.out.println(profissional.getId() + " - " + profissional.getNome() + " - Profissão: " + profissional.getProfissao())
//            );
//        }
    }
}
