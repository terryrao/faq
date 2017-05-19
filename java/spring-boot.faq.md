#jpa PropertyReferenceException: No property Book found for User异常
    解决方法 
    如果采用JPA默认的命名规范，应该写成 
    User findByLoginname(String loginName);   这样会默认找loginname 属性 
    否则findByLoginName 这样找的是Login对象的name属性，如果不想改名字最好就加上 @Query 注解写清楚 
@http://blog.csdn.net/u022812849/article/details/44277355
