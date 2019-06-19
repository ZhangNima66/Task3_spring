package cn.com.hnisi.controller.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 参数绑定 日期对象匹配器
 * @author maxuezhi
 * @company sinobest
 * @date 2019/5/9
 */
public class CustomDateConverter implements Converter<String, Date>
{
    @Override
    public Date convert(String s)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            return format.parse(s);
        } catch (ParseException e)
        {
            throw new RuntimeException(e);
        }
    }
}
