/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.Serializable;

/**
 *
 * @author Leon
 */
public class Operation implements Serializable{
    private static final long serialVersionUID=3;
    
    public static final int OPERATION_LOGIN=1;
    public static final int OPERATION_GET_ALL_KORISNICI=2;
    public static final int OPERATION_SAVE_KORISNIK=3;
    public static final int OPERATION_SEARCH_KORISNIK=4;
    public static final int OPERATION_DELETE_KORISNIK=5;
    public static final int OPERATION_UPDATE_KORISNIK=6;
    public static final int OPERATION_GET_KORISNIK=7;
    public static final int OPERATION_SAVE_KOMPOZICIJA=8;
    public static final int OPERATION_SEARCH_KOMPOZICIJA=9;
    public static final int OPERATION_DELETE_KOMPOZICIJA=10;
    public static final int OPERATION_UPDATE_KOMPOZICIJA=11;
    public static final int OPERATION_GET_AUDIO_ZVUCNIZAPIS=12;
    public static final int OPERATION_BUY_KOMPOZICIJA=13;
}
