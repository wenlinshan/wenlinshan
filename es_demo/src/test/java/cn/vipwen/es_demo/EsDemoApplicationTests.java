package cn.vipwen.es_demo;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.vipwen.es_demo.constants.EsConsts;
import cn.vipwen.es_demo.model.Person;
import cn.vipwen.es_demo.model.SkuInfo;
import cn.vipwen.es_demo.repository.PersonRepository;
import cn.vipwen.es_demo.repository.SkuInfoRepository;
import cn.vipwen.es_demo.service.ElasticSearchService;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
class EsDemoApplicationTests {
    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Autowired
    PersonRepository personRepository;
    @Autowired
    SkuInfoRepository skuInfoRepository;
    @Autowired
    BulkProcessor bulkProcessor;

    @Autowired
    ElasticSearchService elasticSearchService;

    @Test
    void contextLoads() {
    }

    @Test
    public void createIndex() {
        //创建索引
        try {
            boolean exists = restHighLevelClient.indices().exists(new GetIndexRequest(EsConsts.SKU_INDEX_NAME), RequestOptions.DEFAULT);
            if (exists) {
                DeleteIndexRequest qyf = new DeleteIndexRequest(EsConsts.SKU_INDEX_NAME);
                restHighLevelClient.indices().delete(qyf, RequestOptions.DEFAULT);
            }
            CreateIndexRequest qyf = new CreateIndexRequest(EsConsts.SKU_INDEX_NAME);
            restHighLevelClient.indices().create(qyf, RequestOptions.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        //创建索引
        try {
            boolean exists = restHighLevelClient.indices().exists(new GetIndexRequest(EsConsts.INDEX_NAME), RequestOptions.DEFAULT);
            if (exists) {
                DeleteIndexRequest qyf = new DeleteIndexRequest(EsConsts.INDEX_NAME);
                restHighLevelClient.indices().delete(qyf, RequestOptions.DEFAULT);
            }
            CreateIndexRequest qyf = new CreateIndexRequest(EsConsts.INDEX_NAME);
            restHighLevelClient.indices().create(qyf, RequestOptions.DEFAULT);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delTest1() {
        //创建索引
        try {
            boolean exists = restHighLevelClient.indices().exists(new GetIndexRequest(EsConsts.INDEX_NAME), RequestOptions.DEFAULT);
            if (exists) {
                DeleteIndexRequest qyf = new DeleteIndexRequest(EsConsts.INDEX_NAME);
                restHighLevelClient.indices().delete(qyf, RequestOptions.DEFAULT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delTest2() {
        //创建索引
        try {
            boolean exists = restHighLevelClient.indices().exists(new GetIndexRequest(EsConsts.SKU_INDEX_NAME), RequestOptions.DEFAULT);
            if (exists) {
                DeleteIndexRequest qyf = new DeleteIndexRequest(EsConsts.SKU_INDEX_NAME);
                restHighLevelClient.indices().delete(qyf, RequestOptions.DEFAULT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        Person person = new Person(5L, "刘备", "蜀国", 18, DateUtil.parse("1990-01-02 03:04:05", DatePattern.NORM_DATETIME_FORMATTER), "刘备（161年－223年6月10日），即汉昭烈帝（221年－223年在位），又称先主，字玄德，东汉末年幽州涿郡涿县（今河北省涿州市）人，西汉中山靖王刘胜之后，三国时期蜀汉开国皇帝、政治家。\n刘备少年时拜卢植为师；早年颠沛流离，备尝艰辛，投靠过多个诸侯，曾参与镇压黄巾起义。先后率军救援北海相孔融、徐州牧陶谦等。陶谦病亡后，将徐州让与刘备。赤壁之战时，刘备与孙权联盟击败曹操，趁势夺取荆州。而后进取益州。于章武元年（221年）在成都称帝，国号汉，史称蜀或蜀汉。《三国志》评刘备的机权干略不及曹操，但其弘毅宽厚，知人待士，百折不挠，终成帝业。刘备也称自己做事“每与操反，事乃成尔”。\n章武三年（223年），刘备病逝于白帝城，终年六十三岁，谥号昭烈皇帝，庙号烈祖，葬惠陵。后世有众多文艺作品以其为主角，在成都武侯祠有昭烈庙为纪念。");
        Person save = personRepository.save(person);
    }

    @Test
    public void testSave() {
        skuInfoRepository.save(new SkuInfo());
    }

    /**
     * 测试批量新增
     */
    @Test
    public void saveList() {


        List<Person> personList = Lists.newArrayList();
        personList.add(new Person(15L, "曹操", "魏国", 20, DateUtil.parse("1988-01-02 03:04:05", DatePattern.NORM_DATETIME_FORMATTER), "曹操（155年－220年3月15日），字孟德，一名吉利，小字阿瞒，沛国谯县（今安徽亳州）人。东汉末年杰出的政治家、军事家、文学家、书法家，三国中曹魏政权的奠基人。\n曹操曾担任东汉丞相，后加封魏王，奠定了曹魏立国的基础。去世后谥号为武王。其子曹丕称帝后，追尊为武皇帝，庙号太祖。\n东汉末年，天下大乱，曹操以汉天子的名义征讨四方，对内消灭二袁、吕布、刘表、马超、韩遂等割据势力，对外降服南匈奴、乌桓、鲜卑等，统一了中国北方，并实行一系列政策恢复经济生产和社会秩序，扩大屯田、兴修水利、奖励农桑、重视手工业、安置流亡人口、实行“租调制”，从而使中原社会渐趋稳定、经济出现转机。黄河流域在曹操统治下，政治渐见清明，经济逐步恢复，阶级压迫稍有减轻，社会风气有所好转。曹操在汉朝的名义下所采取的一些措施具有积极作用。\n曹操军事上精通兵法，重贤爱才，为此不惜一切代价将看中的潜能分子收于麾下；生活上善诗歌，抒发自己的政治抱负，并反映汉末人民的苦难生活，气魄雄伟，慷慨悲凉；散文亦清峻整洁，开启并繁荣了建安文学，给后人留下了宝贵的精神财富，鲁迅评价其为“改造文章的祖师”。同时曹操也擅长书法，唐朝张怀瓘在《书断》将曹操的章草评为“妙品”。"));
        personList.add(new Person(16L, "孙权", "吴国", 19, DateUtil.parse("1989-01-02 03:04:05", DatePattern.NORM_DATETIME_FORMATTER), "孙权（182年－252年5月21日），字仲谋，吴郡富春（今浙江杭州富阳区）人。三国时代孙吴的建立者（229年－252年在位）。\n孙权的父亲孙坚和兄长孙策，在东汉末年群雄割据中打下了江东基业。建安五年（200年），孙策遇刺身亡，孙权继之掌事，成为一方诸侯。建安十三年（208年），与刘备建立孙刘联盟，并于赤壁之战中击败曹操，奠定三国鼎立的基础。建安二十四年（219年），孙权派吕蒙成功袭取刘备的荆州，使领土面积大大增加。\n黄武元年（222年），孙权被魏文帝曹丕册封为吴王，建立吴国。同年，在夷陵之战中大败刘备。黄龙元年（229年），在武昌正式称帝，国号吴，不久后迁都建业。孙权称帝后，设置农官，实行屯田，设置郡县，并继续剿抚山越，促进了江南经济的发展。在此基础上，他又多次派人出海。黄龙二年（230年），孙权派卫温、诸葛直抵达夷州。\n孙权晚年在继承人问题上反复无常，引致群下党争，朝局不稳。太元元年（252年）病逝，享年七十一岁，在位二十四年，谥号大皇帝，庙号太祖，葬于蒋陵。\n孙权亦善书，唐代张怀瓘在《书估》中将其书法列为第三等。"));
        personList.add(new Person(17L, "诸葛亮", "蜀国", 16, DateUtil.parse("1992-01-02 03:04:05", DatePattern.NORM_DATETIME_FORMATTER), "诸葛亮（181年-234年10月8日），字孔明，号卧龙，徐州琅琊阳都（今山东临沂市沂南县）人，三国时期蜀国丞相，杰出的政治家、军事家、外交家、文学家、书法家、发明家。\n早年随叔父诸葛玄到荆州，诸葛玄死后，诸葛亮就在襄阳隆中隐居。后刘备三顾茅庐请出诸葛亮，联孙抗曹，于赤壁之战大败曹军。形成三国鼎足之势，又夺占荆州。建安十六年（211年），攻取益州。继又击败曹军，夺得汉中。蜀章武元年（221年），刘备在成都建立蜀汉政权，诸葛亮被任命为丞相，主持朝政。蜀后主刘禅继位，诸葛亮被封为武乡侯，领益州牧。勤勉谨慎，大小政事必亲自处理，赏罚严明；与东吴联盟，改善和西南各族的关系；实行屯田政策，加强战备。前后六次北伐中原，多以粮尽无功。终因积劳成疾，于蜀建兴十二年（234年）病逝于五丈原（今陕西宝鸡岐山境内），享年54岁。刘禅追封其为忠武侯，后世常以武侯尊称诸葛亮。东晋政权因其军事才能特追封他为武兴王。\n诸葛亮散文代表作有《出师表》《诫子书》等。曾发明木牛流马、孔明灯等，并改造连弩，叫做诸葛连弩，可一弩十矢俱发。诸葛亮一生“鞠躬尽瘁、死而后已”，是中国传统文化中忠臣与智者的代表人物。"));
        Iterable<Person> people = personRepository.saveAll(personList);
        List<IndexRequest> indexRequests = new ArrayList<>();
        personList.forEach(p -> {

            IndexRequest request = new IndexRequest(EsConsts.INDEX_NAME);
            //填充id
            request.id(p.getId() + "");
            //先不修改id
            request.source(JSON.toJSONString(p), XContentType.JSON);
            request.opType(DocWriteRequest.OpType.CREATE);
            bulkProcessor.add(request);
        });

    }

    /**
     * 测试更新
     */
    @Test
    public void update() {
        personRepository.findById(1L).ifPresent(person -> {
            person.setRemark(person.getRemark() + "\n更新更新更大大大新更新更新");
            Person save = personRepository.save(person);
        });
    }

    /**
     * 测试普通查询，按生日倒序
     */
    @Test
    public void select() {
        personRepository.findAll(Sort.by(Sort.Direction.DESC, "birthday")).forEach(person -> log.info("{} 生日: {}", person.getName(), person.getBirthday()));
    }

    /**
     * 测试普通查询，按生日倒序
     */
    @Test
    public void del() {
        personRepository.deleteAll();
    }


    /**
     * 测试普通查询，按生日倒序
     */
    @Test
    public void findById() {
        personRepository.findById(1L).ifPresent(p -> System.out.println(p));
    }


    @Test
    public void searchParam() throws IOException {
        Map<String, Long> birthday = elasticSearchService.dateHistogram(null, "birthday", DateHistogramInterval.DAY, EsConsts.INDEX_NAME);
        System.out.println(birthday);
    }

    @Test
    public void bach() throws IOException {
        List<Person> personList = Lists.newArrayList();
        personList.add(new Person(15L, "曹操", "魏国", 20, DateUtil.parse("1988-01-02 03:04:05", DatePattern.NORM_DATETIME_FORMATTER), "曹操（155年－220年3月15日），字孟德，一名吉利，小字阿瞒，沛国谯县（今安徽亳州）人。东汉末年杰出的政治家、军事家、文学家、书法家，三国中曹魏政权的奠基人。\n曹操曾担任东汉丞相，后加封魏王，奠定了曹魏立国的基础。去世后谥号为武王。其子曹丕称帝后，追尊为武皇帝，庙号太祖。\n东汉末年，天下大乱，曹操以汉天子的名义征讨四方，对内消灭二袁、吕布、刘表、马超、韩遂等割据势力，对外降服南匈奴、乌桓、鲜卑等，统一了中国北方，并实行一系列政策恢复经济生产和社会秩序，扩大屯田、兴修水利、奖励农桑、重视手工业、安置流亡人口、实行“租调制”，从而使中原社会渐趋稳定、经济出现转机。黄河流域在曹操统治下，政治渐见清明，经济逐步恢复，阶级压迫稍有减轻，社会风气有所好转。曹操在汉朝的名义下所采取的一些措施具有积极作用。\n曹操军事上精通兵法，重贤爱才，为此不惜一切代价将看中的潜能分子收于麾下；生活上善诗歌，抒发自己的政治抱负，并反映汉末人民的苦难生活，气魄雄伟，慷慨悲凉；散文亦清峻整洁，开启并繁荣了建安文学，给后人留下了宝贵的精神财富，鲁迅评价其为“改造文章的祖师”。同时曹操也擅长书法，唐朝张怀瓘在《书断》将曹操的章草评为“妙品”。"));
        personList.add(new Person(16L, "孙权", "吴国", 19, DateUtil.parse("1989-01-02 03:04:05", DatePattern.NORM_DATETIME_FORMATTER), "孙权（182年－252年5月21日），字仲谋，吴郡富春（今浙江杭州富阳区）人。三国时代孙吴的建立者（229年－252年在位）。\n孙权的父亲孙坚和兄长孙策，在东汉末年群雄割据中打下了江东基业。建安五年（200年），孙策遇刺身亡，孙权继之掌事，成为一方诸侯。建安十三年（208年），与刘备建立孙刘联盟，并于赤壁之战中击败曹操，奠定三国鼎立的基础。建安二十四年（219年），孙权派吕蒙成功袭取刘备的荆州，使领土面积大大增加。\n黄武元年（222年），孙权被魏文帝曹丕册封为吴王，建立吴国。同年，在夷陵之战中大败刘备。黄龙元年（229年），在武昌正式称帝，国号吴，不久后迁都建业。孙权称帝后，设置农官，实行屯田，设置郡县，并继续剿抚山越，促进了江南经济的发展。在此基础上，他又多次派人出海。黄龙二年（230年），孙权派卫温、诸葛直抵达夷州。\n孙权晚年在继承人问题上反复无常，引致群下党争，朝局不稳。太元元年（252年）病逝，享年七十一岁，在位二十四年，谥号大皇帝，庙号太祖，葬于蒋陵。\n孙权亦善书，唐代张怀瓘在《书估》中将其书法列为第三等。"));
        personList.add(new Person(17L, "诸葛亮", "蜀国", 16, DateUtil.parse("1992-01-02 03:04:05", DatePattern.NORM_DATETIME_FORMATTER), "诸葛亮（181年-234年10月8日），字孔明，号卧龙，徐州琅琊阳都（今山东临沂市沂南县）人，三国时期蜀国丞相，杰出的政治家、军事家、外交家、文学家、书法家、发明家。\n早年随叔父诸葛玄到荆州，诸葛玄死后，诸葛亮就在襄阳隆中隐居。后刘备三顾茅庐请出诸葛亮，联孙抗曹，于赤壁之战大败曹军。形成三国鼎足之势，又夺占荆州。建安十六年（211年），攻取益州。继又击败曹军，夺得汉中。蜀章武元年（221年），刘备在成都建立蜀汉政权，诸葛亮被任命为丞相，主持朝政。蜀后主刘禅继位，诸葛亮被封为武乡侯，领益州牧。勤勉谨慎，大小政事必亲自处理，赏罚严明；与东吴联盟，改善和西南各族的关系；实行屯田政策，加强战备。前后六次北伐中原，多以粮尽无功。终因积劳成疾，于蜀建兴十二年（234年）病逝于五丈原（今陕西宝鸡岐山境内），享年54岁。刘禅追封其为忠武侯，后世常以武侯尊称诸葛亮。东晋政权因其军事才能特追封他为武兴王。\n诸葛亮散文代表作有《出师表》《诫子书》等。曾发明木牛流马、孔明灯等，并改造连弩，叫做诸葛连弩，可一弩十矢俱发。诸葛亮一生“鞠躬尽瘁、死而后已”，是中国传统文化中忠臣与智者的代表人物。"));
        IndexRequest indexRequest = new IndexRequest();
        List<IndexRequest> indexRequests = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        //关闭时间戳的功能
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        //现在需要在中间加一个
        mapper.registerModule(new JavaTimeModule());
        personList.forEach(p -> {
            IndexRequest request = new IndexRequest(EsConsts.INDEX_NAME);
            //填充id
            request.id(p.getId() + "");
            String s = null;
            try {
                s = mapper.writeValueAsString(p);
                //先不修改id
                request.source(s, XContentType.JSON);
                request.opType(DocWriteRequest.OpType.CREATE);
                indexRequests.add(request);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            //bulkProcessor.add(request);
        });
        indexRequests.forEach(bulkProcessor::add);
        //restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        while (true) ;
    }


}
