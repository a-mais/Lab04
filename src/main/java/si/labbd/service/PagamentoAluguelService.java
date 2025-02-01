package si.labbd.service;

import si.labbd.models.Alugueis;
import si.labbd.models.Locacao;
import si.labbd.repository.AlugueisRepository;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class PagamentoAluguelService {
    private final AlugueisRepository alugueisRepository;

    public PagamentoAluguelService() {
        this.alugueisRepository = new AlugueisRepository();
    }

    // Registrar pagamento do aluguel e calcular multa se houver atraso
    public void registrarPagamento(int locacaoId, Double valorPago, Date dataPagamento) {
        // Buscar o aluguel associado à locação
        Alugueis aluguel = alugueisRepository.findByLocacaoId(locacaoId);

        if (aluguel == null) {
            throw new RuntimeException("Erro: Nenhum aluguel encontrado para a locação ID " + locacaoId);
        }

        // Calcular multa se o pagamento estiver atrasado
        double multa = calcularMulta(aluguel.getDataVencimento(), dataPagamento, aluguel.getValorPago());
        double valorFinal = aluguel.getValorPago() + multa;

        // Atualizar pagamento no aluguel
        aluguel.setDataPagamento(dataPagamento);
        aluguel.setValorPago(valorFinal);
        alugueisRepository.update(aluguel);

        System.out.println("Pagamento registrado! Valor final: R$ " + valorFinal);
    }

    // Calcular multa de 0,33% por dia de atraso, limitada a 20% do valor do aluguel
    private double calcularMulta(Date dataVencimento, Date dataPagamento, double valorAluguel) {
        if (!dataPagamento.after(dataVencimento)) {
            return 0; // Sem multa se pago no prazo
        }

        // Calcular dias de atraso
        long diff = dataPagamento.getTime() - dataVencimento.getTime();
        long diasAtraso = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        // Calcular multa (0,33% ao dia, limitada a 20%)
        double multa = (valorAluguel * 0.0033) * diasAtraso;
        return Math.min(multa, valorAluguel * 0.2); // Máximo 20% do valor do aluguel
    }
}
