# 概述

<br/>

## 1、MongoDB

<br/>

- MongoDB 是一个基于分布式文件存储的数据库。由 C++ 语言编写。
- MongoDB 是一个介于关系数据库和非关系数据库之间的产品，是非关系数据库当中功能最丰富，最像关系数据库的。
- 在 MongoDB 中，数据存储为文档（Document）。文档本质上是一种类似于 JSON 的数据（BSON）。



> In MongoDB, data is stored as documents. These documents are stored in MongoDB in JSON (JavaScript Object Notation) format.
>
> 在 MongoDB 中，数据存储为文档。 这些文档以 JSON 格式存储在 MongoDB 中。



- MongoDB 官方网站：[https://www.mongodb.com](https://www.mongodb.com)

---

## 2、应用场景

<br/>

- 游戏场景，使用 MongoDB 存储游戏用户信息。用户的装备、 积分等直接以内嵌文档的形式存储，方便查询、更新。
- 物流场景，使用 MongoDB 存储订单信息。订单状态在运送过程中会不断更新，以 MongoDB 内嵌数组的形式来存储，一次查询就能将订单所有的变更读取出来。
- 社交场景，使用 MongoDB 存储存储用户信息以及用户发表的朋友圈信息，通过地理位置索引实现附近的人、地点等功能。
- 物联网场景，使用 MongoDB 存储所有接入的智能设备信息以及设备汇报的日志信息，并对这些信息进行多维度的分析。
- 视频直播，使用 MongoDB 存储用户信息、“礼物”信息等。
- ······

---

## 3、优点

<br/>

- 性能优越。但是性能优越并不代表可以使用 MongoDB 一味地替换一些产品，与业务场景完美匹配的才是最好的。
- 弱一致性保证用户访问速度。依据 CAP 定理，在分布式系统中，CAP（一致性、可用性、分隔容忍）最多只能满足其中的两点。MongoDB 只能做到最终一致性（弱一致性），总会有“不一致时间窗口”，这是由于 MongoDB 在更新操作的时候，需要将同样的更新复制到副本节点当中，而这段时间无法保证用户读到的一定是最新数据，只能保证返回目前大多数节点的所持有的数据，而不一定是最新的数据。
- 以文档形式存储，更便捷获取数据。对于那些层级形式的数据，如果要将这样的数据使用扁平式的、**表状的结构**（关系型数据库）来保存数据，无论是在查询还是获取数据时都十分困难。
  - 举例：字典项，一个英文单词，在英文辞典中针对这一个单词，会有“发音”、“词性”、“解释”、“例句”等。如果将这个字典项以关系型数据库的表存储，那么可能会涉及到表关系，依靠外键、主键在多表之间建立关系（比如：一个单词有多个“词性”、“解释”等），这种做法虽然是属于正常的计算机思维，但是会造成大量的不便，每将一个完整的字典项查出来，都得使用`JOIN`等相关操作。所以，还是希望能像现实中的辞典一样，字典项直接以一个整体的形式存储。
- MongoDB 是一个面向文档的数据库，它的功能丰富、齐全，理论上完全可以替代 MySQL 等一些关系型数据库。MongoDB 使用 JSON 的变种 BSON 作为内部存储的格式和语法。针对 MongoDB 的操作都使用 JSON 风格语法，客户端提交或接收的数据都使用 JSON 形式来展现。相对于SQL来说，更加直观，容易理解和掌握。
- MongoDB 是一个 schema-free 的文档数据库。数据存储格式自由度很高。
- 内置 GridFS，是一个出色的分布式文件系统，支持海量的数据存储。
- ······

---

## 4、缺点

<br/>

- 对事务支持不友好。很多 NoSQL 都有这一特点。
- 占用空间过大。
- ······