package br.com.uem.caixeiro;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.tuple.Pair;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class Instance {
    private int dimension;
    private Map<Integer, Pair<Integer, Integer> > pesos;
}
