import com.cxy.customize.core.util.EnumUtil;

/**
 *
 * @description  性别的枚举
 */
public enum SexEnum {
    /**
     * 男
     */
    MALE(0,"男"),
    /**
     * 女
     */
    FEMALE(1,"女");

    private final String desc;//性别的描述
    private final int value;//性别的状态值

    //构造方法
    private SexEnum(int value, String desc) {
        this.desc = desc;
        this.value = value;
    }


    /**
     * 根据描述得到value
     * @param desc
     * @return
     */
    public static int getValue(String desc) {
        for(SexEnum us: SexEnum.values()) {
            if(desc.equals(us.getDesc())) {
                return us.value;
            }
        }
        return -1;
    }

    /**
     * 根据value得到desc
     * @param value
     * @return
     */
    public static String getDesc(int value) {
        for(SexEnum us: SexEnum.values()) {
            if(value==(us.getValue())) {
                return us.desc;
            }
        }
        return null;
    }


    //getter&setter
    public String getDesc() {
        return desc;
    }
    public int getValue() {
        return value;
    }


    public static void main(String[] args) throws NoSuchFieldException{
        EnumUtil.getNames(SexEnum.class).forEach(System.out::println);

        EnumUtil.getFieldNames(SexEnum.class,"desc").forEach(System.out::println);
    }

}