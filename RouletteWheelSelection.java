/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.algorytmgenetyczny;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author czrmj
 */
public class RouletteWheelSelection {

    private Population population;

    public RouletteWheelSelection(Population population) {
        this.population = population;
    }

    public int randomNumber() {
        int randomNumber = ThreadLocalRandom.current().nextInt(0,
                population.sumOfFitnessValues() + 1);
        return randomNumber;
    }

    public int rouletteWheelSelection() {
        int partialSum = 0;
        int randomNumber = randomNumber();

        for (int i = 0; i <= this.population.populationSize() - 1; i++) {
            partialSum += this.population.getFitnessValue(i);

            if (partialSum >= randomNumber) {
                return i;
            }
        }
        return -1;
    }

}
