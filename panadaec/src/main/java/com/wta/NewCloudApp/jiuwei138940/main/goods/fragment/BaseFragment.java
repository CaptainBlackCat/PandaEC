package com.wta.NewCloudApp.jiuwei138940.main.goods.fragment;
import com.wta.NewCloudApp.jiuwei138940.latte_core.delegates.LatteDelegate;
import com.wta.NewCloudApp.jiuwei138940.main.goods.GoodsDetail_miaosha;
import com.wta.NewCloudApp.jiuwei138940.main.goods.GoodsDetail_normal;
import com.wta.NewCloudApp.jiuwei138940.main.goods.GoodsDetail_xianshicuxiao;
import com.wta.NewCloudApp.jiuwei138940.main.goods.GoodsDetail_yushou;
import com.wta.NewCloudApp.jiuwei138940.main.goods.bean.BigClassification;
import com.wta.NewCloudApp.jiuwei138940.main.goods.bean.GoodsDetailEntity;
import org.greenrobot.greendao.annotation.NotNull;
import java.util.List;


public abstract class BaseFragment extends LatteDelegate {

    private GoodsDetailEntity data;


    protected GoodsDetail_miaosha goodsDetail_miaosha;
    protected GoodsDetail_normal goodsDetail;
    protected GoodsDetail_yushou goodsDetail_yushou;
    protected GoodsDetail_xianshicuxiao goodsDetail_xianshicuxiao;



    public GoodsDetailEntity.DataBean getData() {
        if (data!=null){
            return data.getData();
        }
        return new GoodsDetailEntity.DataBean();
    }

    public List<BigClassification> getChoiceData(){
        return data.getChoiceData();
    }

    public void setData(GoodsDetailEntity data) {
        this.data = data;
    }

    public void setParentFragment(@NotNull GoodsDetail_normal goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    public void setParentFragment(@NotNull GoodsDetail_miaosha goodsDetail){
        this.goodsDetail_miaosha=goodsDetail;
    }

    public void setParentFragment(@NotNull GoodsDetail_xianshicuxiao goodsDetail){
        this.goodsDetail_xianshicuxiao=goodsDetail;
    }

    public void setParentFragment(@NotNull GoodsDetail_yushou goodsDetail){
        this.goodsDetail_yushou=goodsDetail;
    }
}
