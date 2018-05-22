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

public class Leitura {

    private final String endereco;
    private final Path caminho;
    private final Charset utf8;
    private final String token;
    private int n, capacidade;
    private int[] valor, peso;

    public Leitura(String end, String token) {
        this.endereco = end;
        caminho = Paths.get(endereco);
        utf8 = StandardCharsets.UTF_8;
        this.token = token;
    }

    public Leitura(String end) {
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
                    capacidade = Integer.parseInt(t[1]);
                    peso = new int[n];
                    valor = new int[n];
                } else {
                    peso[k] = Integer.parseInt(t[0]);
                    valor[k] = Integer.parseInt(t[1]);
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

    public int getCapacidade() {
        return capacidade;
    }

    public int[] getValor() {
        return valor;
    }

    public int[] getPeso() {
        return peso;
    }
}

