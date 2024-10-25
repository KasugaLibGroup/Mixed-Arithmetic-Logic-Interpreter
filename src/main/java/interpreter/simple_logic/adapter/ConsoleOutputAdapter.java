package interpreter.simple_logic.adapter;

import interpreter.compute.data.Namespace;
import interpreter.simple_logic.adapter.io.OutAdapter;
import interpreter.simple_logic.code_cube.LogicalCode;

import java.util.Iterator;
import java.util.Map;

public class ConsoleOutputAdapter extends OutAdapter {
    public ConsoleOutputAdapter(Namespace namespace, String... varName) {
        super(namespace, false, varName);
    }

    @Override
    public boolean onUpdate(Adapter namespace) {
        Iterator<Map.Entry<String, Float>> iterator = cacheIterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Float> entry = iterator.next();
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        return true;
    }

    @Override
    public LogicalCode copy(Namespace namespace) {
        return new ConsoleOutputAdapter(namespace, this.varName);
    }
}
