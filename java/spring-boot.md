# PART III.使用spring-boot

这一部分将会进一步了解更多怎样使用spring-boot的细节。此章覆盖了诸如构建系统、自动化配置和怎么运行你的程序的话题。我们会给出一些 spring boot 的最佳实践。当然这里并没有什么特别的东西（这只是一些你可以消费的库），这里只有很少的建议需要跟随，它可以让你的开发过程更简单。
如果你仅仅只是快速上手 spring boot ，你可以读 Getting Start 导读。
    
##13. 构建系统

强烈建议你选择一个构建系统来提供依赖管理，使用它可以用版本发布到 maven central 仓库中。我们建议你使用 maven 或者 gradle ，可能也会使用其它的构建系统（比如 ant ），但我们不会提供特殊支持。
 
###13.1 依赖管理

每一个版本的 spring boot 会列一个它所支持的依赖列表。事实上你不需要在你的构建配置中提供这些依赖的版本，因为 spring boot 为你管理了这些依赖
    
    如果需要的话你也可以指定依赖的版本

这个清单包括所有你在 spring boot 里用到的 spring 模块，就像一个精简的第三方库一样。这个清单就像一个标准的材料清单 (spring-boot-dependencies) 对 maven 和 gradle 都适用。

    每一个 spring boot 版本都有一个相关联的 spring 框架的主要版本，因此强烈建议不要自己指定版本

##13.2 Maven
Maven 用户可以继承 spring-boot-start-parent 工程获得相应的默认配置。这个父工程提供下列特性：

>java 1.6 做为默认的编译层级
>utf-8 代码字符集
>一个依赖管理部分，能过继承 ```spring-boot-dependencies``` pom 允许你忽略公共依赖的 ```<version> ``` 标签
>精简的资源过滤器
>精简的 plugin 配置
>为 ```application.properties``` 和 ```application.yml``` 提供智能过虑包括特定的配置文件（如 ```application-foo.properties``` 和 ```application-foo.yml``` ）。

最后一点，既然默认配置接收 spring 类型的占位符 ```${}``` ，那maven的就变成使用 ```@..@```占位符了（你可以在 maven properties ```resource.delimiter``` 里重写）


@ImportResource 加载xml文件 
 @EnableAutoConfiguration @SpringBootApplication 自动配置
 You should only ever add one @EnableAutoConfiguration annotation. We generally recommend that you add it to your primary @Configuration class.
@ComponentScan

 @SpringBootApplication 等价于使用@Configuration @EnableAutoConfiguration @ComponentScan