package mutantSet;

import java.util.ArrayList;
import java.util.List;

/**
 * 包含了联通计费的所有待测方法的名字
 * @author phantom
 */
public class TestMethods {
    private List<String> methods;
    public TestMethods(){
        methods = new ArrayList<String>();
        methods.add("billCaculation");
    }

    public List<String> getMethods() {
        return methods;
    }

    public void setMethods(List<String> methods) {
        this.methods = methods;
    }
}
