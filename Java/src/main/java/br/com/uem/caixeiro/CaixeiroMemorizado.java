package br.com.uem.caixeiro;

import br.com.uem.InfoInstance;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class CaixeiroMemorizado implements Caixeiro{
    private int[] memo;

    private int[] visitado;

    private int cont =0;
    private int dimension;
    private Map<Integer, Pair<Integer, Integer>> pesos;

    public CaixeiroMemorizado(int dimension, Map<Integer, Pair<Integer, Integer>> pesos) {
        this.dimension = dimension;
        this.pesos = pesos;
        resetMemo();
    }

    public CaixeiroMemorizado(Instance instance) {
        super();
        this.dimension = instance.getDimension();
        this.pesos = instance.getPesos();
        resetMemo();
    }

    private void resetMemo(){
        memo = new int[this.dimension+5];
    visitado = new int[this.dimension+5];
        for(int i=0;i<this.dimension+5;i++){
                memo[i] = -1;
            visitado[i] = 0;
        }
    }

    private int opt(int k ){
        if(cont == pesos.size()-2){
            return 0;
        }

        visitado[k] = 1;
        cont++;

        if(memo[k]!=-1){
            return memo[k];
        }

        if(cont-pesos.size()==1){
            Set<Map.Entry<Integer, Pair<Integer, Integer>>> entries = pesos.entrySet();
            Iterator<Map.Entry<Integer, Pair<Integer, Integer>>> iterator = entries.iterator();
            return  iterator.next().getValue().getValue();
        }

        Set<Map.Entry<Integer, Pair<Integer, Integer>>> entries = pesos.entrySet();
        Iterator<Map.Entry<Integer, Pair<Integer, Integer>>> iterator = entries.iterator();
        Pair<Integer, Integer> first = iterator.next().getValue();

        int ans = opt(first.getKey())+first.getValue();

        for(Map.Entry<Integer, Pair<Integer, Integer>> pair: iterator ){
            int key = pair.getKey();
            Pair<Integer, Integer> pairValue = pair.getValue();

            if(visitado[pairValue.getKey()]==1){
                continue;
            }

            ans = Math.min(ans, opt(key)+custo(pairValue, first));
            System.out.println(key);
        }

        memo[k] = ans;

        return memo[k];
    }

    public int custo(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2){
        return (int) Math.sqrt(Math.pow((p1.getLeft()-p2.getLeft()),2)+
                Math.pow(p1.getRight()-p2.getRight(),2));
    }

    @Override
    public void solve() {
             this.cont = 0;
            int resp = this.opt(1);
            System.out.println("Resp: "+resp);
    }

    @Override
    public InfoInstance getInfo() {
        return null;
    }
}
