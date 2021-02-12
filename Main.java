/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.algorytmgenetyczny;

import static java.lang.Integer.MIN_VALUE;
import java.util.Scanner;
//import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author czrmj
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Podaj wielkość populacji: ");
        int populationSize = Integer.valueOf(scanner.nextLine());

        Population initialPopulation = new Population();
        Population roulettePopulation = new Population();
        Population geneticOperationsPopulation = new Population();
        initialPopulation.initializePopulation(populationSize);

        System.out.print("Podaj ilość bitów w chromosomie: ");
        int bitsSize = Integer.valueOf(scanner.nextLine());
        initialPopulation.addBitsPopulation(bitsSize);

        System.out.print("Podaj współczynnik krzyżowania: ");
        double crossoverProbability = Double.valueOf(scanner.nextLine());
        System.out.print("Podaj współczynnik mutacji: ");
        double mutationProbability = Double.valueOf(scanner.nextLine());
        System.out.print("Podaj współczynnik a: ");
        int a = Integer.valueOf(scanner.nextLine());
        System.out.print("Podaj współczynnik b: ");
        int b = Integer.valueOf(scanner.nextLine());
        System.out.print("Podaj współczynnik c: ");
        int c = Integer.valueOf(scanner.nextLine());
        System.out.print("Podaj współczynnik d: ");
        int d = Integer.valueOf(scanner.nextLine());

        int terminationCondition = 0;
        int loopCounter = 1;
        int maxima = MIN_VALUE;
        int maxPhenotype = MIN_VALUE;
        int iterationsToMaxima = 1;

        System.out.println("");
        System.out.println("Populacja początkowa:");
        initialPopulation.printPopulation();
        initialPopulation.printPhenotype();
        initialPopulation.addFitnessValues(a, b, c, d);
        System.out.println("Suma funkcji przystosowania: "
                + initialPopulation.sumOfFitnessValues());
        initialPopulation.clearFitnessValues();

        while (terminationCondition <= 10000) {

            initialPopulation.addFitnessValues(a, b, c, d);

            if (maxima < initialPopulation.getFitnessValue(initialPopulation.getFittestIndex())) {
                maxima = initialPopulation.getFitnessValue(initialPopulation.getFittestIndex());
                maxPhenotype = initialPopulation.getChromosome(initialPopulation.getFittestIndex()).calculatePhenotype();
                iterationsToMaxima = loopCounter;
                terminationCondition = 0;
            } else {
                terminationCondition++;
            }

            roulettePopulation.clearPopulation();
            RouletteWheelSelection selection = new RouletteWheelSelection(initialPopulation);
            while (roulettePopulation.populationSize() != initialPopulation.populationSize()) {
                roulettePopulation.addChromosome(initialPopulation.getChromosome(selection.rouletteWheelSelection()));
            }

            Crossover crossover = new Crossover(roulettePopulation);
            crossover.crossover(crossoverProbability);
            geneticOperationsPopulation = crossover.populationAfterCrossover();

            Mutation mutation = new Mutation(geneticOperationsPopulation);
            mutation.mutation(mutationProbability);
            initialPopulation = geneticOperationsPopulation;

            loopCounter++;

        }
        System.out.println("");
        System.out.println("Populacja końcowa:");
        initialPopulation.printPopulation();
        initialPopulation.printPhenotype();
        initialPopulation.addFitnessValues(a, b, c, d);
        System.out.println("Suma funkcji przystosowania: "
                + initialPopulation.sumOfFitnessValues());
        System.out.println("");
        System.out.println("f(" + maxPhenotype + ") = " + maxima);
        if (iterationsToMaxima == 1) {
            System.out.println("Maksimum funkcji znalezione po "
                    + iterationsToMaxima + " iteracji.");
        } else {
            System.out.println("Maksimum funkcji znalezione po "
                    + iterationsToMaxima + " iteracjach.");
        }
    }
}
