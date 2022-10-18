package br.com.uem.caixeiro;

import br.com.uem.InfoInstance;

import java.util.*;
import java.util.stream.Collectors;

public class CaixeiroATSP implements Caixeiro{
    private int[] visitado;

    private int cont =0;
    private int dimension;
    private int[][] dist;

    private long timeMillis;
    private long timeNano;
    private int ans;


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

        System.out.println(rotaMinimizada);

        rotaMinimizada = melhoramento2Opt(rotaMinimizada);


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

    private List<Integer> melhoramento2Opt(List<Integer> rota){

        int custoMelhorRota = custoRota(rota);
        boolean encontrouMelhorRota = true;
        int tamanhoRota = rota.size();

        while(encontrouMelhorRota){
            encontrouMelhorRota = false;

            for(int i=1; i< tamanhoRota - 1;i++ ){
                for(int j = i+2; j<rota.size()-1;j++){
                    List<Integer> novaRota = opt2Swap(i, j, rota);
                    int custNovaRota = custoRota(novaRota);
                    if(custNovaRota<custoMelhorRota){
                        rota = novaRota;
                        custoMelhorRota = custNovaRota;
                        encontrouMelhorRota = true;
                    }

                }
            }
        }

        return rota;
    }

    private List<Integer> opt2Swap(int i, int j, List<Integer> rota){
        List<Integer> parteInicial = rota.subList(0, i+1);

        List<Integer> subRota = new ArrayList<>(rota.subList(i + 1, j + 1));
        Collections.reverse(subRota);


        List<Integer> rotaFinal = rota.subList(j+1, rota.size());

        List<Integer> novaRota =  new ArrayList<>();
        novaRota.addAll(parteInicial);
        novaRota.addAll(subRota);
        novaRota.addAll(rotaFinal);

        return novaRota;
    }

    private int custoRota(List<Integer> rota){
        int custo = 0;

        for(int i = 0; i<rota.size()-1;i++){
            int v1 = rota.get(i);
            int v2 = rota.get(i+1);
            custo += dist[v1][v2];
        }

        return custo;
    }
    @Override
    public void solve() {
        long begin = System.currentTimeMillis();
        long beginNano = System.nanoTime();

        List<Integer> opt = opt(0);

        long endNano = System.nanoTime();
        long end = System.currentTimeMillis();
        
        this.timeMillis = end-begin;
        this.timeNano = endNano - beginNano;

        String collect = opt.stream()
                .map(e -> Integer.toString(e.intValue()))
                .collect(Collectors.joining(", "));

        System.out.println(collect);

        Set<Integer> set = new HashSet<>(opt);
        System.out.println("tam: "+set.size());

        this.ans = custoRota(opt);
        System.out.println("custo: "+this.ans);
    }

    @Override
    public InfoInstance getInfo() {
        InfoInstance instance = InfoInstance.builder()
                .ans(this.ans)
                .timeMillis(this.timeMillis)
                .timeNano(this.timeNano)
                .numeroItens(this.dimension)
                .build();
        return instance;
    }
}
