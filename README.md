#### Redis 字符串

| 命令 | 说明 | 备注 |
| ---- | ----- | ----- |
| set key value | 设置键值对 | 最常用的写入命令 |
| get key | 通过键获取值 | 最常用的读取命令 |
| del key |  通过 key ，删除键值对 | 删除命令，返回删除数，注意，它是一个通用命令，在其他数据结构中，也可以使用它 |
| strlen key  |  求 key 指向字符串的长度  |  返回长度 |
| getset key value  |  修改原来 key 的对应值，并将旧值返回 | 如果原来值为空，则返回空，并设置新值 |
| getrange key start end  |  获取子串 | 记 字符串的长度为 len ，把字符串看作一个数组，而Redis 是以 0 开始计数的，所有start 和 end 的取值范围 为 0 到 len-1  |
| append key value | 将新的字符串 value 加入到 原来 key 指向的字符串末 | 返回 key 指向 新字符串的长度 |

~~~
C:\Users\oukele>redis-cli
127.0.0.1:6379> set name oukele
OK
127.0.0.1:6379> get name
"oukele"
127.0.0.1:6379> getset name oukelelelele
"oukele"
127.0.0.1:6379> getrange name 0 4
"oukel"
127.0.0.1:6379> append name hahahaha
(integer) 20
127.0.0.1:6379> strlen name
(integer) 20
127.0.0.1:6379> get name
"oukelelelelehahahaha"
127.0.0.1:6379> del name
(integer) 1
127.0.0.1:6379> get name
(nil)

~~~

#### Redis 的简单运算

| 命令 | 说明 | 备注 |
| ---- | ----- | ----- |
| incr key | 在原字段上加 1 |  只能对整数操作 |
| incrby key increment | 在原字段上加上整数 (increment) |  只能对整数操作|
| decr key   |  在原字段上减 1 | 只能对整数操作 |
| decrby key decrement | 在原字段上 减去 整数 (decrement) | 只能对整数操作 |
| incrbyfloat key increment | 在原字段上加上浮点数 (increment) | 可以操作浮点数 或者 整数 |
~~~
C:\Users\oukele>redis-cli
127.0.0.1:6379> set number 8
OK
127.0.0.1:6379> incr number
(integer) 9
127.0.0.1:6379> incrby number 10
(integer) 19
127.0.0.1:6379> decr number
(integer) 18
127.0.0.1:6379> decrby number 10
(integer) 8
127.0.0.1:6379> incrbyfloat number 0.8
"8.800000000000001"
127.0.0.1:6379>
~~~
#### Redis 数据结构 -- 哈希

| 命令 | 说明 | 备注 |
| --- | ---- | ---- |
| hdel key field1 [ field2..... ] | 删除 hash 结构中的 某个 (些 ) 字段 | 可以进行多个字段的删除 |
| hexists key field | 判断 hash 结构 中 是否存在 field 字段 | 存在返回 1 ，否则返回 0 |
| hgetall key | 获取所有hash结构中的键值 | 返回键 和 值 |
| hincrby key field increment| 指定给hash 结构 中的某一个字段加上一个整数 | 要求该字段也是整数字符串 |
| hincrbyfloat key field increment | 指定给hash 结构 中的某一字段加上一个浮点数 | 要求该字段也是数字型字符串 |
| hkeys key | 返回 hash 中 所有的键 |  -- |
| hlen key | 返回hash 中 键值对的数量 | -- |
| hmget key field1 [field2 .... ] | 返回 hash 中 指定 的键 的值 ，可以是多个  |  依次返回 |
| hmest key field1 value1 [field2 ....] | 在 hash 结构中 设置多个键值对 | 单个设值 |
| hset key filed value | 在 hash 结构中 设置键值对  | 单个设值 |
| hsetnx key field value |  当hash 结构 中 不存在对应的键值，才设置值 | -- |
| hvals key | 获取hash 结构中所有的值 | -- |
~~~
127.0.0.1:6379> hmset hash f1 val1 f2 val2
OK
127.0.0.1:6379> hset hash f3 6
(integer) 1
127.0.0.1:6379> hexists hash f2
(integer) 1
127.0.0.1:6379> hgetall hash
1) "f1"
2) "val1"
3) "f2"
4) "val2"
5) "f3"
6) "6"
127.0.0.1:6379> hincrby hash f3 2
(integer) 8
127.0.0.1:6379> hincrbyfloat hash f3 0.8
"8.800000000000001"
127.0.0.1:6379> hkeys hash
1) "f1"
2) "f2"
3) "f3"
127.0.0.1:6379> hlen hash
(integer) 3
127.0.0.1:6379> hmget hash f1 f2
1) "val1"
2) "val2"
127.0.0.1:6379> hsetnx key f4 val4
(integer) 1
127.0.0.1:6379> hvals hash
1) "val1"
2) "val2"
3) "8.800000000000001"
127.0.0.1:6379> hdel hash f1
(integer) 1
127.0.0.1:6379> hkeys hash
1) "f2"
2) "f3"
~~~
#### 角色hash 结构

