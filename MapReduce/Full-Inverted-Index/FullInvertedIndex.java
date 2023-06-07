import java.io.IOException;
import java.util.*;
import java.lang.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class FullInvertedIndex {

    public static class TokenizerMapper
            extends Mapper<Object, Text, Text, Text> {

        private Text word = new Text();
        private Text wordLocationCoordinate = new Text();

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            FileSplit fileSplit = (FileSplit) context.getInputSplit();
            String filenameStr = fileSplit.getPath().getName();

            // only get the index value, for example file0 => 0
            String fileIndexStr = filenameStr.replaceAll("[^0-9]", "");
            int wordIndex = 0;
            StringTokenizer itr = new StringTokenizer(value.toString());

            while (itr.hasMoreTokens()) {
                word.set(itr.nextToken());
                String coordinateStr = "(" + fileIndexStr + ", " + wordIndex + ")";
                wordLocationCoordinate.set(coordinateStr);
                context.write(word, wordLocationCoordinate);
                wordIndex++;
            }
        }
    }

    public static class InvertedIndexReducer
            extends Reducer<Text, Text, Text, Text> {

        public void reduce(Text key, Iterable<Text> values,
                Context context) throws IOException, InterruptedException {
            Set<String> result = new HashSet<>();
            for (Text val : values) {
                result.add(val.toString());
            }
            String s = String.join(",", result);
            context.write(key, new Text(s));
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "full inverted index");
        job.setJarByClass(FullInvertedIndex.class);
        job.setMapperClass(TokenizerMapper.class);
        job.setCombinerClass(InvertedIndexReducer.class);
        job.setReducerClass(InvertedIndexReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}