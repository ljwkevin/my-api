package jxl.importTest;

import com.yel.common.bean.PosTip;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReader;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yd on 2018/7/6.
 */
public class Test{

    @org.junit.Test
    public void importOneVoData() {
        try{
            List<PosTip> posTips = new ArrayList<PosTip>();
//            InputStream is = new FileInputStream("D:\\data\\template\\importdata.xml");
//            InputStream isd = new FileInputStream("D:\\data\\template\\importdata.xlsx");
            InputStream is = Test.class.getResourceAsStream("../../importdata.xml");
            InputStream isd = Test.class.getResourceAsStream("../../importdata.xlsx");
            XLSReader reader = ReaderBuilder.buildFromXML(is);

            Map<String, Object> beans = new HashMap();
            List<Map<Object, Object>> datas = new ArrayList<Map<Object, Object>>();
            beans.put("datas",datas);
            reader.read(isd,beans);
            DateFormat df =  new SimpleDateFormat("yyyyMMdd");
            for(Map<Object, Object> p : datas){
                System.out.println("小票号"+p.get("number"));
                System.out.println("收款台号"+p.get("tableNum"));
                System.out.println("服务员代码"+p.get("code"));
                System.out.println("交易日期"+df.format(p.get("dealDate")));
                System.out.println("价格"+p.get("price"));
                System.out.println("支付类型"+p.get("payMode"));
                if("支付宝".equals(p.get("payMode"))){
                    System.out.println("支付类型枚举:"+0);
                }
                if("微信".equals(p.get("payMode"))){
                    System.out.println("支付类型枚举:"+1);
                }
                System.out.println("=================");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
