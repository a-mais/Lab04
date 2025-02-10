package si.labbd.tests;

import si.labbd.models.TipoImovel;
import si.labbd.repository.TipoImovelRepository;

import java.util.List;
import java.util.Optional;

public class TipoImovelTeste {
    public static void main(String[] args) {
        TipoImovelRepository tipoImovelRepo = new TipoImovelRepository();

        // Criar um novo tipo de imóvel
        TipoImovel novoTipoImovel = new TipoImovel();
        novoTipoImovel.setDescricao("Apartamento");

        // Salvando o tipo de imóvel no banco
        tipoImovelRepo.save(novoTipoImovel);
        System.out.println("Tipo de imóvel cadastrado com sucesso!");

        // Buscar um tipo de imóvel pelo ID
        System.out.println("\nBuscando tipo de imóvel com ID 1...");
        Optional<TipoImovel> tipoImovelOpt = tipoImovelRepo.findById(1);
        tipoImovelOpt.ifPresentOrElse(
            tipo -> System.out.println("✔ Tipo de imóvel encontrado: " + tipo.getDescricao()),
            () -> System.out.println("Tipo de imóvel não encontrado.")
        );

        // Listar todos os tipos de imóveis cadastrados
        System.out.println("\nLista de tipos de imóveis cadastrados:");
        List<TipoImovel> listaTiposImoveis = tipoImovelRepo.findAll();
        if (!listaTiposImoveis.isEmpty()) {
            listaTiposImoveis.forEach(tipo ->
                System.out.println(tipo.getId() + " - " + tipo.getDescricao())
            );
        } else {
            System.out.println("⚠Nenhum tipo de imóvel cadastrado.");
        }

        // Atualizar um tipo de imóvel
        if (tipoImovelOpt.isPresent()) {
            TipoImovel tipoAtualizado = tipoImovelOpt.get();
            tipoAtualizado.setDescricao("Apartamento de Luxo");
            tipoImovelRepo.update(tipoAtualizado);
            System.out.println("\nTipo de imóvel atualizado com sucesso!");
        }

//        // Deletar um tipo de imóvel
//        System.out.println("\nDeletando tipo de imóvel com ID 1...");
//        tipoImovelRepo.deleteById(1);
//        System.out.println("Tipo de imóvel deletado com sucesso!");
//
//        // Listar novamente para verificar a exclusão
//        System.out.println("\nLista de tipos de imóveis após exclusão:");
//        List<TipoImovel> listaAposExclusao = tipoImovelRepo.findAll();
//        if (listaAposExclusao.isEmpty()) {
//            System.out.println("Nenhum tipo de imóvel cadastrado após a exclusão.");
//        } else {
//            listaAposExclusao.forEach(tipo ->
//                System.out.println(tipo.getId() + " - " + tipo.getDescricao())
//            );
//        }
    }
}
