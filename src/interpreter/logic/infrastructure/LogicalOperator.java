package interpreter.logic.infrastructure;

public interface LogicalOperator {
    boolean operate(LogicalData former, LogicalData rear);
    boolean is(Object obj);
}
