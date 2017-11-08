package mutantSet;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.io.File.separator;

/**
 * 全部的测试用例集
 * @author phantom
 */
public class MutantSet {
    private static final String MUTANT_PACKAGE = "com.lyq.mutant";
    private static final String CLASS_NAME = "ChinaUnionBill";
    private BinSet[] mutantsList;
    public MutantSet() {
        mutantsList = new BinSet[5];
        for (int i = 0; i < mutantsList.length; i++) {
            mutantsList[i] = new BinSet();
        }
        String[] txt = {"M50-50.txt","M60-40.txt","M70-30.txt","M80-20.txt","M90-10.txt"};
        for (int i = 0; i < txt.length; i++) {
            String path = System.getProperty("user.dir")+ separator + "src"+separator+"mutantSet"+separator+txt[i];
            File file = new File(path);
            try{
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String str = "";
                while((str = bufferedReader.readLine()) != null){
                    String[] tempstr = str.split(";");
                    StringBuffer stringBuffer = new StringBuffer(0);
                    for (int j = 0; j < tempstr.length; j++) {
                        stringBuffer.delete(0,stringBuffer.length());
                        stringBuffer.append(MUTANT_PACKAGE+".");
                        stringBuffer.append(tempstr[j]+".");
                        stringBuffer.append(CLASS_NAME);
                        mutantsList[i].add(new Mutant(stringBuffer.toString()));
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 返回指定变异体集的长度
     * @param index 指定的变异体集
     * @return 长度
     */
    public int size(int index){
        return mutantsList[index].size();
    }

    public BinSet[] getMutantsList() {
        return mutantsList;
    }
}
