package si.labbd.tests;

import si.labbd.models.Clientes;
import si.labbd.models.Imoveis;
import si.labbd.models.TipoImovel;
import si.labbd.repository.ImoveisRepository;
import si.labbd.repository.ClientesRepository;
import si.labbd.repository.TipoImovelRepository;

import java.util.Optional;
import java.util.List;

public class ImoveisTeste {
    public static void main(String[] args) {
        ImoveisRepository imoveisRepo = new ImoveisRepository();
        ClientesRepository clientesRepo = new ClientesRepository();
        TipoImovelRepository tipoImovelRepo = new TipoImovelRepository();

        // Criando um novo imóvel
        Imoveis novoImovel = new Imoveis();

        // Buscar um proprietário no banco (supondo que o ID 1 já exista)
        Optional<Clientes> proprietarioOpt = clientesRepo.findById(1);
        if (proprietarioOpt.isPresent()) {
            novoImovel.setProprietario(proprietarioOpt.get());
        } else {
            System.out.println("Erro: Proprietário não encontrado!");
            return;
        }

        // Buscar um tipo de imóvel no banco (supondo que o ID 1 já exista)
        Optional<TipoImovel> tipoImovelOpt = tipoImovelRepo.findById(1);
        if (tipoImovelOpt.isPresent()) {
            novoImovel.setTipoImovel(tipoImovelOpt.get());
        } else {
            System.out.println("Erro: Tipo de imóvel não encontrado!");
            return;
        }

        // Definindo os atributos do imóvel
        novoImovel.setLogradouro("Rua das Palmeiras, 123");
        novoImovel.setBairro("Centro");
        novoImovel.setCep("12345-678");
        novoImovel.setMetragem(120);
        novoImovel.setDormitorios((byte) 3);
        novoImovel.setBanheiros((byte) 2);
        novoImovel.setSuites((byte) 1);
        novoImovel.setVagasGaragem((byte) 2);
        novoImovel.setValorAluguelSugerido(2500.00);
        novoImovel.setObs("Apartamento bem localizado, próximo ao comércio.");

        // Salvando o imóvel no banco
        imoveisRepo.save(novoImovel);
        System.out.println("Imóvel cadastrado com sucesso!");

        // Buscar um imóvel pelo ID
        System.out.println("\nBuscando imóvel com ID 1...");
        Optional<Imoveis> imovelOpt = imoveisRepo.findById(1);
        imovelOpt.ifPresentOrElse(
            imovel -> System.out.println("✔ Imóvel encontrado: " + imovel.getLogradouro() + " - Bairro: " + imovel.getBairro()),
            () -> System.out.println("Imóvel não encontrado.")
        );

        // Listar todos os imóveis cadastrados
        System.out.println("\nLista de imóveis cadastrados:");
        List<Imoveis> listaImoveis = imoveisRepo.findAll();
        if (!listaImoveis.isEmpty()) {
            listaImoveis.forEach(imovel -> System.out.println(imovel.getId() + " - " + imovel.getLogradouro() + " - Bairro: " + imovel.getBairro()));
        } else {
            System.out.println("⚠Nenhum imóvel cadastrado.");
        }

        // Atualizar um imóvel
        if (imovelOpt.isPresent()) {
            Imoveis imovelAtualizado = imovelOpt.get();
            imovelAtualizado.setValorAluguelSugerido(2700.00);
            imoveisRepo.update(imovelAtualizado);
            System.out.println("\nImóvel atualizado com sucesso!");
        }

//        // Deletar um imóvel
//        System.out.println("\nDeletando imóvel com ID 1...");
//        imoveisRepo.deleteById(1);
//        System.out.println("Imóvel deletado com sucesso!");
//
//        // Listar novamente para verificar a exclusão
//        System.out.println("\nLista de imóveis após exclusão:");
//        List<Imoveis> listaAposExclusao = imoveisRepo.findAll();
//        if (listaAposExclusao.isEmpty()) {
//            System.out.println("Nenhum imóvel cadastrado após a exclusão.");
//        } else {
//            listaAposExclusao.forEach(imovel -> System.out.println(imovel.getId() + " - " + imovel.getLogradouro() + " - Bairro: " + imovel.getBairro()));
//        }
    }
}
