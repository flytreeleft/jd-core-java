package jd.ide.intellij;

import java.io.File;
import java.net.URL;

public class JavaDecompiler {
    static {
        String os = System.getProperty("os.name").toLowerCase();
        String arch = System.getProperty("os.arch");
        String libName = "";
        if (os.indexOf("win") >= 0) {
            os = "windows";
            libName = "jd-intellij.dll";
        } else if (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0 || os.indexOf("aix") > 0) {
            os = "unix";
            libName = "libjd-intellij.so";
        } else if (os.indexOf("mac") >= 0) {
            os = "macosx";
            libName = "libjd-intellij.jnilib";
        }

        try {
            URL path = JavaDecompiler.class.getProtectionDomain().getCodeSource().getLocation();
            String parent = new File(path.toURI()).getParent();

            System.load(parent + "/nativelib/" + os + "/" + arch + "/" + libName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public native String decompile(String basePath, String internalClassName);
}
