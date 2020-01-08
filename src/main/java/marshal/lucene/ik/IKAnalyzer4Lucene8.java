package marshal.lucene.ik;

import marshal.lucene.analyzer.Ik4Lucene8AnalyzerDemo;
import org.apache.lucene.analysis.Analyzer;

public class IKAnalyzer4Lucene8 extends Analyzer {
    private boolean useSmart = true;

    public IKAnalyzer4Lucene8() {
        this(false);
    }

    public IKAnalyzer4Lucene8(boolean useSmart) {
        super();
        this.useSmart = useSmart;
    }

    public boolean isUseSmart() {
        return useSmart;
    }

    public void setUseSmart(boolean useSmart) {
        this.useSmart = useSmart;
    }

    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        IKTokenizer4Lucene8 tk = new IKTokenizer4Lucene8(this.useSmart);
        return new TokenStreamComponents(tk);
    }
}