package br.com.uem.caixeiro;

import br.com.uem.InfoInstance;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CaixeiroATSP implements Caixeiro{
    private Map<Pair<Integer, Mask>, Integer> memo;

    private int[] visitado;

    private int cont =0;
    private int dimension;
    private int[][] dist;

    private int ans;

    private long timeMillis;
    private long timeNano;

    public CaixeiroATSP(InstanceATSP instance) {
        super();
        memo = new HashMap<>();
        this.dimension = instance.getDimension();
        this.dist = instance.getArestas();
    }



    private int opt(int k, Mask mask ){

        if(mask.size() == 2&&k!=0){
            return this.dist[0][k];
        }

        Pair<Integer, Mask> p = new ImmutablePair<>(k, mask);

        if(memo.get(p)!=null){
            return memo.get(p);
        }

        int ans = Integer.MAX_VALUE;

        for(int j=0; j < this.dimension; j++ ){
            if(mask.contain(j)&&j!=k&&j!=0){

                ans = Math.min(ans,
                        opt(j, mask.remove(k))+dist[j][k]);
            }
        }


        memo.put(p, ans);

        return ans;
    }

    @Override
    public void solve() {
        int ans = Integer.MAX_VALUE;

        int valorInicial = (1 << (this.dimension+1))-1;

        Mask mask = new BitMask(valorInicial).remove(0);

        long begin = System.currentTimeMillis();
        long beginNano = System.nanoTime();

        for(int i=0; i < this.dimension; i++ ){
                ans = Math.min(ans,
                        opt(i, mask)+dist[i][0]);
        }

        long endNano = System.nanoTime();
        long end = System.currentTimeMillis();

        this.ans = ans;

        System.out.println("Custo: "+ans);

        this.timeMillis = end-begin;
        this.timeNano = endNano - beginNano;

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
