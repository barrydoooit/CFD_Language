package Utils;

import java.util.ArrayList;

public class PairwiseList extends Variable{
    ArrayList<Integer> valuesOnX;
    ArrayList<Integer> valuesOnY;
    public PairwiseList(String identifier){
        super(identifier, VarType.UnsignedPairwiseList);
        valuesOnX = new ArrayList<>();
        valuesOnY = new ArrayList<>();
    }
    public PairwiseList(){
        super(VarType.UnsignedPairwiseList);
        valuesOnX = new ArrayList<>();
        valuesOnY = new ArrayList<>();
    }
    public PairwiseList append(int onX, int onY){
        valuesOnX.add(onX);
        valuesOnY.add(onY);
        return this;
    }
    public PairwiseList append(Pairwise pairwise){
        if(pairwise.isRelative())
            throw new RuntimeException("Compiler Error! Assigning relative pairwise value to PairwiseList!");
        valuesOnX.add(pairwise.getOnX());
        valuesOnY.add(pairwise.getOnY());
        return this;
    }
    public PairwiseList appendAll(PairwiseList pairwiseList){
        if(pairwiseList.valuesOnX.size()==pairwiseList.valuesOnY.size()){
            valuesOnX.addAll(pairwiseList.valuesOnX);
            valuesOnY.addAll(pairwiseList.valuesOnY);
        }else{
            throw new RuntimeException("Compiler error! PairwiseList to append is broken.");
        }
        return this;
    }

    public PairwiseList clone(){
        return new PairwiseList().appendAll(this);
    }

    public int size(){
        if(valuesOnX.size()==valuesOnY.size()){
            return valuesOnX.size();
        }else{
            throw new RuntimeException("Compiler error! PairwiseList to append is broken.");
        }
    }
}
