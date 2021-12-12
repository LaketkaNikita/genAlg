public class Gene implements Cloneable {
    public int[] alleles = new int[4];
    int fitness;
    double likelihood;

    @Override
    public Object clone() {
        try{
            return super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public String toString(){
        return "[" + alleles[0] + ", " + alleles[1] + ", " + alleles[2] + ", " + alleles[3] + "]";
    }
    @Override
    public boolean equals(Object obj){
        Gene g=(Gene)obj;
        for(int i = 0; i < alleles.length; i++){
            if(g.alleles[i]!=this.alleles[i]) {
                return false;
            }
        }
        return true;
    }
}
