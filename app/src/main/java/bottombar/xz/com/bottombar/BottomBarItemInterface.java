package bottombar.xz.com.bottombar;

import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * creator: zhulunjun
 * time:    2018/2/6
 * describe: 底部控件单个item的接口规范
 */

public interface BottomBarItemInterface {

    /**
     * 是否选中
     * @param check 选中状态
     */
    void setCheck(boolean check);

    /**
     * 设置文本显示
     * @param str 文本
     */
    void setText(String str);

    /**
     * 设置文本是否显示
     * @param isShow 是否显示
     */
    void setTextShow(boolean isShow);

    /**
     * 设置网络文本颜色
     * @param defColor 默认颜色
     * @param selectColor 选中颜色
     */
    void setNetTextColor(String defColor, String selectColor);

    /**
     * 设置本地默认文本颜色
     * @param defColor 默认颜色
     * @param selectColor 选中颜色
     */
    void setLocalTextColor(int defColor, int selectColor);

    /**
     * 设置网络icon图片
     * @param defIcon 默认图片
     * @param selectIcon 选中图片
     */
    void setNetIcon(Drawable defIcon, Drawable selectIcon);

    /**
     * 设置本地icon图片
     * @param defIcon 默认图片
     * @param selectIcon 选中图片
     */
    void setLocalIcon(int defIcon, int selectIcon);

    /**
     * 设置icon大小
     * @param width 宽度
     * @param height 高度
     */
    void setIconSize(int width, int height);

    /**
     * 设置根目录大小
     * @param width 宽度
     * @param height 高度
     */
    void setRootSize(int width, int height);

    /**
     * 设置文本大小
     * @param size 字体大小
     */
    void setTextSize(int size);

    /**
     * 是否显示红色小圆点
     * @param isShow 是否显示
     */
    void setShowRedDot(boolean isShow);

    /**
     * 显示带数字的小红点
     * @param num 数字
     */
    void showRedDotNumber(int num);

    /**
     * 隐藏带数字的小红点
     */
    void hideRedDotNumber();

}
