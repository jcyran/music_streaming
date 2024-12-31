package org.mj.audio.service;

public interface DirectoryTraverseService {
    static void loadLibrary() {
        try {
            System.load(
                    DirectoryTraverse.class.getClassLoader()
                            .getResource("native/libdir_traverse.so")
                            .getPath()
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load native library");
        }
    }

    void greet();
    void init(String java_root);
    String[][] find(String file_name);
}
