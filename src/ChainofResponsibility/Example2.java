package ChainofResponsibility;

/**
 * @author : oranges
 * @title : ChainofResponsibility
 * @date : 2019/10/10 0:03
 * @description :
 */
public class Example2 {
    public static void main(String[] args) {

        Banzhang bz = new Banzhang("赵班长");
        Paizhang pz = new Paizhang("钱排长");
        Yingzhang yz = new Yingzhang("孙营长");

        bz.setNextOfficer(pz);
        pz.setNextOfficer(yz);

        //赵班长下达作战命令
        Mission mission1 = new Mission(5);
        bz.command(mission1);

        //钱排长下达作战命令
        Mission mission2 = new Mission(15);
        bz.command(mission2);

        //孙营长下达作战命令
        Mission mission3 = new Mission(100);
        bz.command(mission3);

        //需要开会讨论再下达作战命令
        Mission mission4 = new Mission(1000);
        bz.command(mission4);
    }
}

//请求类
//任务书
class Mission {

    private int enemyNum;

    public Mission(int enemyNum) {
        this.enemyNum = enemyNum;
    }

    public int getEnemyNum() {
        return this.enemyNum;
    }
}

//抽象Officer类
abstract class Officer {

    protected Officer nextOfficer;
    protected String name;

    public Officer(String name) {
        this.name = name;
    }

    public void setNextOfficer(Officer nextOfficer) {
        this.nextOfficer = nextOfficer;
    }

    public abstract void command(Mission mission);
}

//具体Officer类
//班长
class Banzhang extends Officer {

    public Banzhang(String name) {
        super(name);
    }

    @Override
    public void command(Mission mission) {
        if (mission.getEnemyNum() < 10) {
            System.out.println(this.name + "下达作战命令");
        } else {
            this.nextOfficer.command(mission);
        }
    }
}

//具体Officer类
//排长
class Paizhang extends Officer {

    public Paizhang(String name) {
        super(name);
    }

    @Override
    public void command(Mission mission) {
        if (mission.getEnemyNum() < 50) {
            System.out.println(this.name + "下达作战命令");
        } else {
            this.nextOfficer.command(mission);
        }
    }
}

//具体Officer类
//营长
class Yingzhang extends Officer {

    public Yingzhang(String name) {
        super(name);
    }

    @Override
    public void command(Mission mission) {
        if (mission.getEnemyNum() < 200) {
            System.out.println(this.name + "下达作战命令");
        } else {
            //this.nextOfficer.command(mission);
            System.out.println("需要开会讨论再下达作战命令");
        }
    }
}


