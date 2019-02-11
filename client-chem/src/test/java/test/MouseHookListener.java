package test;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser.HHOOK;
import com.sun.jna.platform.win32.WinUser.HOOKPROC;

public abstract class MouseHookListener implements HOOKPROC {
        public User32 lib = null;
        public HHOOK hhk;
    public LRESULT callback(int nCode, WPARAM wParam, LPARAM lParam){
        return null;
    }
}