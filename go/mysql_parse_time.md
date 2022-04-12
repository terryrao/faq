# Go faq

## go mysql parse time.Time error
如果 mysql 有个字段 a 的类型为 datetime ，对应到 go 的实体类的某个 struct的属性 A 的类型为 `Time.Time` ，有可能会的以下错误：
```shell
unsupported driver -> Scan pair: []uint8 -> *time.Time
```
解决方法是在 `mysql` 连接里加上 `parseTime=True` 这个选项。

ref: https://stackoverflow.com/questions/29341590/how-to-parse-time-from-database/29343013#29343013

## gorm 中 find 与  first 的区别 
find 不会返回  ErrNotFoundRecord 的错误


