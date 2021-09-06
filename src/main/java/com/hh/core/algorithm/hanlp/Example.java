package com.hh.core.algorithm.hanlp;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLWord;
import com.hankcs.hanlp.corpus.document.sentence.Sentence;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.dictionary.py.Pinyin;
import com.hankcs.hanlp.model.crf.CRFLexicalAnalyzer;
import com.hankcs.hanlp.seg.Dijkstra.DijkstraSegment;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.suggest.Suggester;
import com.hankcs.hanlp.tokenizer.IndexTokenizer;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.SpeedTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * Created by hh on 2019/6/10.
 * maven引入方式例子
 */
public class Example {

    public static final Logger logger = LogManager.getLogger(Example.class);

    public static void demo1(String content) {
        logger.info("result:{}", HanLP.segment(content));
    }

    /**
     * 标准分词
     * @param content 输入内容
     */
    public static void standardParticiple(String content) {
        List<Term> termList = StandardTokenizer.segment(content);
        logger.info("standardParticiple:{}", termList);
    }

    /**
     * NLP分词1
     * @param content 输入内容
     */
    public static void NLPParticiple1(String content) {
        List<Term> termList = NLPTokenizer.segment(content);
        logger.info("NLPParticiple1:{}", termList);
    }

    /**
     * NLP分词2
     *  translateLabels：注释采用中文，比如"/v"解释成"/动词"
     * @param content 输入内容
     */
    public static void NLPParticiple2(String content) {
        Sentence sentence = NLPTokenizer.analyze(content).translateLabels();
        logger.info("NLPParticiple2:{}", sentence);
    }

    /**
     * NLP分词3
     * @param content 输入内容
     */
    public static void NLPParticiple3(String content) {
        Sentence sentence = NLPTokenizer.analyze(content);
        logger.info("NLPParticiple3:{}", sentence);
    }

    /**
     * 索引分词
     * @param content 输入内容
     */
    public static void IndexParticiple(String content) {
        List<Term> termList = IndexTokenizer.segment(content);
        for (Term term : termList) {
            logger.info(term + " [" + term.offset + ":" + (term.offset + term.word.length()) + "]");
        }
    }

    /**
     * N-最短路径分词
     * @param content 输入内容
     */
    public static void NShortParticiple(String[] content) {
        Segment nShortSegment = new NShortSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
        Segment shortestSegment = new DijkstraSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
        for (String sentence : content) {
            logger.info("N-最短分词：" + nShortSegment.seg(sentence) + "\n最短路分词：" + shortestSegment.seg(sentence));
        }
    }

