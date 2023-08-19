import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        String archivecsv = "C:\\Users\\Uziel\\OneDrive\\Escritorio\\ArchivosTareas\\car_salesar.csv";

        String finalpathname = "src/fullsum_table.csv";

        List<String> contentList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(archivecsv))) {

            String lineToRead;

            while ((lineToRead = bufferedReader.readLine()) != null) {
                int sign = lineToRead.indexOf('$');

                if (sign != -1 && sign < lineToRead.length() - 1) {
                    String valueSingn = lineToRead.substring(sign + 1);
                    contentList.add(valueSingn);
                }
            }

            double[] arregloD = new double[contentList.size()];
            for (int i = 0; i < contentList.size(); i++) {
                arregloD[i] = Double.parseDouble(contentList.get(i));
            }

            double[] suma = new double[arregloD.length];
            double summedValues = 0.0;
            for (int i = 0; i < arregloD.length; i++) {
                summedValues += arregloD[i];
                suma[i] = summedValues;
            }

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(finalpathname))) {
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                for (double valor : suma) {
                    bufferedWriter.write(decimalFormat.format(valor));
                    bufferedWriter.newLine();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }

        } catch (IOException exception) {
            exception.printStackTrace();

        }
    }
}