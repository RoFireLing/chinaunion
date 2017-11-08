package mutantSet;

import java.util.ArrayList;
import java.util.List;


/**
 * @author phantom
 */
public class BinSet {
    private List<Mutant> mutants;
    public BinSet() {
        mutants = new ArrayList<Mutant>();
    }

    public void add (Mutant mutant){
        mutants.add(mutant);
    }

    /**
     * 返回当前变异体集中的变异体的个数
     * @return 变异体集中的变异体的个数
     */
    public int size(){ return mutants.size(); }

    /**
     * 获得指定变异体的名字
     * @param index 要获得名字的变异体的编号
     * @return 变异体的名字
     */
    public String getMutantName(int index){ return mutants.get(index).getFullClassName(); }

    /**
     * 移除编号为index的变异体
     * @param index
     */
    public void remove(int index){ mutants.remove(index); }



}
