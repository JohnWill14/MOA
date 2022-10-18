package br.com.uem.caixeiro;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class BitMask implements Mask {

    private int mask;

    public BitMask(int mask) {
        this.mask = mask;
    }

    @Override
    public int size() {
        int count = 0;
        int copy = mask;
        while (copy > 0) {
            count += copy & 1;
            copy >>= 1;
        }
        return count;
    }

    @Override
    public boolean contain(int n) {
        if(n==0&&mask==0){
            return true;
        }
        int b = mask & (1 << n);
        return b>0;
    }

    @Override
    public Mask remove(int n) {
        return new BitMask(mask & (~(1 << n)));
    }

}
