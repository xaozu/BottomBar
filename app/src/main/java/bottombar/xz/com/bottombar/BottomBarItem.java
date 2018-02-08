package bottombar.xz.com.bottombar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * creator: zhulunjun
 * time:    2018/2/6
 * describe: 单个底部控件
 */

public class BottomBarItem extends RelativeLayout implements BottomBarItemInterface {
    public static final int NONE = 0; // 不设置高或者宽

    private RelativeLayout rlRoot;
    private TextView text, tvRedNumDot;
    private ImageView iv;
    private View redDot;

    public BottomBarItem(Context context) {
        super(context);
        initView(context);
    }

    public BottomBarItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public BottomBarItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BottomBarItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_bottom_bar_item, this);
        rlRoot = view.findViewById(R.id.rl_bottom_bar);
        text = view.findViewById(R.id.tv_bottom_bar);
        iv = view.findViewById(R.id.iv_bottom_bar);
        redDot = view.findViewById(R.id.view_bottom_bar_dot);
        tvRedNumDot = view.findViewById(R.id.tv_bottom_bar_num);

    }


    @Override
    public void setCheck(boolean check) {
        iv.setEnabled(check);
        text.setEnabled(check);
    }

    @Override
    public void setText(String str) {
        text.setText(str);
    }

    @Override
    public void setTextShow(boolean isShow) {
        text.setVisibility(isShow ? VISIBLE : GONE);
    }

    @Override
    public void setNetTextColor(String defColor, String selectColor) {
        ColorStateList colorStateList = BottomBarHelper.getInstance().getColorStateList(defColor, selectColor);
        if(colorStateList != null) {
            text.setTextColor(colorStateList);
        }
    }

    @Override
    public void setLocalTextColor(int defColor, int selectColor) {
        int def = getContext().getResources().getColor(defColor);
        int checked = getContext().getResources().getColor(defColor);
        text.setTextColor(BottomBarHelper.getInstance().getColorStateList(def, checked));
    }

    @Override
    public void setNetIcon(String defUrl, String selectUrl) {
        // TODO 待实现
    }

    @Override
    public void setLocalIcon(int defIcon, int selectIcon) {
        iv.setBackgroundDrawable(BottomBarHelper.getInstance().getSelector(getContext(), defIcon, selectIcon));
    }

    @Override
    public void setIconSize(int width, int height) {
        LayoutParams layoutParams = (LayoutParams) iv.getLayoutParams();
        if(width != NONE) {
            layoutParams.width = width;
        }
        if(height != NONE) {
            layoutParams.height = height;
        }
        iv.setLayoutParams(layoutParams);
    }

    @Override
    public void setRootSize(int width, int height) {
        ViewGroup.LayoutParams layoutParams = rlRoot.getLayoutParams();
        if(width != NONE) {
            layoutParams.width = width;
        }
        if(height != NONE) {
            layoutParams.height = height;
        }
        rlRoot.setLayoutParams(layoutParams);
    }

    @Override
    public void setTextSize(int size) {
        text.setTextSize(size);
    }

    @Override
    public void setShowRedDot(boolean isShow) {
        redDot.setVisibility(isShow ? VISIBLE : GONE);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showRedDotNumber(int num) {
        if(!tvRedNumDot.isShown()){
            tvRedNumDot.setVisibility(VISIBLE);
        }
        tvRedNumDot.setText(num+"");
    }

    @Override
    public void hideRedDotNumber() {
        tvRedNumDot.setVisibility(GONE);
    }

    @Override
    public void setOnclickListener(OnClickListener listener) {
        rlRoot.setOnClickListener(listener);
    }
}
