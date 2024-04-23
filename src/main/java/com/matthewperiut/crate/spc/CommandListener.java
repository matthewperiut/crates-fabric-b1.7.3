package com.matthewperiut.crate.spc;

import com.matthewperiut.spc.api.CommandRegistry;

public class CommandListener {
    public static void addCrateCommand() {
        CommandRegistry.add(new CrateCommand());
    }
}
