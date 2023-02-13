package myapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    
    public static void main(String[] args) throws IOException {
        
        try {

            Path path = Paths.get(args[0]);
            File file = path.toFile();

            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            List<Application> applist = new ArrayList<>();
            Map<String, ApplicationStatHandler> categoryMap = new HashMap<>(); 
            List<String> headers = Arrays.asList(br.readLine().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1));
            String row;
            String[] buffer = new String[headers.size()];

            while ((row = br.readLine()) != null) {
                buffer = row.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                applist.add(new Application(buffer[headers.indexOf("App")], buffer[headers.indexOf("Category")], 
                                            Float.parseFloat(buffer[headers.indexOf("Rating")])));

                if (!categoryMap.containsKey(buffer[headers.indexOf("Category")])) {
                    categoryMap.put(buffer[headers.indexOf("Category")], new ApplicationStatHandler(buffer[headers.indexOf("Category")]));
                    categoryMap.get(buffer[headers.indexOf("Category")]).updateStats(applist.get(applist.size() - 1));
                } else if (categoryMap.containsKey(buffer[headers.indexOf("Category")])) {
                    categoryMap.get(buffer[headers.indexOf("Category")]).updateStats(applist.get(applist.size() - 1));
                }
            }

            categoryMap.forEach( (k, v) -> v.printStats() );
            writeSummaryToFile("Summary.txt", categoryMap);
            br.close();
            fr.close();

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please provide the CSV file location.");
            System.out.println("usage: java ... myapp.Main <CSVfile>");
            System.exit(0);
        } catch (FileNotFoundException e) {
            System.out.println("File not found, please check!");
            System.exit(0);
        }
    }

    public static void writeSummaryToFile(String outputPath, Map <String,ApplicationStatHandler> categoryMap) throws IOException {
        File f = new File(outputPath);
        FileWriter fw = new FileWriter(f);
        for (String s : categoryMap.keySet()) {
            fw.write("Category %s: %s\n".formatted(s, categoryMap.get(s).toString()));
        }
        fw.flush();
        fw.close();
    }

}


