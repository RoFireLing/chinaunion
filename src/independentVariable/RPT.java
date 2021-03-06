package independentVariable;

import dependentVariable.RPTMeasure;
import logrecorder.RPTLog;
import mutantSet.BinSet;
import mutantSet.MutantSet;
import mutantSet.TestMethods;
import partition.Partition;
import testcases.Bean;
import testcases.GenerateTestcases;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author phantom
 */
public class RPT {
    private static final int SEEDS = 1 ;
    private static final int TESTTIMES = 3 ;
    private static final double DIVISOR = SEEDS * TESTTIMES;
    private static final String ORIGINAL_PACKAGE = "com.lyq.";
    private static final int NUMOFTESTCASES = 900000;

    public void randomPartitionTesting(){
        GenerateTestcases generateTestcases = new GenerateTestcases();
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        TestMethods testMethods = new TestMethods();
        List<String> methodsList = testMethods.getMethods();
        //        int[] partitions = {40,35,20,14,10};//记录每一个分区之中变异体的数量
        int[] partitions = {11};
//        String[] distribution = {"M50-50","M60-40","M70-30","M80-20","M90-10"};
        String[] distribution = {"LowFailureRate"};
        RPTLog rptLog = new RPTLog();
//        int[] numOfPartitions = {20,3};
        int[] numOfPartitions = {20};
        Partition rptPartition = new Partition();

        for (int y = 0; y < distribution.length; y++) {//对不同的变异体集进行测试
            for (int i = 0; i < numOfPartitions.length; i++) {//分区方式

                List<Long> falltime = new ArrayList<Long>();

                List<Long> f2alltime = new ArrayList<Long>();

                List<Long> talltime = new ArrayList<Long>();


                RPTMeasure rptMeasure = new RPTMeasure();//记录每一个种子的平均测试结果

                for (int j = 0; j < SEEDS; j++) {//对每一个种子进行测试
                    for (int k = 0; k < TESTTIMES; k++) {//对每一个随机数种子重复测试30次
                        int counter = 0 ;
                        int fmeasure = 0 ;
                        List<Bean> beans = new ArrayList<Bean>();
                        beans.clear();
                        beans = generateTestcases.generateTestcases(j,NUMOFTESTCASES);//获得指定随机数种子情况下的测试用例集
                        List<String> killedMutants = new ArrayList<String>();//记录杀死的变异体
                        killedMutants.clear();
                        MutantSet ms = new MutantSet();
                        BinSet[] mutants = new BinSet[5];
                        for (int l = 0; l < mutants.length; l++) {
                            mutants[l] = new BinSet();
                        }
                        mutants = ms.getMutantsList();//获得5中变异体分布的变异体集

                        long starttemp = System.currentTimeMillis();
                        long ftime = 0;

                        for (int l = 0; l < beans.size();) {//选取测试用例
                            int partition = rptPartition.nextPartition(numOfPartitions[i]);//获取下一个分区的ID
                            Bean bean;
                            //记录临时某一个测试用例杀死的变异体情况
                            List<String> templist = new ArrayList<String>();
                            templist.clear();
                            //获取属于partition中的tc
                            do {
                                bean = beans.get(l++);
                            }while(!rptPartition.isBelongToOneOfPartition(bean,numOfPartitions[i],partition));
                            //将测试用例初始化
//                            System.out.println("test begin:");
                            counter++;//测试用例的计数器
                            //接下来开始逐个遍历变异体
                            try{
                                for (int m = 0; m < mutants[y].size(); m++) {
                                    //获取原始程序的实例
                                    Class originalClazz = Class.forName(ORIGINAL_PACKAGE+"ChinaUnionBill");
                                    Constructor constructor1 = originalClazz.getConstructor(null);
                                    Object originalInstance = constructor1.newInstance(null);
                                    //获取变异体程序的实例
                                    Class mutantClazz = Class.forName(mutants[y].getMutantName(m));
                                    Constructor constructor2 = mutantClazz.getConstructor(null);
                                    Object mutantInstance = constructor2.newInstance(null);
                                    //对一个变异体的所有方法进行遍历
                                    for (int n = 0; n < methodsList.size(); n++) {
                                        //获取源程序的方法
                                        Method originalMethod = originalClazz.getMethod(methodsList.get(n),char.class,int.class,double.class,int.class,int.class);
                                        Object originalResult =  originalMethod.invoke(originalInstance,bean.getType(),bean.getMonthRent(),bean.getFlow(),bean.getVoiceCall(),bean.getVideoCall());

                                        Method mutantMethod = mutantClazz.getMethod(methodsList.get(n),char.class,int.class,double.class,int.class,int.class);
                                        Object mutantResult = mutantMethod.invoke(mutantInstance,bean.getType(),bean.getMonthRent(),bean.getFlow(),bean.getVoiceCall(),bean.getVideoCall());
                                        if (!originalResult.equals(mutantResult)){
                                            String[] str = mutants[y].getMutantName(m).split("\\.");
                                            //删除杀死的变异体
                                            mutants[y].remove(m);
                                            m--;
                                            String temp = str[3];
                                            killedMutants.add(temp);
                                            templist.add(temp);
                                            if (killedMutants.size() == 1){
                                                long ftimeTemp = System.currentTimeMillis();
                                                ftime = ftimeTemp;
                                                falltime.add(ftimeTemp - starttemp);
                                                fmeasure = counter;
                                                rptMeasure.addFmeasure(counter);
                                            }else if (killedMutants.size() == partitions[y]){
                                                rptMeasure.addTmeasure(counter);
                                                long ttimeTemp = System.currentTimeMillis();
                                                talltime.add(ttimeTemp - starttemp);
                                            }else if (killedMutants.size() == 2){
                                                rptMeasure.addNFmeasure(counter - fmeasure);
                                                long f2timeTemp = System.currentTimeMillis();
                                                f2alltime.add(f2timeTemp - ftime);
                                            }
                                            break;
                                        }
                                    }
                                }
                                //记录1个测试用例在所有得变异体上执行之后的结果
//                                rptLog.recordProcessInfo("rpt_log.txt",distribution[y],String.valueOf(j),
//                                        String.valueOf(i),String.valueOf(bean.getId()),
//                                        templist,String.valueOf(partitions[y] - killedMutants.size()));
                                if (killedMutants.size() >= partitions[y]){
                                    break;
                                }
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            } catch (NoSuchMethodException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                long ftotaltime = 0;
                for (int j = 0; j < falltime.size(); j++) {
                    ftotaltime += falltime.get(j);
                }
                System.out.println(ftotaltime);
                double meanfTime = Double.parseDouble(decimalFormat.format(ftotaltime / DIVISOR));

                long f2totaltime = 0;
                for (int j = 0; j < f2alltime.size(); j++) {
                    f2totaltime += f2alltime.get(j);
                }
                double meanf2time = Double.parseDouble(decimalFormat.format(f2totaltime / DIVISOR));

                long ttotaltime = 0 ;

                for (int j = 0; j < talltime.size(); j++) {
                    ttotaltime += talltime.get(j);
                }
                double meantime = Double.parseDouble(decimalFormat.format(ttotaltime / DIVISOR)) ;
                rptLog.recordResult("rptResult.txt",distribution[y],rptMeasure.getMeanFmeasure(),rptMeasure.getMeanNFmeasure(),
                        rptMeasure.getMeanTmeasure(),rptMeasure.getStandardDevOfFmeasure(),rptMeasure.getStandardDevOfNFmeasure(),
                        rptMeasure.getStandardDevOfTmeasure(),String.valueOf(numOfPartitions[i]),meanfTime,meanf2time,meantime);
            }
        }
    }

    public static void main(String[] args) {
        RPT rpt = new RPT();
        rpt.randomPartitionTesting();
    }
}
