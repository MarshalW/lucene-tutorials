package marshal.lucene.analyzer;

import marshal.lucene.ik.IKAnalyzer4Lucene8;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.StringReader;

public class Ik4Lucene8AnalyzerDemo {
    public static void main(String[] args) throws IOException {
        String text = "iPhone是苹果公司研发及销售的智能手机系列，搭载苹果公司研发的iOS移动操作系统。";
        runIkAnalyzerExample(text);
    }

    static void runIkAnalyzerExample(String text) throws IOException {
        Analyzer analyzer = new IKAnalyzer4Lucene8();
        StringReader reader = new StringReader(text);
        TokenStream stream = analyzer.tokenStream(text, reader);
        stream.reset();

        CharTermAttribute attribute = stream.getAttribute(CharTermAttribute.class);

        System.out.println("IK 分词结果：");

        while (stream.incrementToken()) {
            System.out.print(attribute.toString() + " | ");
        }

        System.out.println();
        System.out.println("结束");
        analyzer.close();
    }
}
