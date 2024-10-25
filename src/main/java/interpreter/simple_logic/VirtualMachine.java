package interpreter.simple_logic;

import interpreter.Code;
import interpreter.compute.data.Namespace;
import interpreter.simple_logic.adapter.Adapter;
import interpreter.simple_logic.adapter.ConsoleInputAdapter;
import interpreter.simple_logic.adapter.ConsoleOutputAdapter;
import interpreter.simple_logic.code_cube.LogicalCode;
import interpreter.simple_logic.code_cube.function.FunctionCube;
import interpreter.simple_logic.code_cube.function.FunctionInputCube;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

public class VirtualMachine {
    private final Namespace namespace;
    private final Set<LogicalCode> codes;
    private final Set<FunctionCube> entries;
    public VirtualMachine(Namespace parent) {
        this.namespace = new Namespace(parent);
        this.codes = new HashSet<>();
        this.entries = new HashSet<>();
        getDefaultEntry();
    }

    private void getDefaultEntry() {
        FunctionInputCube functionInputCube = new FunctionInputCube("main", this.namespace);
        FunctionCube functionCube = new FunctionCube(functionInputCube);
        this.codes.add(functionCube);
        this.entries.add(functionCube);
    }

    public Set<LogicalCode> getCodes() {
        return codes;
    }

    public Set<FunctionCube> getEntries() {
        return entries;
    }

    public FunctionCube getFunction(String name) {
        for (LogicalCode code : codes) {
            if (code instanceof FunctionCube function && function.getName().equals(name))
                return function;
        }
        return null;
    }

    public FunctionInputCube getFunctionEntry(String name) {
        FunctionCube functionCube = getFunction(name);
        if (functionCube == null) return null;
        return functionCube.getEntry();
    }

    public Adapter getAdapter(Function<Namespace, Adapter> adapterSupplier) {
        return adapterSupplier.apply(namespace);
    }

    public boolean putCubeAfter(LogicalCode prev, LogicalCode self) {
        if (prev.writeOnly()) return false;
        prev.addNext(self);
        return true;
    }

    public void removeCube(LogicalCode code) {
        Stream<LogicalCode> codes = getCodes().stream().filter(c -> c.getNext().contains(code));
        codes.forEach(c -> c.removeNext(code));
        getCodes().remove(code);
    }

    public void runAll() {
        entries.forEach(FunctionCube::update);
    }

    public void run(String functionName) {
        entries.stream().filter(e -> e.getName().equals(functionName)).forEach(FunctionCube::update);
    }


    public VirtualMachine() {
        this(Code.root());
    }

    public static void main(String[] args) {
        VirtualMachine vm = new VirtualMachine();
        FunctionInputCube fic = vm.getFunctionEntry("main");
        ConsoleInputAdapter input = new ConsoleInputAdapter(fic.getNamespace(), "var");
        fic.addNext(input);
        input.addNext(new ConsoleOutputAdapter(fic.getNamespace(), "var"));
        vm.run("main");
    }
}
