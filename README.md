# Lucene Tutorials

Lucene 使用教程。

## 使用技术

- 使用 gradle build
- 使用 lucene 8.4.0 版本
- IDE 工具使用 IntelliJ IDEA

## 代码说明

### 最简单的使用

见：./src/marshal/lucene/simple

-  建立索引，IndexDemo.java
    - 使用目前常用的分词 IK 分词
    - IK 分词从 2012 年后不再更新，当时仅支持 Lucene 4.x
    - 示例中内部类是为了封装 IK api 兼容 Lucene 8.x
- 单字段搜索，SearchDemo.java
    - 需要执行 IndexDemo.java 后才能使用
    
### IK 分词兼容 Lucene 8.x 的 API

见： ./src/marshal/lucene/ik

### 第三方中文分词分析器的比较和评估

见：./src/marshal/lucene/analyzer
 
见 TODO.md 列表中的第三方中文分词。
