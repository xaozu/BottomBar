package bottombar.xz.com.bottombar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

/**
 * creator: zhulunjun
 * time:    2018/2/6
 * describe: 底部导航数据处理
 */

public class BottomBarHelper {

    private static BottomBarHelper instance = null;

    public synchronized static BottomBarHelper getInstance() {
        if (instance == null) {
            instance = new BottomBarHelper();
        }
        return instance;
    }

    /**
     * 根据选中颜色和默认颜色，组装selector
     *
     * @param defColor     默认颜色
     * @param checkedColor 选中颜色
     * @return 颜色集合
     */
    public ColorStateList getColorStateList(String defColor, String checkedColor) {
        try {
            int normal = Color.parseColor(defColor);
            int checked = Color.parseColor(checkedColor);
            return getColorStateList(normal, checked);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 根据选中颜色和默认颜色，组装selector
     *
     * @param normal  默认颜色
     * @param checked 选中颜色
     * @return 颜色集合
     */
    public ColorStateList getColorStateList(int normal, int checked) {
        int[] colors = new int[]{normal, checked, normal};
        int[][] states = new int[3][];
        states[0] = new int[]{-android.R.attr.state_enabled};
        states[1] = new int[]{android.R.attr.state_enabled};
        states[2] = new int[]{};
        return new ColorStateList(states, colors);
    }

    /**
     * 控件选择器
     *
     * @param context 当前上下文
     * @param idNormal 默认图片id
     * @param checked 选中时图片id
     * @return 选择器
     */
    public StateListDrawable getSelector(Context context, int idNormal, int checked){
        StateListDrawable bg=new StateListDrawable();
        Drawable normal = idNormal == -1 ? null : context.getResources().getDrawable(idNormal);
        Drawable check = checked == -1 ? null : context.getResources().getDrawable(checked);
        bg.addState(new int[] { android.R.attr.state_enabled }, check);
        bg.addState(new int[] { -android.R.attr.state_enabled }, normal);
        bg.addState(new int[] {}, normal);
        return bg;
    }

}
