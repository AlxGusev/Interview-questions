package hard.classes;

public class HappensBeforeForFinalExample {
    private final int finalVar;
    private int nonFinalVar = -1;

    public HappensBeforeForFinalExample(int finalVar, int nonFinalVar) {
        this.finalVar = finalVar;
        this.nonFinalVar = nonFinalVar;
    }

    public int getFinalVar() {
        return finalVar;
    }

    public int getNonFinalVar() {
        return nonFinalVar;
    }
}