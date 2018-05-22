package problemadamochila;

/**
 *
 * @author luana
 */


public class Main {

    /**
     * @param args the command line arguments
     */
    private static int n, capacity;
    private static int[] amount, weight;

    public static void main(String[] args) {

        String end = "src/problemadamochila/mochila01.txt";
        ReadFile enviroment = new ReadFile(end);
        enviroment.lerArquivo();
        n = enviroment.getN();
        capacity = enviroment.getCapacity();
        amount = enviroment.getAmount();
        weight = enviroment.getWeight();
        
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item("" + (i + 1), amount[i], weight[i]);
        }

        // Calculando e imprimindo os resutados
        System.out.println("Capacidade da Mochila = " + capacity);
        Bag m = new Bag();
        m.init(capacity, items);
        System.out.println("Valor: " + m.getLucro());
        System.out.println("Produdos escolhidos: " + m.getProdutosUsados());

    }
}