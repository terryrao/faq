#常用linux 
##1.设置主机名
	hostnamectl set-hostname 主机名
	hostnamectl --prettty
	hostnamectl --static
	hostnamectl --transient
	vim /etc/hosts  加入 127.0.0.1 主机名  
	reboot
	
##2.查看ip
	ip addr 
	vim /etc/sysconfig/network-scripts/ifcfg-enp0s3
	添加以或者修改以下参数
		BOOTPROTO=static
		IPADDR=192.168.0.111
		NETMASK=255.255.255.0
		GATEWAY=192.168.0.1
		DNS1=202.96.134.33
		NM_CONTROLLED=no
		BOOT=yes
	
	systemctl restart network.service
## 3.免密登录
	vim /etc/ssh/sshd_config
	将 RSAAuthentication yes PubkeyAuthentication yes 两行的注释去掉
	看清楚授权文件
	AuthorizedKeysFile      .ssh/authorized_key
   ssh-keygen
   cat ~/.ssh/id_rsa.pub >>~/.ssh/authorized_key
   chmod 600 authorized_key
   cat ~/.ssh/id_rsa.pub | ssh 远程用户名@远程服务器ip 'cat - 》 ~/.ssh/authorized_keys'
   //没有设置dns的情况下可以将各个机器的主机名：ip 加入到自己的hosts中  这样就可以使用主机名登录了
   
## 4.centos 默认的防火墙是firewalld 
  关闭：systemctl stop firewalld.service
  禁用: systemctl disable firewalld.service
 
 
 
 
 
 
 