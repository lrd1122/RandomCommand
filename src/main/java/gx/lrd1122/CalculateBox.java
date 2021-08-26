package gx.lrd1122;

public class CalculateBox {
    private int line;
    private int minpercentage;
    private int maxpercentage;
    public CalculateBox(){}
    public CalculateBox(int line ,int minpercentage, int maxpercentage)
    {
        this.line = line;
        this.minpercentage = minpercentage;
        this.maxpercentage = maxpercentage;
    }

    public int getLine() {
        return line;
    }


    public void setLine(int line) {
        this.line = line;
    }


    public int getMaxpercentage() {
        return maxpercentage;
    }

    public int getMinpercentage() {
        return minpercentage;
    }

    public void setMaxpercentage(int maxpercentage) {
        this.maxpercentage = maxpercentage;
    }

    public void setMinpercentage(int minpercentage) {
        this.minpercentage = minpercentage;
    }
}
