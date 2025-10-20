package iu.LCAC.Utils.JsonManagerWithConflictSafe;

import java.awt.*;
import java.nio.file.Path;

public interface JsonManagerCallback {

    Component getFrame();

    void actionAfterOpeningJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe);

    void actionAfterSavingJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe);

    void actionAfterReloading(JsonManagerWithConflictSafe jsonManagerWithConflictSafe);
}
