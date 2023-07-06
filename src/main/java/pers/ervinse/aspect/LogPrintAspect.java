package pers.ervinse.aspect;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import pers.ervinse.annotatian.LogPrint;
import pers.ervinse.domain.OperatorLog;
import pers.ervinse.manager.AsyncManager;
import pers.ervinse.manager.factory.AsyncFactory;
import pers.ervinse.utils.UserContextUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
//一般生产环境不允许日志打印参数
@Slf4j
public class LogPrintAspect {
    /**
     * 换行符
     */
    private static final String LINE_SEPARATOR = System.lineSeparator();

    /**
     * 以自定义 @LogPrint 注解为切点
     */
    @Pointcut("@annotation(pers.ervinse.annotatian.LogPrint)")
    public void logPrint() {
    }

    /**
     * 在切点之前织入
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("@annotation(logPrint)")
    public void doBefore(JoinPoint joinPoint, LogPrint logPrint) throws Throwable {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 获取 @WebLog 注解的描述信息
        String methodDescription = getAspectLogDescription(joinPoint);

        String type = logPrint.title();

        String description = logPrint.description();
        Integer userId;
        if (UserContextUtil.get() == null)
            userId = null;
        else userId = UserContextUtil.get().getUserID();
        //TODO:user为空是日志的书写方式

        String url = request.getRequestURL().toString();

        String method = request.getMethod();

        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();

        String ip = request.getRemoteAddr();

        String params = getParams(joinPoint);

        OperatorLog operatorLog = new OperatorLog(userId, type, description, url, method, classMethod, ip, params, new Date());

        AsyncManager.me().execute(AsyncFactory.recordOper(operatorLog));

        // 打印请求相关参数
        log.info("========================================== Start ==========================================");
        // 打印请求 url
        log.info("URL            : {}", url);
        // 打印描述信息
        log.info("Description    : {}", methodDescription);
        // 打印 Http method
        log.info("HTTP Method    : {}", method);
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}", classMethod);
        // 打印请求的 IP
        log.info("IP             : {}", ip);
        // 打印请求入参
        log.info("Request Args   : {}", params);
    }

    /**
     * 在切点之后织入
     *
     * @throws Throwable
     */
    @After("logPrint()")
    public void doAfter() throws Throwable {
        // 接口结束后换行，方便分割查看
        log.info("=========================================== End ===========================================" + LINE_SEPARATOR);
    }

    /**
     * 环绕
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("logPrint()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        // 打印出参
        log.info("Response Args  : {}", result.getClass());
        // 执行耗时
        log.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);
        return result;
    }


    /**
     * 获取切面注解的描述
     *
     * @param joinPoint 切点
     * @return 描述信息
     * @throws Exception
     */
    public String getAspectLogDescription(JoinPoint joinPoint)
            throws Exception {

        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder("");
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description.append(method.getAnnotation(LogPrint.class).description());
                    break;
                }
            }
        }
        return description.toString();
    }

    private String getParams(JoinPoint joinPoint) {
        String params = "";
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                Object arg = joinPoint.getArgs()[i];
                if ((arg instanceof HttpServletResponse) || (arg instanceof HttpServletRequest)
                        || (arg instanceof MultipartFile) || (arg instanceof MultipartFile[])) {
                    continue;
                }
                try {
                    params += JSONObject.toJSONString(joinPoint.getArgs()[i]);
                } catch (Exception e1) {
                    log.error(e1.getMessage());
                }
            }
        }
        return params;
    }

}

