package br.com.uem.caixeiro;

public interface Mask {
    int size();
    boolean contain(int n);
    Mask remove(int n);

}
