package br.com.uem.caixeiro;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static Map<Integer, Pair<Integer, Integer> > pontos = new HashMap<>();

    public static void main(String[] args) {
        int v = sc.nextInt();

        Instance instance = new Instance();
        instance.setDimension(v);

        while (v-- >0){
            int p1 = sc.nextInt();
            int peso = sc.nextInt();
            int p2 = sc.nextInt();

            pontos.put(p1, new ImmutablePair<>(p2, peso));
        }
        instance.setPesos(pontos);

        Caixeiro caixeiro = new CaixeiroMemorizado(instance);


        caixeiro.solve();

    }
}
