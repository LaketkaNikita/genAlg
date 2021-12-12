public class Diafante {
    int vec[];
    int result;
    int num = 1000;
    final int MAXPOP = 5000;
    Gene[] population;

    public Diafante(int res, int[] a){
        vec = a;
        result = res;
        population= new Gene[MAXPOP];
        for (int i = 0; i < population.length; i++){
            population[i] = new Gene();
        }

    }
    public int solve(){
        for (int i = 0; i< population.length; i++){
            for(int j = 0; j <vec.length; j++){
                population[i].alleles[j]=((int) (100 * result*Math.random())) % (result + 1);
            }
        }
        int res = createFitness();
        if(res!=-1){
            return res;
        }
        int iterations = 0;
        while(res!=-1|| iterations < num){
            generateLikelehoods();
            createNewPopulation();
            res = createFitness();
            if(res != -1){
                return res;
            }
            iterations++;
        }

        return -1;
    }

    private void createNewPopulation(){
        Gene[] temppop = new Gene[MAXPOP];
        ex: for (int i = 0; i < temppop.length; i++){
            int parent1 = 0, parent2 = 0, iterations = 0;
            while(parent2==parent1 || population[parent1].equals(population[parent2])){
                parent1 = getIndex(Math.random());
                parent2 = getIndex(Math.random());
                if (++iterations > MAXPOP * MAXPOP){
                    System.out.println("imbreeding");
                    return;
                }
            }
            temppop[i] = breed(parent1, parent2);
        }
        population=temppop;
    }

    private Gene breed(int p1, int p2){
        int crossover= ((int) Math.random() * 3);
        Gene child = (Gene) population[p1].clone();
        int initial = 0, final_e = 3;
        if(Math.random() < 0.5){
            initial = crossover;
        }else{
            final_e = crossover + 1;
        }
        for(int i = initial; i < final_e; i++){
            child.alleles[i] = population[p2].alleles[i];
            if(Math.random()<0.2){
                child.alleles[i] = (int) (Math.random()*result);
            }
        }

        return child;
    }
    private int getIndex(double val){
        double last = 0;
        for (int i = 0; i < population.length; i++){
            last = population[i].likelihood + last;
            if(val <= last){
                return i;
            }
        }
        return -1;
    }
    private void generateLikelehoods(){
        double multinv = multInv();
        for (int i = 0; i < population.length; i++){
            population[i].likelihood = (1./(double)population[i].fitness)/multinv;

        }
    }
    private double multInv(){
        double sum = 0;
        for(int i = 0; i < population.length; i++){
            sum +=1./((double)population[i].fitness);
        }
        return sum;
    }
    private int createFitness(){
        int res = -1;

        for(int i = 0; i < population.length; i++){
            int fitness = fitness(population[i]);
            if (fitness == 0){
                return i;
            }
        }
        return res;
    }

    private int fitness(Gene gene){
        int total = 0;
        for(int i = 0; i < vec.length; i++){
            total = vec[i]*gene.alleles[i]+total;
        }
        return gene.fitness=Math.abs(total-result);
    }

    public Gene getGene(int ans) {
        return population[ans];
    }
}
