package com.bmutinda.chucknorris.utils;

import android.util.Log;

public final class AppLogger {

	// make false on production
	static final boolean debug = true;
	static final String LOG_INFO_TAG = "LOG_INFO_TAG";
	static final String LOG_ERROR_TAG = "LOG_ERROR_TAG";
	static final String LOG_DEBUG_TAG = "LOG_DEBUG_TAG";

    public static void log( String message ){
        AppLogger.logError( message);
    }
	public static void logInfo(String message) {
		if (debug) {
			Log.i(AppLogger.LOG_INFO_TAG, message);
		}
	}

	public static void logError(String error) {
		if (debug) {
			Log.e(AppLogger.LOG_ERROR_TAG, error);
		}
	}

	public static void logDebug(String message) {
		if (debug) {
			Log.e(AppLogger.LOG_DEBUG_TAG, message);
		}
	}

}
