package com.hyperskill.accountservice.exceptions;

import java.util.Date;

public class ErrorMessage  {
    private static String errorMsg="";

    public static String getErrorMsg() {
        return errorMsg;
    }

    public static void setErrorMsg(String errorMsg) {
        ErrorMessage.errorMsg = errorMsg;
    }
}
