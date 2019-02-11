package test;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.POINT;

public class MouseHookStruct extends Structure{
    public static class ByReference extends MouseHookStruct implements Structure.ByReference {};
    public ULONG_PTR dwExtraInfo;
    public HWND hwnd;
    public POINT pt;
    public int wHitTestCode;
	@Override
	protected List<String> getFieldOrder() {
		// TODO Auto-generated method stub
		return Arrays.asList("dwExtraInfo","hwnd","pt","wHitTestCode");
	}
}