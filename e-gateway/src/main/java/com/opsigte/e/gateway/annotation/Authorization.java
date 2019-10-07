package com.opsigte.e.gateway.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *<p> @ClassName: <i>Authorization</i></p>
 *<p> @Description: <i>在Controller的方法上使用此注解，该方法在映射时会检查请求头中的token是否正确</i></p>
 *<p> @Author: <i>zzh</i></p>
 *<p> @Created date: <i>2019/10/7 15:24</i></p>
 *<p> @Version: <i>V1.0.0</i> </p>
 */
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Authorization {
}
