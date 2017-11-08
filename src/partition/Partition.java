package partition;

import testcases.Bean;

import java.util.Random;

/**
 * @author phantom
 */
public class Partition {
    public Partition() {}
    private static final int PARTITION1 = 20;
    private static final int PARTITION2 = 3;

    /**
     * 主要是为RPT测试给出下一个分区的ID
     * @param numOfpartitions
     * @return
     */
    public int nextPartition(int numOfpartitions){
        double[] pd = new double[numOfpartitions];
        for (int i = 0; i < pd.length; i++) {
            pd[i] = 1.0 /numOfpartitions;
        }
        Random random = new Random();
        int npi = -1 ;
        double rd = random.nextDouble();
        double sum = 0.0;
        do{
            ++npi;
            sum += pd[npi];
        }while(rd >= sum && npi < pd.length - 1);
        return npi;
    }

    public boolean isBelongToOneOfPartition(Bean bean,int numOfPartition,int partition){
        char type = bean.getType();
        int monthRent = bean.getMonthRent();
        String[] p20 = {"A46","A66","A96","A126","A156","A186","A226","A286","A386","A586","A886","B46","B66","B96","B126","B156","B186","C46","C66","C96"};
        String str = String.valueOf(type) + String.valueOf(monthRent);
        if (numOfPartition == 20){
            if (p20[partition].equals(str)){
                return true;
            }else
                return false;
        }else {
            boolean flag = false;
            switch (partition){
                case 0:
                    if (type == 'A')
                        flag = true;
                    break;
                case 1:
                    if (type == 'B')
                        flag = true;
                    break;
                case 2:
                    if (type == 'C')
                        flag = true;
                    break;
            }
            return flag;
        }
    }

}
