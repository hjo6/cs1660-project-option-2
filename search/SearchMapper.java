import java.io.*;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Mapper;

public class SearchMapper extends Mapper<Object, Text, Text, Text> {
    static String searchTerm;

    @Override
    public void setup(Context context) throws IOException, InterruptedException {
        Configuration conf = context.getConfiguration();
        searchTerm = conf.get("searchTerm");
    }

    @Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

        String[] tokens = value.toString().split("\t");

        String term = tokens[0];
        String filenamesWithCount = tokens[1];

        if (searchTerm.equals(term)) {
          context.write(new Text(term), new Text(filenamesWithCount));
        }
    }
}
