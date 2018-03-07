package bottombar.xz.com.bottombar;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * creator: zhulunjun
 * time:    2018/1/28
 * describe: 底部导航
 */

public class BottomBarView extends RelativeLayout implements BottomBarViewInterface{

    /**
     * 数据
     */
    private List<BottomBarBean> mBottomBarData;

    /**
     * 控件
     */
    private List<BottomBarItem> mBottomBarItems = new ArrayList<>();

    /**
     * 文本默认颜色
     */
    private String textColor;
    /**
     * 文本选中颜色
     */
    private String textSelectColor;

    /**
     * 文本本地默认颜色
     */
    private int textLocalColor;
    /**
     * 文本本地选中颜色
     */
    private int textSelectLocalColor;

    /**
     * 选中监听
     */
    private OnBottomBarSelectListener onBottomBarSelectListener;

    /**
     * 当前选中的下标
     */
    private int currentSelectIndex = 0;

    private int height;

    private LinearLayout linearLayoutRoot;

    public BottomBarView(Context context) {
        super(context);
        initView(context);
    }

    public BottomBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public BottomBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BottomBarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }



    /**
     * 初始化控件
     */
    private void initView(Context context){
        this.setClipChildren(false);
        this.setClipToPadding(false);
        setGravity(ALIGN_BOTTOM);

        linearLayoutRoot = new LinearLayout(context);
        this.addView(linearLayoutRoot);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        height = (int) (dm.heightPixels * 0.07); // 高度的7%
        LayoutParams layoutParams = (LayoutParams) linearLayoutRoot.getLayoutParams();
        layoutParams.height = height;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        linearLayoutRoot.setLayoutParams(layoutParams);
        linearLayoutRoot.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutRoot.setBackgroundColor(Color.parseColor("#EEEEEE"));
    }

    /**
     * 创建单个选项
     * @return 控件
     */
    private BottomBarItem createItem(BottomBarBean bean){
        BottomBarItem item = new BottomBarItem(getContext());
        linearLayoutRoot.addView(item);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) item.getLayoutParams();
        params.weight = 1.0f;
        params.width = 0;

        params.gravity = Gravity.BOTTOM;
        // 如果是中间大图标
        // 区分大图标与小图标的大小变化
        if(bean.isCenterBigIcon()){
            params.height = (int) (height * 1.3);
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            layoutParams.height = (int) (height * 1.3);
            setLayoutParams(layoutParams);
            item.setIconSize((int) (height * 0.8), (int) (height * 0.8));
        } else {
            params.height = LayoutParams.MATCH_PARENT;
            item.setIconSize((int) (height * 0.5), (int) (height * 0.5));
        }
        item.setLayoutParams(params);
        mBottomBarItems.add(item);
        return item;
    }

    /**
     * 绑定数据
     * 必须先设置颜色，再绑定数据，颜色才能生效
     */
    public void bindData(List<BottomBarBean> mBottomBarData){
        this.mBottomBarData = mBottomBarData;
        if(mBottomBarData != null){
           for (int i = 0; i < mBottomBarData.size(); i++){
               BottomBarBean bean = mBottomBarData.get(i);
               BottomBarItem item = createItem(bean);
               if(bean!=null) {
                   bindData(bean, item);
                   setOnclickListener(item, i);
               }
           }
        }

    }

    /**
     * 绑定数据
     * @param bean 数据
     * @param item 控件
     */
    private void bindData(BottomBarBean bean, BottomBarItem item){
        // 默认不选中
        item.setCheck(false);
        // 文本显示
        if(TextUtils.isEmpty(bean.getText())){
            item.setTextShow(false);
        } else {
            if(!item.isShown()){
                item.setTextShow(true);
            }
            item.setText(bean.getText());
        }
        // 图片显示
        if(bean.getNetIcon() != null && bean.getNetSelectIcon() != null){
            // 先下载，再显示
            item.setNetIcon(bean.getNetIcon(), bean.getNetSelectIcon());
        } else {
            // 本地默认图片
            item.setLocalIcon(bean.getDefaultIcon(), bean.getDefaultSelectIcon());
        }

    }

    /**
     * 设置每个item点击事件
     * @param item 控件
     * @param index 下标
     */
    public void setOnclickListener(BottomBarItem item, final int index) {
        item.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSelect(index);
            }
        });

    }

    /**
     * 切换选中
     * @param index 下标
     */
    public void changeSelect(int index){
        if(index != currentSelectIndex){
            currentSelectIndex = index;
            // 设置选中监听
            if(onBottomBarSelectListener != null) {
                onBottomBarSelectListener.onSelect(index);
            }
            // 将点击的item选中，其他不选中
            for (int i = 0; i < mBottomBarItems.size(); i++){
                mBottomBarItems.get(i).setCheck(i == index);
            }
        }
    }

    public int getCurrentSelectIndex() {
        return currentSelectIndex;
    }

    public void setOnBottomBarSelectListener(OnBottomBarSelectListener onBottomBarSelectListener) {
        this.onBottomBarSelectListener = onBottomBarSelectListener;
    }
    /**
     * 设置数据
     */
    public void setBottomBarData(List<BottomBarBean> mBottomBarData) {
        this.mBottomBarData = mBottomBarData;
    }


    public void setTextSelectorColor(String defColor, String checkColor) {
        this.textColor = defColor;
        this.textSelectColor = checkColor;
        for(BottomBarItem item : mBottomBarItems){
            item.setNetTextColor(textColor, textSelectColor);
        }
    }

    public void setTextSelectorLocalColor(int defColor, int checkColor) {
        this.textLocalColor = defColor;
        this.textSelectLocalColor = checkColor;
        for(BottomBarItem item : mBottomBarItems) {
            item.setLocalTextColor(textLocalColor, textSelectLocalColor);
        }
    }


    @Override
    public void setRedDot(int index, boolean isShow) {
        mBottomBarItems.get(index).setShowRedDot(isShow);
    }

    @Override
    public void showRedDotNum(int index, int num) {
        mBottomBarItems.get(index).showRedDotNumber(num);
    }

    @Override
    public void hideRedDotNum(int index) {
        mBottomBarItems.get(index).hideRedDotNumber();
    }

    @Override
    public void setCheck(int index) {
        if(mBottomBarItems.size() > index && index >= 0){
            mBottomBarItems.get(index).setCheck(true);
        }
    }

}
