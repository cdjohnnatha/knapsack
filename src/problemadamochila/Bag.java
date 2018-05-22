package problemadamochila;
import java.util.ArrayList;

/**
 *
 * @author luana
 */


public class Bag {

    private int benefit;
    private ArrayList<Item> usedItems;
    private int[][] matrix;
    private String usedProducts = "";

    public Bag() {
        benefit = 0;
        usedItems = new ArrayList<>();
    }

    public void init(int capacidade, Item[] obj) {
        int qtdeObjetos = obj.length, aux, p1, p2;
        matrix = new int[qtdeObjetos + 1][capacidade + 1];
        Item ob;
        for (int lin = 0; lin <= qtdeObjetos; lin++) {
            for (int col = 0; col <= capacidade; col++) {
                if (lin == 0 || col == 0) {
                    matrix[lin][col] = 0;
                } else {
                    ob = obj[lin - 1];
                    if (ob.getPeso() <= col) {
                        p1 = ob.getValor() + matrix[lin - 1][col - ob.getPeso()];
                        p2 = matrix[lin - 1][col];
                        matrix[lin][col] = Math.max(p1, p2);
                    } else {
                        matrix[lin][col] = matrix[lin - 1][col];
                    }
                }
            }
        }
//        this.imprimir(matriz);
        benefit = matrix[qtdeObjetos][capacidade];
        this.produtosUsados(matrix, obj);
    }

    private void produtosUsados(int[][] matriz, Item[] obj) {
        int linha = matriz.length - 1;
        int coluna = matriz[0].length - 1;
        for (int i = linha - 1; i >= 0; i--) {
            if (matriz[linha][coluna] - matriz[linha - 1][coluna] > 0) {
                linha--;
                coluna -= obj[i].getPeso();
                usedItems.add(obj[i]);
            } else {
                linha--;
            }
        }
    }

    public int getLucro() {
        return benefit;
    }

    public String getProdutosUsados() {
        int k = 1;
        for (int i = usedItems.size() - 1; i >= 0; i--, k++) {
            if (i > 0) {
                usedProducts += usedItems.get(i).getNome() + ", ";
            } else {
                usedProducts += usedItems.get(i).getNome() + ".";
            }

            if (k % 21 == 0) {
                usedProducts += "\n";
            }
        }
        return usedProducts;
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

