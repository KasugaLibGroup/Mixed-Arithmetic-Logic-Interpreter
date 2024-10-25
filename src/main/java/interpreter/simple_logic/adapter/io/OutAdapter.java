package interpreter.simple_logic.adapter.io;

import interpreter.compute.data.Namespace;
import interpreter.simple_logic.adapter.Adapter;
import interpreter.simple_logic.adapter.AdapterType;

public abstract class OutAdapter extends Adapter {
    public OutAdapter(Namespace namespace, boolean shouldPause, String... varName) {
        super(AdapterType.OUT, namespace, shouldPause, varName);
    }

    @Override
    protected void set(String key, Float value) {
        super.set(key, value);
    }

    @Override
    public void update() {
        getValues();
        super.update();
    }
}
