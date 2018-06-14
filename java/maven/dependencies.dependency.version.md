# dependencies.dependency.version error

     <parent>
     		<groupId>com.tx</groupId>
     		<artifactId>tx-insfra-parent</artifactId>
     		<version>0.0.1-SNAPSHOT</version>
     		<relativePath/> <!-- lookup parent from repository -->
     </parent>
     
用 maven install 会报如下异常

    [ERROR]
    [ERROR]   The project com.tx:tx-xxx-deposit:0.0.1-SNAPSHOT (\tx-xxx\tx-xxx-deposit\pom.xml) has 1 error
    [ERROR]     'dependencies.dependency.version' for com.tx:tx-xxx-core:jar is missing. @ line 30, column 15
    [ERROR]

改为 
    
     <parent>
         		<groupId>com.tx</groupId>
         		<artifactId>tx-xxx-parent</artifactId>
         		<version>0.0.1-SNAPSHOT</version>
         		<relativePath>../pom.xml</relativePath> <!-- lookup parent from repository -->
     </parent>
 
 打包成功
 
 删除 relativePath
 
    <parent>
            <groupId>com.tx</groupId>
            <artifactId>tx-xxx-parent</artifactId>
            <version>0.0.1-SNAPSHOT</version>
    </parent>
    
打包成功