# JAVA 并发机制的底层实现原理
## 1 volatile 的应用
如果一个字段被声明成 volatile ，java 线程内存模型确保所有线程看到的这个变量是一致的

| 术语 | 英文单词 | 描述 |
| :-----: | :-----:| :-----: |
| 内存屏障 | memory barriers |  一组处理器指令，用于实现对内存操作的顺序限制 |
| 缓冲行   | cache line     | CPU 高速缓存中可以分配的最小存储单位。处理器填写缓冲行会加载整个缓存行。|
| 原子操作 | atomic operations | 不可中断的一个或者一系列操作 |
| 缓存行填充 | cache line fill | 当处理器识别到从内存中读取操作数是可缓存的，处理器会读取整个高速缓存行到适当的缓存中 |
| 缓存命中 | cache hit | 如果进行高速缓存填充操作的内存位置仍然是下次处理器访问的地址时，处理器从缓存中读取，而不是从内存中读取 |
| 写命中 | write hit | 当处理将操作数写回到一个内存缓冲的区域时，它首先会检查这个缓存的内存地址是否在缓存行中，如果存在一个有效的缓存行，则处理吕将这个操作数写回到缓存，而不是写回到内存 |
| 写缺失 | write misses the cache | 一个有效的缓存行被写入到不存在的内存区域 |

volatile 的变量在汇编代码里会加入 lock 前缀 

lock 的作用：
1. 将当前缓存行的数据写回到内存中
2. 这个写回内存的操作会使在其它CPU里缓存了该内存地址的数据失效。
3. 一般不会锁总线，而是锁缓存

## 2. synchronized 的实现原理与应用
1.6 以后为了减少获得锁与释放锁带来的性能问题，引入了偏向锁和轻量极锁。JVM 基于进入和退出 monitor 对象来实现方法同步和代码同步的。


三种形式： 
1. 对于普通同步方法，锁的是当前实例对象
2. 对于静态同步方法，锁的是当前类的 Class 对象
3. 对于同步方法块，锁是 Synchronized 括号里的配置对象

### Java 对象头
synchronized 用的锁是存在 java 对象头里的。
锁有四种状态： 无锁状态 -> 偏向锁 -> 轻量级锁 -> 重量级锁 锁只能升级不能降级

  
### 原子操作的实现原理 

CPU 术语定义

| 术语 | 英文 | 解释 |
| :-----------: | :------------: | :-----: |
| 缓存行 | cache line | 缓存的最小操作单位 |
| CPU 流水线 | CPU pipeline | 在 CPU 中由 5-6个不同功能的电路单元组成一条指令处理流水，然后将一条 x86 指令分成  5-6 步后，再由这些电路单元分别执行，这样能实现 在一个 CPU 时钟周期完成一条指令，提高运算速度 |
| 比较并交换 | Compare and Swap | CAS 操作需要两个值，在操作期间上会上一没有发生变化才交换成新值，发生变化了则不交换 |
| 内存顺序冲突 | Memory order violation | 内存顺序冲一般是由假共享引起的，假共享是批多个CPU同时修改同一个缓存行的不同部分而引起的其中一个CPU的操作无效，当出现内存顺序冲突时，CPU必须清空流水线|

#### 实现原子操作
基于对缓存加锁或者对总线加锁的方式来实现多核之间的原子操作。*处理器保证从系统内存中读取或者写入一个字节是原子的，意思是当一个得理器读取 一个字节时，
其它处理器不能访问这个字节的内存地址。pentium 6 以后的处理器能自动保证单处理器对同一个缓存行里进行16/32/64 位的操作是原子的 * 

- 总线锁：

 使用处理器提供的一个 LOCK# 信号，当一个处理器在总线上输出此信号时，其它处理器的请求会
 被阻塞住。
- 缓存锁：

内存区域如果被缓存在处理器的缓存行中，并且在 LOCK 操作期间被锁定，那么当它执行锁操作回写到
内存时，处理器不在总线上声言 LOCK # 信号，而是修改内部的内存地址，并允许它的缓存一致
性机制来保证操作的原子性，因为缓存一致性机制会阻止同时修改由两个以上处理器缓存的内存区域
数据，当其它处理器回写已被锁定的缓存行的数据时，会使缓存行无效。

两种情况下不能使用缓存锁定。

- 操作的数据不能被缓存在处理器内部，或者操作的数据跨多个缓存行
- 有些处理器不支持缓存锁定

## Java 中实现原子操作

java 中可以用锁或者 CAS 的方式实现原子操作

1. 使用循环 CAS 实现原子操作

JVM中的 CAS 操作正是利用了处理器提供的 CMPXCHG 指令实现的。自旋 CAS 实现的基本
思路就是循环进行 CAS 操作直到成功为止。

CAS 的三个问题

- ABA 问题 。

- 循环时间长开销大

- 只能保证一个共享变量的原子操作。

2. 使用锁的方式实现原子操作




