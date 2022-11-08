package com.techelevator.model;

public class Restrictions {
    boolean dairy;
    boolean egg;
    boolean tree_nut;
    boolean peanut;
    boolean shellfish;
    boolean wheat;
    boolean soy;
    boolean fish;
    boolean vegan;
    boolean kosher;
    boolean vegetarian;

    public boolean isDairy() {
        return dairy;
    }

    public void setDairy(boolean dairy) {
        this.dairy = dairy;
    }

    public boolean isEgg() {
        return egg;
    }

    public void setEgg(boolean egg) {
        this.egg = egg;
    }

    public boolean isTree_nut() {
        return tree_nut;
    }

    public void setTree_nut(boolean tree_nut) {
        this.tree_nut = tree_nut;
    }

    public boolean isPeanut() {
        return peanut;
    }

    public void setPeanut(boolean peanut) {
        this.peanut = peanut;
    }

    public boolean isShellfish() {
        return shellfish;
    }

    public void setShellfish(boolean shellfish) {
        this.shellfish = shellfish;
    }

    public boolean isWheat() {
        return wheat;
    }

    public void setWheat(boolean wheat) {
        this.wheat = wheat;
    }

    public boolean isSoy() {
        return soy;
    }

    public void setSoy(boolean soy) {
        this.soy = soy;
    }

    public boolean isFish() {
        return fish;
    }

    public void setFish(boolean fish) {
        this.fish = fish;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public boolean isKosher() {
        return kosher;
    }

    public void setKosher(boolean kosher) {
        this.kosher = kosher;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }
}
