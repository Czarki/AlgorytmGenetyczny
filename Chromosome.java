/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.algorytmgenetyczny;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author czrmj
 */
public class Chromosome {

    private final ArrayList<Integer> bits;

    public Chromosome() {
        this.bits = new ArrayList<>();
        
    }
    
    public Chromosome(ArrayList<Integer> bits) {
        this.bits = bits;
    }

    public void addBit() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 1 + 1);
        this.bits.add(randomNum);
    }
    
    public int getBit(int index) {
        return this.bits.get(index);
    }
    
    public void replaceBit(int index, int value) {
        this.bits.set(index, value);
    }

    
    public List<Integer> subList(int fromIndex, int toIndex) {
        return this.bits.subList(fromIndex, toIndex);
    }

    public void addBitRange(int range) {
        for (int i = 0; i < range; i++) {
            addBit();
        }
    }

    public void printChromosome() {
        for (Integer bit : this.bits) {
            System.out.print(bit) ;
        }
    }
    
    public int getLength() {
        int length = 0;
        for (Integer bit : this.bits) {
            length++;
        }
        return length;
    }

    public int calculatePhenotype() {
        int lastIndex = this.bits.size() - 1;
        int phenotype = 0;

        for (int i = 0; i <= lastIndex; i++) {
            if (this.bits.get(i) == 1) {
                phenotype += Math.pow(2, lastIndex - i);
            }
        }

        return phenotype;
    }
    
    public int fitnessValue(int a, int b, int c, int d) {
        int fitnessValue = (int) (a * Math.pow(this.calculatePhenotype(), 3)) +
                (int) (b * Math.pow(this.calculatePhenotype(), 2)) +
                (int) (c * this.calculatePhenotype()) + d;
        return fitnessValue;
    }
    
    

}
