package com.young.Demo;

import android.view.View;

import com.library.activity.BasicFragment;
import com.young.Demo.widget.BannerLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by Young  16/7/15.
 */
public class MainFragment extends BasicFragment {
    protected BannerLayout bannerLayout;
    protected BannerLayout bannerLayoutBottom;

    @Override
    protected int getViewId() {
        return R.layout.fragment_main_layout;
    }

    @Override
    protected void init() {
        bannerLayout = (BannerLayout) rootView.findViewById(R.id.banner_layout);
        bannerLayoutBottom= (BannerLayout) rootView.findViewById(R.id.banner_layout_bottom);
        List<String> bannerUrls = new ArrayList<String>();
        bannerUrls.add("http://img30.360buyimg.com/da/jfs/t2983/297/2002289844/50960/e2971e62/5796d126Nb934d250.jpg");
        bannerUrls.add("http://d6.yihaodianimg.com/N05/M0B/BA/12/ChEbulebKmuAIGKQAAHjllhJHus39800.jpg");
        bannerUrls.add("http://img20.360buyimg.com/da/jfs/t2710/176/3413602650/131433/1c625fbc/578c9889N672d27a8.jpg");
        bannerUrls.add("http://img13.360buyimg.com/da/jfs/t2848/214/3282152582/48035/cf431448/578733ffN1d445722.jpg");
        bannerUrls.add("http://img12.360buyimg.com/da/jfs/t2827/259/3266026343/46191/9169b41b/5784c4adN4352af6c.jpg");
        bannerUrls.add("https://img20.360buyimg.com/da/jfs/t2860/263/3769467842/290755/ed56604d/579b17f6Naeb3d163.jpg");
        List<String> bannerUrls1 = new ArrayList<String>();

        bannerUrls1.add("http://img30.360buyimg.com/da/jfs/t2983/297/2002289844/50960/e2971e62/5796d126Nb934d250.jpg");
        bannerUrls1.add("http://d6.yihaodianimg.com/N05/M0B/BA/12/ChEbulebKmuAIGKQAAHjllhJHus39800.jpg");
        bannerUrls1.add("https://img13.360buyimg.com/da/jfs/t1927/64/1413648216/158937/2fff826c/56555cd9N70ac7a6b.jpg");
        bannerUrls1.add("http://img13.360buyimg.com/da/jfs/t2848/214/3282152582/48035/cf431448/578733ffN1d445722.jpg");
        bannerUrls1.add("http://img12.360buyimg.com/da/jfs/t2827/259/3266026343/46191/9169b41b/5784c4adN4352af6c.jpg");
        bannerUrls1.add("https://img20.360buyimg.com/da/jfs/t2860/263/3769467842/290755/ed56604d/579b17f6Naeb3d163.jpg");
        bannerUrls1.add("https://img20.360buyimg.com/da/jfs/t3037/330/80835179/100835/d8b77769/5791deccNa2dcf849.jpg");
        bannerUrls1.add("https://img11.360buyimg.com/da/jfs/t2920/214/2092599899/117689/1cb49e54/579ab418N294b1619.jpg");
        bannerUrls1.add("https://img10.360buyimg.com/da/jfs/t2938/217/2105911602/161485/53569b62/579c0a13N6556cb4e.jpg");
        bannerLayout.setViewUrls(bannerUrls);
        bannerLayoutBottom.setViewUrls(bannerUrls1);

        rootView.findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(null, SecondActivity.class);
            }
        });
    }
}
