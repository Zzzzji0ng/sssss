package com.example.jiong.mynews.domain;

import java.util.List;

/**
 * Created by Jiong on 2017/2/16.
 */


/*新闻中心的数据   用全手动的方式  防止gson不能解析该gson */
public class NewsContentPagerBean {
    @Override
    public String toString() {
        return "NewsContentPagerBean{" +
                "reason='" + reason + '\'' +
                ", result=" + result +
                ", error_code=" + error_code +
                '}';
    }

    /**
     * reason : 成功的返回
     * result : {"stat":"1","data":[{"uniquekey":"f9d954267f3c8409486f6f4a38d00ae6","title":"2017年2月23日外交部发言人耿爽主持例行记者会","date":"2017-02-23 18:06","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/170223180613263.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20170223/20170223180613_725d32ddec0a1bd1d28c199b1e10f430_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://04.imgmini.eastday.com/mobile/20170223/20170223180613_725d32ddec0a1bd1d28c199b1e10f430_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://04.imgmini.eastday.com/mobile/20170223/20170223180613_725d32ddec0a1bd1d28c199b1e10f430_3_mwpm_03200403.jpeg"},{"uniquekey":"805ee6f513f9b80b95dfde74822c4fc4","title":"沉默未必是金，美媒抱怨这届美国务院太低调","date":"2017-02-23 18:27","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/170223182733269.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20170223/20170223182733_8f6c8f8f78b71b854c6bd9f870788f80_1_mwpm_03200403.jpeg"},{"uniquekey":"32e627010e722442b6b2e5de3c874d7c","title":"高圆圆束腰白裙现身活动 一眸一笑风情万种","date":"2017-02-23 17:49","category":"头条","author_name":"视觉中国","url":"http://mini.eastday.com/mobile/170223174954691.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20170223/20170223174954_11b413335f423b48cb4426550c077f8d_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://07.imgmini.eastday.com/mobile/20170223/20170223174954_11b413335f423b48cb4426550c077f8d_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://07.imgmini.eastday.com/mobile/20170223/20170223174954_11b413335f423b48cb4426550c077f8d_3_mwpm_03200403.jpeg"},{"uniquekey":"1d7f3434733e8a9adcd8919e5233ad7f","title":"顺应医疗改革 大医同盟医生集团跨界聚会","date":"2017-02-23 17:48","category":"头条","author_name":"光明网","url":"http://mini.eastday.com/mobile/170223174802035.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20170223/20170223174802_ec1b63e5d8a1dca749da8479ab9e86fd_1_mwpm_03200403.png","thumbnail_pic_s02":"http://09.imgmini.eastday.com/mobile/20170223/20170223174802_ec1b63e5d8a1dca749da8479ab9e86fd_2_mwpm_03200403.png","thumbnail_pic_s03":"http://09.imgmini.eastday.com/mobile/20170223/20170223174802_ec1b63e5d8a1dca749da8479ab9e86fd_3_mwpm_03200403.png"},{"uniquekey":"ec6072d630d107773cbfe5563945a89c","title":"三星太子狱中生活更多细节：和连环杀手关在一起 还得看LG电视","date":"2017-02-23 17:29","category":"头条","author_name":"东方头条","url":"http://mini.eastday.com/mobile/170223172947977.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20170223/20170223172947_a60d6369b135457057c879ae0af419fb_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://05.imgmini.eastday.com/mobile/20170223/20170223172947_0cab33c99a5b5c84e71b0ddd0a89175f_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://05.imgmini.eastday.com/mobile/20170223/20170223172947_7f686170f0b0614842d937e532a25de3_3_mwpm_03200403.jpg"},{"uniquekey":"a1999ae7b8d881987cae72ae700dd701","title":"安徽省非物质文化遗产研究会成立，臧世凯当选研究会首届会长","date":"2017-02-23 17:29","category":"头条","author_name":"中安在线","url":"http://mini.eastday.com/mobile/170223172917341.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20170223/20170223172917_a05c252d88d74b913557b944d6883d0f_1_mwpm_03200403.jpeg"},{"uniquekey":"0084d1d10d06ea67719ff61d17092d6a","title":"《乡村爱情9》演员胖了又胖 唯有杜悦瘦成闪电与宋晓峰相爱相杀","date":"2017-02-23 17:17","category":"头条","author_name":"北方网","url":"http://mini.eastday.com/mobile/170223171750880.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20170223/20170223171750_108c5d251aa091a9be5bfc532ae053fc_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://02.imgmini.eastday.com/mobile/20170223/20170223171750_108c5d251aa091a9be5bfc532ae053fc_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://02.imgmini.eastday.com/mobile/20170223/20170223171750_108c5d251aa091a9be5bfc532ae053fc_3_mwpm_03200403.jpeg"},{"uniquekey":"a92445c9f1ca538d52e47693d51ca565","title":"震惊！乐施会：印尼4大富豪身家抵1亿最贫困人口","date":"2017-02-23 17:14","category":"头条","author_name":"中国日报网","url":"http://mini.eastday.com/mobile/170223171443540.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20170223/20170223171443_396200c24ed3628eafc92bd4b58f4bc9_1_mwpm_03200403.jpeg"},{"uniquekey":"dac57df9abfb5f01931b2cfdd198a55f","title":"失能，高龄：透视农村空巢背景中的养老之困","date":"2017-02-23 17:10","category":"头条","author_name":"半月谈网","url":"http://mini.eastday.com/mobile/170223171009846.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20170223/20170223171009_032d53e058085ce48d8df4884141d0e6_1_mwpm_03200403.jpeg"},{"uniquekey":"a7c90c7024bf9dedf0abdd1aef566227","title":"邓丽君死亡真相竟是吸毒","date":"2017-02-23 17:02","category":"头条","author_name":"多维娱乐","url":"http://mini.eastday.com/mobile/170223170254326.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20170223/20170223170254_e6cb57b4fb8b57493fe33bfda37a7025_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://01.imgmini.eastday.com/mobile/20170223/20170223170254_e6cb57b4fb8b57493fe33bfda37a7025_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://01.imgmini.eastday.com/mobile/20170223/20170223170254_e6cb57b4fb8b57493fe33bfda37a7025_3_mwpm_03200403.jpeg"},{"uniquekey":"9325a2844113233763abf0ccbe8caa4a","title":"万事怪英九！\u201c绿委\u201d批台军毒品案是马英九的错","date":"2017-02-23 16:53","category":"头条","author_name":"中国台湾网","url":"http://mini.eastday.com/mobile/170223165325857.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20170223/20170223165325_ca4c20c9e5675b14ab559df85ab01820_1_mwpm_03200403.jpeg"},{"uniquekey":"e0c889a8ff4c1f7ae7c3b975af8a11c8","title":"特朗普宣称削减10亿美元专机开支 美空军：不知情","date":"2017-02-23 16:46","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/170223164608764.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20170223/20170223164608_21c6541215f5bcbb920efc506bfc7c2b_1_mwpm_03200403.jpeg"},{"uniquekey":"81c1dd01269283adb0bd186c2ccb3ab0","title":"陕西截获一级濒危植物\u201c白斜子\u201d","date":"2017-02-23 16:42","category":"头条","author_name":"央广网","url":"http://mini.eastday.com/mobile/170223164253708.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20170223/20170223164253_2ee9d7260679cb9b33d038c59b95cb22_1_mwpm_03200403.jpeg"},{"uniquekey":"01c27217391e3ab363a52057e705cc1d","title":"旅美熊猫\u201c宝宝\u201d回国第一天：兴奋地到处打滚，染上自己味道","date":"2017-02-23 16:37","category":"头条","author_name":"川报观察客户端","url":"http://mini.eastday.com/mobile/170223163700029.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20170223/20170223163700_f03606213fb7a669d5a57935e6bc8e68_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://01.imgmini.eastday.com/mobile/20170223/20170223163700_f03606213fb7a669d5a57935e6bc8e68_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://01.imgmini.eastday.com/mobile/20170223/20170223163700_f03606213fb7a669d5a57935e6bc8e68_3_mwpm_03200403.jpeg"},{"uniquekey":"b766563a88b3e3c8eafdd552d1415de8","title":"国防部辟谣：中国在中朝边境大规模增兵报道不实","date":"2017-02-23 16:33","category":"头条","author_name":"中国新闻网","url":"http://mini.eastday.com/mobile/170223163316778.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20170223/20170223163316_9183101353b8920bfcccfed2bd8c0497_1_mwpm_03200403.jpeg"},{"uniquekey":"909c8548376cf7d6e1234399988cbd52","title":"第二十五届中国国际广播电视信息网络展览会在京召开","date":"2017-02-23 16:31","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/170223163140698.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20170223/20170223163140_d19874e6e80b4e3bdd516c8616cd1927_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://01.imgmini.eastday.com/mobile/20170223/20170223163140_d19874e6e80b4e3bdd516c8616cd1927_2_mwpm_03200403.jpeg"},{"uniquekey":"5fe84b192cfcd985d0b949cb226565b7","title":"美称太平洋舰队随时应战 国防部回应：南海局势稳定 勿渲染战争威胁","date":"2017-02-23 16:30","category":"头条","author_name":"环球时报","url":"http://mini.eastday.com/mobile/170223163004041.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20170223/20170223163004_c66052a914936a9d6fdb2a759220df49_1_mwpm_03200403.jpeg"},{"uniquekey":"23646d411a652531870691123425255e","title":"英电信公司将尝试用无人机和气球发射4G信号","date":"2017-02-23 16:21","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/170223162111338.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20170223/20170223162111_d2e68a27e91259d19c929fe3f4154e29_1_mwpm_03200403.jpeg"},{"uniquekey":"fe47e298bd575879a736a1177e19465c","title":"也门政府军副参谋长在袭击中被导弹击中丧生","date":"2017-02-23 16:18","category":"头条","author_name":"新华社","url":"http://mini.eastday.com/mobile/170223161839885.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20170223/20170223161839_9810794d638341340d2fe3ba041e378d_1_mwpm_03200403.jpeg"},{"uniquekey":"be1215122a7bdd626acda1d77b4ec63b","title":"男子扮摄影师骗美女试镜性侵10余人 朝阳警方呼吁受害人报案","date":"2017-02-23 16:09","category":"头条","author_name":"法制晚报","url":"http://mini.eastday.com/mobile/170223160905702.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20170223/20170223160905_676e7a3ab8e5fd77f2fb9d5245ae2e24_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://01.imgmini.eastday.com/mobile/20170223/20170223160905_676e7a3ab8e5fd77f2fb9d5245ae2e24_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://01.imgmini.eastday.com/mobile/20170223/20170223160905_676e7a3ab8e5fd77f2fb9d5245ae2e24_3_mwpm_03200403.jpeg"},{"uniquekey":"325175d0bfdb891d87288ae4fd4dec9f","title":"在太平间工作的女孩：最担心找不到男友","date":"2017-02-23 16:07","category":"头条","author_name":"三十年工人人生感悟","url":"http://mini.eastday.com/mobile/170223160710589.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20170223/20170223160710_e2c89b73229447719e47aa0f84b31525_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://07.imgmini.eastday.com/mobile/20170223/20170223160710_e2c89b73229447719e47aa0f84b31525_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://07.imgmini.eastday.com/mobile/20170223/20170223160710_e2c89b73229447719e47aa0f84b31525_3_mwpm_03200403.jpeg"},{"uniquekey":"2fb4539e0ffcffb061add257294d95a3","title":"美收紧移民政策 智库：中国应借机对国际人才实行更开放政策","date":"2017-02-23 16:05","category":"头条","author_name":"中国日报网","url":"http://mini.eastday.com/mobile/170223160550694.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20170223/20170223160550_2183cdba394d30003a925792466797b4_1_mwpm_03200403.jpeg"},{"uniquekey":"456a52fc1187149d19815c4995fb19d4","title":"她是百花影后却被陈道明痛骂演技差 感情坎坷现成这男星后妈","date":"2017-02-23 16:04","category":"头条","author_name":"前瞻网","url":"http://mini.eastday.com/mobile/170223160456362.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20170223/20170223160456_49e29f9075a541f551337359cdf454ec_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://06.imgmini.eastday.com/mobile/20170223/20170223160456_49e29f9075a541f551337359cdf454ec_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://06.imgmini.eastday.com/mobile/20170223/20170223160456_49e29f9075a541f551337359cdf454ec_3_mwpm_03200403.jpeg"},{"uniquekey":"4310a2d4873870efbd67752d717c0a2e","title":"目标更强大有力！俄罗斯宣布成立信息战新军","date":"2017-02-23 16:03","category":"头条","author_name":"新华网","url":"http://mini.eastday.com/mobile/170223160351570.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20170223/20170223160351_f96d4551e82fd8e43ad5741da205c7c1_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://06.imgmini.eastday.com/mobile/20170223/20170223160351_f96d4551e82fd8e43ad5741da205c7c1_2_mwpm_03200403.jpeg"},{"uniquekey":"6ed73c7b52c02109f72e17b4a2d1745e","title":"民调：勒庞赢得法总统选举首轮投票胜算增大","date":"2017-02-23 15:56","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/170223155629788.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20170223/20170223155629_f649ccc7aedfb00139355e09480c0718_1_mwpm_03200403.jpeg"},{"uniquekey":"6f79647ee89b8d8322078f15cc735cf4","title":"美国白宫发言人：一小部分抗议者是职业的","date":"2017-02-23 15:51","category":"头条","author_name":"中国日报网","url":"http://mini.eastday.com/mobile/170223155132072.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20170223/20170223155132_41c28a17a7c938189511afa34b24e4c4_1_mwpm_03200403.jpeg"},{"uniquekey":"8ebcfbd36a665a832db3d91acf1d6f07","title":"想把家常红烧肉做的肥而不腻喷香入味，掌握这个秘诀就很有必要！","date":"2017-02-23 15:48","category":"头条","author_name":"美味糕点","url":"http://mini.eastday.com/mobile/170223154855902.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20170223/20170223154855_659881e168a3929faaf99181ef935c50_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://01.imgmini.eastday.com/mobile/20170223/20170223154855_659881e168a3929faaf99181ef935c50_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://01.imgmini.eastday.com/mobile/20170223/20170223154855_659881e168a3929faaf99181ef935c50_3_mwpm_03200403.jpeg"},{"uniquekey":"b7fc6b2a6a139a4e627b4160ed44d8bd","title":"为何越南背叛了中国 而巴铁却无比忠诚？","date":"2017-02-23 15:48","category":"头条","author_name":"中华网","url":"http://mini.eastday.com/mobile/170223154845745.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20170223/20170223154845_ab6dc54aad34b36f6c78c5ba62d783f7_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://07.imgmini.eastday.com/mobile/20170223/20170223154845_ab6dc54aad34b36f6c78c5ba62d783f7_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://07.imgmini.eastday.com/mobile/20170223/20170223154845_ab6dc54aad34b36f6c78c5ba62d783f7_4_mwpm_03200403.jpeg"},{"uniquekey":"7ae502515c698b59d61880215efe0c70","title":"门诊按人头付费，剑指\u201c过度医疗\u201d","date":"2017-02-23 15:48","category":"头条","author_name":"人民日报中央厨房","url":"http://mini.eastday.com/mobile/170223154814530.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20170223/20170223154814_9e34155136454085c8b7e667579465c4_1_mwpm_03200403.png"},{"uniquekey":"22c5ac0a2a0642fd3210fb1718196c22","title":"盘点娱乐圈老年丧子很凄惨的十大明星！","date":"2017-02-23 15:48","category":"头条","author_name":"小阳看电影","url":"http://mini.eastday.com/mobile/170223154804851.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20170223/20170223154804_fab8901b52b0deeab5d20c4e49ca3031_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://05.imgmini.eastday.com/mobile/20170223/20170223154804_fab8901b52b0deeab5d20c4e49ca3031_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://05.imgmini.eastday.com/mobile/20170223/20170223154804_fab8901b52b0deeab5d20c4e49ca3031_3_mwpm_03200403.jpeg"}]}
     * error_code : 0
     */

