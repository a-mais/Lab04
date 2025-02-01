package si.labbd.tests;

import si.labbd.models.Alugueis;
import si.labbd.repository.AlugueisRepository;

import java.util.Date;
import java.util.List;
import java.util.Calendar;

public class AlugueisTeste {
    public static void main(String[] args) {
        AlugueisRepository alugueisRepository = new AlugueisRepository();

        System.out.println("=== INICIANDO TESTES DE ALUGUEIS ===");

        // Criar um aluguel para teste
        Alugueis aluguel = new Alugueis();
        aluguel.setDataVencimento(new Date());
        aluguel.setValorPago(2000.00);
        aluguel.setDataPagamento(new Date()); // Simulando pagamento em dia

        alugueisRepository.save(aluguel);
        System.out.println("Aluguel registrado com valor: R$" + aluguel.getValorPago());

        // Testar busca de aluguel por ID da locação
        System.out.println("\nBuscando aluguel por ID da locação...");
        Alugueis aluguelEncontrado = alugueisRepository.findByLocacaoId(1);
        if (aluguelEncontrado != null) {
            System.out.println("Aluguel encontrado! Valor: R$" + aluguelEncontrado.getValorPago());
        } else {
            System.out.println("Nenhum aluguel encontrado para esta locação.");
        }

        // Testar busca de aluguéis com valor abaixo de R$2500
        System.out.println("\nBuscando aluguéis com valor abaixo de R$2500...");
        List<Alugueis> alugueisBaratos = alugueisRepository.findByValorAluguelMenorQue(2500);
        System.out.println("Total de aluguéis encontrados: " + alugueisBaratos.size());

        // Simular um pagamento atrasado
        System.out.println("\nSimulando um aluguel pago com atraso...");
        Alugueis aluguelAtrasado = new Alugueis();
        aluguelAtrasado.setDataVencimento(new Date());

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 10); // Atraso de 10 dias
        aluguelAtrasado.setDataPagamento(cal.getTime());

        aluguelAtrasado.setValorPago(1800.00);
        alugueisRepository.save(aluguelAtrasado);

        // Buscar aluguéis pagos com atraso
        System.out.println("\nBuscando aluguéis pagos com atraso...");
        List<Alugueis> alugueisAtrasados = alugueisRepository.findAlugueisComAtraso();
        System.out.println("Total de aluguéis pagos com atraso: " + alugueisAtrasados.size());
    }
}
