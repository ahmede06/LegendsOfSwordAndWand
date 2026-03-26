package factory;

import model.Hero;

public class HeroFactory {

    public static Hero createHero(String type){

        if(type.equalsIgnoreCase("warrior")){
            return new Hero("Warrior",1,120,20,10);
        }

        if(type.equalsIgnoreCase("mage")){
            return new Hero("Mage",1,80,30,5);
        }

        return new Hero("Adventurer",1,100,15,8);
    }
}