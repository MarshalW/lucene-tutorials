package marshal.lucene.analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.StringReader;

public class StandardAnalyzerDemo {
    public static void main(String[] args) throws IOException {
        String text = "The iPhone is a line of smartphones designed and marketed by Apple Inc."
                + " All generations of the iPhone use Apple's iOS mobile operating system software. ";
        runStandardAnalyzerExample(text);
    }

    static void runStandardAnalyzerExample(String text) throws IOException {
        Analyzer analyzer = new StandardAnalyzer();
        StringReader reader = new StringReader(text);
        TokenStream stream = analyzer.tokenStream(text, reader);
        stream.reset();

        CharTermAttribute attribute = stream.getAttribute(CharTermAttribute.class);

        System.out.println("Standard 分词结果：");

        while (stream.incrementToken()) {
            System.out.print(attribute.toString() + " | ");
        }

        System.out.println();
        System.out.println("结束");
        analyzer.close();
    }
}
