public class MyException {
    public static void main(String[] args) {
            int[] a ={14, 2, -26, 1};
            Diafante df = new Diafante(53, a);
            int ans = df.solve();
            if(ans==-1){
                System.out.println("Решение не найдено");
            }else{
                Gene gn = df.getGene(ans);
                System.out.println(gn);
            }
    }
}
