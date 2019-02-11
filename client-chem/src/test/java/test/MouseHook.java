package test;

import com.sun.jna.Platform;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinUser.HHOOK;
import com.sun.jna.platform.win32.WinUser.MSG;

public class MouseHook {
    public static final int WM_MOUSEMOVE = 512;
    public static final int WM_LBUTTONDOWN = 513;
    public static final int WM_LBUTTONUP = 514;
    public static final int WM_RBUTTONDOWN = 516;
    public static final int WM_RBUTTONUP = 517;
    public static final int WM_MBUTTONDOWN = 519;
    public static final int WM_MBUTTONUP = 520;
    public User32 lib;
    private static HHOOK hhk;
    private MouseHookListener mouseHook;
    private HMODULE hMod;
    private boolean isWindows = false;

    public MouseHook() {
        isWindows = Platform.isWindows();
        if (isWindows) {
            lib = User32.INSTANCE;
            hMod = Kernel32.INSTANCE.GetModuleHandle(null);
        }

    }

    public void addMouseHookListener(MouseHookListener mouseHook) {
        this.mouseHook = mouseHook;
        this.mouseHook.lib = lib;
    }

    public void startWindowsHookEx() {
        if (isWindows) {
            lib.SetWindowsHookEx(WinUser.WH_MOUSE_LL, mouseHook, hMod, 0);
            int result;
            MSG msg = new MSG();
            while ((result = lib.GetMessage(msg, null, 0, 0)) != 0) {
                if (result == -1) {
                    System.err.println("error in get message");
                    break;
                } else {
                    System.err.println("got message");
                    lib.TranslateMessage(msg);
                    lib.DispatchMessage(msg);
                }
            }
        }

    }

    public void stopWindowsHookEx() {
        if (isWindows) {
            lib.UnhookWindowsHookEx(hhk);
        }

    }

    public static void main(String[] args) {
        try {
            MouseHook mouseHook = new MouseHook();
            mouseHook.addMouseHookListener(new MouseHookListener() {
                public LRESULT callback(int nCode, WPARAM wParam,
                		LPARAM lParam) {
                    if (nCode >= 0) {
                        switch (wParam.intValue()) {
                        case MouseHook.WM_MOUSEMOVE:

                            break;
                        case MouseHook.WM_LBUTTONDOWN:
                            System.err.println("mouse left button down, x="
                                    + lParam + " y=" + lParam);
                            break;
                        case MouseHook.WM_LBUTTONUP:
                            System.err.println("mouse left button up, x="
                                    + lParam + " y=" + lParam);
                            break;

                        case MouseHook.WM_MBUTTONDOWN:
                            break;
                        case MouseHook.WM_MBUTTONUP:
                            break;
                        }
                    }
                    return lib.CallNextHookEx(hhk, nCode, wParam,lParam);
                }
            });
            mouseHook.startWindowsHookEx();

            Thread.sleep(20000);
            mouseHook.stopWindowsHookEx();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}