    private String reason;
    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        @Override
        public String toString() {
            return "ResultBean{" +
                    "stat='" + stat + '\'' +
                    ", data=" + data +
                    '}';
        }

        /**
         * stat : 1
         * data : [{"uniquekey":"f9d954267f3c8409486f6f4a38d00ae6","title":"2017年2月23日外交部发言人耿爽主持例行记者会","date":"2017-02-23 18:06","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/170223180613263.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20170223/20170223180613_725d32ddec0a1bd1d28c199b1e10f430_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://04.imgmini.eastday.com/mobile/20170223/20170223180613_725d32ddec0a1bd1d28c199b1e10f430_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://04.imgmini.eastday.com/mobile/20170223/20170223180613_725d32ddec0a1bd1d28c199b1e10f430_3_mwpm_03200403.jpeg"},{"uniquekey":"805ee6f513f9b80b95dfde74822c4fc4","title":"沉默未必是金，美媒抱怨这届美国务院太低调","date":"2017-02-23 18:27","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/170223182733269.html","thumbnail_pic_s":"http://04.imgmini.eastday.com/mobile/20170223/20170223182733_8f6c8f8f78b71b854c6bd9f870788f80_1_mwpm_03200403.jpeg"},{"uniquekey":"32e627010e722442b6b2e5de3c874d7c","title":"高圆圆束腰白裙现身活动 一眸一笑风情万种","date":"2017-02-23 17:49","category":"头条","author_name":"视觉中国","url":"http://mini.eastday.com/mobile/170223174954691.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20170223/20170223174954_11b413335f423b48cb4426550c077f8d_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://07.imgmini.eastday.com/mobile/20170223/20170223174954_11b413335f423b48cb4426550c077f8d_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://07.imgmini.eastday.com/mobile/20170223/20170223174954_11b413335f423b48cb4426550c077f8d_3_mwpm_03200403.jpeg"},{"uniquekey":"1d7f3434733e8a9adcd8919e5233ad7f","title":"顺应医疗改革 大医同盟医生集团跨界聚会","date":"2017-02-23 17:48","category":"头条","author_name":"光明网","url":"http://mini.eastday.com/mobile/170223174802035.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20170223/20170223174802_ec1b63e5d8a1dca749da8479ab9e86fd_1_mwpm_03200403.png","thumbnail_pic_s02":"http://09.imgmini.eastday.com/mobile/20170223/20170223174802_ec1b63e5d8a1dca749da8479ab9e86fd_2_mwpm_03200403.png","thumbnail_pic_s03":"http://09.imgmini.eastday.com/mobile/20170223/20170223174802_ec1b63e5d8a1dca749da8479ab9e86fd_3_mwpm_03200403.png"},{"uniquekey":"ec6072d630d107773cbfe5563945a89c","title":"三星太子狱中生活更多细节：和连环杀手关在一起 还得看LG电视","date":"2017-02-23 17:29","category":"头条","author_name":"东方头条","url":"http://mini.eastday.com/mobile/170223172947977.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20170223/20170223172947_a60d6369b135457057c879ae0af419fb_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://05.imgmini.eastday.com/mobile/20170223/20170223172947_0cab33c99a5b5c84e71b0ddd0a89175f_2_mwpm_03200403.jpg","thumbnail_pic_s03":"http://05.imgmini.eastday.com/mobile/20170223/20170223172947_7f686170f0b0614842d937e532a25de3_3_mwpm_03200403.jpg"},{"uniquekey":"a1999ae7b8d881987cae72ae700dd701","title":"安徽省非物质文化遗产研究会成立，臧世凯当选研究会首届会长","date":"2017-02-23 17:29","category":"头条","author_name":"中安在线","url":"http://mini.eastday.com/mobile/170223172917341.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20170223/20170223172917_a05c252d88d74b913557b944d6883d0f_1_mwpm_03200403.jpeg"},{"uniquekey":"0084d1d10d06ea67719ff61d17092d6a","title":"《乡村爱情9》演员胖了又胖 唯有杜悦瘦成闪电与宋晓峰相爱相杀","date":"2017-02-23 17:17","category":"头条","author_name":"北方网","url":"http://mini.eastday.com/mobile/170223171750880.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20170223/20170223171750_108c5d251aa091a9be5bfc532ae053fc_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://02.imgmini.eastday.com/mobile/20170223/20170223171750_108c5d251aa091a9be5bfc532ae053fc_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://02.imgmini.eastday.com/mobile/20170223/20170223171750_108c5d251aa091a9be5bfc532ae053fc_3_mwpm_03200403.jpeg"},{"uniquekey":"a92445c9f1ca538d52e47693d51ca565","title":"震惊！乐施会：印尼4大富豪身家抵1亿最贫困人口","date":"2017-02-23 17:14","category":"头条","author_name":"中国日报网","url":"http://mini.eastday.com/mobile/170223171443540.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20170223/20170223171443_396200c24ed3628eafc92bd4b58f4bc9_1_mwpm_03200403.jpeg"},{"uniquekey":"dac57df9abfb5f01931b2cfdd198a55f","title":"失能，高龄：透视农村空巢背景中的养老之困","date":"2017-02-23 17:10","category":"头条","author_name":"半月谈网","url":"http://mini.eastday.com/mobile/170223171009846.html","thumbnail_pic_s":"http://02.imgmini.eastday.com/mobile/20170223/20170223171009_032d53e058085ce48d8df4884141d0e6_1_mwpm_03200403.jpeg"},{"uniquekey":"a7c90c7024bf9dedf0abdd1aef566227","title":"邓丽君死亡真相竟是吸毒","date":"2017-02-23 17:02","category":"头条","author_name":"多维娱乐","url":"http://mini.eastday.com/mobile/170223170254326.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20170223/20170223170254_e6cb57b4fb8b57493fe33bfda37a7025_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://01.imgmini.eastday.com/mobile/20170223/20170223170254_e6cb57b4fb8b57493fe33bfda37a7025_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://01.imgmini.eastday.com/mobile/20170223/20170223170254_e6cb57b4fb8b57493fe33bfda37a7025_3_mwpm_03200403.jpeg"},{"uniquekey":"9325a2844113233763abf0ccbe8caa4a","title":"万事怪英九！\u201c绿委\u201d批台军毒品案是马英九的错","date":"2017-02-23 16:53","category":"头条","author_name":"中国台湾网","url":"http://mini.eastday.com/mobile/170223165325857.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20170223/20170223165325_ca4c20c9e5675b14ab559df85ab01820_1_mwpm_03200403.jpeg"},{"uniquekey":"e0c889a8ff4c1f7ae7c3b975af8a11c8","title":"特朗普宣称削减10亿美元专机开支 美空军：不知情","date":"2017-02-23 16:46","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/170223164608764.html","thumbnail_pic_s":"http://08.imgmini.eastday.com/mobile/20170223/20170223164608_21c6541215f5bcbb920efc506bfc7c2b_1_mwpm_03200403.jpeg"},{"uniquekey":"81c1dd01269283adb0bd186c2ccb3ab0","title":"陕西截获一级濒危植物\u201c白斜子\u201d","date":"2017-02-23 16:42","category":"头条","author_name":"央广网","url":"http://mini.eastday.com/mobile/170223164253708.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20170223/20170223164253_2ee9d7260679cb9b33d038c59b95cb22_1_mwpm_03200403.jpeg"},{"uniquekey":"01c27217391e3ab363a52057e705cc1d","title":"旅美熊猫\u201c宝宝\u201d回国第一天：兴奋地到处打滚，染上自己味道","date":"2017-02-23 16:37","category":"头条","author_name":"川报观察客户端","url":"http://mini.eastday.com/mobile/170223163700029.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20170223/20170223163700_f03606213fb7a669d5a57935e6bc8e68_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://01.imgmini.eastday.com/mobile/20170223/20170223163700_f03606213fb7a669d5a57935e6bc8e68_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://01.imgmini.eastday.com/mobile/20170223/20170223163700_f03606213fb7a669d5a57935e6bc8e68_3_mwpm_03200403.jpeg"},{"uniquekey":"b766563a88b3e3c8eafdd552d1415de8","title":"国防部辟谣：中国在中朝边境大规模增兵报道不实","date":"2017-02-23 16:33","category":"头条","author_name":"中国新闻网","url":"http://mini.eastday.com/mobile/170223163316778.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20170223/20170223163316_9183101353b8920bfcccfed2bd8c0497_1_mwpm_03200403.jpeg"},{"uniquekey":"909c8548376cf7d6e1234399988cbd52","title":"第二十五届中国国际广播电视信息网络展览会在京召开","date":"2017-02-23 16:31","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/170223163140698.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20170223/20170223163140_d19874e6e80b4e3bdd516c8616cd1927_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://01.imgmini.eastday.com/mobile/20170223/20170223163140_d19874e6e80b4e3bdd516c8616cd1927_2_mwpm_03200403.jpeg"},{"uniquekey":"5fe84b192cfcd985d0b949cb226565b7","title":"美称太平洋舰队随时应战 国防部回应：南海局势稳定 勿渲染战争威胁","date":"2017-02-23 16:30","category":"头条","author_name":"环球时报","url":"http://mini.eastday.com/mobile/170223163004041.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20170223/20170223163004_c66052a914936a9d6fdb2a759220df49_1_mwpm_03200403.jpeg"},{"uniquekey":"23646d411a652531870691123425255e","title":"英电信公司将尝试用无人机和气球发射4G信号","date":"2017-02-23 16:21","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/170223162111338.html","thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20170223/20170223162111_d2e68a27e91259d19c929fe3f4154e29_1_mwpm_03200403.jpeg"},{"uniquekey":"fe47e298bd575879a736a1177e19465c","title":"也门政府军副参谋长在袭击中被导弹击中丧生","date":"2017-02-23 16:18","category":"头条","author_name":"新华社","url":"http://mini.eastday.com/mobile/170223161839885.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20170223/20170223161839_9810794d638341340d2fe3ba041e378d_1_mwpm_03200403.jpeg"},{"uniquekey":"be1215122a7bdd626acda1d77b4ec63b","title":"男子扮摄影师骗美女试镜性侵10余人 朝阳警方呼吁受害人报案","date":"2017-02-23 16:09","category":"头条","author_name":"法制晚报","url":"http://mini.eastday.com/mobile/170223160905702.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20170223/20170223160905_676e7a3ab8e5fd77f2fb9d5245ae2e24_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://01.imgmini.eastday.com/mobile/20170223/20170223160905_676e7a3ab8e5fd77f2fb9d5245ae2e24_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://01.imgmini.eastday.com/mobile/20170223/20170223160905_676e7a3ab8e5fd77f2fb9d5245ae2e24_3_mwpm_03200403.jpeg"},{"uniquekey":"325175d0bfdb891d87288ae4fd4dec9f","title":"在太平间工作的女孩：最担心找不到男友","date":"2017-02-23 16:07","category":"头条","author_name":"三十年工人人生感悟","url":"http://mini.eastday.com/mobile/170223160710589.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20170223/20170223160710_e2c89b73229447719e47aa0f84b31525_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://07.imgmini.eastday.com/mobile/20170223/20170223160710_e2c89b73229447719e47aa0f84b31525_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://07.imgmini.eastday.com/mobile/20170223/20170223160710_e2c89b73229447719e47aa0f84b31525_3_mwpm_03200403.jpeg"},{"uniquekey":"2fb4539e0ffcffb061add257294d95a3","title":"美收紧移民政策 智库：中国应借机对国际人才实行更开放政策","date":"2017-02-23 16:05","category":"头条","author_name":"中国日报网","url":"http://mini.eastday.com/mobile/170223160550694.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20170223/20170223160550_2183cdba394d30003a925792466797b4_1_mwpm_03200403.jpeg"},{"uniquekey":"456a52fc1187149d19815c4995fb19d4","title":"她是百花影后却被陈道明痛骂演技差 感情坎坷现成这男星后妈","date":"2017-02-23 16:04","category":"头条","author_name":"前瞻网","url":"http://mini.eastday.com/mobile/170223160456362.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20170223/20170223160456_49e29f9075a541f551337359cdf454ec_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://06.imgmini.eastday.com/mobile/20170223/20170223160456_49e29f9075a541f551337359cdf454ec_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://06.imgmini.eastday.com/mobile/20170223/20170223160456_49e29f9075a541f551337359cdf454ec_3_mwpm_03200403.jpeg"},{"uniquekey":"4310a2d4873870efbd67752d717c0a2e","title":"目标更强大有力！俄罗斯宣布成立信息战新军","date":"2017-02-23 16:03","category":"头条","author_name":"新华网","url":"http://mini.eastday.com/mobile/170223160351570.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20170223/20170223160351_f96d4551e82fd8e43ad5741da205c7c1_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://06.imgmini.eastday.com/mobile/20170223/20170223160351_f96d4551e82fd8e43ad5741da205c7c1_2_mwpm_03200403.jpeg"},{"uniquekey":"6ed73c7b52c02109f72e17b4a2d1745e","title":"民调：勒庞赢得法总统选举首轮投票胜算增大","date":"2017-02-23 15:56","category":"头条","author_name":"环球网","url":"http://mini.eastday.com/mobile/170223155629788.html","thumbnail_pic_s":"http://06.imgmini.eastday.com/mobile/20170223/20170223155629_f649ccc7aedfb00139355e09480c0718_1_mwpm_03200403.jpeg"},{"uniquekey":"6f79647ee89b8d8322078f15cc735cf4","title":"美国白宫发言人：一小部分抗议者是职业的","date":"2017-02-23 15:51","category":"头条","author_name":"中国日报网","url":"http://mini.eastday.com/mobile/170223155132072.html","thumbnail_pic_s":"http://09.imgmini.eastday.com/mobile/20170223/20170223155132_41c28a17a7c938189511afa34b24e4c4_1_mwpm_03200403.jpeg"},{"uniquekey":"8ebcfbd36a665a832db3d91acf1d6f07","title":"想把家常红烧肉做的肥而不腻喷香入味，掌握这个秘诀就很有必要！","date":"2017-02-23 15:48","category":"头条","author_name":"美味糕点","url":"http://mini.eastday.com/mobile/170223154855902.html","thumbnail_pic_s":"http://01.imgmini.eastday.com/mobile/20170223/20170223154855_659881e168a3929faaf99181ef935c50_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://01.imgmini.eastday.com/mobile/20170223/20170223154855_659881e168a3929faaf99181ef935c50_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://01.imgmini.eastday.com/mobile/20170223/20170223154855_659881e168a3929faaf99181ef935c50_3_mwpm_03200403.jpeg"},{"uniquekey":"b7fc6b2a6a139a4e627b4160ed44d8bd","title":"为何越南背叛了中国 而巴铁却无比忠诚？","date":"2017-02-23 15:48","category":"头条","author_name":"中华网","url":"http://mini.eastday.com/mobile/170223154845745.html","thumbnail_pic_s":"http://07.imgmini.eastday.com/mobile/20170223/20170223154845_ab6dc54aad34b36f6c78c5ba62d783f7_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://07.imgmini.eastday.com/mobile/20170223/20170223154845_ab6dc54aad34b36f6c78c5ba62d783f7_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://07.imgmini.eastday.com/mobile/20170223/20170223154845_ab6dc54aad34b36f6c78c5ba62d783f7_4_mwpm_03200403.jpeg"},{"uniquekey":"7ae502515c698b59d61880215efe0c70","title":"门诊按人头付费，剑指\u201c过度医疗\u201d","date":"2017-02-23 15:48","category":"头条","author_name":"人民日报中央厨房","url":"http://mini.eastday.com/mobile/170223154814530.html","thumbnail_pic_s":"http://00.imgmini.eastday.com/mobile/20170223/20170223154814_9e34155136454085c8b7e667579465c4_1_mwpm_03200403.png"},{"uniquekey":"22c5ac0a2a0642fd3210fb1718196c22","title":"盘点娱乐圈老年丧子很凄惨的十大明星！","date":"2017-02-23 15:48","category":"头条","author_name":"小阳看电影","url":"http://mini.eastday.com/mobile/170223154804851.html","thumbnail_pic_s":"http://05.imgmini.eastday.com/mobile/20170223/20170223154804_fab8901b52b0deeab5d20c4e49ca3031_1_mwpm_03200403.jpeg","thumbnail_pic_s02":"http://05.imgmini.eastday.com/mobile/20170223/20170223154804_fab8901b52b0deeab5d20c4e49ca3031_2_mwpm_03200403.jpeg","thumbnail_pic_s03":"http://05.imgmini.eastday.com/mobile/20170223/20170223154804_fab8901b52b0deeab5d20c4e49ca3031_3_mwpm_03200403.jpeg"}]
         */

