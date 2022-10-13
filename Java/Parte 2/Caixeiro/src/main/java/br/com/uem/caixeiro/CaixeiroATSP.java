package br.com.uem.caixeiro;

import br.com.uem.InfoInstance;

import java.util.*;
import java.util.stream.Collectors;

public class CaixeiroATSP implements Caixeiro{
    private int[] visitado;

    private int cont =0;
    private int dimension;
    private int[][] dist;

    public CaixeiroATSP(InstanceATSP instance) {
        super();
        this.dimension = instance.getDimension();
        this.dist = instance.getArestas();
        visitado = new int[this.dimension];

    }



    private  List<Integer> opt(int s){
        int pontoVerificar = s;
        List<Integer> rotaMinimizada = new ArrayList<>();
        rotaMinimizada.add(s);

        int menor = Integer.MAX_VALUE;
        int pontoMenor;

        pontoMenor = pontoMaisProximo(s, rotaMinimizada);
        rotaMinimizada.add(pontoMenor);

        pontoMenor = pontoMaisProximo(s, rotaMinimizada);
        rotaMinimizada.add(pontoMenor);

        System.out.println("oi");
        rotaMinimizada.stream()
                .forEach(System.out::println);
        System.out.println("fim /=");

        int tamanhoRotaMinimizada = this.dimension -2;

        while(tamanhoRotaMinimizada>0){
            int custo = Integer.MAX_VALUE;
            int pontoInserir = 0;

            for (int i=0;i<this.dimension;i++){
                if(rotaMinimizada.contains(i)){
                    continue;
                }

                for(int j = 0; j<rotaMinimizada.size()-1; j++ ){
                    int k1 = rotaMinimizada.get(j);
                    int k2 = rotaMinimizada.get(j+1);
                    System.out.println(k1+" "+k2);
                    int custoNovaRota = dist[k1][i] + dist[k2][i] - dist[k1][k2];

                    if(custoNovaRota<custo){
                        custo = custoNovaRota;
                        pontoInserir = i;
                        pontoMenor = k1;
                    }
                }

            }

            if(custo!=Integer.MAX_VALUE){
                inserirPontoApos(pontoInserir, rotaMinimizada, pontoMenor);
            }

            tamanhoRotaMinimizada-=1;
        }

        rotaMinimizada.add(s);

        return rotaMinimizada;
    }

    private int pontoMaisProximo(int s, List<Integer> listaPontos){
        int menor = Integer.MAX_VALUE;
        int pontoMaisProximo = 0;

        for (int i=0;i<this.dimension;i++){
            if(listaPontos.contains(i)){
                continue;
            }
            if(dist[s][i]<menor) {
                menor = dist[s][i];
                pontoMaisProximo = i;
            }
        }

        return pontoMaisProximo;
    }
    private void inserirPontoApos(int pontoInserir, List<Integer> rotaMinimizada, int pontoMenor){
        int lastIndexOf = rotaMinimizada.lastIndexOf(pontoMenor);
        lastIndexOf++;
        rotaMinimizada.add(lastIndexOf, pontoInserir);
    }
    @Override
    public void solve() {
        List<Integer> opt = opt(0);

        String collect = opt.stream()
                .map(e -> Integer.toString(e.intValue()))
                .collect(Collectors.joining(", "));

        System.out.println(collect);

        Set<Integer> set = new HashSet<>(opt);
        System.out.println("tam: "+set);

        int custo = 0;

        for(int i = 1;i<opt.size();i++){
            Integer k = opt.get(i);
            Integer j = opt.get(i-1);

            custo += dist[j][k];
        }

        System.out.println("custo: "+custo);
    }

    @Override
    public InfoInstance getInfo() {
        return null;
    }
}
