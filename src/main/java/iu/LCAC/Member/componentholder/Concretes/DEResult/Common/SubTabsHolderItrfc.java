package iu.LCAC.Member.componentholder.Concretes.DEResult.Common;

import java.util.ArrayList;

public interface SubTabsHolderItrfc {
    String getSectionName();

    ArrayList<ManagerOfSubTabBasePane> getArrayList_of_ManagerOfSubTabBasePane();

    //panel の baseTabPane の 0 番目 の 中で一番上に配置されている OneDEResult_Pane_Abs クラスオブジェクトを取得する
    public One_DEResult_Pane_Abs getTheFirstJsonPanel();
}
