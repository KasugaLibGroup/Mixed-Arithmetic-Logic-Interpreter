package interpreter.simple_logic.adapter.io;

import interpreter.compute.data.Namespace;
import interpreter.simple_logic.adapter.Adapter;
import interpreter.simple_logic.adapter.AdapterType;
import interpreter.simple_logic.adapter.Input;

public abstract class InAdaptor extends Adapter implements Input {
    private boolean dirty;
    public InAdaptor(Namespace namespace, boolean shouldPause, String... varName) {
        super(AdapterType.IN, namespace, shouldPause, varName);
        dirty = false;
    }

    public boolean isDirty() {
        return dirty;
    }

    public void markDirty() {
        this.dirty = true;
    }

    @Override
    protected Float get(String key) {
        return super.get(key);
    }

    @Override
    public void update() {
        super.update();
        if (!dirty) return;
        setValues();
        dirty = false;
    }
}
