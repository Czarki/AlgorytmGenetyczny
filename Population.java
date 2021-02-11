/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.algorytmgenetyczny;

import static java.lang.Integer.MIN_VALUE;
import java.util.ArrayList;

/**
 *
 * @author czrmj
 */
public class Population {

    private ArrayList<Chromosome> population;
    private ArrayList<Integer> fitnessValues;

    public Population() {
        this.population = new ArrayList<>();
        this.fitnessValues = new ArrayList<>();
    }

    public int populationSize() {
        return this.population.size();
    }

    public void initializePopulation(int populationSize) {
        for (int i = 0; i < populationSize; i++) { 
            this.population.add(new Chromosome());
        }
    }

    public void addBitsPopulation(int range) {
        for (Chromosome chr : this.population) {
            chr.addBitRange(range);
        }
    }

    public Chromosome getChromosome(int index) {
        return this.population.get(index);
    }

    public void addChromosome(Chromosome chr) {
        this.population.add(chr);
    }

    public void printPopulation() {
        for (Chromosome chr : this.population) {
            chr.printChromosome();
            System.out.print(" ");
        }
        System.out.println("");
    }
    
    public void clearPopulation() {
        this.population.clear();
        this.fitnessValues.clear();
    }

    public void addFitnessValues(int a, int b, int c, int d) {
        for (Chromosome chr : this.population) {
            this.fitnessValues.add(chr.fitnessValue(a, b, c, d));
        }
    }
    
    public int getFitnessValue(int index) {
        return this.fitnessValues.get(index);
    }
    
    public int getFittestIndex() {
        int maxValue = MIN_VALUE;
        int index = 0;
        for (int i = 0; i < this.fitnessValues.size() - 1; i++) {
            if (getFitnessValue(i) > maxValue) {
                maxValue = getFitnessValue(i);
                index = i;
            }
        }
        return index;
    }

    public void printPhenotype() {
        int i = 1;
        for (Chromosome chr : this.population) {
            System.out.print("Ch(" + i + ") = " + chr.calculatePhenotype() + ";");
            System.out.print(" ");
            i++;
        }
        System.out.println("");
    }

    public void printFitnessPopulation() {
        for (Integer fitnessValue : this.fitnessValues) {
            System.out.print(fitnessValue);
            System.out.print(" ");
        }
        System.out.println("");
    }

    public int sumOfFitnessValues() {
        int populationFitnessValue = 0;
        for (Integer value : this.fitnessValues) {
            populationFitnessValue += value;
        }

        return populationFitnessValue;
    }
    
    public void clearFitnessValues() {
        this.fitnessValues.clear();
    }

}
