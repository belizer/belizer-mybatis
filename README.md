# belizer-mybatis
gupao-exercise

# v1版本
#边界：实现功能即可
#思路 ：理清mybatis从获取SqlSession到操作数据库获取结果整个过程中都发生了哪些操作
### 1，读取配置文件
####a,通过配置文件获取的信息有数据库信息，包括数据库驱动，地址，用户名和密码，还有mapper文件信息
    
### 2，获取SqlSession
####a，mybatis通过工厂模式获取sqlsession，这里我直接new
### 3,获取Mapper
####a，mapper是一个接口，获取他的对象得是代理类的对象
### 4，调用mapper方法
####a，获取到sql语句，把sql传入Executor
####b，Executor使用jdbc执行sql
#####<1>使用Jdbc执行sql，那么Executor必须有数据库的驱动等信息，这些信息怎么过来呢？或者我直接从SqlSession或Configuration获取Connection？那种是比较好的方式呢？
######目前先使用Configuration传递过来
#####<2>如果想要执行查询语句，先定义一个doQuery方法,如果需要传入多个参数，可以把他封装成一个查询参数对象