package deml.chrisflix.Util;

import deml.chrisflix.*;

import java.util.logging.*;

public class Logger {

    public static void INFO(final String message) {
        java.util.logging.Logger.getLogger(ChrisflixApplication.class.getName()).log(Level.INFO, message);
    }

    public static void WARN(final String message) {
        java.util.logging.Logger.getLogger(ChrisflixApplication.class.getName()).log(Level.WARNING, message);
    }

    public static void ERROR(final String message) {
        java.util.logging.Logger.getLogger(ChrisflixApplication.class.getName()).log(Level.SEVERE, message);
    }
}
