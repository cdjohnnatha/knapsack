package problemadamochila;
import java.util.ArrayList;

/**
 *
 * @author luana
 */


public class Mochila {

    private int lucro;
    private ArrayList<Objeto> objetosUsados;
    private int[][] matriz;
    private String produtosUsados = "";

    public Mochila() {
        lucro = 0;
        objetosUsados = new ArrayList<>();
    }

    public void calcula(int capacidade, Objeto[] obj) {
        int qtdeObjetos = obj.length, aux, p1, p2;
        matriz = new int[qtdeObjetos + 1][capacidade + 1];
        Objeto ob;

        /**
         * Cria-se uma matriz [n+1][capacidade+1]
         *
         * OBS: A matriz possui uma linha a mais que a quantidade de produtos e
         * uma coluna a mais que a capacidade.
         */
        for (int lin = 0; lin <= qtdeObjetos; lin++) {
            for (int col = 0; col <= capacidade; col++) {
                // inicializa a primeira linha e a primeira coluna da matriz com 0
                if (lin == 0 || col == 0) {
                    matriz[lin][col] = 0;
                } else {
                    // objeto em análise atual
                    ob = obj[lin - 1];
                    /**
                     * Compara se o peso do objeto atual é maior ou igual ao
                     * valor da coluna
                     */
                    if (ob.getPeso() <= col) {
                        /**
                         * Se sim, atribui a matriz atual[i][j] o maior valor
                         * entre:
                         *
                         * a) p1 = o somatório do valor do objeto atual + o
                         * valor da matriz [linha anterior] [coluna atual menos
                         * o peso desse objeto]
                         *
                         * b) p2 = valor da matriz [linha anterior] [mesma
                         * coluna]
                         *
                         */
                        p1 = ob.getValor() + matriz[lin - 1][col - ob.getPeso()];
                        p2 = matriz[lin - 1][col];
                        // escolhe o maior entre os dois valores
                        matriz[lin][col] = Math.max(p1, p2);
                    } else {
                        /**
                         * Se não, repete o valor da matriz [linha
                         * anterior][mesma coluna]
                         */
                        matriz[lin][col] = matriz[lin - 1][col];
                    }
                }
            }
        }
//        this.imprimir(matriz);
        // o lucro é o valor correspondente a última célula da matriz
        lucro = matriz[qtdeObjetos][capacidade];
        // chamada de método para obter quais foram os produtos usados
        this.produtosUsados(matriz, obj);
    }

    private void produtosUsados(int[][] matriz, Objeto[] obj) {
        int linha = matriz.length - 1;
        int coluna = matriz[0].length - 1;

        /**
         * Percorrer todas as linhas da matriz na ordem inversa.
         */
        for (int i = linha - 1; i >= 0; i--) {
            /**
             * Comparar se houve variação entre célula da matriz [lin][col] com
             * a célula matriz da linha anterior [lin-1] [col];
             */
            if (matriz[linha][coluna] - matriz[linha - 1][coluna] > 0) {
                /**
                 * Se sim, decremente a linha e atribua a nova coluna subtraindo
                 * o peso do objeto correspondente a última linha;
                 */
                linha--;
                coluna -= obj[i].getPeso(); // subtraindo o peso do objeto da capacidade
                objetosUsados.add(obj[i]); // addicionando o objeto a lista de usados
            } else {
                // Se não, apenas decremente o índice da linha
                linha--;
            }
        }
    }

    public int getLucro() {
        return lucro;
    }

    public String getProdutosUsados() {
        int k = 1;
        for (int i = objetosUsados.size() - 1; i >= 0; i--, k++) {
            if (i > 0) {
                produtosUsados += objetosUsados.get(i).getNome() + ", ";
            } else {
                produtosUsados += objetosUsados.get(i).getNome() + ".";
            }

            if (k % 21 == 0) {
                produtosUsados += "\n";
            }
        }
        return produtosUsados;
    }

    private void imprimir(int[][] vetor) {
        System.out.println("vETOR.LENGTH = " + vetor.length);
        for (int i = 0; i < vetor.length; i++) {
            for (int j = 0; j < vetor[0].length; j++) {
                System.out.printf("%3d ", vetor[i][j]);
            }
            System.out.println("");
        }
    }
}

