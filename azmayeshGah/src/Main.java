import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {

    }
}

class User {
    String id;
    int age;
    String illness;
    private ArrayList<Test> tests = new ArrayList<>();

    public ArrayList<Test> getTests() {
        return tests;
    }

    public void setTests(ArrayList<Test> tests) {
        this.tests = tests;
    }

}

abstract class Test {

    private static int number = 0;
    private String info;
    private boolean testConfirm;
    String id;

    public void setTestConfirm(boolean testConfirm) {
        this.testConfirm = testConfirm;
    }

    public void getTestConfirmation(boolean confirmation){
        this.testConfirm =confirmation;
    }
    Test(String info, boolean confirmation) {
        this.info = info;
        this.testConfirm = confirmation;
        number++;
        id = idBuilder();
    }

    public String idBuilder() {

        return String.valueOf("user" + number);
    }

    private int CBC;
    private int BMP;
    private int bloodPressure;

    public int getBMP() {
        return BMP;
    }

    public void setBMP(int BMP) {
        this.BMP = BMP;
    }

    public int getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(int bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public int getCBC() {
        return CBC;
    }

    public void setCBC(int CBC) {
        this.CBC = CBC;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getTestConfirm() {
        return testConfirm;
    }



    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


}

abstract class Laboratory {
    String name;
    private final ArrayList<Test> allTests = new ArrayList<>();
    private final ArrayList<User> allUsers = new ArrayList<>();

    public static void setInstance(Laboratory instance) {
        Laboratory.instance = instance;
    }

    public ArrayList<Test> getStatistics() {
        return statistics;
    }

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public ArrayList<Test> getAllTests() {
        return allTests;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private final ArrayList<Test> statistics = new ArrayList<>();

    private Laboratory(String info) {
        name = info;
    }

    private static Laboratory instance;


    public static Laboratory getInstance() {
        if (instance == null) {
            instance = new Laboratory(instance.name) {

            };
        }
        return instance;
    }
}
//************************************************

class Anemia extends Test  {

    ThreadLocalRandom random = ThreadLocalRandom.current();
    Math math;
    int rand = random.nextInt(1, 11);


    Anemia(String info, boolean confirmation) {
        super(info, confirmation);
    }


}

//***********************************************************
class Thyroid extends Test {
    private int heartPressure;


    public int getHeartPressure() {
        return heartPressure;
    }

    Thyroid(int prePressure, String info, boolean confirm) {
        super(info, confirm);
        this.heartPressure = prePressure;
    }

    public void setHeartPressure(int heartPressure) {
        this.heartPressure = heartPressure;
    }

    Thyroid(String info, boolean confirmation) {
        super(info, confirmation);
    }




}

//*****************************************************************
class BloodType extends Test  {

    private boolean bloodConfirm;

    public BloodType(boolean bloodConfirm, boolean testConfirm, String info) {
        super(info, testConfirm);
        this.bloodConfirm = bloodConfirm;
    }

    public boolean getBloodConfirm() {
        return bloodConfirm;
    }

    public void setBloodConfirm(boolean bloodConfirm) {
        this.bloodConfirm = bloodConfirm;
    }





    @Override
    public void setBMP(int BMP) {
        super.setBMP(BMP);
    }

    @Override
    public void setInfo(String info) {
        super.setInfo(info);
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }

    @Override
    public void setBloodPressure(int bloodPressure) {
        super.setBloodPressure(bloodPressure);
    }

    @Override
    public void setCBC(int CBC) {
        super.setCBC(CBC);
    }


}

//--------------------------------------------------
class AIDS extends Test  {
    public boolean getAidsConform() {
        return aidsConfirm;
    }

    private boolean aidsConfirm;

    private String illness;

    AIDS(String info, boolean aidsConfirm, boolean testConfirm, String illness) {
        super(info, testConfirm);
        this.illness = illness;
        this.aidsConfirm = aidsConfirm;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public void setAidsConfirm(boolean aidsConfirm) {
        this. aidsConfirm=aidsConfirm;
    }

    ThreadLocalRandom random = ThreadLocalRandom.current();
    int rand = random.nextInt(3, 7);
    String[] ill = new String[8];





    @Override
    public void setCBC(int CBC) {
        super.setCBC(CBC);
    }

    @Override
    public void setBMP(int BMP) {
        super.setBMP(BMP);
    }

    @Override
    public void setInfo(String info) {
        super.setInfo(info);
    }

    @Override
    public void setBloodPressure(int bloodPressure) {
        super.setBloodPressure(bloodPressure);
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }


    @Override
    public String toString() {
        return super.toString();
    }
}

//+++++++++++++++++++++++++++++++++++++++++++++

