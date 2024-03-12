package interpreter.compute.data;

import interpreter.compute.infrastructure.Assignable;
import interpreter.compute.infrastructure.Formula;
import interpreter.compute.exceptions.FormulaSynatxError;

import java.util.HashMap;
import java.util.List;

public class Numeric implements Formula {
    private float number = 0;
    private boolean flip = false;

    public Numeric(float number) {
        this.number = number;
    }

    public Numeric(String string) {
        this.number = Float.parseFloat(string);
    }

    @Override
    public String getString() {
        return (flip ? "-" : "") + number;
    }

    @Override
    public String getIdentifier() {
        return "numeric";
    }

    @Override
    public float getResult() {
        return flip ? - number : number;
    }

    @Override
    public List<Formula> getElements() {
        return List.of(this);
    }

    @Override
    public boolean isAtomic() {
        return true;
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    public void fromString(String string) {
        try {number = Float.parseFloat(string);}
        catch (Exception e) {throw new FormulaSynatxError(this, 0);}
    }

    public static boolean isNumber(String string) {
        return string.replaceAll(" ", "")
                .replaceAll("^\\d+(\\.\\d+)?$", "").equals("");
    }

    public Numeric clone() {
        return new Numeric(String.valueOf(this.number));
    }

    @Override
    public void flipOutput(boolean flip) {
        this.flip = flip;
    }

    @Override
    public boolean isOutputFlipped() {
        return flip;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Numeric numeric)) return false;
        return numeric.number == number;
    }
}
