/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.algorytmgenetyczny;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author czrmj
 */
public class Mutation {
    private Population population;

    public Mutation(Population population) {
        this.population = population;
    }
    
    private double mutationProbability() {
        double mutationProbability = ThreadLocalRandom.current().nextDouble(0, 1);
        return mutationProbability;
    }
    
    private int locus() { //locus = index od którego zaczynamy mutację
        int locus = ThreadLocalRandom.current().nextInt(0,
                this.population.getChromosome(0).getLength());
        return locus;
    }
    
    public void mutation(double mutationRate) {
        
        for (int i = 0; i <= this.population.populationSize() - 1; i++) {
            
            double randomDouble = mutationProbability();
            if (randomDouble <= mutationRate) {
                int locus = locus();
                 if (this.population.getChromosome(i).getBit(locus) == 1) {
                     this.population.getChromosome(i).replaceBit(locus, 0);
                 } else {
                     this.population.getChromosome(i).replaceBit(locus, 1);
                 }
            }
        }
    }

}
