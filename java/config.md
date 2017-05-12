#1.为某一个单独的类增加日志 
##apiLog 
log4j.logger.com.phoenix.admin.api.APIController=debug,myLog
log4j.appender.myLog=org.apache.log4j.DailyRollingFileAppender
log4j.appender.myLog.File=${catalina.base}/p2padmin/apiLog.log
log4j.appender.myLog.layout=org.apache.log4j.PatternLayout
log4j.appender.myLog.layout.ConversionPattern=%-d{yyyy-MM-dd HH:mm:ss} %p[%c{3}] -%m%n

##控制台日志
log4j.rootLogger=error,console
log4j.logger.com.phoenix=debug
log4j.appender.console=org.apache.log4j.ConsoleAppender
log4j.appender.console.layout=org.apache.log4j.PatternLayout
log4j.appender.console.layout.ConversionPattern=%-d{yyyy-MM-dd HH:mm:ss} %p[%c{3}] -%m%n
