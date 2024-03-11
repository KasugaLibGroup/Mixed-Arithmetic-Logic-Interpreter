package interpreter.compute.infrastructure;

import interpreter.UniversalAssignable;
import interpreter.compute.data.Namespace;

import java.util.Map;
import java.util.Set;

public interface Assignable extends UniversalAssignable {

    Namespace getNamespace();
    Set<String> variableCodecs();
    void assign(String codec, float value);
    boolean containsVar(String codec);
    float getValue(String codec);
    boolean hasVar();
}
