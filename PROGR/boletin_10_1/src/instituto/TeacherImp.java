package instituto;

import instituto.enums.PermanentPosition;

public class TeacherImp implements Teacher {

    private String fName;
    private String lName;
    private int age;

    public TeacherImp(String fName, String lName, int age) {
        this.fName = fName;
        this.lName = lName;
        this.age = age;
    }

    @Override
    public String getfName() {
        return fName;
    }

    @Override
    public String getlName() {
        return lName;
    }

    @Override
    public int getAge() {
        return age;
    }


}
