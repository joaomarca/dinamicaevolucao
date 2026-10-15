public class CalculadoraFrete {

    // ATENÇÃO: Este método viola o OCP e tem alta Complexidade Ciclomática.
    // Você deve EXCLUIR esta classe e substituí-la pela Interface e pelas Estratégias Concretas!
    public double calcular(Cliente cliente) {
        if (cliente.getTipoCliente() == 1) {
            return 0.0; // Frete VIP
        } else if (cliente.getTipoCliente() == 2) {
            String estado = cliente.getEstado();
            if (estado.equals("SP") || estado.equals("RJ") || estado.equals("MG")) {
                return 15.0; // Frete Sudeste
            } else {
                return 50.0; // Frete Nacional
            }
        }
        return 50.0; // Default
    }
}