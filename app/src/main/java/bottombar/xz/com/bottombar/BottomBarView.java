package bottombar.xz.com.bottombar;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.AttributeSet;
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

public class BottomBarView extends LinearLayout implements BottomBarViewInterface{

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
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_bottom_bar2, this);

        mBottomBarItems.add((BottomBarItem) view.findViewById(R.id.bbi_1));
        mBottomBarItems.add((BottomBarItem) view.findViewById(R.id.bbi_2));
        mBottomBarItems.add((BottomBarItem) view.findViewById(R.id.bbi_3));
        mBottomBarItems.add((BottomBarItem) view.findViewById(R.id.bbi_4));
        mBottomBarItems.add((BottomBarItem) view.findViewById(R.id.bbi_5));

    }

    /**
     * 绑定数据
     * 必须先设置颜色，再绑定数据，颜色才能生效
     */
    public void bindData(List<BottomBarBean> mBottomBarData){
        this.mBottomBarData = mBottomBarData;
        if(mBottomBarData != null && mBottomBarData.size() == mBottomBarItems.size()){
           for (int i = 0; i<mBottomBarData.size(); i++){
               BottomBarBean bean = mBottomBarData.get(i);
               BottomBarItem item = mBottomBarItems.get(i);
               if(bean!=null && item != null) {
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
        if(!TextUtils.isEmpty(bean.getNetIconUrl()) && !TextUtils.isEmpty(bean.getNetSelectIconUrl())){
            // TODO 待实现，先下载，再显示
        } else {
            // 本地默认图片
            item.setLocalIcon(bean.getDefaultIcon(), bean.getDefaultSelectIcon());
        }

        // 如果是中间大图标
        // 区分大图标与小图标的大小变化
        if(bean.isCenterBigIcon()){
            item.setRootSize(BottomBarItem.NONE, 100);
            item.setIconSize(72, 72);
        } else {
            item.setIconSize(50, 50);
        }

    }

    /**
     * 设置每个item点击事件
     * @param item 控件
     * @param index 下标
     */
    public void setOnclickListener(BottomBarItem item, final int index) {
        item.setOnclickListener(new OnClickListener() {
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

}
