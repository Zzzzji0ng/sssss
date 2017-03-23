package com.example.jiong.mynews.Utils;

/**
 * Created by Jiong on 2017/2/15.
 */
public class Constants {

    /*新闻请求URL*/
    public static final String BASE_URL ="http://v.juhe.cn/toutiao/index?type=";
    public static final String APP_KEY="&key=d698ca1cfe777e1d96892956767c803d";
    /*ctrl shift u  大小写转换*/
    public static final String TOP=BASE_URL+"top"+APP_KEY;
    public static final String SHEHUI=BASE_URL+"shehui"+APP_KEY;
    public static final String GUONEI=BASE_URL+"guonei"+APP_KEY;
    public static final String GUOJI=BASE_URL+"guoji"+APP_KEY;
    public static final String YULE=BASE_URL+"yule"+APP_KEY;
    public static final String TIYU=BASE_URL+"tiyu"+APP_KEY;
    public static final String JUNSHI=BASE_URL+"junshi"+APP_KEY;
    public static final String KEJI=BASE_URL+"keji"+APP_KEY;
    public static final String CAIJING=BASE_URL+"caijing"+APP_KEY;
    public static final String SHISHANG=BASE_URL+"shishang"+APP_KEY;
    public static final String[] NewsCategory =new String[] {"头条","社会","国内","国际","娱乐","体育","军事","科技","财经","时尚"};

    /*图片请求URL*/
    public static final String PIC_BASE_URL ="http://route.showapi.com/959-1?showapi_appid=33911&type=";
    public static final String PIC_APP_KEY="&showapi_sign=514ac8fa5a1445adaf4f087005585f4c";
    public static final String leixing1=PIC_BASE_URL+"dmbz"+PIC_APP_KEY;
    public static final String leixing2=PIC_BASE_URL+"rwbz"+PIC_APP_KEY;
    public static final String leixing3=PIC_BASE_URL+"nvyou"+PIC_APP_KEY;
    public static final String leixing4=PIC_BASE_URL+"tpzp"+PIC_APP_KEY;
    public static final String leixing5=PIC_BASE_URL+"meishi"+PIC_APP_KEY;
    public static final String leixing6=PIC_BASE_URL+"dongwu"+PIC_APP_KEY;
    public static final String leixing7=PIC_BASE_URL+"siwa"+PIC_APP_KEY;
    public static final String leixing8=PIC_BASE_URL+"wenshen"+PIC_APP_KEY;
    public static final String leixing9=PIC_BASE_URL+"yingshi"+PIC_APP_KEY;
    public static final String leixing10=PIC_BASE_URL+"rufang"+PIC_APP_KEY;

    public static final String[] PictureCategory=new String []{"动漫壁纸","人物壁纸","写真艺术","自拍艺术","美食图片","动物图片","丝袜美女","纹身图片","影视剧照","美女车展"};

    public static final String VideoDataUrlFirst="https://route.showapi.com/255-1?page=";
    public static final String VideoDataUrlSecond="&showapi_appid=33911&showapi_timestamp=";
    public static final String VideoDataUrlThird="&title=";
    public static final String VideoDataUrlForce="&type=41&showapi_sign=514ac8fa5a1445adaf4f087005585f4c";
}