| role_1  | role_1  |
|---| --- |
| field | value |
| id | 001 |
| roleName | oukele |
| age | 19 |
~~~
127.0.0.1:6379> hmset role_1 id: 001 roleName: oukele age: 19
OK
127.0.0.1:6379> hgetall role_1
1) "id:"
2) "001"
3) "roleName:"
4) "oukele"
5) "age:"
6) "19"
127.0.0.1:6379> hget role_1 roleName:
"oukele"
127.0.0.1:6379> hlen role_1
(integer) 3

~~~

#### Redis 数据结构 --链表（linked-list）

| 命令 | 说明  | 备注 |
| :------| :-----: | :----- |
| lpush key node1 [node2 ...] | 把节点 node1 加入到 链表最左边 | 如果是 node1、node2 ....noden 这样加入，那么链表开头从左到右的顺序是 noden ... node2、node1 |
| rpush key nodel [dode2 ...] | 把节点 node1 加入到 链表最右边 | 如果是 nodel、node2 .... noden 这样加入，那么链表结尾从左到右的顺序是 node1、node2...noden |
| lindex key index | 读取下标为 index 的节点 | 返回节点字符串，从0开始算 |
| llen key | 求链表的长度 | 返回链表节点数 |
| lpop key | 删除左边第一个节点，并将其返回 | -- |
| linsert key [befor after]  piovt  |  插入一个节点 node ，并且可以指定在值为pivot的节点的前面(before)或者 后面 (after) |  如果 list 不存在，则报错，如果没有值为对应pivot的，也会插入失败返回 -1 |
| lpushx list node | 如果存在key为list的链表，则插入节点node，并且作为从左到右的 第一个节点 | 如果list 不存在 则失败 |
| rpushx list node | 如果存在key为 list 的链表，则插入节点node，并且作为从左到右的 最后一个节点 | 如果 list 不存在 则失败 |
| lrange list start end | 获取链表 list 从 start下标 到 end下标的节点值 | 包含 start 和 end 下标的值 |
| lrem list count value | 如果 count 为 0，则删除所有值等于value的节点，如果count 不是 0，则先对count取绝对值，假设记为abs，然后从左到右删除不大于 abs 个 等于 value的节点 | 注意，count 为整数，如果是负数，则Redis会先求取其绝对值，然后传递到后台操作 |
| lset key index node | 设置列表下标为index的节点的值为node  |  -- |
| ltrim key start stop | 修剪链表，只保留从start 到 stop 的区间的节点，其余的都删除掉 | 包含 start 和 end 的下标的节点会保留 | 
~~~
C:\Users\oukele>redis-cli
127.0.0.1:6379> lpush role oukele dakele xiaokele
(integer) 3
127.0.0.1:6379> lindex role 0
"xiaokele"
127.0.0.1:6379> rpush role oukele1
(integer) 4
127.0.0.1:6379> lindex role 0
"xiaokele"
127.0.0.1:6379> llen role
(integer) 4
127.0.0.1:6379> lpop role
"xiaokele"
127.0.0.1:6379> rpop role
"oukele1"
127.0.0.1:6379> llen role
(integer) 2
127.0.0.1:6379> linsert role before dakele dadakele
(integer) 3
127.0.0.1:6379> linsert role after dakele dadadakele
(integer) 4
127.0.0.1:6379> lrange role 0 10
1) "dadakele"
2) "dakele"
3) "dadadakele"
4) "oukele"
127.0.0.1:6379> lpush role lala wuwu oooo
(integer) 7
127.0.0.1:6379> lrange role 0 10
1) "oooo"
2) "wuwu"
3) "lala"
4) "dadakele"
5) "dakele"
6) "dadadakele"
7) "oukele"
127.0.0.1:6379> lrem role 3 dakele
(integer) 1
127.0.0.1:6379> lrange role 0 10
1) "oooo"
2) "wuwu"
3) "lala"
4) "dadakele"
5) "dadadakele"
6) "oukele"
127.0.0.1:6379> ltrim role 3  6
OK
127.0.0.1:6379> lrange role 0 10
1) "dadakele"
2) "dadadakele"
3) "oukele"
127.0.0.1:6379>
~~~

~~~
  //缓存管理器
   @Bean
    public RedisCacheManager redisCacheManager() {
        //这里 设置 缓存管理器的配置
        RedisCacheConfiguration configuration = RedisCacheConfiguration
                .defaultCacheConfig()
                .computePrefixWith(cacheName -> cacheName)
                //头部使用字符串序列化
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.string()))
                //有四种方法序列化、默认jdk的序列化
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()));

        return RedisCacheManager.builder(redisConnectionFactory()).cacheDefaults(configuration).build();

    }

~~~

 
