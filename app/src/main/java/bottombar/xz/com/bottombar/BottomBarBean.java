package bottombar.xz.com.bottombar;

/**
 * creator: zhulunjun
 * time:    2018/2/5
 * describe: 底部导航实体类
 */

public class BottomBarBean {
    /**
     * 默认icon
     */
    private int defaultIcon;
    /**
     * 默认选中的icon
     */
    private int defaultSelectIcon;
    /**
     * 网络图片地址
     */
    private String netIconUrl;
    /**
     * 网络选中图片地址
     */
    private String netSelectIconUrl;
    /**
     * 是否是中间大图标
     */
    private boolean isCenterBigIcon;
    /**
     * 文本显示
     */
    private String text;



    public int getDefaultIcon() {
        return defaultIcon;
    }

    public void setDefaultIcon(int defaultIcon) {
        this.defaultIcon = defaultIcon;
    }

    public int getDefaultSelectIcon() {
        return defaultSelectIcon;
    }

    public void setDefaultSelectIcon(int defaultSelectIcon) {
        this.defaultSelectIcon = defaultSelectIcon;
    }

    public String getNetIconUrl() {
        return netIconUrl;
    }

    public void setNetIconUrl(String netIconUrl) {
        this.netIconUrl = netIconUrl;
    }

    public String getNetSelectIconUrl() {
        return netSelectIconUrl;
    }

    public void setNetSelectIconUrl(String netSelectIconUrl) {
        this.netSelectIconUrl = netSelectIconUrl;
    }

    public boolean isCenterBigIcon() {
        return isCenterBigIcon;
    }

    public void setCenterBigIcon(boolean centerBigIcon) {
        isCenterBigIcon = centerBigIcon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
