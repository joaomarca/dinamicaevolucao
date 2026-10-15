import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class ProcessadorDeVendas {

    private EstrategiaFrete estrategiaFrete;

    public ProcessadorDeVendas(EstrategiaFrete estrategiaFrete) {
        this.estrategiaFrete = estrategiaFrete;
    }

    public void processar(Cliente cliente, List<Item> itens) {
        System.out.println("Iniciando processamento...");

        if (!cliente.isDocumentoValido()) {
            System.out.println("Erro: Documento invalido.");
            return;
        }

        double subtotal = calcularSubtotal(itens);
        double impostos = calcularImpostos(itens);

        // Aqui o Strategy será acionado no futuro:
        double frete = estrategiaFrete.calcularFrete(cliente);

        double totalFinal = subtotal + impostos + frete;
        imprimirRecibo(cliente, subtotal, impostos, frete, totalFinal);
    }

    private double calcularSubtotal(List<Item> itens) {
        double total = 0;
        for (Item item : itens) {
            total += item.getSubtotal();
        }
        return total;
    }

    private double calcularImpostos(List<Item> itens) {
        double impostoTotal = 0;
        for (Item item : itens) {
            if (item.getValor() > 100) {
                impostoTotal += item.getSubtotal() * 0.15;
            } else {
                impostoTotal += item.getSubtotal() * 0.05;
            }
        }
        return impostoTotal;
    }

    private void imprimirRecibo(Cliente cliente, double subtotal, double impostos, double frete, double totalFinal) {
        System.out.println("=====================================");
        System.out.println("RECIBO DE VENDA");
        System.out.println("=====================================");
        System.out.println("Data: " + new Date());
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Documento: " + cliente.getDocumento());
        System.out.println("-------------------------------------");
        System.out.println("Subtotal Itens: R$ " + subtotal);
        System.out.println("Total Impostos: R$ " + impostos);
        System.out.println("Frete: R$ " + frete);
        System.out.println("-------------------------------------");
        System.out.println("TOTAL A PAGAR: R$ " + totalFinal);
        System.out.println("=====================================");
    }

    public static void main(String[] args) {
        Cliente cliente = new Cliente("João Silva", "12345678901", 2, "SP");

        List<Item> itens = new ArrayList<>();
        itens.add(new Item(50.0, 2));
        itens.add(new Item(150.0, 1));
        itens.add(new Item(30.0, 3));

        EstrategiaFrete estrategia;
        if (cliente.getTipoCliente() == 1) {
            estrategia = new FreteVipStrategy();
        } else if (cliente.getEstado().equalsIgnoreCase("SP")) {
                estrategia = new FreteSudesteStrategy();
            } else {
                estrategia = new FreteNacionalStrategy();
        }

        ProcessadorDeVendas processador = new ProcessadorDeVendas(estrategia);

        processador.processar(cliente, itens);
    }
}