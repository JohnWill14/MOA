package br.com.uem;

import java.util.Scanner;

public class Main {
    private static int[][] memo;
    private static int[] pesos;
    private static int[] valores;
    private static int capacidade;
    private static int qtd;
    private static Scanner sc = new Scanner(System.in);

    private static long numeroDeChamadas = 0;

    public static int opt(int pos, int capacidade){
        if(pos < 0 || capacidade == 0){
            return 0;
        }

        numeroDeChamadas += 1;

        int bia = opt(pos-1, capacidade);

        if(pesos[pos]>capacidade){
            return bia;
        }

        return Math.max(bia, opt(pos-1, capacidade-pesos[pos]) + valores[pos]);
    }

    public static void main(String[] args) {
        qtd = sc.nextInt();
        capacidade = sc.nextInt();

        valores = new int[qtd];
        pesos = new int[qtd];

        for(int i=0;i<qtd;i++){
            valores[i] = sc.nextInt();
            pesos[i] = sc.nextInt();
        }

        long begin = System.currentTimeMillis();
        int valorMax = opt(qtd-1, capacidade);
        long end = System.currentTimeMillis();

        System.out.println("valor maximo => "+ valorMax);
        System.out.println("Número de chamadas => "+ numeroDeChamadas);
        System.out.println("Valor em milisegundos => "+ (end-begin));
    }
}