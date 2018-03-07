package bottombar.xz.com.bottombar.download;

import android.graphics.drawable.Drawable;

import java.io.File;

/**
 * creator: zhulunjun
 * time:    2018/3/6
 * describe: 下载图片的状态回调
 */

public interface ImageDownLoadCallBack {
    void onDownLoadSuccess(Drawable icon, Drawable selectIcon);
    void onDownLoadFailed();
}
