package br.com.uem.caixeiro;

import lombok.EqualsAndHashCode;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@EqualsAndHashCode
public class MaskBruta implements Mask{
    private List<Integer> positions;

    public MaskBruta() {
    }

    public MaskBruta(int... pontos) {
        this.positions = new ArrayList<Integer>();
        for(int i: pontos)
         CollectionUtils.addAll(positions, i);
    }

    public MaskBruta(List<Integer> pontos) {
        this.positions = new ArrayList<Integer>(pontos);
    }

    public String getPositions(){
        return positions.stream()
                 .map(e -> Integer.toString(e))
                .collect(Collectors.joining(", "));
    }

    public int size(){
        return positions.size();
    }

    public boolean contain(int n){
        for(int i: positions){
            if(i==n){
                return true;
            }
        }
        return false;
    }

    public MaskBruta remove(int n){
        List<Integer> lista = new ArrayList(positions);

        for (Iterator<Integer> i = lista.iterator();i.hasNext();){
            int v = i.next();
            if(v==n){
                i.remove();
            }
        }

        return new MaskBruta(lista);
    }

    public int getValue() {
        return this.size();
    }
}
