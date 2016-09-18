package main;

/**
 * Created by Andréas Appelqvist on 2016-09-18.
 * UVa Online Judge 839
 */
public class BT {
    int weightLeft, weightRight, distanceRight, distanceLeft;
    BT rightSubTree, leftSubTree;

    public BT() {
    }

    public void setWeightLeft(int weightLeft) {
        this.weightLeft = weightLeft;
    }

    public void setWeightRight(int weightRight) {
        this.weightRight = weightRight;
    }

    public void setDistanceRight(int distanceRight) {
        this.distanceRight = distanceRight;
    }

    public void setDistanceLeft(int distanceLeft) {
        this.distanceLeft = distanceLeft;
    }

    public void setRightSubTree(BT rightSubTree) {
        this.rightSubTree = rightSubTree;
    }

    public void setLeftSubTree(BT leftSubTree) {
        this.leftSubTree = leftSubTree;
    }

    private int getWeightLeft() {
        if (weightLeft == 0) {
            leftSubTree.getTotalWeight();
        } else {
            return weightLeft;
        }
        return -1;
    }

    private int getWeightRight() {
        if (weightRight == 0) {
            rightSubTree.getTotalWeight();
        } else {
            return weightRight;
        }
        return -1;
    }

    public int getTotalWeight() {
        return getWeightRight() + getWeightLeft();
    }

    public boolean equilibrium() {
        if (weightRight == 0 && weightLeft == 0) { //It's subtree(s) to both right aswell as the left
            if (!leftSubTree.equilibrium() || !rightSubTree.equilibrium()) {
                return false;
            } else {
                return ((getWeightLeft() * distanceLeft) == (getWeightRight() * distanceLeft));
            }
        } else if (weightRight == 0) {// Subtree to the right
            if (rightSubTree.equilibrium()) {
                return (weightLeft * distanceLeft) == (getWeightRight() * distanceRight);
            } else {
                return false;
            }
        } else if (weightLeft == 0) { //Subtree to the left
            if (leftSubTree.equilibrium()) {
                return (getWeightLeft() * distanceLeft) == (weightRight * distanceRight);
            } else {
                return false;
            }
        } else { // No subtrees at all just calculate the formula
            if((weightLeft * distanceLeft) == (weightRight * distanceRight)){
                return true;
            }
            return false;
        }
    }
}
