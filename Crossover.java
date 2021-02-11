/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.algorytmgenetyczny;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author czrmj
 */
public class Crossover {

    private Population population;
    public Population populationAfterCrossover;

    public Crossover(Population population) {
        this.population = population;
        this.populationAfterCrossover = new Population();
    }

    private double crossoverProbability() {
        double crossoverProbability = ThreadLocalRandom.current().nextDouble(0, 1);
        return crossoverProbability;
    }

    private int locus() { //locus = index od którego zaczynamy krzyżowanie
        int locus = ThreadLocalRandom.current().nextInt(1,
                this.population.getChromosome(0).getLength());
        return locus;
    }

    public void crossover(double crossoverRate) {

        for (int i = 0; i < this.population.populationSize() - 1; i = i + 2) {
            
            double randomDouble = crossoverProbability();
            if (randomDouble <= crossoverRate) {
                int locus = locus();

                List<Integer> chromosomeOnePart1 = new ArrayList<>();
                List<Integer> chromosomeOnePart2 = new ArrayList<>();
                List<Integer> chromosomeTwoPart1 = new ArrayList<>();
                List<Integer> chromosomeTwoPart2 = new ArrayList<>();

                chromosomeOnePart1 = this.population.getChromosome(i).subList(0, locus);
                chromosomeTwoPart1 = this.population.getChromosome(i + 1).subList(0, locus);
                chromosomeOnePart2 = this.population.getChromosome(i + 1).subList(locus, this.population.getChromosome(i).getLength());
                chromosomeTwoPart2 = this.population.getChromosome(i).subList(locus, this.population.getChromosome(i).getLength());

                List<Integer> chromosomeOne = Stream.concat(chromosomeOnePart1.stream(), chromosomeOnePart2.stream())
                        .collect(Collectors.toList());
                List<Integer> chromosomeTwo = Stream.concat(chromosomeTwoPart1.stream(), chromosomeTwoPart2.stream())
                        .collect(Collectors.toList());
                ArrayList<Integer> ch1 = new ArrayList<>(chromosomeOne);
                ArrayList<Integer> ch2 = new ArrayList<>(chromosomeTwo);

                this.populationAfterCrossover.addChromosome(new Chromosome(ch1));
                this.populationAfterCrossover.addChromosome(new Chromosome(ch2));

            } else {
                this.populationAfterCrossover.addChromosome(this.population.getChromosome(i));
                this.populationAfterCrossover.addChromosome(this.population.getChromosome(i + 1));
            }

        }
    }

    public Population populationAfterCrossover() {
        return this.populationAfterCrossover;
    }
}
