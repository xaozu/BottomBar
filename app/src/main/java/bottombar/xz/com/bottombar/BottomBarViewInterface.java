package bottombar.xz.com.bottombar;

import java.util.List;

/**
 * creator: zhulunjun
 * time:    2018/2/6
 * describe: 底部控件view接口规范
 */

public interface BottomBarViewInterface {

    /**
     * 绑定数据
     * @param mBottomBarData 数据
     */
    void bindData(List<BottomBarBean> mBottomBarData);

    /**
     * 是否显示不带数字的小红点
     * @param index 需要控制的item下标
     * @param isShow 是否显示
     */
    void setRedDot(int index, boolean isShow);

    /**
     * 显示带数字的小红点
     * @param index 需要控制的item下标
     * @param num 显示的数字
     */
    void showRedDotNum(int index, int num);

    /**
     * 隐藏带数字的小红点
     * @param index 需要控制的item下标
     */
    void hideRedDotNum(int index);
}
