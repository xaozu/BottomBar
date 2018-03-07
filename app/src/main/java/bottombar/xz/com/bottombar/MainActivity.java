package bottombar.xz.com.bottombar;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bottombar.xz.com.bottombar.download.DownLoadImageService;
import bottombar.xz.com.bottombar.download.DownLoadImageManager;
import bottombar.xz.com.bottombar.download.ImageDownLoadCallBack;

public class MainActivity extends AppCompatActivity {

    private BottomBarView bottomBarView;
    private TextView textView;
    private List<BottomBarBean> bottomBarBeans = new ArrayList<>();
    private String [] urls = {"https://ws1.sinaimg.cn/large/610dc034ly1foowtrkpvkj20sg0izdkx.jpg",
            "http://7xi8d6.com1.z0.glb.clouddn.com/20180208080314_FhzuAJ_Screenshot.jpeg",
            "http://7xi8d6.com1.z0.glb.clouddn.com/20180129074038_O3ydq4_Screenshot.jpeg",
            "http://7xi8d6.com1.z0.glb.clouddn.com/20180122090204_A4hNiG_Screenshot.jpeg",
            "http://7xi8d6.com1.z0.glb.clouddn.com/20180115085556_8AeReR_taeyeon_ss_15_1_2018_7_58_51_833.jpeg",
            "http://7xi8d6.com1.z0.glb.clouddn.com/20180109085038_4A7atU_rakukoo_9_1_2018_8_50_25_276.jpeg"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv_text);
        bottomBarView = findViewById(R.id.bbv);
        createData();

    }

    public void createData(){

        BottomBarBean bean1 = new BottomBarBean();
        bean1.setText("首页");
        bean1.setDefaultIcon(R.mipmap.tabbar_home_nor);
        bean1.setDefaultSelectIcon(R.mipmap.tabbar_home_sel_driver);
        bean1.setNetIconUrl(urls[0]);
        bean1.setNetSelectIconUrl(urls[1]);
        downLoad(bean1);

        BottomBarBean bean2 = new BottomBarBean();
        bean2.setText("我的订单");
        bean2.setDefaultIcon(R.mipmap.tabbar_order_nor);
        bean2.setDefaultSelectIcon(R.mipmap.tabbar_order_sel_driver);
        bean2.setNetIconUrl(urls[2]);
        bean2.setNetSelectIconUrl(urls[3]);
        downLoad(bean2);

        BottomBarBean bean3 = new BottomBarBean();
        bean3.setCenterBigIcon(true); // 中间按钮
        bean3.setText("听单");
        bean3.setDefaultIcon(R.mipmap.tabbar_findgoods);
        bean3.setDefaultSelectIcon(R.mipmap.tabbar_listen_order);
        bean3.setNetIconUrl(urls[4]);
        bean3.setNetSelectIconUrl(urls[5]);
        downLoad(bean3);

        BottomBarBean bean4 = new BottomBarBean();
        bean4.setText("消息");
        bean4.setDefaultIcon(R.mipmap.tabbar_msg_nor);
        bean4.setDefaultSelectIcon(R.mipmap.tabbar_msg_sel_driver);
        bean4.setNetIconUrl(urls[0]);
        bean4.setNetSelectIconUrl(urls[1]);
        downLoad(bean4);

        BottomBarBean bean5 = new BottomBarBean();
        bean5.setText("我的");
        bean5.setDefaultIcon(R.mipmap.tabbar_user_nor);
        bean5.setDefaultSelectIcon(R.mipmap.tabbar_user_sel_driver);
        bean5.setNetIconUrl(urls[2]);
        bean5.setNetSelectIconUrl(urls[3]);
        downLoad(bean5);
    }

    /**
     * 绑定数据
     * @param bottomBarBeans 数据
     */
    private void bindData(final List<BottomBarBean> bottomBarBeans){
        bottomBarView.bindData(bottomBarBeans);
        bottomBarView.setTextSelectorLocalColor(R.color.colorPrimary, R.color.colorAccent);

        bottomBarView.setCheck(0);

        bottomBarView.setOnBottomBarSelectListener(new OnBottomBarSelectListener() {
            @Override
            public void onSelect(int index) {
                Toast.makeText(getApplicationContext(), bottomBarBeans.get(index).getText(), Toast.LENGTH_LONG).show();
            }
        });

        bottomBarView.setRedDot(4, true);
        bottomBarView.showRedDotNum(3, 10);
    }

    private void downLoad(final BottomBarBean bean){
        DownLoadImageService service = new DownLoadImageService(this,
                bean.getNetIconUrl(),
                bean.getNetSelectIconUrl(),
                new ImageDownLoadCallBack() {
                    @Override
                    public void onDownLoadSuccess(Drawable icon, Drawable selectIcon) {
                        bean.setNetSelectIcon(selectIcon);
                        bean.setNetIcon(icon);

                        bottomBarBeans.add(bean);
                        // 全部设置完成，则绑定数据
                        if(bottomBarBeans.size() == 5){
                            bindData(bottomBarBeans);
                        }
                    }

                    @Override
                    public void onDownLoadFailed() {
                        // 图片保存失败
                        textView.setText(textView.getText()+" \n 失败");
                    }
                });

        // 启动下载线程
        DownLoadImageManager.runOnQueue(service);

    }

}
