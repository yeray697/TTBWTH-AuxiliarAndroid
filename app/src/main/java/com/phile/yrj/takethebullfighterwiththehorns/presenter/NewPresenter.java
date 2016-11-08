package com.phile.yrj.takethebullfighterwiththehorns.presenter;

import com.phile.yrj.takethebullfighterwiththehorns.interfaces.ILoginMvp;
import com.phile.yrj.takethebullfighterwiththehorns.interfaces.INewMvp;

/**
 * Created by yeray697 on 7/11/16.
 */

public class NewPresenter implements INewMvp.Presenter {
    private INewMvp.View view;

    public NewPresenter(INewMvp.View view){
        this.view = view;
    }
}
