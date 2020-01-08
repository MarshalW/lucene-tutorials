package marshal.lucene.simple;

import marshal.lucene.ik.IKAnalyzer4Lucene8;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IndexDemo {

    private static int count = 133;

    private static int index = 0;

    public static void main(String[] args) throws IOException {
        List<News> newsList = new ArrayList();
        newsList.add(
                new News(
                        index++, "美众议院要求特朗普对伊袭击作说明", "美军发动空袭刺杀行动后，引发美国舆论哗然。不少政界人士批评政府的行动，并对当前局势表示担忧。", count++)
        );
        newsList.add(
                new News(
                        index++, "台湾黑鹰黑匣子判读完成：直接撞山 动力系统正常", "2日，台湾黑鹰直升机坠毁。台湾运输安全调查机构负责人杨宏智4日表示，失事飞机的黑匣子已判读完成。初判动力系统没问题，直升机是直接撞山，事发前的30秒是关键，这30秒内的每个细节都已整理出来，交由给台防务部门整理事故原因。", count++)
        );
        newsList.add(
                new News(
                        index++, "2020北京第一场雪酝酿中 这波冷空气的“带货量”有多大？", "中国天气网讯 虽说今天（4日）北京仍将阳光当道，气温小幅回升，但是新一股冷空气正在奔赴京城的路上，2020年的第一场雪也在酝酿中。预计明天傍晚开始，北京将会自西向东出现小雪天气，入夜后影响城区，需注意降雪天气对周一早高峰可能产生的不利影响。", count++)
        );

        Analyzer analyzer = new IKAnalyzer4Lucene8();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

        Path indexPath = Paths.get("index");

        Directory directory = FSDirectory.open(indexPath);
        IndexWriter indexWriter = new IndexWriter(directory, config);

        newsList.forEach((news) -> {
            Document document = new Document();

            // id
            FieldType type = new FieldType();
            type.setIndexOptions(IndexOptions.DOCS);
            type.setStored(true);
            document.add(new Field("id", String.valueOf(news.getId()), type));

            // title
            type = new FieldType();
            type.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
            type.setStored(true);
            type.setTokenized(true);
            document.add(new Field("title", news.getTitle(), type));

            // content
            type = new FieldType();
            type.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
            type.setStored(true);
            type.setTokenized(true);
            type.setStoreTermVectors(true);
            type.setStoreTermVectorPositions(true);
            type.setStoreTermVectorOffsets(true);
            type.setStoreTermVectorPayloads(true);
            document.add(new Field("content", news.getContent(), type));

            // reply
            document.add(new IntPoint("reply", news.getReply()));
            document.add(new StoredField("reply_display", news.getReply()));

            try {
                indexWriter.addDocument(document);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        indexWriter.commit();
        indexWriter.close();
    }

    private static class News {
        int id;

        String title;

        String content;

        int reply;

        public News(int id, String title, String content, int reply) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.reply = reply;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getReply() {
            return reply;
        }

        public void setReply(int reply) {
            this.reply = reply;
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
