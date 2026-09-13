
package projetoprincipiosdesign;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class PedidoRepository {

    public void salvar(Pedido pedido, double total) {
        String linha = pedido.obterNomeCliente() + ";"
        + total + System.lineSeparator();

        try {
            Files.writeString(
                Path.of("pedidos.txt"),
                linha,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            throw new RuntimeException(
                "Erro ao salvar o pedido em arquivo.", e
            );
        }
    }
}