        private String stat;
        private List<DataBean> data;

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            @Override
            public String toString() {
                return "DataBean{" +
                        "uniquekey='" + uniquekey + '\'' +
                        ", title='" + title + '\'' +
                        ", date='" + date + '\'' +
                        ", category='" + category + '\'' +
                        ", author_name='" + author_name + '\'' +
                        ", url='" + url + '\'' +
                        ", thumbnail_pic_s='" + thumbnail_pic_s + '\'' +
                        ", thumbnail_pic_s02='" + thumbnail_pic_s02 + '\'' +
                        ", thumbnail_pic_s03='" + thumbnail_pic_s03 + '\'' +
                        '}';
            }

            /**
             * uniquekey : f9d954267f3c8409486f6f4a38d00ae6
             * title : 2017年2月23日外交部发言人耿爽主持例行记者会
             * date : 2017-02-23 18:06
             * category : 头条
             * author_name : 环球网
             * url : http://mini.eastday.com/mobile/170223180613263.html
             * thumbnail_pic_s : http://04.imgmini.eastday.com/mobile/20170223/20170223180613_725d32ddec0a1bd1d28c199b1e10f430_1_mwpm_03200403.jpeg
             * thumbnail_pic_s02 : http://04.imgmini.eastday.com/mobile/20170223/20170223180613_725d32ddec0a1bd1d28c199b1e10f430_2_mwpm_03200403.jpeg
             * thumbnail_pic_s03 : http://04.imgmini.eastday.com/mobile/20170223/20170223180613_725d32ddec0a1bd1d28c199b1e10f430_3_mwpm_03200403.jpeg
             */

            private String uniquekey;
            private String title;
            private String date;
            private String category;
            private String author_name;
            private String url;
            private String thumbnail_pic_s;
            private String thumbnail_pic_s02;
            private String thumbnail_pic_s03;

            public String getUniquekey() {
                return uniquekey;
            }

            public void setUniquekey(String uniquekey) {
                this.uniquekey = uniquekey;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getAuthor_name() {
                return author_name;
            }

            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getThumbnail_pic_s() {
                return thumbnail_pic_s;
            }

            public void setThumbnail_pic_s(String thumbnail_pic_s) {
                this.thumbnail_pic_s = thumbnail_pic_s;
            }

            public String getThumbnail_pic_s02() {
                return thumbnail_pic_s02;
            }

            public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
                this.thumbnail_pic_s02 = thumbnail_pic_s02;
            }

            public String getThumbnail_pic_s03() {
                return thumbnail_pic_s03;
            }

            public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
                this.thumbnail_pic_s03 = thumbnail_pic_s03;
            }
        }
    }
}