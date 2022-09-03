# 插件

## 1、插件管理
使用插件管理软件:
https://github.com/junegunn/vim-plug
```bash
iwr -useb https://raw.githubusercontent.com/junegunn/vim-plug/master/plug.vim |`
    ni "$(@($env:XDG_DATA_HOME, $env:LOCALAPPDATA)[$null -eq $env:XDG_DATA_HOME])/nvim-data/site/autoload/plug.vim" -Force

```

## 2、配置文件的使用
nvim 的配置文件 win 是放在 `~/AppData/Local/nvim/init.vim` 文件中。


## 3、golang 插件的安装
插件参考：
```http
https://github.com/ray-x/go.nvim
```
