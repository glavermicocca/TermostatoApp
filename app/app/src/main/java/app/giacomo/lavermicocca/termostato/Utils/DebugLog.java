package app.giacomo.lavermicocca.termostato.Utils;

import android.util.Log;

/**
 * Created by Giacomo on 01/12/2014.
 */
public class DebugLog {

    public static void getMethodTrace(final Thread current) {
        StackTraceElement[] stack = current.getStackTrace();

        if (stack[3] != null) {
            StackTraceElement element = stack[3];
            if (!element.isNativeMethod()) {
                String className = element.getClassName();
                int lineNumber = element.getLineNumber();
                String methodName = element.getMethodName();
                Log.d("getMethodTrace", methodName + " " + className + " " + lineNumber + " ");
            }
        }
    }
}
