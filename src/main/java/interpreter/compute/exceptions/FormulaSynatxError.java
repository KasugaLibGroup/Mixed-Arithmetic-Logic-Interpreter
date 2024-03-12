package interpreter.compute.exceptions;

import interpreter.compute.infrastructure.Formula;

public class FormulaSynatxError extends RuntimeException {

    private final Formula formula;
    private final int position;
    public FormulaSynatxError(Formula formula, int position) {
        this.formula = formula;
        this.position = position;
    }
}
