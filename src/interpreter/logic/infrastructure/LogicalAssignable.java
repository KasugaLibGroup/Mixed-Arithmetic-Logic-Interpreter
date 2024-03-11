package interpreter.logic.infrastructure;

import interpreter.UniversalAssignable;
import interpreter.compute.data.Namespace;
import interpreter.compute.infrastructure.Assignable;

import java.util.Map;

public interface LogicalAssignable extends UniversalAssignable {
    boolean isAssignable();
    void assign(String codec, float value);
    Namespace getNamespace();
}
