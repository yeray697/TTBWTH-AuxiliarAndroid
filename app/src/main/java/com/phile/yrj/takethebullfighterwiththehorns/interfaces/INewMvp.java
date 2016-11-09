package com.phile.yrj.takethebullfighterwiththehorns.interfaces;

import com.phile.yrj.takethebullfighterwiththehorns.model.Comment;

/**
 * Created by yeray697 on 7/11/16.
 */

public interface INewMvp {
    interface View{
        void setMessageError(String messageError);
    }
    interface Presenter{
        boolean publishComment(String comment, int idnew);
    }
}