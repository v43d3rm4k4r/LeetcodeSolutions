package leetcode.solutions;

public abstract class LeetcodeSolution implements Runnable {
    private int    _solutionID;
    private String _solutionName;

    public int    getSolutionID()   { return _solutionID; }
    public String getSolutionName() { return _solutionName; }

    @Override
    public String toString() {
        return "problem \"" + _solutionName + "\" (" + _solutionID + ")\n";
    }
}