    /**
     * CRF分词
     * @param content 输入内容
     */
    public static void CRFParticiple(String[] content) {
        try {
            CRFLexicalAnalyzer analyzer = new CRFLexicalAnalyzer();
            for (String sentence : content) {
                logger.info("sentence:{}", analyzer.analyze(sentence));
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 极速词典分词
     * @param content 输入内容
     */
    public static void speedParticiple(String content) {
        List<Term> termList = SpeedTokenizer.segment(content);
        logger.info("speedParticiple:{}", termList);

        long start = System.currentTimeMillis();
        int pressure = 1000000;
        for (int i = 0; i < pressure; ++i) {
            SpeedTokenizer.segment(content);
        }
        double costTime = (System.currentTimeMillis() - start) / (double)1000;
        logger.info("分词速度：{}字每秒", content.length() * pressure / costTime);
    }

    /**
     * 用户自定义词典
     * @param content 输入内容
     */
    public static void customParticiple(String content) {
        // 动态增加
        CustomDictionary.add("攻城狮");
        // 强行插入
        CustomDictionary.insert("白富美", "nz 1024");
        // 删除词语（注释掉试试）
//        CustomDictionary.remove("攻城狮");
        logger.info("CustomDictionary add:{}", CustomDictionary.add("单身狗", "nz 1024 n 1"));
        logger.info("CustomDictionary add:{}", CustomDictionary.get("单身狗"));

        // AhoCorasickDoubleArrayTrie自动机扫描文本中出现的自定义词语
        final char[] charArray = content.toCharArray();
        CustomDictionary.parseText(charArray, new AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute>() {
            @Override
            public void hit(int begin, int end, CoreDictionary.Attribute value) {
                logger.info("[{}:{}] = {} {}\n", begin, end, new String(charArray, begin, end - begin), value);
            }
        });

        // 自定义词典在所有分词器中都有效
        logger.info("result:{}", HanLP.segment(content));
    }

    /**
     * 中国人名识别
     * @param content 输入内容
     */
    public static void nameChinaParticiple(String[] content) {
        Segment segment = HanLP.newSegment().enableNameRecognize(true);
        for (String sentence : content) {
            List<Term> termList = segment.seg(sentence);
            logger.info("nameChinaParticiple:{}", termList);
        }
    }

    /**
     * 音译人名识别
     * @param content 输入内容
     */
    public static void nameForeignParticiple(String[] content) {
        Segment segment = HanLP.newSegment().enableTranslatedNameRecognize(true);
        for (String sentence : content) {
            List<Term> termList = segment.seg(sentence);
            logger.info("nameForeignParticiple:{}", termList);
        }
    }

    /**
     * 日本人名识别
     * @param content 输入内容
     */
    public static void nameJapanParticiple(String[] content) {
        Segment segment = HanLP.newSegment().enableJapaneseNameRecognize(true);
        for (String sentence : content) {
            List<Term> termList = segment.seg(sentence);
            logger.info("nameJapanParticiple:{}", termList);
        }
    }

    /**
     * 地名识别
     * @param content 输入内容
     */
    public static void namePlaceParticiple(String[] content) {
        Segment segment = HanLP.newSegment().enablePlaceRecognize(true);
        for (String sentence : content) {
            List<Term> termList = segment.seg(sentence);
            logger.info("namePlaceParticiple:{}", termList);
        }
    }

    /**
     * 机构名识别
     * @param content 输入内容
     */
    public static void nameOrganizationParticiple(String[] content) {
        Segment segment = HanLP.newSegment().enableOrganizationRecognize(true);
        for (String sentence : content) {
            List<Term> termList = segment.seg(sentence);
            logger.info("nameOrganizationParticiple:{}", termList);
        }
    }

    /**
     * 关键词提取
     * @param content 输入内容
     */
    public static void keywordParticiple(String content) {
        List<String> keywordList = HanLP.extractKeyword(content, 5);
        logger.info("keywordParticiple:{}", keywordList);
    }

    /**
     * 自动摘要
     * @param content 输入内容
     */
    public static void topSentenceParticiple(String content) {
        List<String> sentenceList = HanLP.extractSummary(content, 3);
        logger.info("topSentenceParticiple:{}", sentenceList);
    }

    /**
     * 短语提取
     * @param content 输入内容
     */
    public static void phraseParticiple(String content) {
        List<String> phraseList = HanLP.extractPhrase(content, 10);
        logger.info("phraseParticiple:{}", phraseList);
    }

    /**
     * 拼音转换
     * @param content 输入内容
     */
    public static void pinyinParticiple(String content) {
        List<Pinyin> pinyinList = HanLP.convertToPinyinList(content);
        System.out.print("原文:");
        for (char c : content.toCharArray()) {
            System.out.printf("%c,", c);
        }
        System.out.println();

        System.out.print("拼音（数字音调）:");
        for (Pinyin pinyin : pinyinList) {
            System.out.printf("%s,", pinyin);
        }
        System.out.println();

        System.out.print("拼音（符号音调）:");
        for (Pinyin pinyin : pinyinList) {
            System.out.printf("%s,", pinyin.getPinyinWithToneMark());
        }
        System.out.println();

        System.out.print("拼音（无音调）:");
        for (Pinyin pinyin : pinyinList) {
            System.out.printf("%s,", pinyin.getPinyinWithoutTone());
        }
        System.out.println();

        System.out.print("声调:");
        for (Pinyin pinyin : pinyinList) {
            System.out.printf("%s,", pinyin.getTone());
        }
        System.out.println();

        System.out.print("声母:");
        for (Pinyin pinyin : pinyinList) {
            System.out.printf("%s,", pinyin.getShengmu());
        }
        System.out.println();

        System.out.print("韵母:");
        for (Pinyin pinyin : pinyinList) {
            System.out.printf("%s,", pinyin.getYunmu());
        }
        System.out.println();

        System.out.print("输入法头:");
        for (Pinyin pinyin : pinyinList) {
            System.out.printf("%s,", pinyin.getHead());
        }
        System.out.println();
    }

    /**
     * 简体中文转为繁体中文
     * @param content 输入内容
     */
    public static void simplifiedChinese2TraditionalChinese(String content) {
        String result = HanLP.convertToTraditionalChinese(content);
        logger.info("simplifiedChinese2TraditionalChinese:{}", result);
    }

    /**
     * 繁体中文转为简体中文
     * @param content 输入内容
     */
    public static void traditionalChinese2SimplifiedChinese(String content) {
        String result = HanLP.convertToSimplifiedChinese(content);
        logger.info("traditionalChinese2SimplifiedChinese:{}", result);
    }

    /**
     * 文本推荐
     * @param content 输入内容
     */
    public static void textRecommendation(String[] content) {
        Suggester suggester = new Suggester();
        for (String title : content) {
            suggester.addSentence(title);
        }

        logger.info("suggest:{}", suggester.suggest("发言", 1));       // 语义
        logger.info("suggest:{}", suggester.suggest("危机公共", 1));   // 字符
        logger.info("suggest:{}", suggester.suggest("mayun", 1));      // 拼音
    }

    /**
     * 依存句法分析
     * @param content 输入内容
     */
    public static void dependencyParser(String content) {
        CoNLLSentence sentence = HanLP.parseDependency(content);
        logger.info("原文:{}", sentence);
        // 可以方便地遍历它
        for (CoNLLWord word : sentence) {
            logger.info("{} --({})--> {}", word.LEMMA, word.DEPREL, word.HEAD.LEMMA);
        }
        // 也可以直接拿到数组，任意顺序或逆序遍历
        CoNLLWord[] wordArray = sentence.getWordArray();
        for (int i = wordArray.length - 1; i >= 0; i--) {
            CoNLLWord word = wordArray[i];
            logger.info("{} --({})--> {}", word.LEMMA, word.DEPREL, word.HEAD.LEMMA);
        }
        // 还可以直接遍历子树，从某棵子树的某个节点一路遍历到虚根
        CoNLLWord head = wordArray[12];
        while ((head = head.HEAD) != null) {
            if (head == CoNLLWord.ROOT) {
                logger.info(head.LEMMA);
            } else {
                logger.info("{} --({})--> ", head.LEMMA, head.DEPREL);
            }
        }
    }

}
