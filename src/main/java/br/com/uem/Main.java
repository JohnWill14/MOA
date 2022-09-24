package br.com.uem;

import java.util.Scanner;

public class Main {
    private static int[][] memo;
    private static Scanner sc = new Scanner(System.in);

    private static int qtd;
    private static int capacidade;
    private static int valores[];;
    private static int pesos[];

    public static void main(String[] args) {
        qtd = sc.nextInt();
        capacidade = sc.nextInt();

        valores = new int[qtd];
        pesos = new int[qtd];

        for(int i=0;i<qtd;i++){
            valores[i] = sc.nextInt();
            pesos[i] = sc.nextInt();
        }

        Instance instance = Instance.builder()
                .capacidade(capacidade)
                .qtd(qtd)
                .pesos(pesos)
                .valores(valores)
                .build();

        mochilaOtimizada(instance);
        mochilaPedreragem(instance);

    }

    private static void mochilaPedreragem(Instance instance){
        Mochila mochila = new MochilaBruta(instance);

        mochila.solve();

        InfoInstance info = mochila.getInfo();

        System.out.println("*** Mochila sem memorizacao ***");
        System.out.println("valor maximo => "+ info.getAns());
        System.out.println("Quantidade de itens => "+ info.getNumeroItens());
        System.out.println("Valor em milisegundos => "+ info.getTimeMillis());
        System.out.println("Valor em nanosegundos => "+ (info.getTimeNano()/1e6)+" * 1e6\n" );
    }

    private static void mochilaOtimizada(Instance instance){
        Mochila mochila = new MochilaMemorizada(instance);

        mochila.solve();

        InfoInstance info = mochila.getInfo();

        System.out.println("*** Mochila otimizada ***");
        System.out.println("valor maximo => "+ info.getAns());
        System.out.println("Quantidade de itens => "+ info.getNumeroItens());
        System.out.println("Valor em milisegundos => "+ info.getTimeMillis());
        System.out.println("Valor em nanosegundos => "+ (info.getTimeNano()/1e6)+" * 1e6\n" );
    }
}