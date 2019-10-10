package ChainofResponsibility;

/**
 * @author : oranges
 * @title : ChainofResponsibility
 * @date : 2019/10/10 23:34
 * @description :
 */
public class Example4 {

    public static void main(String[] args) {
        Request request = new Request(15);

        Zhuren zr = new Zhuren("赵主任");
        Jingli jl = new Jingli("钱经理");
        Zongjingli zjl = new Zongjingli("孙总经理");

        zr.setNextManager(jl);
        jl.setNextManager(zjl);

        zr.execute(request);
        //孙总经理可以批准该假条
    }
}

//具体假条类
class Request {

    private int days;

    public Request(int days) {
        this.days = days;
    }

    public int getDays() {
        return this.days;
    }
}

//抽象Manager类
abstract class Manager {

    String name;
    Manager nextManager;

    public Manager(String name) {
        this.name = name;
    }

    public void setNextManager(Manager manager) {
        this.nextManager = manager;
    }

    public abstract void execute(Request request);
}

//具体Manager类
//主任
class Zhuren extends Manager {

    public Zhuren(String name) {
        super(name);
    }

    @Override
    public void execute(Request request) {
        if (request.getDays() < 3) {
            System.out.println(this.name + "可以批准该假条");
        } else {
            this.nextManager.execute(request);
        }
    }
}

//具体Manager类
//经理
class Jingli extends Manager {

    public Jingli(String name) {
        super(name);
    }

    @Override
    public void execute(Request request) {
        if (request.getDays() < 10) {
            System.out.println(this.name + "可以批准该假条");
        } else {
            this.nextManager.execute(request);
        }
    }
}

//具体Manager类
//总经理
class Zongjingli extends Manager {

    public Zongjingli(String name) {
        super(name);
    }

    @Override
    public void execute(Request request) {
        if (request.getDays() < 30) {
            System.out.println(this.name + "可以批准该假条");
        } else {
            System.out.println("该假条不予批准");
        }
    }
}