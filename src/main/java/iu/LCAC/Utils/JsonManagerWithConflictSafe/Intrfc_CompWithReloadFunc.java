package iu.LCAC.Utils.JsonManagerWithConflictSafe;

import java.awt.*;
import java.nio.file.Path;

public interface Intrfc_CompWithReloadFunc {

    Component getFrame();

    void actionAfterReloading();

    void actionAfterOpeningJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe);

    void actionAfterSavingJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe);
}
