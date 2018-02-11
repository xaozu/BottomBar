package bottombar.xz.com.bottombar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomBarView bottomBarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomBarView = findViewById(R.id.bbv);
        createData();


    }

    public void createData(){
        List<BottomBarBean> bottomBarBeans = new ArrayList<>();

        BottomBarBean bean1 = new BottomBarBean();
        bean1.setText("首页");
        bean1.setDefaultIcon(R.mipmap.tabbar_home_nor);
        bean1.setDefaultSelectIcon(R.mipmap.tabbar_home_sel_driver);

        BottomBarBean bean2 = new BottomBarBean();
        bean2.setText("我的订单");
        bean2.setDefaultIcon(R.mipmap.tabbar_order_nor);
        bean2.setDefaultSelectIcon(R.mipmap.tabbar_order_sel_driver);

        BottomBarBean bean3 = new BottomBarBean();
        bean3.setCenterBigIcon(true); // 中间按钮
        bean3.setText("听单");
        bean3.setDefaultIcon(R.mipmap.tabbar_findgoods);
        bean3.setDefaultSelectIcon(R.mipmap.tabbar_listen_order);

        BottomBarBean bean4 = new BottomBarBean();
        bean4.setText("消息");
        bean4.setDefaultIcon(R.mipmap.tabbar_msg_nor);
        bean4.setDefaultSelectIcon(R.mipmap.tabbar_msg_sel_driver);

        BottomBarBean bean5 = new BottomBarBean();
        bean5.setText("我的");
        bean5.setDefaultIcon(R.mipmap.tabbar_user_nor);
        bean5.setDefaultSelectIcon(R.mipmap.tabbar_user_sel_driver);

        bottomBarBeans.add(bean1);
        bottomBarBeans.add(bean2);
        bottomBarBeans.add(bean3);
        bottomBarBeans.add(bean4);
        bottomBarBeans.add(bean5);

        bottomBarView.bindData(bottomBarBeans);
        bottomBarView.setTextSelectorLocalColor(R.color.colorPrimary, R.color.colorAccent);

        bottomBarView.setCheck(0);


    }
}
