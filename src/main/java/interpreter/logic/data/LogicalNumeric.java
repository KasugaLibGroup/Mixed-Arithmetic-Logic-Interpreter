package interpreter.logic.data;

import interpreter.Code;
import interpreter.compute.data.Namespace;
import interpreter.compute.infrastructure.Assignable;
import interpreter.compute.infrastructure.Formula;
import interpreter.logic.infrastructure.LogicalAssignable;
import interpreter.logic.infrastructure.LogicalData;

import java.util.HashMap;
import java.util.Map;

public class LogicalNumeric implements LogicalData, LogicalAssignable {
    private final Formula formula;
    private final Namespace namespace;

    public LogicalNumeric(Formula formula) {
        this.formula = formula;
        if(formula instanceof Assignable assignable)
            namespace = assignable.getNamespace();
        else
            namespace = null;
    }

    public LogicalNumeric(String formulaString, Namespace namespace) {
        this.formula = namespace.decodeFormula(formulaString);
        this.namespace = namespace;
    }

    public boolean getResult() {return (int) mathResult() != 0;}

    @Override
    public boolean isAtomic() {
        return true;
    }

    @Override
    public void assign(String codec, float value) {
        if(isAssignable()) ((Assignable) formula).assign(codec, value);
    }

    @Override
    public Namespace getNamespace() {
        return namespace;
    }

    @Override
    public String toString() {
        return formula.getString();
    }

    @Override
    public boolean isAssignable() {
        return namespace != null;
    }

    @Override
    public LogicalNumeric clone() {
        return new LogicalNumeric(formula.clone());
    }

    public float mathResult() {return formula.getResult();}

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof LogicalNumeric numeric)) return false;
        return formula.equals(numeric.formula);
    }
}
