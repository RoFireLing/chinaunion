package mutantSet;

/**
 * bean类主要包含了变异体的完整的路径名称
 * @author phantom
 */
public class Mutant {
    private String fullClassName;//变异体的全名
    public Mutant(String fullname) {setFullClassName(fullname); }

    public String getFullClassName() {
        return fullClassName;
    }

    public void setFullClassName(String fullClassName) {
        this.fullClassName = fullClassName;
    }
}
