package br.com.uem;

import br.com.uem.caixeiro.Caixeiro;
import br.com.uem.caixeiro.CaixeiroATSP;
import br.com.uem.caixeiro.InstanceATSP;

import java.util.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static int[][] valores;

    public static void main(String[] args) {
        int v = sc.nextInt();


        InstanceATSP instance = new InstanceATSP();
        instance.setDimension(v);
        valores = new int[v][v];

        for (int i=0;i<v;i++){
            for (int j=0;j<v;j++){
                valores[i][j] = sc.nextInt();
            }
        }
        instance.setArestas(valores);

        Caixeiro caixeiro = new CaixeiroATSP(instance);


        caixeiro.solve();

    }
}
