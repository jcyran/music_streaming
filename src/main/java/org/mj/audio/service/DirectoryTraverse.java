package org.mj.audio.service;

import org.springframework.stereotype.Service;

@Service
public class DirectoryTraverse implements DirectoryTraverseService {
    public native void greet();
    public native void init(String java_root);
    public native String[][] find(String file_name);
}
