package com.miapuffia.removeclearallnotificationsbutton;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class RemoveClearAllNotificationsButtonModule implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        XposedHelpers.findAndHookMethod("com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout", loadPackageParam.classLoader, "updateFooter", mRemoveClearAll);
    }

    private final XC_MethodHook mRemoveClearAll = new XC_MethodHook() {
        @Override
        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
            XposedHelpers.setBooleanField(param.thisObject, "mClearAllEnabled", false);
        }
    };
}
