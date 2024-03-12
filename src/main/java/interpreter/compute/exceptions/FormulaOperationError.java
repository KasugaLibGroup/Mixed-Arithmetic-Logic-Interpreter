package interpreter.compute.exceptions;

import interpreter.compute.data.Operational;
import interpreter.compute.infrastructure.Formula;

public class FormulaOperationError extends RuntimeException {
    private final String statement;
    public FormulaOperationError(Formula former, Operational operational, Formula rear) {
        this.statement = "<" + former.getString() + operational.getString() + rear.getString() + ">";
    }

    @Override
    public void printStackTrace() {
        System.err.println("Cannot operate via " + statement + ", pls check your input");
        super.printStackTrace();
    }
}
