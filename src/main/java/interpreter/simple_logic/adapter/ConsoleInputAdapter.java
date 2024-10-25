package interpreter.simple_logic.adapter;

import interpreter.compute.data.Namespace;
import interpreter.simple_logic.adapter.io.InAdaptor;
import interpreter.simple_logic.code_cube.LogicalCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ConsoleInputAdapter extends InAdaptor {
    private final Thread inputThread;
    private boolean listening;
    String cache;
    public ConsoleInputAdapter(Namespace namespace, String varName) {
        super(namespace, true, varName);
        inputThread = new Thread(() -> {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                cache = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        inputThread.setName("input_thread");
        cache = null;
        listening = false;
    }

    private void startThread() {
        inputThread.start();
        listening = true;
    }

    @Override
    public boolean onUpdate(Adapter namespace) {
        if (!listening) startThread();
        if (cache == null) return false;
        listening = false;
        try {
            float p = Float.parseFloat(cache);
            set(varName[0], p);
            cache = null;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            cache = null;
            startThread();
            return false;
        }
    }

    @Override
    public LogicalCode copy(Namespace namespace) {
        return new ConsoleInputAdapter(this.namespace, this.varName[0]);
    }
}
