package si.labbd.tests;

import si.labbd.models.Clientes;
import si.labbd.repository.ClientesRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ClientesTeste {
    public static void main(String[] args) {
        ClientesRepository clientesRepo = new ClientesRepository();

        // Criar um novo cliente
        Clientes novoCliente = new Clientes();
        novoCliente.setNome("Ana Pereira");
        novoCliente.setCpf("123.456.789-00");
        novoCliente.setTelefone("11987654321");
        novoCliente.setEmail("ana.pereira@email.com");
        novoCliente.setDataNascimento(new Date());

        // Salvando o cliente no banco
        clientesRepo.save(novoCliente);
        System.out.println("✅ Cliente cadastrado com sucesso!");

        // Buscar um cliente pelo ID
        System.out.println("\n🔍 Buscando cliente com ID 1...");
        Optional<Clientes> clienteOpt = clientesRepo.findById(1);
        clienteOpt.ifPresentOrElse(
            cliente -> System.out.println("✔ Cliente encontrado: " + cliente.getNome() + " - CPF: " + cliente.getCpf()),
            () -> System.out.println("❌ Cliente não encontrado.")
        );

        // Listar todos os clientes cadastrados
        System.out.println("\n📋 Lista de clientes cadastrados:");
        List<Clientes> listaClientes = clientesRepo.findAll();
        if (!listaClientes.isEmpty()) {
            listaClientes.forEach(cliente ->
                System.out.println(cliente.getId() + " - " + cliente.getNome() + " - CPF: " + cliente.getCpf())
            );
        } else {
            System.out.println("⚠️ Nenhum cliente cadastrado.");
        }

        // Atualizar um cliente
        if (clienteOpt.isPresent()) {
            Clientes clienteAtualizado = clienteOpt.get();
            clienteAtualizado.setEmail("ana.nova@email.com");
            clientesRepo.update(clienteAtualizado);
            System.out.println("\n🔄 Cliente atualizado com sucesso!");
        }

//        // Deletar um cliente
//        System.out.println("\n🗑️ Deletando cliente com ID 1...");
//        clientesRepo.deleteById(1);
//        System.out.println("✅ Cliente deletado com sucesso!");
//
//        // Listar novamente para verificar a exclusão
//        System.out.println("\n📋 Lista de clientes após exclusão:");
//        List<Clientes> listaAposExclusao = clientesRepo.findAll();
//        if (listaAposExclusao.isEmpty()) {
//            System.out.println("✅ Nenhum cliente cadastrado após a exclusão.");
//        } else {
//            listaAposExclusao.forEach(cliente ->
//                System.out.println(cliente.getId() + " - " + cliente.getNome() + " - CPF: " + cliente.getCpf())
//            );
//        }
    }
}
