package iu.LCAC.Utils.JsonManagerWithConflictSafe;

import java.awt.*;
import java.nio.file.Path;

public interface Intrfc_CompWithReloadFunc {

    boolean actionAfterReloading();

    void actionAfterOpeningJson(Path p, String content);

    Component getFrame();

    void actionAfterSavingJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe);
}
