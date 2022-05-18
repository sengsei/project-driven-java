package chapter03;

public class JsonExporter implements Exporter {
    @Override
    public String export(SummaryStatistics summaryStatistics) {
        String result = "{";
        result += "'sum':"+ summaryStatistics.getSum() +",";
        result += "'average':"+ summaryStatistics.getAverage() +",";
        result += "'max':"+ summaryStatistics.getMax() +",";
        result += "'min':"+ summaryStatistics.getMin();
        result += "}";
        return result;
    }
}
