package com.zx.whm.common.util;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by lenovo on 2017/1/3.
 */
public class CustomFileFilter {

    public static class ZKSJFileFilter implements FileFilter{ //制卡数据
        public boolean accept(File file) {
            if (file.getName().startsWith("ZKSJ_")) {
                return true;
            }
            return false;
        }
    }

    public  static class ZKCPFileFilter implements FileFilter{ //成品卡制卡数据
        public boolean accept(File file) {
            if (file.getName().startsWith("ZKCP_")) {
                return true;
            }
            return false;
        }
    }
    public  static class ZKYSFileFilter implements FileFilter{ //衍生卡制卡数据
        public boolean accept(File file) {
            if (file.getName().startsWith("ZKYS_")) {
                return true;
            }
            return false;
        }
    }
    public  static class ZKYZFileFilter implements FileFilter{ //预制卡制卡数据
        public boolean accept(File file) {
            if (file.getName().startsWith("ZKYZ_")) {
                return true;
            }
            return false;
        }
    }

}
