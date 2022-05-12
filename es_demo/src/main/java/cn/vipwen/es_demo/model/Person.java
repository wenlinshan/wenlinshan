package cn.vipwen.es_demo.model;

import cn.vipwen.es_demo.constants.EsConsts;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户实体类
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-20 17:29
 */
@Document(indexName = EsConsts.INDEX_NAME,  shards = 1, replicas = 0)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 名字
     */
    @Field(type = FieldType.Keyword)
    private String name;

    /**
     * 国家
     */
    @Field(type = FieldType.Keyword)
    private String country;

    /**
     * 年龄
     */
    @Field(type = FieldType.Integer)
    private Integer age;

    /**
     * 生日
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime birthday;

    /**
     * 介绍
     */
    @Field(type = FieldType.Text, analyzer = "ik_smart")
    private String remark;
}
