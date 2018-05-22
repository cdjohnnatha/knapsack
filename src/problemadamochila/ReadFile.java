package problemadamochila;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author luana
 */

public class ReadFile {

    private final String address;
    private final Path caminho;
    private final Charset utf8;
    private final String token;
    private int n, capacity;
    private int[] amount, weight;

    public ReadFile(String end, String token) {
        this.address = end;
        caminho = Paths.get(address);
        utf8 = StandardCharsets.UTF_8;
        this.token = token;
    }

    public ReadFile(String end) {
        this(end, " ");
    }

    public void lerArquivo() {
        int k = -1;
        try (BufferedReader leitor = Files.newBufferedReader(caminho, utf8)) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] t = linha.split(token); // recuperando um linha
                if (k == -1) {
                    n = Integer.parseInt(t[0]);
                    capacity = Integer.parseInt(t[1]);
                    weight = new int[n];
                    amount = new int[n];
                } else {
                    weight[k] = Integer.parseInt(t[0]);
                    amount[k] = Integer.parseInt(t[1]);
                }
                k++;
            }
        } catch (IOException e) {
            System.err.println("Erro no recuperar");
        }
    }

    public int getN() {
        return n;
    }

    public int getCapacity() {
        return capacity;
    }

    public int[] getAmount() {
        return amount;
    }

    public int[] getWeight() {
        return weight;
    }
}

