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

    public void getTestConfirmation(boolean confirmation) {
        this.testConfirm = confirmation;
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

    abstract public void runOperation(Test test);

    abstract public void sendResult(User user, Test test);
}

abstract class Laboratory implements GettingRequest{
    String name;
    private final ArrayList<Test> allTests = new ArrayList<>();
    private final ArrayList<User> allUsers = new ArrayList<>();
    private final ArrayList<Request>requests=new ArrayList<>();

    public ArrayList<Request> getRequests() {
        return requests;
    }

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

    @Override
    public String gettingRequest(User user, String id) {
        String str="not found";
        for(int i=0;i<Laboratory.getInstance().getRequests().size();i++){
            if(Laboratory.getInstance().getRequests().get(i).getId().equals(id)){
                str=Laboratory.getInstance().getRequests().get(i).toString();
            }
        }
        return str;  }

    public static Laboratory getInstance() {
        if (instance == null) {
            instance = new Laboratory(instance.name) {

            };
        }
        return instance;
    }
}
//************************************************

class Anemia extends Test implements Cloneable,MakingPrivate {
    public String toString(){
        return "id"+getId()+"info"+getInfo()+"bloodPressure"+getBloodPressure()+"cbc"+getCBC()+"bmp"+getBMP();
    }

    ThreadLocalRandom random = ThreadLocalRandom.current();
    Math math;
    int rand = random.nextInt(1, 11);


    Anemia(String info, boolean confirmation) {
        super(info, confirmation);
    }

    @Override
    public void runOperation(Test test) {
        Anemia anemia = (Anemia) test;
        anemia.setCBC((int) Math.sqrt(rand));
        anemia.setBMP(anemia.getCBC() / 2);
        anemia.setBloodPressure(anemia.getCBC() / anemia.getBMP());
    }

    @Override
    public void sendResult(User user, Test anemia) {
        user.getTests().add((BloodType) clone());
        Laboratory.getInstance().getAllTests().add(anemia);
    }

    public Object clone() {

        return new Thyroid(getInfo(), getTestConfirm());
    }

    @Override
    public String makingPrivate(Test test) {
        Anemia anemia=(Anemia) clone();
        anemia.setBloodPressure(10);
        anemia.setBMP(12);
        return anemia.toString();
    }
}

//***********************************************************
class Thyroid extends Test implements Cloneable,MakingPrivate {
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
    @Override
    public void runOperation(Test test) {
        Thyroid thyroid = (Thyroid) test;
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int rand = random.nextInt(3, 7);
        int i, fact = 1;
        for (i = 1; i <= rand; i++) {
            fact = fact * i;
        }
        thyroid.setCBC(fact * 10);
        thyroid.setBMP(Math.abs(thyroid.getCBC() - rand));
        thyroid.setBloodPressure(thyroid.getCBC() / thyroid.getBMP());
        heartPressure = rand;
    }

    @Override
    public void sendResult(User user, Test thyroid) {
        user.getTests().add((BloodType) clone());
        Laboratory.getInstance().getAllTests().add(thyroid);
    }

    public Object clone() {

        return new Thyroid(heartPressure, getInfo(), getTestConfirm());
    }
    public void addRequest(User user,String id) {
        Request request=new Request(user,id);
        Laboratory.getInstance().getRequests().add(request);
    }
    @Override
    public String makingPrivate(Test test) {
        Thyroid thyroid=(Thyroid) clone();
        thyroid.heartPressure=10;
        thyroid.setCBC(3);
        Laboratory.getInstance().getAllTests().add(thyroid);
        return thyroid.toString();
    }
    public String toString(){
        return "id"+getId()+"info"+getInfo()+"bloodPressure"+getBloodPressure()+"cbc"+getCBC()+"bmp"+getBMP()+"heartPressure"+
                heartPressure;
    }
}

//*****************************************************************
class BloodType extends Test implements Cloneable, Checking, WrongAnswer {

    private boolean bloodConfirm;
    static int numberOfChecking = 0;

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
    public void runOperation(Test test) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        BloodType bloodType = (BloodType) test;
        bloodType.setCBC(random.nextInt(20, 195));
        bloodType.setBMP(random.nextInt(35, 374));
        bloodType.setBloodPressure(random.nextInt(4, 124));
    }

    @Override
    public void sendResult(User user, Test bloodType) {
        user.getTests().add((BloodType) clone());
        Laboratory.getInstance().getAllTests().add(bloodType);
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

    @Override
    public BloodType clone() {
        BloodType bloodType = new BloodType(bloodConfirm, getTestConfirm(), getInfo());

        return bloodType;
    }

    @Override
    public void checking(Test test) {
        numberOfChecking++;
    }

    @Override
    public void wrongAnswer(Test test) {
        if (numberOfChecking < 3) {
            runOperation(test);
        }
    }
    public String toString(){
        return "id"+getId()+"info"+getInfo()+"bloodPressure"+getBloodPressure()+"cbc"+getCBC()+"bmp"+getBMP()+"confirmation"
                +bloodConfirm;
    }
}

