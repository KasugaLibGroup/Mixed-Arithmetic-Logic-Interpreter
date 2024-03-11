package interpreter.logic.data.functions;

import interpreter.logic.infrastructure.LogicalData;

public abstract class LogicFunction implements LogicalData {

    @Override
    public abstract LogicFunction clone();
}
