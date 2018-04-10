package main.java.com.yd.groovy;

/**
 * @author Yd on  2018-04-09
 * @description
 **/
public interface GenericService {

    Object invoke(String method, String[] parameterTypes, Object[] args);
}
