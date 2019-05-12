package project.areas.parsing;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class ForkParser extends RecursiveAction {
    private final LineFactory factory;
    private final List<String> source;
    private List<MoodleLogLine> destination;
    private int length;
    private int start;
    //defines the array size at which the task will be performed concurrently
    private static int threshold = 10000;
    private static int threadCount = 0;

    public ForkParser(List<String> source, List<MoodleLogLine> destination,LineFactory lineFactory, int length, int start){
        System.out.println("Threads started: " + (++threadCount));
        this.source = source;
        this.destination = destination;
        this.length = length;
        this.start = start;
        this.factory = lineFactory;
    }

    protected void computeDirect(){
        for(int index = start; index < start + length; index++){
            Line line = factory.convert(source.get(index));
            if(line instanceof MoodleLogLine)
                destination.add((MoodleLogLine) line);
        }
    }

    @Override
    protected void compute() {
        if(length < threshold){
            computeDirect();
            return;
        }

        int split = length / 2;

        invokeAll(new ForkParser(source, destination, this.factory, split, start),
                new ForkParser(source, destination, this.factory,length - split, start + split));
    }
}
