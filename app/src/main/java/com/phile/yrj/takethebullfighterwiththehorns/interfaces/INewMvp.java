package com.phile.yrj.takethebullfighterwiththehorns.interfaces;

/**
 * @author Yeray Ruiz Juárez
 * @version 1.0
 * Created on 04/11/16
 */
public interface INewMvp {
    interface View{
        void setMessageError(String messageError);
    }
    interface Presenter{
        boolean publishComment(String comment, int idnew);
    }
}