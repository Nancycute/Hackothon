import java.util.*;
import java.io.*;
import org.json.JSONArray;
import org.json.JSONObject;
public class UsecaseBattery {
public static void main(String[] args) throws IOException {
JSONObject js = new JSONObject();
double[] arr = new double[10000];
File file = new File("E:\\Battery.txt");
try (BufferedReader br = new BufferedReader(new FileReader(file))) {
String readLine;
String str, value = "";
String batteryDrain = "";
int flag = 0;
while ((readLine = br.readLine()) != null) {
StringTokenizer st = new StringTokenizer(readLine);
while (st.hasMoreTokens()) {
str = st.nextToken();
if (flag == 0) {
if (str.equals("Uid")) {
batteryDrain = readLine.substring(16, 21);
flag = 1;
}
}
if (str.equals("Foreground")) {
value = readLine.substring(27, 60);
value = value.trim();
break;
}
}
}
double drain = Double.parseDouble(batteryDrain);
double percentage = drain/(double)1000;
js.put("Foreground_time", value);
js.put("Battery_percentage", percentage);
js.put("Battery_drain", drain);
JSONArray jsonList = new JSONArray();
jsonList.add(js);
try (FileWriter file1 = new FileWriter("D://BatteryOutput.json")) {
file1.write(jsonList.toString());
file1.flush();
} catch (Exception e) {
e.printStackTrace();
}
}
}
}
