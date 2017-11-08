package testcases;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author phantom
 */
public class GenerateTestcases {
    public GenerateTestcases() {}

    /**
     * 产生测试用例的主要方法
     * @param seed 随机数的种子
     * @param numOftestcases 产生的测试用例的数目
     * @return 一个测试用例的序列
     */
    public List<Bean> generateTestcases(int seed,int numOftestcases){
        Random random = new Random(seed);
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        List<Bean> templist = new ArrayList<Bean>();
        for (int i = 0; i < numOftestcases; i++) {
            int id = i;
            char[] types = {'A','B','C'};
            char type = types[random.nextInt(types.length)];
            int monthRent = 0 ;
            int voicecall = 0 ;
            double flow = 0.0;
            if (type =='A'){
                int[] monthRents1 = {46,66,96,126,156,186,226,286,386,586,886};
                monthRent = monthRents1[random.nextInt(monthRents1.length)];
                switch (monthRent){
                    case 46:
                        voicecall = random.nextInt(50)+25;
                        flow = Double.parseDouble(decimalFormat.format(random.nextDouble() * 300));
                        break;
                    case 66:
                        voicecall = random.nextInt(50)+25;
                        flow = Double.parseDouble(decimalFormat.format(random.nextDouble() * 500));
                        break;
                    case 96:
                        voicecall = random.nextInt(150)+150;
                        flow = Double.parseDouble(decimalFormat.format(random.nextDouble() * 600));
                        break;
                    case 126:
                        voicecall = random.nextInt(200)+200;
                        flow = Double.parseDouble(decimalFormat.format(random.nextDouble() * 500));
                        break;
                    case 156 :
                        voicecall = random.nextInt(300)+300;
                        flow = Double.parseDouble(decimalFormat.format(random.nextDouble() * 700));
                        break;
                    case 186:
                        voicecall = random.nextInt(400)+300;
                        flow = Double.parseDouble(decimalFormat.format(random.nextDouble() * 850));
                        break;
                    case 226:
                        voicecall = random.nextInt(720);
                        flow = Double.parseDouble(decimalFormat.format(random.nextDouble() * 950));
                        break;
                    case 286:
                        voicecall = random.nextInt(910);
                        flow = Double.parseDouble(decimalFormat.format(random.nextDouble() * 1350));
                        break;
                    case 386:
                        voicecall = random.nextInt(1300);
                        flow = Double.parseDouble(decimalFormat.format(random.nextDouble() * 1500));
                        break;
                    case 586:
                        voicecall = random.nextInt(2000);
                        flow = Double.parseDouble(decimalFormat.format(random.nextDouble() * 2500));
                        break;
                    case 886:
                        voicecall = random.nextInt(3010);
                        flow = Double.parseDouble(decimalFormat.format(random.nextDouble() * 3100));
                        break;
                }
            }else if (type == 'B'){
                int[] monthRents2 = {46,66,96,126,156,186};
                monthRent = monthRents2[random.nextInt(monthRents2.length)];
                switch (monthRent){
                    case 46:
                        voicecall = random.nextInt(200);
                        flow = Double.parseDouble(decimalFormat.format(random.nextDouble() * 60));
                        break;
                    case 66:
                        voicecall = random.nextInt(250);
                        flow = Double.parseDouble(decimalFormat.format(random.nextDouble() * 90));
                        break;
                    case 96:
                        voicecall = random.nextInt(500);
                        flow = Double.parseDouble(decimalFormat.format(random.nextDouble() * 130));
                        break;
                    case 126:
                        voicecall = random.nextInt(750);
                        flow = Double.parseDouble(decimalFormat.format(random.nextDouble() * 150));
                        break;
                    case 156:
                        voicecall = random.nextInt(1000);
                        flow = Double.parseDouble(decimalFormat.format(random.nextDouble() * 180));
                        break;
                    case 186 :
                        voicecall = random.nextInt(1350);
                        flow = Double.parseDouble(decimalFormat.format(random.nextDouble() * 200));
                        break;
                }
            }else {
                int[] monthRents3 = {46,66,96};
                monthRent = monthRents3[random.nextInt(monthRents3.length)];
                switch (monthRent){
                    case 46:
                        voicecall = random.nextInt(350);
                        flow = Double.parseDouble(decimalFormat.format(random.nextDouble() * 80));
                        break;
                    case 66:
                        voicecall = random.nextInt(450);
                        flow = Double.parseDouble(decimalFormat.format(random.nextDouble() * 120));
                        break;
                    case 96:
                        voicecall = random.nextInt(650);
                        flow = Double.parseDouble(decimalFormat.format(random.nextDouble() * 160));
                        break;
                }
            }
            int videocall = random.nextInt(60);
            Bean bean = new Bean();
            bean.setParameters(id,type,monthRent,flow,voicecall,videocall);
            templist.add(bean);
        }
        return templist;
    }

    public static void main(String[] args) {
        GenerateTestcases gt = new GenerateTestcases();
        List<Bean> lists = gt.generateTestcases(0,1);
        System.out.println(lists.get(0).toString());
    }


}
