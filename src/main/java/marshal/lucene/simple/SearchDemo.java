package marshal.lucene.simple;

import marshal.lucene.ik.IKAnalyzer4Lucene8;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SearchDemo {
    public static void main(String[] args) throws IOException, ParseException {
        singleFieldQuery();
    }

    static void singleFieldQuery() throws IOException, ParseException {
        Path indexPath = Paths.get("index");
        Directory directory = FSDirectory.open(indexPath);

        IndexReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);
        Analyzer analyzer = new IKAnalyzer4Lucene8();

        QueryParser parser = new QueryParser("title", analyzer);
        parser.setDefaultOperator(QueryParser.Operator.AND);

        Query query = parser.parse("北京");
        System.out.println("Search: " + query.toString());

        TopDocs docs = searcher.search(query, 10);

        for (ScoreDoc d : docs.scoreDocs) {
            Document document = searcher.doc(d.doc);
            System.out.println("title: " + document.get("title"));
            System.out.println("Score: " + d.score);
        }

        reader.close();
        directory.close();
    }


}