//--------------------------------------------------
enum BloodTypeRange {
    CBC(20, 160),
    BLOOD_PRESSURE(4, 100),
    BMP(35, 210);

    private int range_one;
    private int range_two;

    BloodTypeRange(int range_one, int range_two) {
        this.range_one = range_one;
        this.range_two = range_two;
    }

    public int getRange_one() {
        return range_one;
    }

    public int getRange_two() {
        return range_two;
    }

}


//--------------------------------------------------
class AIDS extends Test implements Cloneable, Checking, WrongAnswer, MakingPrivate {
    public boolean getAidsConform() {
        return aidsConfirm;
    }

    private boolean aidsConfirm;

    private String illness;
    private static int numberOfChecking = 0;

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
        this.aidsConfirm = aidsConfirm;
    }

    ThreadLocalRandom random = ThreadLocalRandom.current();
    int rand = random.nextInt(3, 7);
    String[] ill = new String[8];

    public void runOperation(Test aid) {
        AIDS aids = (AIDS) aid;
        ThreadLocalRandom random = ThreadLocalRandom.current();
        aids.setCBC(random.nextInt(20, 220));
        aids.setBMP(random.nextInt(35, 416));
        aids.setBloodPressure(random.nextInt(4, 184));
        ill[0] = "HEALTHY";
        ill[1] = "CORONARY";
        ill[2] = "STROKE";
        ill[3] = "PERIPHERALARTERIAL";
        ill[4] = "AORTIC";
        ill[5] = "NOT";
        ill[6] = "ERROR";
        ill[7] = "FINDFAILD";
        rand = random.nextInt(0, 7);
        aids.illness = ill[rand];

    }

    @Override
    public void sendResult(User user, Test aids) {
        user.getTests().add((AIDS) clone());
        Laboratory.getInstance().getAllTests().add(aids);
    }

    public Object clone() {
        AIDS userAids = new AIDS(getInfo(), aidsConfirm, getTestConfirm(), illness);
        return userAids;
    }

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


    public String toString(){
        return "id"+getId()+"info"+getInfo()+"bloodPressure"+getBloodPressure()+"cbc"+getCBC()+"bmp"+getBMP()+"confirmation"
                +aidsConfirm+"illness"+illness;
    }

    //public void checking(AIDS aids){
//
//}
    @Override
    public void checking(Test test) {
        wrongAnswer(test);
    }

    @Override
    public void wrongAnswer(Test test) {
        if (numberOfChecking < 3) {
            runOperation(test);
        }
    }

    @Override
    public String makingPrivate(Test test) {
        AIDS userAids = (AIDS) clone();
        userAids.setBloodPressure(11);
        userAids.setIllness("heartAtack");
        Laboratory.getInstance().getAllTests().add(userAids);
        return userAids.toString();
    }

    public void addRequest(User user,String id) {
        Request request=new Request(user,id);
        Laboratory.getInstance().getRequests().add(request);
    }
}
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
class Request{
    private User user;

    public Request(User user,String id) {
        this.user = user;
        this.id=id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
}
//---------------------------------------------------------------
enum AIDSRange {
    CBC(20, 180),
    BLOOD_PRESSURE(4, 150),
    BMP(35, 340);

    private enum HeartDisease {
        HEALTHY,
        CORONARY,
        STROKE,
        PERIPHERAL_ARTERIAL,
        AORTIC;
    }

    private int range_one;
    private int range_two;

    AIDSRange(int range_one, int range_two) {
        this.range_one = range_one;
        this.range_two = range_two;
    }

    public int getRange_one() {
        return range_one;
    }

    public int getRange_two() {
        return range_two;
    }
}

//+++++++++++++++++++++++++++++++++++++++++++++
interface Checking {
    public void checking(Test test); // interface method
}

interface WrongAnswer {
    public void wrongAnswer(Test test); // interface method
}

interface MakingPrivate {
    public String makingPrivate(Test test);
}

interface GettingRequest {
    public String gettingRequest(User user,String id);
}
