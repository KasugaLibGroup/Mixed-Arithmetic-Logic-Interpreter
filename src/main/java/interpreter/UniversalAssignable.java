package interpreter;

import interpreter.compute.data.Namespace;
import interpreter.compute.infrastructure.Assignable;

import java.util.Map;

public interface UniversalAssignable {
    void assign(String codec, float value);
    Namespace getNamespace();
}
