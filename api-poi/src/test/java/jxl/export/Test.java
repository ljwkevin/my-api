package jxl.export;

import com.yel.common.bean.PosTip;
import org.apache.commons.collections.map.HashedMap;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Yd on 2018/7/5.
 */
public class Test {
    @org.junit.Test
    public void exportOneVoData(){
        List datas = new ArrayList();
        PosTip posTip = new PosTip();
        posTip.setNumber("1806010002000246284");
        posTip.setTableNum("0002");
        posTip.setCode("0141");
        posTip.setDealDate(new Date());
        posTip.setPrice(20.21);
        posTip.setPayMode(0);
        Map posTipMap = entityToMap(posTip);
        String payModeName = "支付宝";
        posTipMap.put("payModeName",payModeName);
        datas.add(posTipMap);

        PosTip posTip1 = new PosTip();
        posTip1.setNumber("1806010002000246285");
        posTip1.setTableNum("0004");
        posTip1.setCode("0141");
        posTip1.setDealDate(new Date());
        posTip1.setPrice(10.21);
        posTip1.setPayMode(1);
        Map posTip1Map = entityToMap(posTip1);
        //处理枚举值
        String payModeName1 = "微信";
        posTip1Map.put("payModeName",payModeName1);
        datas.add(posTip1Map);
        try{
//            InputStream is = new FileInputStream("exportOneVoData.xlsx");
            InputStream is = Test.class.getResourceAsStream("../../exportOneVoData.xlsx");

            OutputStream os = new FileOutputStream("D:\\data\\complete-exportOneVoData.xlsx");
            Context context = new Context();
            context.putVar("title","大大的标题");
            context.putVar("datas",datas);
            JxlsHelper.getInstance().processTemplate(is, os, context);

        }catch(Exception e){
            e.printStackTrace();
        }


    }


    public static  Map entityToMap(Object  obj){
        try{
            Method [] methods = obj.getClass().getMethods();
            Map map = new HashedMap();
            for(Method method : methods){
                if(method.getName().startsWith("get")){
                    String key = method.getName().substring(3);
                    key = key.substring(0,1).toLowerCase()+key.substring(1);
                    map.put(key,method.invoke(obj));

                }
            }
            return  map;
        }catch(Exception e){
            e.printStackTrace();
        }
        return  null;

    }
}
